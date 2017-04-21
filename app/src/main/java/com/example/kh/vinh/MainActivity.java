package com.example.kh.vinh;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.example.kh.vinh.Module.Movies;
import com.example.kh.vinh.Module.MyAdapter;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private GridView grid;
 private   ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading, please wait...");


    }

    private class LoadData extends AsyncTask<String, Integer, ArrayList<Movies>> {
     private    HttpURLConnection con;
       private Movies m;
      private  ArrayList<Movies.cast> castArrayList;
        private   BufferedReader reader;
        private   ArrayList<Movies> arrayList = new ArrayList<Movies>();
        private  String jsonString;
        @Override
        protected ArrayList<Movies> doInBackground(String... params) {
            try {
                URL u = new URL(params[0]);
                con = (HttpURLConnection)u.openConnection();
                InputStream input = con.getInputStream();
                reader = new BufferedReader(new InputStreamReader(input));
                String line;
                StringBuffer buffer = new StringBuffer();
                while((line = reader.readLine())!=null){
                    buffer.append(line);
                }
                 jsonString = buffer.toString();

                JSONObject root = new JSONObject(jsonString);
                JSONArray arrayroot = root.getJSONArray("movies");
                Gson gson = new Gson();
                for(int i = 0 ; i<arrayroot.length();i++){
                    JSONObject child = arrayroot.getJSONObject(i);
                 //    m=  new Movies();

              m=  gson.fromJson(child.toString(),Movies.class);
//                    m.setYear(child.getInt("year"));
//                    m.setMovie(child.getString("movie"));
//                    m.setRating(child.getDouble("rating")/2);
//                    m.setDuration( child.getString("duration"));
//                    m.setDuration( child.getString("director"));
//                    m.setTagline( child.getString("tagline"));
//                    m.setImage(child.getString("image"));
//                    m.setStory(child.getString("story"));
//
//                    JSONArray arraycast = child.getJSONArray("cast");
//                    castArrayList = new ArrayList<Movies.cast>();
//                    for(int j = 0 ; j<arraycast.length();j++){
//                        JSONObject childcast = arraycast.getJSONObject(j);
//                        String name = childcast.getString("name");
//                       Movies.cast c = new Movies.cast();
//                        c.setName(name);
//
//                        castArrayList.add(c);
//                    }
//                    m.setCast(castArrayList);
                   arrayList.add(m);

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }finally {
                if(reader!=null)
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(con!=null)
                        con.disconnect();
            }


            return arrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<Movies> s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
           // Toast.makeText(MainActivity.this,s, Toast.LENGTH_SHORT).show();
            grid = (GridView) findViewById(R.id.grid);
           // ArrayList<Movies> arrayList = new ArrayList<Movies>();
          //  arrayList.add(new Movies("vinh",1994));
          //  Toast.makeText(MainActivity.this, s.get(0).getCast().get(0).getName(), Toast.LENGTH_SHORT).show();
            MyAdapter adapter = new MyAdapter(MainActivity.this, R.layout.list_main,s);
            grid.setAdapter(adapter);
         //   Toast.makeText(MainActivity.this, arrayList.get(0).getDirector(), Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.row_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.settings:{
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new LoadData().execute("https://jsonparsingdemo-cec5b.firebaseapp.com/jsonData/moviesData.txt");
                    }
                });
            }
            break;
        }

        return super.onOptionsItemSelected(item);
    }
}
