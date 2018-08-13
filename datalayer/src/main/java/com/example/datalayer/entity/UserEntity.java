package com.example.datalayer.entity;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * UserEntity used in the data layer.
 */
public class UserEntity extends RealmObject {
    public UserEntity() {
    }

    public UserEntity(String id) {
        this.id = id;
    }

    public UserEntity(Username username, String id, String nsid, int iconserver, int iconfarm) {

        this.username = username;
        this.id = id;
        this.nsid = nsid;
        this.iconserver = iconserver;
        this.iconfarm = iconfarm;
    }

    public Username getUsername() {
        return username;
    }

    public void setUsername(Username username) {
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

    @SerializedName("username")
    Username username;
    @PrimaryKey
    @SerializedName("id")
    String id;
    @SerializedName("nsid")
    String nsid;
    @SerializedName("iconserver")
    int iconserver;
    @SerializedName("iconfarm")
    int iconfarm;

}
