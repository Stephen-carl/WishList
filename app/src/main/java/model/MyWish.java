package model;

import java.text.DateFormat;

public class MyWish {
    //to create the getter and setter
    //try to make sure that the objects here are te same with the columns name
    public String title;
    public String content;
    public String recorddate;

    //constructor
    public MyWish(String title, String content, String recorddate) {
        this.title = title;
        this.content = content;
        this.recorddate = recorddate;
    }

    //empty constructor in case it is needed
    public MyWish() {

    }

    @Override
    public String toString() {
        return "MyWish{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", recorddate='" + recorddate + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRecorddate() {
        return recorddate;
    }

    public void setRecorddate(String recorddate) {
        this.recorddate = recorddate;
    }
}
