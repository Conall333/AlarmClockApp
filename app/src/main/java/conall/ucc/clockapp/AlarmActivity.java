package conall.ucc.clockapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

public class AlarmActivity extends AppCompatActivity
{
    TimePicker alarmTimePicker;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    Context context;
    Button setButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        ActionBar bar = getSupportActionBar();
        bar.setTitle("    Alarm Clock App");
        bar.setSubtitle("        Conall McCarthy");
        bar.setBackgroundDrawable(getResources().getDrawable(R.drawable.custom_menu));
        bar.setDisplayOptions(
                ActionBar.DISPLAY_SHOW_TITLE |
                        ActionBar.DISPLAY_SHOW_CUSTOM);
        bar.show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        alarmTimePicker = findViewById(R.id.timePicker);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        context = getApplicationContext();
        setButton = findViewById(R.id.setAlarm);
        cancelButton = findViewById(R.id.cancelAlarm);


        try {
            Intent stopIntent = new Intent(getApplicationContext(), RingtoneNotificationService.class);
            getApplicationContext().stopService(stopIntent);
        } catch(Exception e){
            Log.d("TAG-", e.toString());
        }




        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long time;



                if (pendingIntent != null) {
                    Toast.makeText(AlarmActivity.this, "Alarm Updated and Set", Toast.LENGTH_LONG).show();
                    //...
                }
                else {

                    Toast.makeText(AlarmActivity.this, "Alarm is Set", Toast.LENGTH_SHORT).show();

                }

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
                Intent intent = new Intent(context, AlarmReceiver.class);


                // flag update current will override the existing alarm if set again
                pendingIntent = PendingIntent.getBroadcast(context, 0, intent,PendingIntent.FLAG_UPDATE_CURRENT );

                time=(calendar.getTimeInMillis()-(calendar.getTimeInMillis()%60000));
                if(System.currentTimeMillis()>time)
                {
                    if (calendar.AM_PM == 0)
                        time = time + (1000*60*60*12);
                    else
                        time = time + (1000*60*60*24);
                }

                // allowWhileIdle makes the alarm work when app in background
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, pendingIntent);



            }
        });



        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {

                    Intent intent = new Intent(context, AlarmReceiver.class);
                    pendingIntent = PendingIntent.getBroadcast(context, 0, intent,PendingIntent.FLAG_UPDATE_CURRENT );

                    alarmManager.cancel(pendingIntent);
                    Toast.makeText(AlarmActivity.this, "Alarm is Canceled", Toast.LENGTH_SHORT).show();

                    Intent stopIntent = new Intent(getApplicationContext(), RingtoneNotificationService.class);
                    getApplicationContext().stopService(stopIntent);
                } catch(Exception e){
                    Log.d("TAG-", e.toString());
                }



            }
        });


    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.alarm_menu, menu);
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

            case R.id.home_page:

                // set alarm //alarmManager

                Intent intentToAlarm = new Intent(this, MainActivity.class);
                startActivity(intentToAlarm);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }





}