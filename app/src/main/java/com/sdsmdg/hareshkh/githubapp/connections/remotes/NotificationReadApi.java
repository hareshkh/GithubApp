package com.sdsmdg.hareshkh.githubapp.connections.remotes;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NotificationReadApi {

    String BASE_URL = "https://api.github.com/notifications/";

    @PATCH("threads/{id}")
    Call<String> markNotificationRead(@Path("id") String id, @Query("access_token") String token);

    class Factory {
        public static NotificationReadApi service;

        public static NotificationReadApi getInstance() {
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                        addConverterFactory(ScalarsConverterFactory.create()).build();
                service = retrofit.create(NotificationReadApi.class);
                return service;
            } else {
                return service;
            }
        }
    }
}
