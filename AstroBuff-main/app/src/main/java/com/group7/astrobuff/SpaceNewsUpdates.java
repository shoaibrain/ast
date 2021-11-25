package com.group7.astrobuff;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SpaceNewsUpdates extends AppCompatActivity {
    LinearLayout linearLayout;
    Button link_more;


    //notification

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_news_updates);
        //set page title
        getSupportActionBar().setTitle("Space News/Updates");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.dark_blue)));

        //load data
        ArrayList<NewsDataModel> NEWS = new ArrayList<NewsDataModel>();
        //five news object
        NewsDataModel news1 = new NewsDataModel();
        NewsDataModel news2 = new NewsDataModel();
        NewsDataModel news3 = new NewsDataModel();
        NewsDataModel news4 = new NewsDataModel();
        NewsDataModel news5 = new NewsDataModel();
        NewsDataModel news6 = new NewsDataModel();
        NewsDataModel news7= new NewsDataModel();
        NewsDataModel news8 = new NewsDataModel();
        NewsDataModel news9 = new NewsDataModel();
        NewsDataModel news10 = new NewsDataModel();

        NEWS.add(news1);
        NEWS.add(news2);
        NEWS.add(news3);
        NEWS.add(news4);
        NEWS.add(news5);
        NEWS.add(news6);
        NEWS.add(news7);
        NEWS.add(news8);
        NEWS.add(news9);
        NEWS.add(news10);

        //api
        String URL = "https://newsdata.io/api/1/news?apikey=pub_22544cddd426c9e1e21c47b670b0617983c0&category=science&language=en";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String status = response.getString("status");
                            JSONArray results = response.getJSONArray("results");

                            //get first five news from JsonArray
                            for (int i = 0; i< 10; i++){
                                JSONObject current = results.getJSONObject(i);

                                //Update news object
                                NEWS.get(i).setTitle(current.getString("title"));
                                NEWS.get(i).setName(current.getString("source_id"));
                                NEWS.get(i).setDescription(current.getString("description"));
                                NEWS.get(i).setUrl(current.getString("link"));
                                NEWS.get(i).setUrlToImage(current.getString("image_url"));
                                NEWS.get(i).setPublished_time(current.getString("pubDate"));

                            }

                            //Update UI here
                            //Toast.makeText(SpaceNewsUpdates.this, "Status:"+status + "\nURL:"+NEWS.get(0).getUrl()+"\nTitle:"+NEWS.get(0).getTitle(), Toast.LENGTH_LONG).show();

                            //Read more...
                            linearLayout = findViewById(R.id.container);
                            link_more = findViewById(R.id.link_more);
                            link_more.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    readMore();
                                    //Article more
                                    Button newsTextMore = (Button) findViewById(R.id.addNews);
                                    newsTextMore.setText(NEWS.get(5).getTitle());
                                    Button articlesMore = (Button) findViewById(R.id.addNews);
                                    articlesMore.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            String url_more = NEWS.get(5).getUrl();
                                            Intent i = new Intent(Intent.ACTION_VIEW);
                                            i.setData(Uri.parse(url_more));
                                        }
                                    });
                                }

                            });
                            //Article 1
                            Button newsText0 = (Button) findViewById(R.id.link_a);
                            newsText0.setText(NEWS.get(0).getTitle());
                            Button article_a = (Button) findViewById(R.id.link_a);
                            article_a.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String url_a = NEWS.get(0).getUrl();
                                    Intent i = new Intent(Intent.ACTION_VIEW);
                                    i.setData(Uri.parse(url_a));
                                    startActivity(i);
                                }
                            });

                            //Article 2
                            Button newsText1 = (Button) findViewById(R.id.link_b);
                            newsText1.setText(NEWS.get(1).getTitle());
                            Button article_b = (Button) findViewById(R.id.link_b);
                            article_b.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String url_b = NEWS.get(1).getUrl();
                                    Intent i = new Intent(Intent.ACTION_VIEW);
                                    i.setData(Uri.parse(url_b));
                                    startActivity(i);
                                }
                            });

                            //Article 3
                            Button newsText2 = (Button) findViewById(R.id.link_c);
                            newsText2.setText(NEWS.get(2).getTitle());

                            Button article_c = (Button) findViewById(R.id.link_c);
                            article_c.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String url_c = NEWS.get(2).getUrl();
                                    Intent i = new Intent(Intent.ACTION_VIEW);
                                    i.setData(Uri.parse(url_c));
                                    startActivity(i);
                                }
                            });

                            //Article 4
                            Button newsText3 = (Button) findViewById(R.id.link_d);
                            newsText3.setText(NEWS.get(3).getTitle());
                            Button article_d = (Button) findViewById(R.id.link_d);
                            article_d.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String url_d= NEWS.get(3).getUrl();
                                    Intent i = new Intent(Intent.ACTION_VIEW);
                                    i.setData(Uri.parse(url_d));
                                    startActivity(i);
                                }
                            });

                            //Article 5
                            Button newsText4 = (Button) findViewById(R.id.link_e);
                            newsText4.setText(NEWS.get(4).getTitle());
                            Button article_e = (Button) findViewById(R.id.link_e);
                            article_e.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String url_e = NEWS.get(4).getUrl();
                                    Intent i = new Intent(Intent.ACTION_VIEW);
                                    i.setData(Uri.parse(url_e));
                                    startActivity(i);
                                }
                            });



                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SpaceNewsUpdates.this, "Error on GET", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        System.out.println("error");
                        Toast.makeText(SpaceNewsUpdates.this, "Error on GET", Toast.LENGTH_LONG).show();
                    }
                });

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);



    }
    //END Read More
    public void readMore(){
        View view = getLayoutInflater().inflate(R.layout.news_card, null);
        linearLayout.addView(view);

    }
}