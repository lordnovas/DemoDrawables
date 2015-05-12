package com.cs211d.joel.demodrawables;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //Set the content view to the frame that will display the img
        setContentView(R.layout.activity_main);

        //Get a reference to the frame - Note I'm returning a RelativeLayout called frame
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.frame);

        //Create a image view obj and set it's value to the trollface icon
        ImageView iv = new ImageView(getApplicationContext());
        iv.setImageDrawable(getResources().getDrawable(R.drawable.trollface,getTheme()));

        //set the w/h for image view obj
        int width = (int) getResources().getDimension(R.dimen.image_width);
        int height = (int) getResources().getDimension(R.dimen.image_height);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width,height);

        //Add rule to center object inside of parent
        params.addRule(RelativeLayout.CENTER_IN_PARENT);

        //set the layout properties to the image view
        iv.setLayoutParams(params);

        //Add ImageView as new child of frame/rl/RelativeLayout
        rl.addView(iv);

    }

    public void next(View v)
    {
        Intent i = new Intent(getApplicationContext(),ShapeDrawActivity.class);
        startActivity(i);
    }

}
