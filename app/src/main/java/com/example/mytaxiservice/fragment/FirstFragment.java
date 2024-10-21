package com.example.mytaxiservice.fragment;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import com.example.mytaxiservice.tripAddress.Trip_Category;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.mytaxiservice.R;
import com.example.mytaxiservice.utils.UtilCurrentLocation;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_COARSE_LOCATION;

public class FirstFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private UtilCurrentLocation utilCurrentLocation;
    private TextView pickLocation;
    private Button dropLocation;
    public static String pickup;

    public  static String current;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 100;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        pickLocation = view.findViewById(R.id.tvPickUpLocation);
        dropLocation = view.findViewById(R.id.btnDropLocation);

        dropLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndProceed();
            }

            private void validateAndProceed() {
                if (TextUtils.isEmpty(pickLocation.getText().toString().trim())) {
                    Toast.makeText(getActivity(), "Enter your pickup Location", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getActivity(), Trip_Category.class);
                    startActivity(intent);
                }
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        checkLocationPermissionAndFetch();
    }

    private void checkLocationPermissionAndFetch() {
        if (ContextCompat.checkSelfPermission(getActivity(), ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Request the permission
            ActivityCompat.requestPermissions(getActivity(), new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            // Permission already granted
            utilCurrentLocation = new UtilCurrentLocation(getActivity());
            fetchCurrentLocation();
        }
    }

    private void fetchCurrentLocation() {
        utilCurrentLocation.getCurrentLocation(new UtilCurrentLocation.LocationCallback() {
            @Override
            public void onLocationFound(Location location) {
                LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());

                // Add a marker at the current location
                mMap.addMarker(new MarkerOptions()
                        .position(currentLatLng)
                        .title("Current Location")
                        .snippet("You are here"));

                // Move the camera to the current location and zoom in
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15));

                // Optionally, fetch the address from the current location
                getAddressFromLocation(location);
            }
        });
    }

    private void getAddressFromLocation(Location location) {
        if (getActivity() == null) {
            return;
        }

        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                String addressString = address.getAddressLine(0);
                current=addressString;
                pickLocation.setText(addressString);
                pickup = addressString;
            } else {
                pickLocation.setText("Unable to find address");
            }
        } catch (IOException e) {
            e.printStackTrace();
            pickLocation.setText("Error fetching address");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                utilCurrentLocation = new UtilCurrentLocation(getActivity());
                fetchCurrentLocation();
            } else {
                Toast.makeText(getActivity(), "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void showExitDialog() {
        if (getActivity() != null) {
            new AlertDialog.Builder(getActivity())
                    .setTitle("Exit")
                    .setMessage("Are you sure you want to exit?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getActivity().finishAffinity();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }
    }
}
