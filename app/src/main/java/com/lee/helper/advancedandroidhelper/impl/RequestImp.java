package com.lee.helper.advancedandroidhelper.impl;

import com.lee.helper.advancedandroidhelper.model.IRequest;

import java.util.Map;

public class RequestImp implements IRequest {

    private String api;
    private Map<String ,String> params;
    private Map<String,String>header;

    public RequestImp(String api ,Map<String ,String >params , Map<String ,String> header){
        this.api = api;
        this.params = params;
        this.header = header;
    }

    @Override
    public String getApi() {
        return api;
    }

    @Override
    public Map getParams() {
        return params;
    }

    @Override
    public Map<String, String> getHeader() {
        return header;
    }
}
