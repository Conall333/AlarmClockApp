package conall.ucc.clockapp;

import android.graphics.Canvas;
import android.graphics.Paint;

public class RegPoly {

    private float x0,y0,r;
    private int n;
    private float x[],y[];

    private Canvas c = null; private Paint p = null;

    public RegPoly(int n,float r,float x0,float y0,Canvas canvas,Paint paint) {

        this.n = n;
        this.r = r;
        this.x0 = x0;
        this.y0 = y0;

        this.c = canvas;
        this.p = paint;

        x = new float[n];
        y = new float[n];


        for(int i =0;i<n;i++)
        {

            x[i] = (float)(x0+r*Math.cos(2*Math.PI*i/n));
            y[i] = (float)(y0+r*Math.sin(2*Math.PI*i/n));

        }



    }
    public float getX(int i){return  x[i%n];}
    public float getY(int i){return  y[i%n];}

    public void drawRegPoly(){

        for(int i=0;i<n;i++)
        {
            c.drawLine(x[i],y[i],x[(i+1)%n],y[(i+1)%n],p);
        }


    }
    public void drawPoints(){


        for(int i=0;i<n;i++)
        {
            c.drawCircle(x[i],y[i],4,p);
        }


    }

    public void drawNums(){

        String[] nums = {"3","4","5","6","7","8","9","10","11","12","1","2"};


        for(int i=0;i<n;i++)

        {
            float z = x[i];
            float q = y[i];

            if (nums[i].length()> 1) {
                z -= 22;

            }
            else if (nums[i].equals("9")) {

                z -= 22;
                q += 12;
             }

            else if (nums[i].equals("8")) {

                z -= 22;
                q += 20;
            }

            else if (nums[i].equals("7")) {

                z -= 22;
                q += 25;
            }

            else if (nums[i].equals("6")) {

                z -= 12;
                q += 25;
            }
            else if (nums[i].equals("5")) {

                z -= 3;
                q += 25;
            }
            else if (nums[i].equals("4")) {

                z += 5;
                q += 20;
            }

            else if (nums[i].equals("3")) {

                z += 5;
                q += 12;
            }




            c.drawText(nums[i],z,q,p);
        }


    }




    public void drawRadius(int i){

            c.drawLine(x0,y0,x[i%n],y[i%n],p);

    }

}
