package com.sdsmdg.hareshkh.githubapp.connections.remotes;

import com.sdsmdg.hareshkh.githubapp.connections.models.profile.ProfileModel;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProfileApi {

    String BASE_URL = "https://api.github.com/";

    @GET("user")
    Call<ProfileModel> getProfileModel(@Query("access_token") String token);

    class Factory {
        public static ProfileApi service;

        public static ProfileApi getInstance() {
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build();
                service = retrofit.create(ProfileApi.class);
                return service;
            } else {
                return service;
            }
        }
    }
}
