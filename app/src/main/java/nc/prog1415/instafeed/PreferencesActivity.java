package nc.prog1415.instafeed;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class PreferencesActivity extends AppCompatActivity {

    public static final String shared_prefs = "sharedPrefs";
    public static final String userName_prefsKey = "userName";

    public TextView txtDisplayName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        LinearLayout prefsChips = findViewById(R.id.prefsChips);

        txtDisplayName = findViewById(R.id.txtDisplayName);
        final Button btnSavePreferences = findViewById(R.id.btnSavePreferences);

        SharedPreferences sharedPreferences = getSharedPreferences(shared_prefs,MODE_PRIVATE);
        txtDisplayName.setText(sharedPreferences.getString(userName_prefsKey, "Unknown Legend"));

        btnSavePreferences.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                savePrefs();
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
    public void savePrefs(){


        Intent i = new Intent(this, MainActivity.class);

        SharedPreferences sharedPreferences = getSharedPreferences(shared_prefs,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String userName = txtDisplayName.getText().toString();
        editor.putString(userName_prefsKey, userName);
        editor.apply();

        Toast.makeText(this, "Preferences successfully changed.", Toast.LENGTH_SHORT).show();

        this.startActivity(i);
    }
}
