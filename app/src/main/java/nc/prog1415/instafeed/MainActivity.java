package nc.prog1415.instafeed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ArrayList<Business> businessArray = new ArrayList<Business>();

    Button btnTestServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTestServer = (Button) findViewById(R.id.btnTestServer);

        btnTestServer.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                testServer();
            }
        });

        Log.d(TAG, "onCreate: started");

        initBusinessNames();
        initRecyclerView();

    }

    private void testServer(){
        RatingSender ratingSender = new RatingSender();
        ratingSender.execute("Message to server from client");
    }

    private void initBusinessNames(){
        businessArray.add(new Business("Tim Hortons","123 Street Ave. Welland, ON"));
        businessArray.add(new Business("Wendy's","234 Street Ave. Welland, ON"));
        businessArray.add(new Business("McDonalds","456 Street Ave. Welland, ON"));
        businessArray.add(new Business("Burger King","789 Street Ave. Welland, ON"));
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.mainRecycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, businessArray);
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
