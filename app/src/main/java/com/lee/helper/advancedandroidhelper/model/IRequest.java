package com.lee.helper.advancedandroidhelper.model;

import java.util.Map;

public interface IRequest<T> {
    String getApi();
    Map<String,T> getParams();
    Map<String , String> getHeader();
}
