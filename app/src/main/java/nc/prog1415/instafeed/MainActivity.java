package nc.prog1415.instafeed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ArrayList<String> businessNames = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: started");

        initBusinessNames();
        initRecyclerView();


    }

    private void initBusinessNames(){
        businessNames.add("Tim Hortons");
        businessNames.add("Wendy's");
        businessNames.add("McDonalds");
        businessNames.add("Burger King");
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.mainRecycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, businessNames);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
}
