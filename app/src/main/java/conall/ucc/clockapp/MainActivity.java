package conall.ucc.clockapp;

import android.os.Bundle;
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
                return true;

            case R.id.set_alarm:

                // set alarm //alarmManager

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }









}
