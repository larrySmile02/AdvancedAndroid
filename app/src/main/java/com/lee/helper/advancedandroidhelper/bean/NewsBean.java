package com.lee.helper.advancedandroidhelper.bean;

import com.google.gson.annotations.SerializedName;

public class NewsBean {


    @SerializedName(value = "reason",alternate = {"reason_suc","reason_fail"})
    private String Reason; //":"成功的返回",
    private NewsResult result;

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        this.Reason = reason;
    }

    public NewsResult getResult() {
        return result;
    }

    public void setResult(NewsResult result) {
        this.result = result;
    }
}
