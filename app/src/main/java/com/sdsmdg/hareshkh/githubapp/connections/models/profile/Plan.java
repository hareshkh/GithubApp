package com.sdsmdg.hareshkh.githubapp.connections.models.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Plan {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("space")
    @Expose
    private Integer space;
    @SerializedName("collaborators")
    @Expose
    private Integer collaborators;
    @SerializedName("private_repos")
    @Expose
    private Integer privateRepos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSpace() {
        return space;
    }
}
