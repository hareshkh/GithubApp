package com.sdsmdg.hareshkh.githubapp.helpers;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sdsmdg.hareshkh.githubapp.LoginActivity;
import com.sdsmdg.hareshkh.githubapp.R;
import com.sdsmdg.hareshkh.githubapp.connections.remotes.NotificationReadApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationListAdapter extends ArrayAdapter<NotificationListItem> {

    public NotificationListAdapter(Activity context, ArrayList<NotificationListItem> listItems) {
        super(context, 0, listItems);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.notif_list_item, parent, false);
        }

        final NotificationListItem currentNotif = getItem(position);
        ImageView icon = (ImageView) listItemView.findViewById(R.id.notif_icon);
        TextView reason = (TextView) listItemView.findViewById(R.id.notif_reason);
        TextView repoName = (TextView) listItemView.findViewById(R.id.notif_repo_name);
        TextView repoOwnerName = (TextView) listItemView.findViewById(R.id.notif_repo_owner_name);
        TextView date = (TextView) listItemView.findViewById(R.id.notif_date);
        ImageView markRead = (ImageView) listItemView.findViewById(R.id.mark_read);

        assert currentNotif != null;

        markRead.setImageResource(R.drawable.tick);
        markRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Marking as read...", Toast.LENGTH_SHORT).show();
                NotificationReadApi.Factory.getInstance().markNotificationRead(
                        currentNotif.getId(),
                        LoginActivity.oAuthToken).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String responseStatus = response.body();
                        Toast.makeText(getContext(), "Marked as Read", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(getContext(), "Request failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        reason.setText(currentNotif.getTitle());
        repoName.setText(currentNotif.getRepositoryName());
        repoOwnerName.setText(currentNotif.getRepositoryOwnerName());

        switch (currentNotif.getType()) {
            case "Commit":
                icon.setImageResource(R.drawable.commit);
                break;
            case "PullRequest":
                icon.setImageResource(R.drawable.pull_request);
                break;
            case "Issue":
                icon.setImageResource(R.drawable.issue);
                break;
            default:
                icon.setImageResource(R.drawable.octoface);
                break;
        }

        long timeDifference = 0;
        timeDifference = System.currentTimeMillis() - currentNotif.getDate().getTime();

        timeDifference /= 1000; //Converting into seconds.

        if (timeDifference < 60) {
            date.setText("Just Now");
        } else if (timeDifference < 60 * 60) {
            long minutes = timeDifference / 60;
            long seconds = timeDifference % 60;
            if (seconds >= 30)
                minutes++;

            if (minutes != 1)
                date.setText(String.valueOf(minutes).concat(" minutes ago"));
            else
                date.setText("A minute ago");
        } else if (timeDifference < 60 * 60 * 24) {
            long hours = timeDifference / (60 * 60);
            long minutes = (timeDifference / 60) % 60;
            if (minutes >= 30)
                hours++;

            if (hours != 1)
                date.setText(String.valueOf(hours).concat(" hours ago"));
            else
                date.setText("An hour ago");
        } else {
            long days = timeDifference / (60 * 60 * 24);
            long hours = (timeDifference / (60 * 60)) % 24;
            if (hours >= 12)
                days++;

            if (days != 1)
                date.setText(String.valueOf(days).concat(" days ago"));
            else
                date.setText("A day ago");
        }

        return listItemView;
    }
}
