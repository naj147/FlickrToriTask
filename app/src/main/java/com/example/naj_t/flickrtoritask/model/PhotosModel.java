package com.example.naj_t.flickrtoritask.model;

import java.util.List;

public class PhotosModel {

    private int page;
    private int pages;
    private int total;
    private String lastUpdated;
    private List<PhotoModel> photo;

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

    public PhotosModel(int page, int pages, int total, String lastUpdated, List<PhotoModel> photo) {
        this.page = page;
        this.pages = pages;
        this.total = total;
        this.lastUpdated = lastUpdated;
        this.photo = photo;
    }

    public PhotosModel() {

    }

    public List<PhotoModel> getPhoto() {
        return photo;
    }

    public void setPhoto(List<PhotoModel> photo) {
        this.photo = photo;
    }
}
