/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.drivit.androidsdk_sample;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.drivit.core.trips.TripType;

import java.text.SimpleDateFormat;

/**
 * teste2
 * Provide views to RecyclerView with data from mDataSet.
 */
public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ViewHolder> {
    private static final String TAG = "TripAdapter";
    public static final String TRIP_GUID = "trip_guid";

    private TripType[] mDataSet;

    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView_timeOrigin;
        private final TextView textView_timeDestination;


        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.

            textView_timeOrigin = (TextView) v.findViewById(R.id.textView_timeOrigin);
            textView_timeDestination = (TextView) v.findViewById(R.id.textView_timeDestination);
        }

        public TextView getTextView_timeOrigin() {
            return textView_timeOrigin;
        }

        public TextView getTextView_timeDestination() {
            return textView_timeDestination;
        }
    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
     */
    public TripAdapter(TripType[] dataSet) {
        mDataSet = dataSet;
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);


        return new ViewHolder(v);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)1

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        TripType trip = mDataSet[position];
        viewHolder.getTextView_timeOrigin().setText("Beggining: " + SimpleDateFormat.getDateTimeInstance().format(mDataSet[position].getTimeOrigin()) + ", " + trip.getAddressOrigin());

        String consumptionDataString = "Consumption data not yet available";
        if (trip.getConsumptionData() != null) {
            consumptionDataString = "Base consumption: " + trip.getConsumptionData().getFuelConsumption() + "l/100km"
                    + "\nBase cost: " + trip.getConsumptionData().getTotalCost();
        }

        viewHolder.getTextView_timeDestination().setText("End: " + SimpleDateFormat.getDateTimeInstance().format(mDataSet[position].getTimeDestination()) + ", " + trip.getAddressDestination()
                + "\nDistance: " + trip.getDistance()
                + "\nConsumption: " + trip.getConsumption()
                + "\nMoney: " + trip.getMoneySpent()
                + "\nScore: " + trip.getRiskScore()
                + "\nRejection state: " + trip.getRejectedReason()
                + "\nCarGuid: " + trip.getAssociatedVehicleGuid()
                + "\n" + consumptionDataString);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trip.hasAnyLocationPoint()) {
                    Intent intent = new Intent(v.getContext(), MapsActivity.class);
                    intent.putExtra(TRIP_GUID, trip.getGuid());
                    v.getContext().startActivity(intent);
                } else {
                    Toast.makeText(v.getContext(), "Trip has no locations", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}
