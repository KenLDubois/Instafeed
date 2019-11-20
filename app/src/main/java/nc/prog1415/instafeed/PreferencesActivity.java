package nc.prog1415.instafeed;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class PreferencesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        LinearLayout prefsChips = findViewById(R.id.prefsChips);

        final Button btnSavePreferences = findViewById(R.id.btnSavePreferences);

        btnSavePreferences.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                goHome();
            }
        });

        ArrayList<String> chips = new ArrayList<String>(
                Arrays.asList(
                        "Coffee Shops",
                        "Fast Food",
                        "Restaurants",
                        "Convenience Stores"));

        for(Integer i = 0; i < chips.size(); i++){
            Switch newSwitch = new Switch(this);
            newSwitch.setText(chips.get(i));
            prefsChips.addView(newSwitch);
        }
    }
    public void goHome(){
        Intent i = new Intent(this, MainActivity.class);
        this.startActivity(i);
    }
}
