package com.angler.retrofit.http;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetroClient {
    private static Listener listener;

    public static <ResponseClass> void execute(Context context, final Call<ResponseClass> call) {
        listener = (Listener) context;
        try {
            call.enqueue(new Callback<ResponseClass>() {
                @Override
                public void onResponse(@SuppressWarnings("NullableProblems") Call<ResponseClass> call,
                                       @SuppressWarnings("NullableProblems") Response<ResponseClass> response) {
                    Log.e("Response --> ", new Gson().toJson(response.body()));
                    listener.onResponse(response);
                }

                @Override
                public void onFailure(@SuppressWarnings("NullableProblems") Call<ResponseClass> call,
                                      @SuppressWarnings("NullableProblems") Throwable t) {
                    Log.e("Response Error --> ", t.getMessage());
                    listener.onResponse(t);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
