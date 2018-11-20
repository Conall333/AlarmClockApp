package conall.ucc.clockapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SharedPrefs {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context _context;
    private int PRIVATE_MODE = 0;


    private static final String PREF_NAME = "MyPref";
    private static final String COLOR_SET = "coloursAreSet";
    public static final String CLOCK_COLOUR = "hands";
    public static final String BACKGROUND_COLOUR = "background";

    public SharedPrefs(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void setColors(String hands, String background){

        editor.putBoolean(COLOR_SET, true);
        editor.putString(CLOCK_COLOUR, hands);
        editor.putString(BACKGROUND_COLOUR, background);
        editor.commit();
    }




    public HashMap<String, String> getColors(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(CLOCK_COLOUR, pref.getString(CLOCK_COLOUR, "RED"));
        user.put(BACKGROUND_COLOUR, pref.getString(BACKGROUND_COLOUR, "BLACK"));
        return user;
    }


    public void clearColours(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }


}
