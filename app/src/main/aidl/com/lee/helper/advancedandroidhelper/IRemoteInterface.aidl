// IRemoteInterface.aidl
package com.lee.helper.advancedandroidhelper;
// Declare any non-default types here with import statements
import com.lee.helper.advancedandroidhelper.RemoteMsg;
interface IRemoteInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
    RemoteMsg getMsg();
    void setMsg(in RemoteMsg msg);
}
