package com.sdsmdg.hareshkh.githubapp.helpers;

public class RepositoryListItem {
    private boolean isForked;
    private String name;
    private String owner;
    private int starCount;

    public RepositoryListItem(boolean isForked, String name, String owner, int starCount) {
        this.isForked = isForked;
        this.name = name;
        this.owner = owner;
        this.starCount = starCount;
    }

    public boolean isForked() {
        return isForked;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public int getStarCount() {
        return starCount;
    }
}
