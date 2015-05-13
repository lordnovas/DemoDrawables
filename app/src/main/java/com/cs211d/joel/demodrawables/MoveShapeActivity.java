package com.cs211d.joel.demodrawables;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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
        msv.choice = msv.CIRCLE;

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
        private int screenHeight;
        private int screenWidth;
        private final int CIRCLE = 0;
        private final int CLEAR = 1;
        private int choice =0;
        private ArrayList<Circle> circles = new ArrayList<>();


        public MoveShapeView(Context context)
        {
            super(context);
            p.setColor(Color.WHITE);
            choice = CLEAR;
        }

        @Override
        protected void onDraw(Canvas canvas)
        {
            screenHeight = canvas.getHeight();
            screenWidth = canvas.getWidth();

            switch (choice)
            {
                case CIRCLE:
                {
                    makeCircle(canvas);
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
            //set choice to Clear
            choice = CLEAR;

            //delete all circles
            circles.clear();
        }

        public void makeCircle(Canvas canvas)
        {
            //set choice to 1 to enable circles drawn on screen

            //Create Circle Object
            Circle c = new Circle();
            c.setR(getRandomNum(1,200));
            c.setX(getRandomNum(1, screenWidth));
            c.setY(getRandomNum(1, screenHeight));

            //Add newly created circle to Circle Array
            circles.add(c);

            p.setColor(Color.WHITE);
            //Draw all Circles from
            for(Circle circle:circles)
            {
                checkBounds(circle);
                canvas.drawCircle(circle.getX(), circle.getY(),
                        circle.getR(),circle.getP());
            }
        }

        public void checkBounds(Circle circle)
        {
            /*todo - upper/lower left corner and upper/lower right corner
             */
            Rect bounds = circle.getBounds();
            if (bounds.left < 0)
            {
                //do something
                Message.message(getApplicationContext(),
                        "Bounding Box left out of bounds" + screenWidth);
                    circle.setX(getRandomNum(5, (circle.getX() + screenWidth) / 2));
            }
            else if(bounds.right > screenWidth)
            {
                Message.message(getApplicationContext(),
                        "Bounding Box Right out of bounds" + screenWidth);
                circle.setX(getRandomNum(1,(screenWidth - circle.getX())/2));
            }else if(bounds.top < 0)
            {
                Message.message(getApplicationContext(),
                        "Bounding Box Top of bounds" + screenHeight);
                circle.setY(getRandomNum(1, (screenHeight + circle.getX()) / 2));
            }else if(bounds.bottom > screenHeight)
            {
                Message.message(getApplicationContext(),
                        "Bounding Box Top of bounds" + screenHeight);
                circle.setY(getRandomNum(1, (screenHeight + circle.getX()) / 2));
            }
        }


        /***********getRandomNum()*********************************/
        public int getRandomNum(int min, int max)
        {
            //Generate random int between min-max
            System.out.println("Max Value is " + max + "Min Value is " + min);
            return rand.nextInt(Math.abs(max) - min)+ min;
        }

    }




}
