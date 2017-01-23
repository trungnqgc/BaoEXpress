package com.example.merrychistmasnguyenquangtrung2016.docbaoexpress.model;

/**
 * Created by dell on 1/20/2017.
 */

public class NewsModel {
    private String title;
    private String path;
    private String imagePath;

    public NewsModel(String title, String path, String imagePath) {
        this.title = title;
        this.path = path;
        this.imagePath = imagePath;
    }

    public NewsModel() {
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
