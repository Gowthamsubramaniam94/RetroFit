package com.angler.retrofit;

import android.content.Context;

import com.angler.retrofit.http.RetroClient;
import com.angler.retrofit.model.Model;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


class Webservices {

    private static final String BASE_URL = "http://192.168.0.13/showtime_club/admin_live/scripts/5/v2/";
    private static Retrofit retrofit = null;

    private static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private interface ApiInterface {

        // Adding Param in URL
        @GET("eo_webservice_v3.php?Case=clubLogin")
        Call<Model.SIGGroup> login(@Query("user_name") String username,
                                   @Query("password") String password);

        // Adding Param with Header
        @GET("eo_webservice_v3.php?Case=clubLogin")
        Call<Model.SIGGroup> header(@QueryMap Map<String, String> mParams,
                                    @HeaderMap Map<String, String> mHeaders);

        // Adding Param
        @GET("eo_webservice_v3.php?Case=SIGGroupList")
        Call<Model.SIGGroup> param(@QueryMap Map<String, String> params);

        // JSON Request
        @POST("eo_webservice_v3.php?Case=SIGGroupList")
        Call<Object> jsonRequest(@Body Object body);

        // Multipart
        @Multipart
        @POST("/api/fileupload")
        Call<ResponseBody> upload(@Part MultipartBody.Part file, @Query("description") String description);

    }

    static void login(Context context) {
        try {
            ApiInterface apiService = getClient().create(ApiInterface.class);

            Call<Model.SIGGroup> call = apiService.login("daisy@aol.com", "Eosouthasia@17");

            RetroClient.execute(context, call);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void upload(Context context) {
        try {
            ApiInterface apiService = getClient().create(ApiInterface.class);

            File file = new File("");
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
            Call<ResponseBody> call = apiService.upload(body, "");
            RetroClient.execute(context, call);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void json(Context context) {
        try {
            ApiInterface apiService = getClient().create(ApiInterface.class);
            JSONObject jsonObject = new JSONObject();
            Call<Object> call = apiService.jsonRequest(jsonObject);

            RetroClient.execute(context, call);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static void getSIGGroupList(Context context) {
        try {
            ApiInterface apiService = getClient().create(ApiInterface.class);

            HashMap<String, String> mParam = new HashMap<>();
            mParam.put("event_id", "");
            mParam.put("user_name", "daisy@aol.com");
            mParam.put("password", "Eosouthasia@17");
            mParam.put("global_event_id", "2");
            mParam.put("change_chapter_status", "");

            Call<Model.SIGGroup> call = apiService.param(mParam);

            RetroClient.execute(context, call);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
