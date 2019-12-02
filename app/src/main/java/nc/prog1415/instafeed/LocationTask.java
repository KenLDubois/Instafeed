package nc.prog1415.instafeed;

import android.content.Context;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.util.SparseArray;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import static android.content.Context.MODE_PRIVATE;
import static nc.prog1415.instafeed.PreferencesActivity.shared_prefs;

public class LocationTask implements LocationListener {

    private LocationManager manager = null;
    public Location location = null;
    private AppCompatActivity activity;

    int maxResults;

    public String status = "";

    public ArrayList<Business> closeBusinesses = new ArrayList<Business>();

    //a collection to store the status of the service being used
    private SparseArray<String> providerStatus = new SparseArray<String>() {
        //initialize collection with status values using keys provided by the location provider
        {
            put(LocationProvider.AVAILABLE, "Available");
            put(LocationProvider.OUT_OF_SERVICE, "Out of Service");
            put(LocationProvider.TEMPORARILY_UNAVAILABLE, "Temporarily Unavailable");
            put(-1, "Not Reported");
        }
    };

    public LocationTask(AppCompatActivity _activity){
        activity = _activity;
        manager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);


        maxResults = 5;

        //specify the applications criteria (requirements)
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.NO_REQUIREMENT);
        criteria.setPowerRequirement(Criteria.NO_REQUIREMENT);

        //display the best service available based on the required criteria
        String best = manager.getBestProvider(criteria, true);

        //register the manager to listen for location updates
        try {
            manager.requestLocationUpdates(best, 60000l, 0f, this);
        }catch(SecurityException ex){

        }

    }

    @Override
    public void onLocationChanged(Location location) {
        //obtain location coordinates
        String info = String.format("Current loc = (%f, %f) @ (%f meters up)", location.getLatitude(), location.getLongitude(), location.getAltitude());
        //determine distance between the current location and the last location
        if (this.location != null) {
            float distance = location.distanceTo(this.location);
            info += String.format("\n Distance from last = %f meters", distance);
        }
        //store the current location
        this.location = location;

        //check if a Geocoder service is running to obtain the location details

        if(Geocoder.isPresent()) {

            try {
                //construct a GeoCoder
                Geocoder  coder = new Geocoder(activity);
                //obtain the addresses of the current location

                Iterator<Address> addresses = coder.getFromLocation(location.getLatitude(), location.getLongitude(), maxResults).iterator();
                //process the address details
                if (addresses != null) {
                    while (addresses.hasNext()) {
                        Address namedLoc = addresses.next();
                        String city = namedLoc.getLocality();
                        String featureName = namedLoc.getFeatureName();
                        String country = namedLoc.getCountryName();
                        String prov = namedLoc.getAdminArea();
                        String road = namedLoc.getThoroughfare();
                        info += String.format("\n[%s][%s][%s][%s]", city, featureName, road, country);
                        int addIdx = namedLoc.getMaxAddressLineIndex();

                        for (int idx = 0; idx <= addIdx; idx++){
                            String addLine = namedLoc.getAddressLine(idx);
                            info += String.format("\nLine %d: %s", idx, addLine);
                        }

                        String address = "";

                        for(int i = 0; i < namedLoc.getMaxAddressLineIndex(); i++){
                            address += namedLoc.getAddressLine(i) + "\n";
                        }

                        Business business = new Business(featureName, namedLoc.getAddressLine(0));

//                        Business business = new Business(featureName,featureName + " " + road + ", " + city + "\n" + prov + ", " + country.toUpperCase());



                        closeBusinesses.add(business);

                    }
                }
            } catch (IOException e) {
                Toast.makeText(activity, "Failed to get address", Toast.LENGTH_SHORT).show();
            }

        }
        else {
            Toast.makeText(activity, "No addressing service available", Toast.LENGTH_SHORT).show();
        }

        //display location information to the user
        status = info;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        int satellites = extras.getInt("satellites", -1);
        this.status = String.format("Provider: %s, status: %s, satellites: %d", provider, providerStatus.get(status), satellites);
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
