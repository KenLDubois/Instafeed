package nc.prog1415.instafeed;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RatingDetailsActivity extends AppCompatActivity {

    public Business business;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratingdetails);

        Bundle bundle = getIntent().getExtras();
        business = (Business) bundle.get("business");

        final Button btnSaveReview = findViewById(R.id.btnSaveReview);
        final TextView txtBusinessName = findViewById(R.id.businessName_detailRating);
        final TextView txtBusinessAddress = findViewById(R.id.businessAddress_detailRating);

        txtBusinessName.setText(business.BusinessName);
        txtBusinessAddress.setText(business.Address);

        btnSaveReview.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                saveReview();
            }
        });
    }

    public void saveReview(){
        Intent i = new Intent(this, MainActivity.class);
        String message = "Your rating for " + business.BusinessName + " has been saved.";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        this.startActivity(i);
    }
}
