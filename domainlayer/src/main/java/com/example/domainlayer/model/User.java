package com.example.domainlayer.model;


/**
 * Class that represents a User in the domain layer.
 */
public class User {

    private String username;
    private String id;
    private String nsid;
    private int iconserver;
    private int iconfarm;

    public User(String username, String id, String nsid, int iconserver, int iconfarm) {
        this.username = username;
        this.id = id;
        this.nsid = nsid;
        this.iconserver = iconserver;
        this.iconfarm = iconfarm;
    }

    public User() {
    }

    public User(String userId) {
        this.id=userId;
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
