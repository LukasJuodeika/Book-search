package com.example.android.books;

/**
 * Created by lukas on 6/26/17.
 */

public class Books {
    private String rating = "";
    private String title = "";
    private String authors = "";

    public Books(String rating, String title, String authors)
    {
        this.rating = rating;
        this.title = title;
        this.authors = authors;
    }

    public String getRating(){
        return rating;
    }
    public String getTitle(){
        return title;
    }
    public String getAuthors(){
        return authors;
    }
}
