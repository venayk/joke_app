package com.example.vinaykumar.jokeapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.Random;

/**
 * Created by vinay kumar on 1/10/2016.
 */
public class GridViewController extends BaseAdapter
{
    private Context context;
    private String[] cellData;
    GridViewController(Context context, String[] cellData)
    {
        this.context = context;
        this.cellData = cellData;
    }

    @Override
    public int getCount()
    {
        return cellData.length;
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View cell;
        cell = new View(context);
        cell = inflater.inflate(R.layout.gird_cell, null);
        Button cellbtn = (Button) cell.findViewById(R.id.button);
        Random ran = new Random();
        int a = ran.nextInt(256);
        int r = ran.nextInt(256);
        int g = ran.nextInt(256);
        int b = ran.nextInt(256);
        cellbtn.setBackgroundColor(Color.argb(255-a,255-r,255-g,255-b));
        cellbtn.setText(cellData[position]);
        cellbtn.setTextColor(Color.argb(255, r, g, b));
        cellbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String urlString = "http://api.icndb.com/jokes/random?firstName=Anit&lastName=Kumar&limitTo=[nerdy]";
                URL url = null;
                try
                {
                    url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    InputStream is = conn.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine())!=null)
                    {
                        sb.append(line);
                    }
                    String result;
                    result = sb.toString();
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(result);
                        JSONObject value = jsonObject.getJSONObject("value");
                        String joke = value.getString("joke");
                        Log.v("JOKE", joke);
                        Toast.makeText(context, joke, Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(getApplicationContext(), SignoutActivity.class);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                catch (MalformedURLException e)
                {
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            }
        });
        return cell;
    }
}
