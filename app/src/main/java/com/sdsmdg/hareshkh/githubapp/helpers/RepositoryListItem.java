package com.sdsmdg.hareshkh.githubapp.helpers;

public class RepositoryListItem {
    private boolean isForked;
    private String name;
    private String owner;

    public RepositoryListItem(boolean isForked, String name, String owner) {
        this.isForked = isForked;
        this.name = name;
        this.owner = owner;
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
}
