package nc.prog1415.instafeed;

import android.app.ActionBar;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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
}
