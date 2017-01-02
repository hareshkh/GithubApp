package com.sdsmdg.hareshkh.githubapp.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.sdsmdg.hareshkh.githubapp.LoginActivity;
import com.sdsmdg.hareshkh.githubapp.R;
import com.sdsmdg.hareshkh.githubapp.connections.models.notification.NotificationModel;
import com.sdsmdg.hareshkh.githubapp.connections.remotes.NotificationApi;
import com.sdsmdg.hareshkh.githubapp.helpers.NotificationListAdapter;
import com.sdsmdg.hareshkh.githubapp.helpers.NotificationListItem;
import com.sdsmdg.hareshkh.githubapp.helpers.ProgressDialogHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notifications extends Fragment {

    ListView notifListView;
    ProgressDialogHelper dialogHelper;

    public Notifications() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflatedView = inflater.inflate(R.layout.fragment_notifications, container, false);
        dialogHelper = new ProgressDialogHelper(getContext());
        notifListView = (ListView) inflatedView.findViewById(R.id.notif_list);
        dialogHelper.showDialog();
        populateList();
        return inflatedView;
    }

    public void populateList() {
        NotificationApi.Factory.getInstance().getNotificationModel(LoginActivity.oAuthToken).enqueue(
                new Callback<List<NotificationModel>>() {
                    @Override
                    public void onResponse(Call<List<NotificationModel>> call, Response<List<NotificationModel>> response) {
                        List<NotificationModel> responseList = response.body();
                        ArrayList<NotificationListItem> notifList = new ArrayList<>();
                        for (NotificationModel n : responseList) {
                            notifList.add(new NotificationListItem(
                                    n.getSubject().getType(),
                                    n.getSubject().getTitle(),
                                    n.getRepository().getName(),
                                    n.getRepository().getOwner().getLogin(),
                                    n.getUpdatedAt()
                            ));
                        }
                        NotificationListAdapter adapter = new NotificationListAdapter(getActivity(), notifList);
                        notifListView.setAdapter(adapter);
                        dialogHelper.hideDialog();
                    }

                    @Override
                    public void onFailure(Call<List<NotificationModel>> call, Throwable t) {
                        dialogHelper.hideDialog();
                    }
                }
        );
    }
}
