package com.example.android.books;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukas on 6/26/17.
 */

public class BooksAdapter extends ArrayAdapter<Books> {
    public BooksAdapter(Context context, List<Books> books) {
        super(context,0 ,books);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        Books book = getItem(position);
        TextView title = (TextView) convertView.findViewById(R.id.book_title);
        title.setText(book.getTitle());
        TextView rating = (TextView) convertView.findViewById(R.id.rating);
        rating.setText(book.getRating());
        TextView authors = (TextView) convertView.findViewById(R.id.authors);
        authors.setText(book.getAuthors());

        return convertView;
    }
}
