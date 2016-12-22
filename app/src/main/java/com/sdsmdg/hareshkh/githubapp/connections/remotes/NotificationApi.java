package com.sdsmdg.hareshkh.githubapp.connections.remotes;

import com.sdsmdg.hareshkh.githubapp.connections.models.notification.NotificationModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NotificationApi {

    String BASE_URL = "https://api.github.com/";

    @GET("notifications")
    Call<List<NotificationModel>> getNotificationModel(@Query("access_token") String token);

    class Factory {
        public static NotificationApi service;

        public static NotificationApi getInstance() {
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build();
                service = retrofit.create(NotificationApi.class);
                return service;
            } else {
                return service;
            }
        }
    }
}
