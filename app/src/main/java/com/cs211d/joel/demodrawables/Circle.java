package com.cs211d.joel.demodrawables;


import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

public class Circle
{

    private int x;
    private int y;
    private int r;
    private Paint p;
    private Random rand = new Random();


    public Circle()
    {
        //A new paint color is generated randomly for Circle Obj
        p = new Paint();
        p.setColor(Color.rgb(getRandomNum(25, 255),
                getRandomNum(1, 255), getRandomNum(25, 255)));
    }


    public Rect getBounds()
    {
        System.out.println("X Value is "+getX());
        System.out.println("Y Value is "+getY());
        System.out.println("R Value is "+getR());
        return new Rect(getX()-r, getY()-r, (getR() + getX()), getR() + getY());
    }

    public Paint getP()
    {
        return p;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void setR(int r)
    {
        this.r = r;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getR()
    {
        return r;
    }




    /***********getRandomNum()*********************************/
    public int getRandomNum(int min, int max)
    {
        //Generate random int between min-max
        return rand.nextInt(max-min)+min;
    }

}
