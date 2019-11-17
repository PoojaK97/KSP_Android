package com.project.coders.ksp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class TabPresent extends Fragment{

    private HashMap<String, Marker> mMarkers = new HashMap<>();

    public TabPresent() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.activity_tab_present, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapPresent);  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                mMap.clear(); //clear old markers

                CameraPosition googlePlex = CameraPosition.builder()
                        .target(new LatLng(37.4219999,-122.0862462))
                        .zoom(10)
                        .bearing(0)
                        .tilt(45)
                        .build();

                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null);

//                mMap.addMarker(new MarkerOptions()
//                        .position(new LatLng(12.922612, 77.504118))
//                        .title("RVCE INDOOR STADIUM")
//                        .icon(BitmapDescriptorFactory
//                                .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

                MarkBeatPoints(mMap);
                subscribeToUpdates(mMap);
            }
        });

        return rootView;
    }

    private void subscribeToUpdates(final GoogleMap mMap) {
        final String uid = "lElVHGwqGFPdweG1xol9ByPPGVN2";
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Current_location"+'/'+uid);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                double lat = dataSnapshot.child("latitude").getValue(double.class);
                double lng = dataSnapshot.child("longitude").getValue(double.class);
                LatLng location = new LatLng(lat, lng);

                if (!mMarkers.containsKey(uid)) {
                    mMarkers.put(uid, mMap.addMarker(new MarkerOptions().title(uid).position(location)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))));
                } else {
                    mMarkers.get(uid).setPosition(location);
                }
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                for (Marker marker : mMarkers.values()) {
                    builder.include(marker.getPosition());
                }
                changeBeatPointsStatus(location, mMap);
                mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 300));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }});
    }

            private void changeBeatPointsStatus(final LatLng cur, final GoogleMap mMap) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Beat");
                ref.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                        setMarkerBP2(dataSnapshot, cur, mMap);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                        setMarkerBP2(dataSnapshot, cur, mMap);
                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Log.d("TAG", "Failed to read value.", error.toException());
                    }
                });
            }

            private void setMarkerBP2(DataSnapshot dataSnapshot, LatLng cur, GoogleMap mMap) {
                // When a location update is received, put or update
                // its value in mMarkers, which contains all the markers
                // for locations received, so that we can build the
                // boundaries required to show them all on the map at once
                String key = dataSnapshot.getKey();
                HashMap<String, Object> value = (HashMap<String, Object>) dataSnapshot.getValue();
                double lat = Double.parseDouble(value.get("latitude").toString());
                double lng = Double.parseDouble(value.get("longitude").toString());
                LatLng location = new LatLng(lat, lng);

                //if (CalculationByDistance(location, cur) <= 50) {

                Circle mCircle;
                mCircle = mMap.addCircle(new CircleOptions()
                        .center(location)
                        .radius(500.0)
                        .strokeWidth(5f)
                        .strokeColor(Color.argb(50, 255, 0, 0))
                        .fillColor(Color.argb(10, 100, 10, 10)));

                //mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 300));
                //}

                float[] distance = new float[2];

                Location.distanceBetween(cur.latitude, cur.longitude,
                        mCircle.getCenter().latitude, mCircle.getCenter().longitude, distance);

                if (distance[0] < mCircle.getRadius()) {
                    mCircle.remove();
                    mCircle = mMap.addCircle(new CircleOptions()
                            .center(location)
                            .radius(500.0)
                            .strokeWidth(5f)
                            .strokeColor(Color.argb(50, 0, 255, 0))
                            .fillColor(Color.argb(10, 10, 100, 10)));
                    //startService(new Intent(this, BeatPointService.class));


                }

            }


            private void setMarker(DataSnapshot dataSnapshot, GoogleMap mMap) {
                // When a location update is received, put or update
                // its value in mMarkers, which contains all the markers
                // for locations received, so that we can build the
                // boundaries required to show them all on the map at once
                String key = dataSnapshot.getKey();
                Log.i("KEY       ", key);
                Double lat = Double.parseDouble(dataSnapshot.child("latitude").getValue().toString());
                Double lng = Double.parseDouble(dataSnapshot.child("longitude").getValue().toString());
                LatLng location = new LatLng(lat, lng);
                //curloc.set(0,location);

                //changeBeatPointsStatus(location);
                mMarkers.put(key, mMap.addMarker(new MarkerOptions().title(key).position(location)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))));
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                for (Marker marker : mMarkers.values()) {
                    builder.include(marker.getPosition());
                }
                //changeBeatPointsStatus(location);
                mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 300));
            }

            private void MarkBeatPoints(final GoogleMap mMap) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Beat");
                ref.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                        setMarkerBP(dataSnapshot,mMap);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                        setMarkerBP(dataSnapshot, mMap);
                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Log.d("TAG", "Failed to read value.", error.toException());
                    }
                });
            }

            private void setMarkerBP(DataSnapshot dataSnapshot, GoogleMap mMap) {
                String key = dataSnapshot.getKey();
                HashMap<String, Object> value = (HashMap<String, Object>) dataSnapshot.getValue();
                double lat = Double.parseDouble(value.get("latitude").toString());
                double lng = Double.parseDouble(value.get("longitude").toString());
                LatLng location = new LatLng(lat, lng);
                if (!mMarkers.containsKey(key)) {
                    mMarkers.put(key, mMap.addMarker(new MarkerOptions().title(key).position(location)));
                } else {
                    mMarkers.get(key).setPosition(location);
                }
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                for (Marker marker : mMarkers.values()) {
                    builder.include(marker.getPosition());
                }
                mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 300));
            }
        }