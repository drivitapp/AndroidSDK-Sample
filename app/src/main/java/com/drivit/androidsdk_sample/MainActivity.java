package com.drivit.androidsdk_sample;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.drivit.core.DrivitCloud;
import com.drivit.core.DrivitLoginSignupOperation;
import com.drivit.core.DrivitUser;
import com.drivit.core.trips.DrivitStatusActivity;
import com.drivit.core.trips.DrivitStatusManager;
import com.drivit.core.trips.DrivitStatusType;
import com.drivit.core.utils.DrivitUtils;
import com.drivit.core.utils.LoginListener;

import java.util.ArrayList;

public class MainActivity extends DrivitStatusActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        if (savedInstanceState == null) refreshContent();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.hide();
    }

    private void refreshContent() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        transaction.replace(R.id.sample_content_fragment, fragment);
        transaction.commit();

        TextView version = findViewById(R.id.textView_version);
        if (DrivitUser.isUserLogged(this)) {
            DrivitStatusManager drivitStatus = DrivitStatusManager.getSingleton(this);
            drivitStatus.addListener(status -> setTextView(version, drivitStatus.getStatusString(status)));
            drivitStatus.getCurrentStatus(status -> setTextView(version, drivitStatus.getStatusString(status)));

            version.setOnClickListener(v -> {
                MaterialDialog.Builder dialogBuilder = new MaterialDialog.Builder(this);
                dialogBuilder.title("Settings");
                ArrayList<DrivitStatusType> list = drivitStatus.getLastStatusData();
                dialogBuilder.adapter(new DrivitStatusAdapter(list), null);
                dialogBuilder.build().show();
            });
        } else {
            version.setText("Not logged");
        }
    }

    private void setTextView(TextView version, String drivitStatus) {
        version.setText("Drivit SDK version: " + DrivitUtils.getSdkVersion(this) + ", account: " + DrivitUser.getUser(this).getEmail()
                + "\nDrivit status: " + drivitStatus + ", click to see more");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {

            case R.id.action_login:
                login();
                break;

            case R.id.action_logout:
                new DrivitLoginSignupOperation(this).logout();
                refreshContent();
                break;

            case R.id.action_refresh_trip_list:
                refreshContent();
                break;

            case R.id.action_feedback:
                showFeedbackDialog();
                break;
            case R.id.crash:
                throw new RuntimeException();

            case R.id.action_sendLogs:
                sendLogs();
                break;

            case R.id.action_simulate:
                DrivitUtils.simulateTrip(MainActivity.this, new DrivitUtils.OnCompletedListener() {
                    @Override
                    public void onCompleted(boolean success, int errorCause) {
                        if (errorCause != DrivitUtils.TRIP_SIMULATION_SUCCESS) {
                            Toast.makeText(MainActivity.this, "Error: " + errorCause, Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                break;
        }


        return super.onOptionsItemSelected(item);
    }

    private void showFeedbackDialog() {
        MaterialDialog.Builder dialogBuilder = new MaterialDialog.Builder(this);
        dialogBuilder.title("Feedback");
        dialogBuilder.customView(R.layout.dialog_feedback, false);
        dialogBuilder.positiveText("Enviar");
        dialogBuilder.negativeText("Cancelar");

        dialogBuilder.onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                EditText edit = (EditText) dialog.findViewById(R.id.edit_feedback);

                DrivitCloud.sendUserFeedback(MainActivity.this, edit.getText().toString(), true, null, new DrivitCloud.OperationListener() {
                    @Override
                    public void onCompleted(boolean success, int errorCause) {
                        if (success) {
                            Toast.makeText(MainActivity.this, "Feedback sent", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        dialogBuilder.build().show();
    }

    private void sendLogs() {
        DrivitCloud drivitCloud = DrivitCloud.getInstance(MainActivity.this);
        if (drivitCloud != null) {
            drivitCloud.forceSyncLogs(new DrivitCloud.OperationListenerGeneric<Double>() {
                @Override
                public void onCompleted() {
                    Toast.makeText(MainActivity.this, "Logs sent successfully", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onError(int error) {
                    Toast.makeText(MainActivity.this, "Error while sending logs, cause: " + error, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onProgress(Double value) {

                }
            });
        }
    }

    private void login() {

        if (DrivitUser.isUserLogged(getApplication())) {
            Toast.makeText(MainActivity.this, "Already logged in", Toast.LENGTH_SHORT).show();
            return;
        }

        MaterialDialog.Builder dialogBuilder = new MaterialDialog.Builder(this);
        dialogBuilder.title("Login");
        dialogBuilder.customView(R.layout.dialog_login, false);
        dialogBuilder.positiveText("Login");
        dialogBuilder.autoDismiss(false);

        dialogBuilder.onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                EditText userName = (EditText) dialog.findViewById(R.id.edit_email);
                EditText password = (EditText) dialog.findViewById(R.id.edit_password);

                //Authenticate with Drivit
                DrivitLoginSignupOperation login = new DrivitLoginSignupOperation(MainActivity.this);
                login.doLogin(MainActivity.this, userName.getText().toString(), password.getText().toString(), new LoginListener() {
                    @Override
                    public void onCompleted(boolean codeOk, int cause, DrivitUser appUser) {
                        Toast.makeText(MainActivity.this, "Login completed, success? " + codeOk + ", cause: " + cause, Toast.LENGTH_SHORT).show();
                        if (codeOk) refreshContent();
                    }
                });

                //Dismiss the login dialog
                dialog.dismiss();
            }
        });

        dialogBuilder.build().show();

    }
}
