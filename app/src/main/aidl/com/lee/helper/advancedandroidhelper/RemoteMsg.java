package com.lee.helper.advancedandroidhelper;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.lee.helper.advancedandroidhelper.service.MyRemoteService;

public class RemoteMsg implements Parcelable {
    private int id;
    private String name;

    //注意要跟writeToParcel顺序一致，先读name后读id
    protected RemoteMsg(Parcel in) {
        this.name = in.readString();
        this.id = in.readInt();
        Log.e(MyRemoteService.TAG,"in.readInt() = "+in.readInt()+" in.readString()="+in.readString());
    }

    public RemoteMsg(int id ,String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "id = "+id +" name = "+name+" hash = "+hashCode();
    }

    public static final Creator<RemoteMsg> CREATOR = new Creator<RemoteMsg>() {
        @Override
        public RemoteMsg createFromParcel(Parcel in) {
            return new RemoteMsg(in);
        }

        @Override
        public RemoteMsg[] newArray(int size) {
            return new RemoteMsg[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(id);
        Log.e(MyRemoteService.TAG,"writeString(name) = "+name+" dest.writeInt(id) = "+id);
    }
}
