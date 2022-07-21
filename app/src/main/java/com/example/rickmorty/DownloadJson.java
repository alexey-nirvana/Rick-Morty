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
import java.util.List;

class DownloadJson extends AsyncTask<String, Void, List<CharacterModel>> {

    private DownloadJsonCompleteListener completeListener;

    public DownloadJson(DownloadJsonCompleteListener completeListener) {
        super();
        this.completeListener = completeListener;
    }

    @Override
    protected List<CharacterModel> doInBackground(String... strings) {
        URL url;
        HttpURLConnection urlConnection = null;
        StringBuilder result = new StringBuilder();

        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String jsonString = reader.readLine();
            while (jsonString != null) {
                result.append(jsonString);
                jsonString = reader.readLine();
            }
            return parseJsonToListOfNames(result.toString());

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
    protected void onPostExecute(List<CharacterModel> characterModels) {
        super.onPostExecute(characterModels);
        completeListener.onComplete(characterModels);
    }

    private List<CharacterModel> parseJsonToListOfNames(String jsonString) {
        ArrayList<CharacterModel> modelsList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            for (int i = 0; i <= jsonArray.length() - 1; i++) {
                JSONObject one = jsonArray.getJSONObject(i);
                String name = one.getString("name");
                String image = one.getString("image");
                CharacterModel model = new CharacterModel(name, image);
                modelsList.add(model);
            }

            return modelsList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    interface DownloadJsonCompleteListener{
        void onComplete(List<CharacterModel> models);
    }
}