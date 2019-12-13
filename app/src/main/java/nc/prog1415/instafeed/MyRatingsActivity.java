package nc.prog1415.instafeed;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.ScriptGroup;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import nc.sharedInstafeedClasses.Business;
import nc.sharedInstafeedClasses.Rating;

import static nc.prog1415.instafeed.MainActivity.locationTask;
import static nc.prog1415.instafeed.SplashActivity.connectionTask;

public class MyRatingsActivity extends AppCompatActivity {

    private ArrayList<Business> businessArray = new ArrayList<Business>();
    private ArrayList<Rating> myRatingsArray = new ArrayList<Rating>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myratings);

        final TextView lblMyReviews = (TextView) findViewById(R.id.lblMyReviews);

        lblMyReviews.setText(connectionTask.feedback);

        initBusinessNames();
        initRecyclerView();
    }


    private void initBusinessNames(){
        businessArray.add(new Business("Tim Hortons","123 Street Ave. Welland, ON"));
        businessArray.add(new Business("Wendy's","234 Street Ave. Welland, ON"));
        businessArray.add(new Business("McDonalds","456 Street Ave. Welland, ON"));
        businessArray.add(new Business("Burger King","789 Street Ave. Welland, ON"));

        SharedPreferences prefs = getSharedPreferences(getString(R.string.shared_prefs),0);
        String userName = prefs.getString(getString(R.string.user_name),"Unknown Legend");

        for(int i = 0; i < businessArray.size(); i++){



            Rating rating = new Rating(
                    businessArray.get(i),
                    5f,
                    "Awesome title",
                    "great description!",
                    100d,
                    100d,
                    userName
            );

            myRatingsArray.add(rating);
        }
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.ratings_recyclerView);
        RatingsRecyclerViewAdapter adapter = new RatingsRecyclerViewAdapter(this, myRatingsArray);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    ///// MENU //////

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getTitle().toString()=="My Ratings"){
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
