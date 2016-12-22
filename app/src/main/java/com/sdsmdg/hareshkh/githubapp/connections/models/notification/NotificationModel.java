package com.sdsmdg.hareshkh.githubapp.connections.models.notification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("unread")
    @Expose
    private Boolean unread;
    @SerializedName("reason")
    @Expose
    private String reason;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("last_read_at")
    @Expose
    private Object lastReadAt;
    @SerializedName("subject")
    @Expose
    private Subject subject;
    @SerializedName("repository")
    @Expose
    private Repository repository;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("subscription_url")
    @Expose
    private String subscriptionUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getUnread() {
        return unread;
    }

    public void setUnread(Boolean unread) {
        this.unread = unread;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getLastReadAt() {
        return lastReadAt;
    }

    public void setLastReadAt(Object lastReadAt) {
        this.lastReadAt = lastReadAt;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSubscriptionUrl() {
        return subscriptionUrl;
    }

    public void setSubscriptionUrl(String subscriptionUrl) {
        this.subscriptionUrl = subscriptionUrl;
    }
}
