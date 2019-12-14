package nc.prog1415.instafeed;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

public class SplashActivity extends AppCompatActivity {

    public static ConnectionTask connectionTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

//        SystemClock.sleep(3000);

        String shared_prefs = getString(R.string.shared_prefs);
        SharedPreferences sharedPreferences = getSharedPreferences(shared_prefs,0);
        String defaultUserName = "212#CCq---Th!s_uS3r-HASno-nme?!---WJK%512";
        String currentUserName = sharedPreferences.getString(getString(R.string.user_name),defaultUserName);

        if(currentUserName == defaultUserName){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(getString(R.string.user_name), UUID.randomUUID().toString());
            editor.commit();
        }

        connectionTask = new ConnectionTask();
        connectionTask.setNetworkContext(getApplicationContext());
        connectionTask.execute();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
//        finish();
    }
}
