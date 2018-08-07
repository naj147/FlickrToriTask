package com.example.datalayer.entity;

import com.example.datalayer.net.Exclude;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class PhotosEntity extends RealmObject {
    @Exclude
    @PrimaryKey
    String id;
    // page="2" pages="10" perpage="100" total="1000"
    @SerializedName("page")
    private int page;
    @SerializedName("pages")
    private int pages;
    @SerializedName("total")
    private int total;
    @Exclude
    private String lastUpdated;
    @SerializedName("photo")
    private RealmList<PhotoEntity> photo;

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

    public PhotosEntity() {
        this.lastUpdated = String.valueOf(System.currentTimeMillis());

    }

    public PhotosEntity(String id, int page, int pages, int total, RealmList<PhotoEntity> photo) {
        this.id = id;
        this.page = page;
        this.pages = pages;
        this.total = total;
        this.photo = photo;
        this.lastUpdated = String.valueOf(System.currentTimeMillis());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RealmList<PhotoEntity> getPhoto() {
        return photo;
    }

    public void setPhoto(RealmList<PhotoEntity> photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "ID :" + this.getId()+ "\n Page :" + this.getPage()+ "\n Pages :" + this.getPages() +"\n";
    }
}