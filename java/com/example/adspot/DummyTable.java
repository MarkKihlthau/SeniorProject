package com.example.adspot;

import android.content.Context;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.DateTimeOffset;

public class DummyTable {

    private static MobileServiceClient mClient;
    private static Context mCon;
    private static DummyTable mInstance = null;

    @com.google.gson.annotations.SerializedName("id")
    private String mId;
    public String getId() { return mId; }
    public final void setId(String id) { mId = id; }

    @com.google.gson.annotations.SerializedName("username")
    private String mUsername;
    public String getUsername() { return mUsername; }
    protected void setUsername(String text) { mUsername = text; }

    @com.google.gson.annotations.SerializedName("password")
    private String mPassword;
    public String getPassword() { return mPassword; }
    protected void setPassword(String text) { mPassword = text; }

    @com.google.gson.annotations.SerializedName("createdAt")
    private DateTimeOffset mCreatedAt;
    public DateTimeOffset getCreatedAt() { return mCreatedAt; }
    protected void setCreatedAt(DateTimeOffset createdAt) { mCreatedAt = createdAt; }

    @com.google.gson.annotations.SerializedName("updatedAt")
    private DateTimeOffset mUpdatedAt;
    public DateTimeOffset getUpdatedAt() { return mUpdatedAt; }
    protected void setUpdatedAt(DateTimeOffset updatedAt) { mUpdatedAt = updatedAt; }

    @com.google.gson.annotations.SerializedName("version")
    private String mVersion;
    public String getVersion() { return mVersion; }
    public final void setVersion(String version) { mVersion = version; }

    @com.google.gson.annotations.SerializedName("CustomerID")
    private int mCustomerID;
    public int getCustomerID() { return mCustomerID; }
    public final void setCustomerID(int customerID) { mCustomerID = customerID; }


    public DummyTable() {}

    public DummyTable(String text, String text2) {
        //this.setId(id);
        this.setUsername(text);
        this.setPassword(text2);
    }


}
