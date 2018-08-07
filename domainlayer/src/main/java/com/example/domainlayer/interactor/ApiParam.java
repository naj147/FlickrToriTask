package com.example.domainlayer.interactor;

public class ApiParam {

    //int method,String title, String tags
    int method;
    String title;
    String tags;

    public ApiParam() {
        this.method=0;
    }

    public ApiParam(int method, String title, String tags) {
        this.method = method;
        this.title = title;
        this.tags = tags;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}

