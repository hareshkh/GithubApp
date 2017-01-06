package com.sdsmdg.hareshkh.githubapp.helpers;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class NotificationListItem {
    private String type;
    private String title;
    private String repositoryName;
    private String repositoryOwnerName;
    private Date date;

    public NotificationListItem(String type, String title, String repositoryName,
                                String repositoryOwnerName, String date) {
        this.type = type;
        this.title = title;
        this.repositoryName = repositoryName;
        this.repositoryOwnerName = repositoryOwnerName;

        String datePattern = "yyyy-MM-dd'T'hh:mm:ss'Z'";
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        try {
            this.date = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public String getRepositoryOwnerName() {
        return repositoryOwnerName;
    }

    public Date getDate() {
        return date;
    }
}
