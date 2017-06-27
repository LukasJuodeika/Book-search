package com.example.android.books;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Books>>{


    private BooksAdapter adapter;
    private String REQUESTED_URL="https://www.googleapis.com/books/v1/volumes?q=harry";
    private String BASE_URL = "https://www.googleapis.com/books/v1/volumes?q=";
   //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.list);
        adapter = new BooksAdapter(this,new ArrayList<Books>());

        listView.setAdapter(adapter);
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(0, null, MainActivity.this);

       /* Button button = (Button) findViewById(R.id.search_b);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String text = edit.getText().toString();
                if(text.contains(" "))
                {
                    text = text.replaceAll(" ","+");
                }
                REQUESTED_URL+=text;

                loaderManager.initLoader(0, null, MainActivity.this);
            }
        });*/



    }


    @Override
    public Loader<List<Books>> onCreateLoader(int id, Bundle args) {
        return new BooksLoader(this,REQUESTED_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Books>> loader, List<Books> data) {
        adapter.clear();
        adapter.addAll(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Books>> loader) {
        adapter.clear();
    }
    public void clicked(View v){
        EditText edit = (EditText) findViewById(R.id.search);
        String text = edit.getText().toString();
        if(text.contains(" "))
        {
            text = text.replaceAll(" ","+");
        }

        REQUESTED_URL = BASE_URL + text;
        getLoaderManager().restartLoader(0, null, MainActivity.this);

    }

}
