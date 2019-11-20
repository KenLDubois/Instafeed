package nc.prog1415.instafeed;

import android.app.ActionBar;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRatingsActivity extends AppCompatActivity {

    private ArrayList<String> businessNames = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myratings);

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
        RecyclerView recyclerView = findViewById(R.id.ratings_recyclerView);
        RatingsRecyclerViewAdapter adapter = new RatingsRecyclerViewAdapter(this, businessNames);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
