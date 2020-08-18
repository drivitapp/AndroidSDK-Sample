package com.drivit.androidsdk_sample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.drivit.core.DrivitCloud;
import com.drivit.core.DrivitUser;
import com.drivit.core.trips.LocationInfo;
import com.drivit.core.trips.RoadSnapResult;
import com.drivit.core.trips.TripType;
import com.drivit.core.utils.Constants;
import com.google.android.libraries.maps.CameraUpdateFactory;
import com.google.android.libraries.maps.GoogleMap;
import com.google.android.libraries.maps.OnMapReadyCallback;
import com.google.android.libraries.maps.SupportMapFragment;
import com.google.android.libraries.maps.model.LatLng;
import com.google.android.libraries.maps.model.LatLngBounds;
import com.google.android.libraries.maps.model.MarkerOptions;

/*13*/
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "MapsActivity";

    private GoogleMap mMap;
    private TripType mTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        String tripGuid = getIntent().getExtras().getString(TripAdapter.TRIP_GUID);
        mTrip = DrivitUser.getUser(this).getTrip(this, tripGuid);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (mTrip.areLocationsAndEventsLocallyAvailable()) {
            new SnapTask().execute();
        } else {
            mTrip.downloadLocationsAndEvents(new DrivitCloud.OperationListener() {
                @Override
                public void onCompleted(boolean success, int errorCause) {
                    if (success) {
                        new SnapTask().execute();
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MapsActivity.this, "Error downloading locations", Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                }
            });
        }
    }

    class SnapTask extends AsyncTask<Void, Void, Integer> {

        LatLngBounds.Builder bounds = new LatLngBounds.Builder();
        LocationInfo[] locs = mTrip.getLocations();
        LocationInfo origin = mTrip.getOrigin();
        LocationInfo destination = mTrip.getDestination();

        @Override
        protected void onPreExecute() {
            // Pre Code
        }

        @Override
        protected Integer doInBackground(Void... unused) {
            return mTrip.getSnappedLocations().result;
        }

        @Override
        protected void onPostExecute(Integer result) {

            if (result == Constants.NO_ERROR) {
                if (locs != null) {
                    RoadSnapResult snapResult = mTrip.getSnappedLocations();
                    for (LocationInfo loc : snapResult.snappedArray) {
                        includeLocation(new LatLng(loc.la,loc.lo), bounds);
                        //includeLocation(loc,bounds);
                    }
                }

                if (origin != null) includeLocation(new LatLng(origin.la,origin.lo), bounds);
                if (destination != null) includeLocation(new LatLng(destination.la,destination.lo), bounds);

                //We have to confirm the view is laid out
                View container = findViewById(R.id.mapContainer);
                if (container.getHeight() != 0) {
                    mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 50));
                } else {
                    ViewTreeObserver obs = container.getViewTreeObserver();
                    obs.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                        @Override
                        public boolean onPreDraw() {
                            container.getViewTreeObserver().removeOnPreDrawListener(this);
                            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 50));
                            return false;
                        }
                    });
                }
            } else {
                Toast.makeText(MapsActivity.this, "Error snapping locations", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void includeLocation(LatLng loc, LatLngBounds.Builder bounds) {
        bounds.include(loc);
        mMap.addMarker(new MarkerOptions().position(loc));
    }
}
