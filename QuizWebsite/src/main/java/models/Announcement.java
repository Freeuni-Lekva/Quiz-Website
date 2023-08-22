package models;

import java.util.Date;

public class Announcement {
    private Date date;
    private String text;

    // Constructors
    public Announcement() {
        // Default constructor
    }

    public Announcement(Date date, String text) {
        this.date = date;
        this.text = text;
    }

    // Getters and Setters
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
