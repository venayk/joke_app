package com.example.vinaykumar.jokeapp;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity
{
    GridView gridView;
    GridViewController gridViewController;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gridviewlayout);
        String[] cellData = new String[50];
        for(int i=0;i<50;i++)
        {
            cellData[i]="label";
        }

        gridViewController = new GridViewController(MainActivity.this, cellData);
        gridView.setAdapter(gridViewController);

    }


}
