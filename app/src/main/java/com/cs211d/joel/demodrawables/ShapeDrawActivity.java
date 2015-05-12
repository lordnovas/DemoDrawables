package com.cs211d.joel.demodrawables;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class ShapeDrawActivity extends ActionBarActivity
{
    int alpha = 127;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_draw);

        int width = (int) getResources().getDimension(R.dimen.shapeImage_width);
        int height = (int) getResources().getDimension(R.dimen.shapeImage_height);
        int padding = (int) getResources().getDimension(R.dimen.shapeImage_padding);

        //Get A reference to the shapeFrame on screen
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.shapeFrame);

        //Create Cyan Shape obj
        ShapeDrawable cyanShape = new ShapeDrawable(new OvalShape());
        cyanShape.getPaint().setColor(Color.CYAN);
        cyanShape.setIntrinsicHeight(height);
        cyanShape.setIntrinsicWidth(width);
        cyanShape.setAlpha(alpha);

        //Put Cyan Shape into an ImageView obj
        ImageView cyanView = new ImageView(getApplicationContext());
        cyanView.setImageDrawable(cyanShape);
        cyanView.setPadding(padding,padding,padding,padding);

        //Specify placement of ImageView
        RelativeLayout.LayoutParams cyanViewLayoutParams = new RelativeLayout.LayoutParams(
                height,width);
        cyanViewLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        cyanViewLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        cyanView.setLayoutParams(cyanViewLayoutParams);
        rl.addView(cyanView);


        //Create Magenta Shape
        ShapeDrawable magentaShape = new ShapeDrawable(new OvalShape());
        magentaShape.getPaint().setColor(Color.MAGENTA);
        magentaShape.setIntrinsicHeight(height);
        magentaShape.setIntrinsicWidth(width);
        magentaShape.setAlpha(alpha);


        //Put Magenta Shape into an ImageView obj
        ImageView magentaView = new ImageView(getApplicationContext());
        magentaView.setImageDrawable(magentaShape);
        magentaView.setPadding(padding,padding,padding,padding);

        //Specify placement of ImageView
        RelativeLayout.LayoutParams magentaViewLayoutParams = new RelativeLayout.LayoutParams(
                height,width);
        magentaViewLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        magentaViewLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        magentaView.setLayoutParams(magentaViewLayoutParams);
        rl.addView(magentaView);

    }



    public void goToMove(View v)
    {
        Intent i = new Intent(getApplicationContext(),
                MoveShapeActivity.class);
        startActivity(i);
    }



}
