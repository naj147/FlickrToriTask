package com.example.domainlayer.interactor;

public class ApiParam {

    //int method,String param1, String param2
    int method;
    String param1;
    int param2;

    public ApiParam() {
        this.method=0;
    }

    public ApiParam(int method, String param1, int param2) {
        this.method = method;
        this.param1 = param1;
        this.param2 = param2;
    }


    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public int getParam2() {
        return param2;
    }

    public void setParam2(int param2) {
        this.param2 = param2;
    }
}

