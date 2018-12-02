package conall.ucc.clockapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    MySurfaceView mySurfaceView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mySurfaceView = new MySurfaceView(this,300);
        setContentView(mySurfaceView);



        ActionBar bar = getSupportActionBar();
        bar.setTitle("    Alarm Clock App");
        bar.setSubtitle("        Conall McCarthy");
        bar.setBackgroundDrawable(getResources().getDrawable(R.drawable.custom_menu));
        bar.setDisplayOptions(
                ActionBar.DISPLAY_SHOW_TITLE |
                ActionBar.DISPLAY_SHOW_CUSTOM);
        bar.show();


    }


    @Override
    protected  void onResume() {

        super.onResume();
        mySurfaceView.onResumeMySurfaceView();
    }


    @Override
    protected  void onPause() {

        super.onPause();
        mySurfaceView.onPauseMySurfaceView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {


            case R.id.change_hands:
                // start an activity

                Intent intent = new Intent(this, ColourActivity.class);
                startActivity(intent);


                return true;

            case R.id.set_alarm:

                // set alarm //alarmManager


                Intent intentToAlarm = new Intent(this, AlarmActivity.class);
                startActivity(intentToAlarm);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }









}
