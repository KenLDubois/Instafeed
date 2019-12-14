package nc.prog1415.instafeed;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class PreferencesActivity extends AppCompatActivity {

    public static SharedPreferences sharedPreferences;

    public TextView txtDisplayName;
    private int maxResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

//        LinearLayout prefsChips = findViewById(R.id.prefsChips);

        txtDisplayName = findViewById(R.id.txtDisplayName);
        final Button btnSavePreferences = findViewById(R.id.btnSavePreferences);

        sharedPreferences = getSharedPreferences(getString(R.string.shared_prefs),MODE_PRIVATE);
        txtDisplayName.setText(sharedPreferences.getString(getString(R.string.user_name), "Unknown Legend"));

        final TextView lblMaxResults = (TextView) findViewById(R.id.lblMaxResults);
        final SeekBar seekMaxResults = (SeekBar) findViewById(R.id.seekMaxResults);

        maxResults = sharedPreferences.getInt(getString(R.string.max_results),3);

        seekMaxResults.setProgress(maxResults);
        lblMaxResults.setText("Max Results: " + maxResults);

        seekMaxResults.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

                maxResults = seekBar.getProgress();
                lblMaxResults.setText("Max Results: " + maxResults);

            }
        });



        btnSavePreferences.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                savePrefs();
            }
        });

//        ArrayList<String> chips = new ArrayList<String>(
//                Arrays.asList(
//                        "Coffee Shops",
//                        "Fast Food",
//                        "Restaurants",
//                        "Convenience Stores"));
//
//        for(Integer i = 0; i < chips.size(); i++){
//            Switch newSwitch = new Switch(this);
//            newSwitch.setText(chips.get(i));
//            prefsChips.addView(newSwitch);
//        }
    }
    public void savePrefs(){


        Intent i = new Intent(this, MainActivity.class);

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_prefs),MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String userName = txtDisplayName.getText().toString();
        editor.putString(getString(R.string.user_name), userName);
        editor.putInt(getString(R.string.max_results),maxResults);

        editor.apply();

        Toast.makeText(this, "Preferences successfully changed.", Toast.LENGTH_SHORT).show();

        this.startActivity(i);
    }
}
