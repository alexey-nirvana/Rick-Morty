package com.example.rickmorty;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

class DownloadJson extends AsyncTask<String, Void, String> {

    ArrayList<String> arrayListName = new ArrayList<>();
    ArrayList<String> arrayListImage = new ArrayList<>();

    @Override
    protected String doInBackground(String... strings) {
        URL url;
        HttpURLConnection urlConnection = null;
        StringBuilder result = new StringBuilder();
        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                result.append(line);
                line = reader.readLine();
            }
            return String.valueOf(result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }

    @Override
    public void onPostExecute(String s) {
        super.onPostExecute(s);

         //  ArrayList<String> arrayListName = new ArrayList<>();
         //  ArrayList<String> arrayListImage = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            // JSONObject one = jsonArray.getJSONObject(1);

            for (int i = 0; i <= 20; i++) {
                JSONObject one = jsonArray.getJSONObject(i);
                String name = one.getString("name");
                String image = one.getString("image");
                Log.wtf("Name", name);
                Log.wtf("Image", image);

                arrayListName.add(name);
                arrayListImage.add(image);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<String> getName() {
        return arrayListName;
    }

}