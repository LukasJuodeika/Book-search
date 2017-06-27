package com.example.android.books;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukas on 6/27/17.
 */

public class Network {

    private final String TAG = Network.class.getName();

    public ArrayList<Books> fetchList(String sUrl){
        ArrayList<Books> myBooks;
        URL url = getUrl(sUrl);
        String jsonString = getJsonString(url);
        myBooks = getMyList(jsonString);

        return myBooks;
    }

    public String getJsonString(URL url)
    {
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
        }
        catch(IOException e)
        {
            Log.e(TAG,"Bad convert httpurl",e);
        }
        httpURLConnection.setReadTimeout(10000);
        httpURLConnection.setConnectTimeout(15000);
        InputStream is = null;
        String jString = "";
        try {
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            is = httpURLConnection.getInputStream();
            jString = readFromStream(is);
        }
        catch (IOException e)
        {
            Log.e(TAG,"Unable to connect",e);
        }


        return jString;
    }




    public URL getUrl(String Surl)
    {
        URL url = null;
        try {
            url = new URL(Surl);
        }
        catch (MalformedURLException e)
        {
            Log.e(TAG,"Wrong URL string",e);
        }
        return url;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public ArrayList<Books> getMyList(String jsonString)
    {

        ArrayList<Books> books = new ArrayList<>();
        try {
            JSONObject myObject = new JSONObject(jsonString);
            JSONArray array = myObject.getJSONArray("items");
            JSONObject volume;
            for( int i = 0; i<array.length(); i++)
            {
                volume = array.getJSONObject(i).getJSONObject("volumeInfo");
                double avg;
                try{
                    avg = volume.getDouble("averageRating");
                }
                catch (Exception e)
                {
                    avg= -1;
                }
                JSONArray authors = volume.getJSONArray("authors");
                String authorsString = "";
                for(int j = 0; j < authors.length(); j++){
                    authorsString+=authors.get(j);
                }


                if(avg <0 || avg>5)
                    books.add(new Books("n/a",volume.getString("title"),authorsString));
                else
                    books.add(new Books(avg+"",volume.getString("title"),authorsString));



            }

        }
        catch (JSONException e)
        {
            Log.e(TAG,"Unable to create JSONObject",e);
        }

        return books;
    }
}
