package nc.prog1415.instafeed;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RatingDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratingdetails);

        final Button btnSaveReview = findViewById(R.id.btnSaveReview);

        btnSaveReview.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                goHome();
            }
        });
    }

    public void goHome(){
        Intent i = new Intent(this, MainActivity.class);
        this.startActivity(i);
    }
}
