package com.cs211d.joel.demodrawables;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Random;


public class MoveShapeActivity extends ActionBarActivity
{

    protected static final String TAG = "MoveShapeActivity";
    MoveShapeView  msv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_shape);

        final RelativeLayout frame = (RelativeLayout) findViewById(R.id.moveFrame);

        msv = new MoveShapeView(getApplicationContext());
        frame.addView(msv);


    }


    public void makeCircle(View view)
    {
        msv.makeCircle();
        msv.move();
        msv.invalidate();
    }

    public void makeSqr(View view)
    {
        msv.makeSqr();
        msv.move();
        msv.invalidate();
    }

    public void clear(View view)
    {
        msv.clearScreen();
        msv.invalidate();
    }



    private class MoveShapeView extends View
    {
        private Random rand = new Random();
        private Paint p = new Paint();
        Paint p2 = new Paint();
        int screenHeight;
        int screenWidth;
        private boolean isCircle = false;
        private final int SQR = 0;
        private final int CIRCLE =1;
        private final int CLEAR = 2;
        private int choice =0;


        private int h ,w,r,left,right,top,bottom;

        public MoveShapeView(Context context)
        {
            super(context);
            this.p.setColor(Color.rgb(getRandomNum(25, 255),
                    getRandomNum(1, 255), getRandomNum(25, 255)));
            choice = 2;

        }

        //todo setup buttons and complete switch commands

        @Override
        protected void onDraw(Canvas canvas)
        {
            screenHeight = canvas.getHeight();
            screenWidth = canvas.getWidth();


            switch (choice)
            {
                case SQR:
                {
                    canvas.drawRect(left, top, right, bottom, p2);
                    break;
                }
                case CIRCLE:
                {
                    canvas.drawCircle(w, h, r, p);
                    break;
                }
                case CLEAR:
                {
                    canvas.drawColor(Color.WHITE);
                    break;
                }
            }

        }

        public void clearScreen()
        {
            choice = 2;
        }

        public void makeCircle()
        {
            choice  =1;
        }

        public void makeSqr()
        {
            choice = 0;
        }

        public void move()
        {
            h = getRandomNum(50, screenHeight - 50);
            w = getRandomNum(10, screenWidth - 10);
            r = getRandomNum(1, 200);
            p.setColor(Color.rgb(getRandomNum(25, 255),
                    getRandomNum(1, 255), getRandomNum(25, 255)));
            p2.setColor(Color.rgb(getRandomNum(25, 255),
                    getRandomNum(1, 255), getRandomNum(25, 255)));


            left = getRandomNum(1,screenWidth);
            top = getRandomNum(1,screenHeight);
            right = left + left;
            bottom = top + top;

        }

        /***********getRandomNum()*********************************/
        public int getRandomNum(int min, int max)
        {
            //Generate random int between min-max
            return rand.nextInt(max-min)+min;
        }

    }




}
