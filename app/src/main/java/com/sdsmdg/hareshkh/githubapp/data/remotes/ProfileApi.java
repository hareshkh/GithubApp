package com.sdsmdg.hareshkh.githubapp.data.remotes;

import com.sdsmdg.hareshkh.githubapp.data.models.profile.ProfileModel;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ProfileApi {

    String BASE_URL = "https://api.github.com/users/";

    @GET("hareshkh")
    Call<ProfileModel> getProfileModel();

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
