package com.sdsmdg.hareshkh.githubapp.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdsmdg.hareshkh.githubapp.LoginActivity;
import com.sdsmdg.hareshkh.githubapp.helpers.ProgressDialogHelper;
import com.sdsmdg.hareshkh.githubapp.R;
import com.sdsmdg.hareshkh.githubapp.connections.models.profile.ProfileModel;
import com.sdsmdg.hareshkh.githubapp.connections.remotes.ProfileApi;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends Fragment {
    private TextView name, institution;
    private ImageView profilePhoto;
    private ProgressDialogHelper dialogHelper;

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
        dialogHelper = new ProgressDialogHelper(getContext());
        name = (TextView) inflatedView.findViewById(R.id.name);
        institution = (TextView) inflatedView.findViewById(R.id.institute);
        profilePhoto = (ImageView) inflatedView.findViewById(R.id.profile_photo);
        dialogHelper.showDialog();
        getData();
        return inflatedView;
    }

    public void getData() {
        ProfileApi.Factory.getInstance().getProfileModel(LoginActivity.oAuthToken).enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                name.setText(response.body().getName());
                institution.setText(response.body().getCompany());
                Picasso.with(getContext()).load(response.body().getAvatarUrl()).into(profilePhoto);
                dialogHelper.hideDialog();
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                dialogHelper.hideDialog();
            }
        });
    }
}
