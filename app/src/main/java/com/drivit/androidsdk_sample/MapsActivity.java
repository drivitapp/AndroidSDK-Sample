package com.drivit.androidsdk_sample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewTreeObserver;

import com.drivit.core.DrivitUser;
import com.drivit.core.trips.LocationInfo;
import com.drivit.core.trips.SnappedPoint;
import com.drivit.core.trips.TripType;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

/*13*/
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

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

        LatLngBounds.Builder bounds = new LatLngBounds.Builder();
        LocationInfo[] locs = mTrip.getLocations();
        LocationInfo origin = mTrip.getOrigin();
        LocationInfo destination = mTrip.getDestination();

        new AsyncTask<Void, Void, Void>() {
            protected void onPreExecute() {
                // Pre Code
            }

            protected Void doInBackground(Void... unused) {
                mTrip.calculateSnappedLocations();
                return null;
            }

            protected void onPostExecute(Void unused) {
                if (locs != null) {
                    for (SnappedPoint loc : mTrip.getSnappedPoints()) {
                        includeLocation(loc.coordinates, bounds);
                        //includeLocation(loc,bounds);
                    }
                }

                if (origin != null) includeLocation(origin.toLatLng(), bounds);
                if (destination != null) includeLocation(destination.toLatLng(), bounds);

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
            }
        }.execute();


    }

    private void includeLocation(LatLng loc, LatLngBounds.Builder bounds) {
        bounds.include(loc);
        mMap.addMarker(new MarkerOptions().position(loc));
    }
}
