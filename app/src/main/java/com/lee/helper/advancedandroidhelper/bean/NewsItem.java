package com.lee.helper.advancedandroidhelper.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class NewsItem implements Parcelable {

    private String uniquekey; //":"779e0448503ff134fef798f81170b008",
    private String title; //":"亚足联：2023年亚洲杯将由中国承办",
    private String date; //":"2019-06-04 17:06",
    private String category; //":"头条",
    private String author_name; //":"央视网",
    private String url; //":"http:\/\/mini.eastday.com\/mobile\/190604170654219.html",
    private String thumbnail_pic_s;//":"http:\/\/05imgmini.eastday.com\/mobile\/20190604\/20190604170654_6fb492fbe34b25ca811121c7a7ea3c56_1_mwpm_03200403.jpg"

    protected NewsItem(Parcel in) {
        uniquekey = in.readString();
        title = in.readString();
        date = in.readString();
        category = in.readString();
        author_name = in.readString();
        url = in.readString();
        thumbnail_pic_s = in.readString();
    }


    public static final Creator<NewsItem> CREATOR = new Creator<NewsItem>() {
        @Override
        public NewsItem createFromParcel(Parcel in) {
            return new NewsItem(in);
        }

        @Override
        public NewsItem[] newArray(int size) {
            return new NewsItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uniquekey);
        dest.writeString(title);
        dest.writeString(date);
        dest.writeString(category);
        dest.writeString(author_name);
        dest.writeString(url);
        dest.writeString(thumbnail_pic_s);
    }

    public String getUniquekey() {
        return uniquekey;
    }

    public void setUniquekey(String uniquekey) {
        this.uniquekey = uniquekey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail_pic_s() {
        return thumbnail_pic_s;
    }

    public void setThumbnail_pic_s(String thumbnail_pic_s) {
        this.thumbnail_pic_s = thumbnail_pic_s;
    }


    @Override
    public String toString() {
        return "uniquekey : "+
                uniquekey+
                " title : "+
                title+
                " date : "+
                " category : "+
                " author_namef : "+
                author_name+
                " url : "+
                url+
                " thumbnail_pic_s : "+
                thumbnail_pic_s;
    }
}
