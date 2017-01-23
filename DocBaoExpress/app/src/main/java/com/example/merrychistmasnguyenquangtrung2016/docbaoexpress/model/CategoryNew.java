package com.example.merrychistmasnguyenquangtrung2016.docbaoexpress.model;

/**
 * Created by dell on 1/20/2017.
 */

public class CategoryNew {
    private String title;
    private String path;

    public CategoryNew() {
    }

    public CategoryNew(String title, String path) {
        this.title = title;
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
