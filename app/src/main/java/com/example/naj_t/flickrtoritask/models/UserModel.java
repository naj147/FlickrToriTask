package com.example.naj_t.flickrtoritask.models;

/**
 * Class that represents a User in the presentation layer.
 */
public class UserModel {
    private String username;
    private String id;
    private String nsid;
    private int iconserver;
    private int iconfarm;

    public UserModel() {
    }

    public UserModel(String id) {
        this.id = id;
    }

    public UserModel(String username, String id, String nsid, int iconserver, int iconfarm) {
        this.username = username;
        this.id = id;
        this.nsid = nsid;
        this.iconserver = iconserver;
        this.iconfarm = iconfarm;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNsid() {
        return nsid;
    }

    public void setNsid(String nsid) {
        this.nsid = nsid;
    }

    public int getIconserver() {
        return iconserver;
    }

    public void setIconserver(int iconserver) {
        this.iconserver = iconserver;
    }

    public int getIconfarm() {
        return iconfarm;
    }

    public void setIconfarm(int iconfarm) {
        this.iconfarm = iconfarm;
    }
}
