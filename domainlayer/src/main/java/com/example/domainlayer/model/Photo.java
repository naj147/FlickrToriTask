package com.example.domainlayer.model;

/**
 * Class that represents a Photo in the domain layer.
 */
public class Photo {

    private String id;
    private String owner;
    private String secret;
    private String server;
    private String farm;
    private String Title;
    private int isPublic;
    private int isFriend;
    private int isFamily;

    public Photo(String id, String owner, String secret, String server, String farm, String title, int isPublic, int isFriend, int isFamily) {
        this.id = id;
        this.owner = owner;
        this.secret = secret;
        this.server = server;
        this.farm = farm;
        Title = title;
        this.isPublic = isPublic;
        this.isFriend = isFriend;
        this.isFamily = isFamily;
    }

    public Photo() {

    }

    public Photo(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getFarm() {
        return farm;
    }

    public void setFarm(String farm) {
        this.farm = farm;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(int isPublic) {
        this.isPublic = isPublic;
    }

    public int getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(int isFriend) {
        this.isFriend = isFriend;
    }

    public int getIsFamily() {
        return isFamily;
    }

    public void setIsFamily(int isFamily) {
        this.isFamily = isFamily;
    }

}
