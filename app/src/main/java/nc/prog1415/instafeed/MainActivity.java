package nc.prog1415.instafeed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import nc.sharedInstafeedClasses.Business;
import nc.sharedInstafeedClasses.ContentRequest;

import static nc.prog1415.instafeed.SplashActivity.connectionTask;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static ArrayList<Business> businessArray = new ArrayList<Business>();

    public static LocationTask locationTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "MainActivity onCreate: started");

        SharedPreferences sharedPrefs = this.getSharedPreferences(getString(R.string.shared_prefs),Context.MODE_PRIVATE);
        int setMaxResults = sharedPrefs.getInt(getString(R.string.max_results), 5);

        locationTask = new LocationTask(this, setMaxResults);
        businessArray = locationTask.closeBusinesses;
        initRecyclerView();

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

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_prefs),0);
        String userName = sharedPreferences.getString(getString(R.string.user_name), "Unknown Legend");

        if(item.getTitle().toString()=="My Ratings"){


            connectionTask.sendContentRequest(new ContentRequest(userName));

            Intent i = new Intent(this, MyRatingsActivity.class);
            i.putExtra("heading", "My Reviews");
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
        else if(item.getTitle().toString()=="Refresh My Location"){
            Intent i = new Intent(this, MainActivity.class);
            this.startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //build android menu
        menu.add("Refresh My Location");
        menu.add("My Ratings");
        menu.add("Preferences");
        menu.add("About");
        return super.onCreateOptionsMenu(menu);
    }
}
