package com.cs211d.joel.demodrawables;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Random;


public class MoveShapeActivity extends ActionBarActivity
{

    protected static final String TAG = "MoveShapeActivity";
    public FrameLayout bt_panel;
    MoveShapeView  msv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_shape);

        final RelativeLayout frame = (RelativeLayout) findViewById(R.id.moveFrame);

        bt_panel = (FrameLayout) findViewById(R.id.bt_panel);
        bt_panel.setBackgroundColor(Color.WHITE);
        msv = new MoveShapeView(getApplicationContext());

        frame.addView(msv);


    }


    public void makeCircle(View view)
    {
        msv.choice = msv.CIRCLE;

        msv.makeCircle();
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

            canvas.drawColor(Color.WHITE);
            switch (choice)
            {
                case CIRCLE:
                {
                    for(Circle circle:circles)
                    {
                        canvas.drawCircle(circle.getX(), circle.getY(),
                                circle.getR(),circle.getP());
                    }
                    break;
                }
                case CLEAR:
                {
                    canvas.drawColor(Color.WHITE);
                    break;
                }
            }

        }


        @Override
        protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec)
        {

            int widthMode = MeasureSpec.getMode(widthMeasureSpec);
            int widthSize = MeasureSpec.getSize(widthMeasureSpec);
            int heightMode = MeasureSpec.getMode(heightMeasureSpec);
            int heightSize = MeasureSpec.getSize(heightMeasureSpec);

            int width;
            int height;

            //Measure Width
            if ((widthMode == MeasureSpec.EXACTLY)
                    ||(widthMode == MeasureSpec.AT_MOST))
            {
                width = widthSize;
            } else{
                //Be whatever you want
                width = widthSize;
            }

            //Measure Height
            if ((heightMode == MeasureSpec.EXACTLY)
                    ||(heightMode == MeasureSpec.AT_MOST))
             {
                height = heightSize;
            } else {
                height = heightSize;
            }

            setMeasuredDimension(width,
                    height- bt_panel.getHeight());

        }

        public void clearScreen()
        {
            //set choice to Clear
            choice = CLEAR;

            //delete all circles
            circles.clear();
        }

        public void makeCircle()
        {
            //Create Circle Object
            Circle c = new Circle();
            c.setR(getRandomNum(1, 200));
            c.setX(getRandomNum(1, screenWidth));
            c.setY(getRandomNum(1, screenHeight));

            //Add newly created circle to Circle Array
            circles.add(c);

            //check newly created circle bounds and fix it
            for(Circle circle:circles)
                checkBounds(circle);

            //redraw
            invalidate();
        }

        public void checkBounds(Circle circle)
        {
            /**
             * Check bounds of the Circle
             * if the original bounds are off screen
             * push/pull circle to the screen
             */
            Rect bounds = circle.getBounds();

            int x = circle.getX();
            int y = circle.getY();

            if (bounds.left < 0)
            {
                circle.setX(getRandomNum(5,
                        (x + screenWidth) / 2));
            }
            else if(bounds.right > screenWidth)
            {
                circle.setX(getRandomNum(5,
                        (screenWidth - x)/2));
            }
            else if(bounds.top < 0)
            {
                circle.setY(getRandomNum(5,
                        (screenHeight + y )/ 2));
            }
            else if(bounds.bottom > screenHeight)
            {
                 circle.setY(getRandomNum(5,
                        (screenHeight + y) / 2));
            }

        }

        /***********getRandomNum()*********************************/
        public int getRandomNum(int min, int max)
        {
            //Generate random int between min-max
            System.out.println("Max Value is " + max + "Min Value is " + min);
            return min + rand.nextInt(Math.abs((max - min)) + 1);

        }

    }




}
