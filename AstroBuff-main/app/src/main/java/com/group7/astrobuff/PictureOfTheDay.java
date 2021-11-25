package com.group7.astrobuff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NotificationCompat;

import android.Manifest;
import android.app.DownloadManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class PictureOfTheDay extends AppCompatActivity {
    private static final int REQUEST_CODE = 100;
    //create PictureOfTheDay object
    PictureOfTheDayModel pic = new PictureOfTheDayModel();

    ImageView imageView;

    //pict notification

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_of_the_day);
        //set page title
        getSupportActionBar().setTitle("Picture of the Day");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.caramel)));

        //load the data
        String URL = "https://api.nasa.gov/planetary/apod?api_key=Z6MmKdvGYkuc84Ecrwyw3IVHERWISk6z7gYasW16";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //Use setters to update attributes
                            pic.setCopyright(response.getString("copyright"));
                            pic.setDate(response.getString("date"));
                            pic.setExplanation(response.getString("explanation"));
                            pic.setHdurl(response.getString("hdurl"));
                            pic.setMedia_type(response.getString("media_type"));
                            pic.setTitle(response.getString("title"));
                            pic.setUrl(response.getString("url"));

                            //Update UI here
                            //Toast.makeText(PictureOfTheDay.this, "image url:"+pic.getUrl() + "\nDescription:"+pic.getExplanation(), Toast.LENGTH_LONG).show();
                            imageView = findViewById(R.id.imageview);
                            Picasso.get().load(pic.getUrl()).fit().centerCrop().into(imageView);
                            TextView pictText = (TextView)findViewById(R.id.pict);
                            pictText.setText(pic.getExplanation());

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(PictureOfTheDay.this, "Error on GET", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        System.out.println("error");
                        Toast.makeText(PictureOfTheDay.this, "Error on Get", Toast.LENGTH_SHORT).show();

                    }
                });

        // Access the RequestQueue through singleton class
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }
    // share and download button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        //share clicked
        if (id == R.id.share){
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String Sub = pic.getUrl();
            intent.putExtra(Intent.EXTRA_TEXT, Sub);
            startActivity((Intent.createChooser(intent, "Share Using")));
        }
        //download clicked
        if (id == R.id.downIm){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
                }
            }

            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(pic.getUrl()));
            request.setDescription("Downloading..." + pic.getTitle());
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.allowScanningByMediaScanner();
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,pic.getTitle());
            DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            manager.enqueue(request);
        }
        return true;
    }

}