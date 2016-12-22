package com.sdsmdg.hareshkh.githubapp.connections.remotes;

import com.sdsmdg.hareshkh.githubapp.Login;
import com.sdsmdg.hareshkh.githubapp.connections.models.oauth.OauthModel;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface OauthApi {

    String BASE_URL = Login.TOKEN_GH_URL;

    @FormUrlEncoded
    @POST("access_token")
    Call<OauthModel> getOauthModel(
            @Field("client_id") String id,
            @Field("client_secret") String secret,
            @Field("code") String code,
            @Field("redirect_uri") String uri,
            @Field("state") String state,
            @Header("Accept") String type
    );

    class Factory {
        public static OauthApi service;

        public static OauthApi getInstance() {
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build();
                service = retrofit.create(OauthApi.class);
                return service;
            } else {
                return service;
            }
        }
    }
}
