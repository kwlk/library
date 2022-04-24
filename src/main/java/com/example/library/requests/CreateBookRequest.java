package com.example.library.requests;

public class CreateBookRequest {
    private final String title;
    private final String author;
    private final int year;

    public CreateBookRequest(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }
}
