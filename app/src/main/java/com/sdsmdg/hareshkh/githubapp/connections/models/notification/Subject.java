package com.sdsmdg.hareshkh.githubapp.connections.models.notification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subject {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("latest_comment_url")
    @Expose
    private String latestCommentUrl;
    @SerializedName("type")
    @Expose
    private String type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLatestCommentUrl() {
        return latestCommentUrl;
    }

    public void setLatestCommentUrl(String latestCommentUrl) {
        this.latestCommentUrl = latestCommentUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
