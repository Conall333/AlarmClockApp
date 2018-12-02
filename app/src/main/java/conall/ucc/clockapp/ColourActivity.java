package conall.ucc.clockapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ColourActivity extends AppCompatActivity {

    String selectedItem = "BLACK";
    String selectedItem2= "WHITE";
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour);

        final SharedPrefs prefs = new SharedPrefs(this);

        Button button = findViewById(R.id.button3);



        ActionBar bar = getSupportActionBar();
        bar.setTitle("    Alarm Clock App");
        bar.setSubtitle("        Conall McCarthy");
        bar.setBackgroundDrawable(getResources().getDrawable(R.drawable.custom_menu));
        bar.setDisplayOptions(
                ActionBar.DISPLAY_SHOW_TITLE |
                        ActionBar.DISPLAY_SHOW_CUSTOM);
        bar.show();



        Spinner spinner = findViewById(R.id.hands_spinner);
        Spinner backgroundSpinner = findViewById(R.id.background_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.colours_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        backgroundSpinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = parent.getItemAtPosition(position).toString();



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        backgroundSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem2 = parent.getItemAtPosition(position).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                prefs.setColors(selectedItem,selectedItem2);

                Intent intent = new Intent(context,MainActivity.class);
                startActivity(intent);

            }
        });










    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.colour_menu, menu);
        return super.onCreateOptionsMenu(menu);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {


            case R.id.home_page:
                // start an activity

                Intent intent = new Intent(this, MainActivity.class);
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
