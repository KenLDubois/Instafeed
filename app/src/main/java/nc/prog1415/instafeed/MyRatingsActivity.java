package nc.prog1415.instafeed;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import nc.sharedInstafeedClasses.Business;

public class MyRatingsActivity extends AppCompatActivity {

    private ArrayList<Business> businessArray = new ArrayList<Business>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myratings);

        initBusinessNames();
        initRecyclerView();
    }

    private void initBusinessNames(){
        businessArray.add(new Business("Tim Hortons","123 Street Ave. Welland, ON"));
        businessArray.add(new Business("Wendy's","234 Street Ave. Welland, ON"));
        businessArray.add(new Business("McDonalds","456 Street Ave. Welland, ON"));
        businessArray.add(new Business("Burger King","789 Street Ave. Welland, ON"));
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.ratings_recyclerView);
        RatingsRecyclerViewAdapter adapter = new RatingsRecyclerViewAdapter(this, businessArray);
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
