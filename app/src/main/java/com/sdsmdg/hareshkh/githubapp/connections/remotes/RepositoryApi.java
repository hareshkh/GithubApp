package com.sdsmdg.hareshkh.githubapp.connections.remotes;

import com.sdsmdg.hareshkh.githubapp.Login;
import com.sdsmdg.hareshkh.githubapp.connections.models.repository.RepositoryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RepositoryApi {

    String BASE_URL = "https://api.github.com/user/";

    @GET("repos")
    Call<List<RepositoryModel>> getRepositryModel(
            @Query("access_token") String token,
            @Query(value = "affiliation", encoded = true) String affiliations
    );

    class Factory {
        public static RepositoryApi service;

        public static RepositoryApi getInstance() {
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build();
                service = retrofit.create(RepositoryApi.class);
                return service;
            } else {
                return service;
            }
        }
    }
}
