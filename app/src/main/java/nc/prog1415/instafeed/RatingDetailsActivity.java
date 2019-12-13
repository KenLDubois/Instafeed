package nc.prog1415.instafeed;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import nc.sharedInstafeedClasses.Business;
import nc.sharedInstafeedClasses.Rating;

import static nc.prog1415.instafeed.MainActivity.locationTask;
import static nc.prog1415.instafeed.SplashActivity.connectionTask;

public class RatingDetailsActivity extends AppCompatActivity {

    private Business business;
    private RatingBar ratingBar;
    private TextView txtTitle;
    private TextView txtReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratingdetails);

        Bundle bundle = getIntent().getExtras();
        business = (Business) bundle.get("business");

        ratingBar = (RatingBar) findViewById(R.id.barDetailRating);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtReview = (TextView) findViewById(R.id.txtReview);

        final Button btnSaveReview = findViewById(R.id.btnSaveReview);
        final Button btnCancelRating = findViewById(R.id.btnCancelRating);
        final TextView txtBusinessName = findViewById(R.id.businessName_detailRating);
        final TextView txtBusinessAddress = findViewById(R.id.businessAddress_detailRating);

        txtBusinessName.setText(business.BusinessName);
        txtBusinessAddress.setText(business.Address);

        btnSaveReview.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                saveReview();
            }
        });

        btnCancelRating.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                returnToMain();
            }
        });
    }

    public void saveReview(){

        SharedPreferences prefs = getSharedPreferences(getString(R.string.shared_prefs),0);
        String userName = prefs.getString(getString(R.string.user_name),"Unknown Legend");

        Rating rating = new Rating(
          business,
                ratingBar.getRating(),
                txtTitle.getText().toString(),
                txtReview.getText().toString(),
                locationTask.location.getLatitude(),
                locationTask.location.getLongitude(),
                userName
        );

        connectionTask.sendRating(rating);

        Intent i = new Intent(this, MainActivity.class);
        String message = "Your rating for " + business.BusinessName + " has been saved.";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        this.startActivity(i);
    }

    public void returnToMain(){
        Intent i = new Intent(this, MainActivity.class);
        this.startActivity(i);
    }


}
