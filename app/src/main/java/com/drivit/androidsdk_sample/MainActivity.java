package com.drivit.androidsdk_sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.drivit.core.DrivitLoginSignupOperation;
import com.drivit.core.DrivitUser;
import com.drivit.core.utils.LoginListener;

/*The main activity of the application*/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void login() {
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

                DrivitLoginSignupOperation login = new DrivitLoginSignupOperation();
                login.doLogin(MainActivity.this, userName.getText().toString(), password.getText().toString(), null, new LoginListener() {
                    @Override
                    public void onCompleted(boolean codeOk, int cause, DrivitUser appUser) {
                        Toast.makeText(MainActivity.this, "Login completed: " + codeOk + ", cause: " + cause, Toast.LENGTH_SHORT).show();
                        if (codeOk){
                            dialog.dismiss();
                        }
                    }
                });
            }
        });

        dialogBuilder.build().show();
    }
}
