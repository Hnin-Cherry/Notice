package com.example.winlwinoo.notice;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Win Lwin Oo on 5/26/2017.
 */

public class Memo implements Serializable {

    private Date date;
    private String text;
    private boolean fullDisplayed;
    private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy 'at' hh:mm aaa");

    public Memo(){
        this.date = new Date();

    }

    public Memo(long time , String text){
        this.date = new Date(time);
        this.text = text ;
    }

    public String getDate(){
        return dateFormat.format(date);
    }

    public long getTime(){
        return date.getTime();
    }

    public void setTime(long time){
        this.date = new Date(time);
    }

    public void setText (String text){
        this.text = text ;
    }

    public String getText(){
        return this.text;
    }

    public String getShortText(){

        String temp = text.replaceAll("\n" , " ");
        if (temp.length() > 25){
            return temp.substring(0 , 25) + "...";
        }
        else {
            return temp;
        }
    }

    public void setFullDisplayed(boolean fulldisplayed){

        this.fullDisplayed = fulldisplayed ;
    }

    public boolean isFullDisplayed(){
        return this.fullDisplayed;
    }

    @Override
    public String toString() {
        return this.text;
    }
}

