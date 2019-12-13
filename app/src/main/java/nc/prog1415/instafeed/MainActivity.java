package nc.prog1415.instafeed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import nc.sharedInstafeedClasses.Business;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static nc.prog1415.instafeed.SplashActivity.connectionTask;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static ArrayList<Business> businessArray = new ArrayList<Business>();

//    public static ConnectionTask connectionTask;
    public static LocationTask locationTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "MainActivity onCreate: started");

        SharedPreferences sharedPrefs = this.getSharedPreferences("sharedPrefs",Context.MODE_PRIVATE);

        int setMaxResults = sharedPrefs.getInt("maxResults", 5);

        locationTask = new LocationTask(this, setMaxResults);

        initBusinessNames();
        initRecyclerView();

        //// TEMP BEGINS ////

        btnTestServer = (Button) findViewById(R.id.btnTestServer);

        btnTestServer.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                testServer();
            }
        });

        //// TEMP END /////
    }

    //// TEMP BEGINS (Again) ////

    private void testServer(){

//        LocationTask lt = new LocationTask(this);
//        Location myLocation = lt.location;
//        Toast.makeText(this, myLocation.toString(),Toast.LENGTH_SHORT);

//        connectionTask.sendRating(new Rating(new Business("Test business", "business address"),
//                (float)3,"Successful Rating","Awesome sauce!"));

        Toast.makeText(this, locationTask.status, Toast.LENGTH_LONG).show();


    }

    /// TEMP ENDS ////

    public static Button btnTestServer;

    private void initBusinessNames(){
//        businessArray.add(new Business("Tim Hortons","123 Street Ave. Welland, ON"));
////        businessArray.add(new Business("Wendy's","234 Street Ave. Welland, ON"));
////        businessArray.add(new Business("McDonalds","456 Street Ave. Welland, ON"));
////        businessArray.add(new Business("Burger King","789 Street Ave. Welland, ON"));

        businessArray = locationTask.closeBusinesses;

    }

    public void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.mainRecycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, businessArray);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    ///// MENU //////

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getTitle().toString()=="My Ratings"){

            connectionTask.sendContentRequest();

            Intent i = new Intent(this, MyRatingsActivity.class);
            this.startActivityForResult(i,1);
        }
        else if(item.getTitle().toString()=="Preferences"){
            Intent i = new Intent(this, PreferencesActivity.class);
            this.startActivityForResult(i,1);
        }
        else if(item.getTitle().toString()=="About"){
            Intent i = new Intent(this, AboutActivity.class);
            this.startActivityForResult(i,1);
        }
        else if(item.getTitle().toString()=="Home"){
            Intent i = new Intent(this, MainActivity.class);
            this.startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //build android menu
        menu.add("Home");
        menu.add("My Ratings");
        menu.add("Preferences");
        menu.add("About");
        return super.onCreateOptionsMenu(menu);
    }
}
