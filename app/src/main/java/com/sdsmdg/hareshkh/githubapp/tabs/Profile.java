package com.sdsmdg.hareshkh.githubapp.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdsmdg.hareshkh.githubapp.Login;
import com.sdsmdg.hareshkh.githubapp.R;
import com.sdsmdg.hareshkh.githubapp.data.models.profile.ProfileModel;
import com.sdsmdg.hareshkh.githubapp.data.remotes.ProfileApi;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends Fragment {
    private TextView name, place;
    private ImageView profilePhoto;

    public Profile() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_profile, container, false);
        name = (TextView) inflatedView.findViewById(R.id.name);
        place = (TextView) inflatedView.findViewById(R.id.place);
        profilePhoto = (ImageView) inflatedView.findViewById(R.id.profilePhoto);
        change();
        return inflatedView;
    }

    public void change() {
        ProfileApi.Factory.getInstance().getProfileModel(Login.oAuthToken).enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                name.setText(response.body().getName());
                place.setText(response.body().getLocation());
                Picasso.with(getContext()).load(response.body().getAvatarUrl()).into(profilePhoto);
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {

            }
        });
    }

}
