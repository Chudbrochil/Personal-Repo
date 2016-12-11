// Anthony Galczak - agalczak@cnm.edu
// CIS 2237 - Program 6 - Reminders

package com.cis2237.reminders.galczakp6;

/**
 * Created by anthony on 11/22/2016.
 */
public class Reminder {

    private String content;
    private int important;
    private int id;

    public Reminder(){
        id = -1;
        content = "";
        important = 0;
    }

    public Reminder(int id, String content, int important)
    {
        this.id = id;
        this.content = content;
        this.important = important;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImportant() {
        return important;
    }

    public void setImportant(int important) {
        this.important = important;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
