package com.angler.retrofit.model;

import com.angler.retrofit.http.STResponse;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Model {

    public static class SIGGroup extends STResponse {

        @SerializedName("details")
        public ArrayList<SIGGroup> groupDetails = new ArrayList<>();

        @SerializedName("group_id")
        public String group_id;

        @SerializedName("group_name")
        public String group_name;

        @SerializedName("description")
        public String description;

        @SerializedName("approval_type")
        public String approval_type;

        @SerializedName("user_id")
        public String user_id;

        @SerializedName("name")
        public String name;

        @SerializedName("member_profile")
        public String member_profile;

        @SerializedName("email_id")
        public String email_id;

        @SerializedName("admin")
        public String admin;

        @SerializedName("is_join")
        public String is_join;

        @SerializedName("member_status")
        public String member_status;

        public String selected_member_ids;
        public String selected_owner_type;

    }
}
