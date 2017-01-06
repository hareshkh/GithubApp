package com.sdsmdg.hareshkh.githubapp.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.sdsmdg.hareshkh.githubapp.LoginActivity;
import com.sdsmdg.hareshkh.githubapp.R;
import com.sdsmdg.hareshkh.githubapp.connections.models.repository.RepositoryModel;
import com.sdsmdg.hareshkh.githubapp.connections.remotes.RepositoryApi;
import com.sdsmdg.hareshkh.githubapp.helpers.ProgressDialogHelper;
import com.sdsmdg.hareshkh.githubapp.helpers.RepositoryListAdapter;
import com.sdsmdg.hareshkh.githubapp.helpers.RepositoryListItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repositories extends Fragment {

    ListView repoListView;
    ProgressDialogHelper dialogHelper;
    String affiliations = "owner%2Ccollaborator";

    public Repositories() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflatedView = inflater.inflate(R.layout.fragment_repositories, container, false);
        dialogHelper = new ProgressDialogHelper(getContext());
        repoListView = (ListView) inflatedView.findViewById(R.id.repo_list);
        dialogHelper.showDialog();
        populateList();
        return inflatedView;
    }

    public void populateList() {
        RepositoryApi.Factory.getInstance().getRepositryModel(LoginActivity.oAuthToken, affiliations).enqueue(
                new Callback<List<RepositoryModel>>() {
                    @Override
                    public void onResponse(Call<List<RepositoryModel>> call, Response<List<RepositoryModel>> response) {
                        List<RepositoryModel> responseList = response.body();
                        ArrayList<RepositoryListItem> repoList = new ArrayList<>();
                        for (RepositoryModel r : responseList) {
                            repoList.add(new RepositoryListItem(
                                    r.getFork(), r.getName(), r.getOwner().getLogin(), r.getStargazersCount()
                            ));
                        }
                        RepositoryListAdapter adapter = new RepositoryListAdapter(getActivity(), repoList);
                        repoListView.setAdapter(adapter);
                        dialogHelper.hideDialog();

                    }

                    @Override
                    public void onFailure(Call<List<RepositoryModel>> call, Throwable t) {
                        dialogHelper.hideDialog();
                    }
                }
        );
    }
}
