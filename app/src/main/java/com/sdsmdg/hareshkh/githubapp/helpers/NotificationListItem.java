package com.sdsmdg.hareshkh.githubapp.helpers;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

        String datePattern = "yyyy-mm-dd hh:mm:ss";
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdt = new SimpleDateFormat(datePattern);
        String dateString = date.substring(0, 10) + " " + date.substring(11, 19);

        try {
            this.date = sdt.parse(dateString);
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
