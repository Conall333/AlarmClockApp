package conall.ucc.clockapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Calendar;
import java.util.HashMap;

public class MySurfaceView extends SurfaceView implements Runnable {

    private Thread thread = null;
    public SurfaceHolder surfaceHolder = getHolder();
    private boolean running = false;

    private float length;
    private Context c;

    SharedPrefs prefs = new SharedPrefs(getContext());



    public MySurfaceView(Context c,float l){
        super(c);
        this.c = c;
        this.length = l;



    }

    public void onResumeMySurfaceView(){
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void onPauseMySurfaceView(){
        boolean retry = true;
        running = false;
        while(retry)
        {
            try{
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    public Paint returnColour (Paint p) {

        prefs.getColors();
        HashMap<String, String> info = prefs.getColors();
        String hands = info.get("hands");
        Log.d("X25", hands);


        if (hands.equals("RED")) {
            p.setColor(Color.RED);
        }

        else if (hands.equals("BLUE")) {
            p.setColor(Color.BLUE);
        }

        else if (hands.equals("BLACK")) {
            p.setColor(Color.BLACK);
        }

        else if (hands.equals("WHITE")) {
            p.setColor(Color.WHITE);
        }

        else if (hands.equals("MAGENTA")) {
            p.setColor(Color.MAGENTA);
        }

        else if (hands.equals("YELLOW")) {
            p.setColor(Color.YELLOW);
        }
        else if (hands.equals("GREEN")) {
            p.setColor(Color.GREEN);
        }


        return p;

    }

    public Canvas returnCanvasColour (Canvas c) {

        prefs.getColors();
        HashMap<String, String> info = prefs.getColors();
        String bgColour = info.get("background");

        if (bgColour.equals("BLACK")) {
            c.drawColor(Color.BLACK);
        }
        else if (bgColour.equals("BLUE")) {
            c.drawColor(Color.BLUE);
        }

        else if (bgColour.equals("RED")) {
            c.drawColor(Color.RED);
        }

        else if (bgColour.equals("WHITE")) {
            c.drawColor(Color.WHITE);
        }

        else if (bgColour.equals("MAGENTA")) {
            c.drawColor(Color.MAGENTA);
        }

        else if (bgColour.equals("YELLOW")) {
            c.drawColor(Color.YELLOW);
        }

        else if (bgColour.equals("GREEN")) {
            c.drawColor(Color.GREEN);
        }


        return c;

    }





    public void run(){

        int hour = 0, min = 0, sec = 0;

        Paint paint = new Paint();
        paint.setStrokeWidth(3);
        while(running)
        {
            if (surfaceHolder.getSurface().isValid())

            {
                paint = returnColour(paint);

                Canvas canvas = surfaceHolder.lockCanvas();

                canvas = returnCanvasColour(canvas);

                RegPoly secMarks = new RegPoly(60,length,getWidth()/2,getHeight()/2,canvas,paint);
                RegPoly hourMarks = new RegPoly(12,length - 20,getWidth()/2,getHeight()/2,canvas,paint);

                RegPoly hourNums = new RegPoly(12,length - 70,getWidth()/2,getHeight()/2,canvas,paint);

                RegPoly hourHand = new RegPoly(60,length-85,getWidth()/2,getHeight()/2,canvas,paint);
                RegPoly minHand = new RegPoly(60,length-50,getWidth()/2,getHeight()/2,canvas,paint);
                RegPoly secHand = new RegPoly(60,length-20,getWidth()/2,getHeight()/2,canvas,paint);

                paint.setTextSize(40);

                secMarks.drawPoints();
                hourMarks.drawPoints();
                hourNums.drawNums();


                Calendar calendar = Calendar.getInstance();
                hour = calendar.get(calendar.HOUR);
                min = calendar.get(calendar.MINUTE);
                sec = calendar.get(calendar.SECOND);


                secHand.drawRadius(sec+45);
                minHand.drawRadius(min+45);
                hourHand.drawRadius(hour*5+min/12+45);



                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){e.printStackTrace();}


                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
