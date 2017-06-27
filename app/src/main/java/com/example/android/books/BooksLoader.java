package com.example.android.books;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by lukas on 6/26/17.
 */

public class BooksLoader extends AsyncTaskLoader<List<Books>> {
    String url="";
   public BooksLoader(Context context, String url){
       super(context);
       this.url=url;
   }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Books> loadInBackground() {
        Network n = new Network();
        List<Books> books = n.fetchList(url);
        return books;
    }
}
