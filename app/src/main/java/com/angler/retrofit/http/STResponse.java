package com.angler.retrofit.http;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Mathan on 12-02-2019.
 */

public class STResponse implements Serializable {

    @SerializedName("Response")
    public STResponse response;

    @SerializedName("response")
    public String response_message;

    @SerializedName("response_code")
    public int response_code;

}
