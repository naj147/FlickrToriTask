package com.example.domainlayer.model;

import java.util.List;


/**
 * Class that represents a Photos in the domain layer.
 */
public class Photos {
    private int page;
    private int pages;
    private int total;
    private String lastUpdated;
    private List<Photo> photo;

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Photos(int page, int pages, int total, String lastUpdated, List<Photo> photo) {
        this.page = page;
        this.pages = pages;
        this.total = total;
        this.lastUpdated = lastUpdated;
        this.photo = photo;
    }

    public Photos() {

    }

    public List<Photo> getPhoto() {
        return photo;
    }

    public void setPhoto(List<Photo> photo) {
        this.photo = photo;
    }

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

}
