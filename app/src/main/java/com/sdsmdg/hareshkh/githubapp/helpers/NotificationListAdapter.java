package com.sdsmdg.hareshkh.githubapp.helpers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sdsmdg.hareshkh.githubapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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

        NotificationListItem currentNotif = getItem(position);
        ImageView icon = (ImageView) listItemView.findViewById(R.id.notif_icon);
        TextView reason = (TextView) listItemView.findViewById(R.id.notif_reason);
        TextView repoName = (TextView) listItemView.findViewById(R.id.notif_repo_name);
        TextView repoOwnerName = (TextView) listItemView.findViewById(R.id.notif_repo_owner_name);
        TextView date = (TextView) listItemView.findViewById(R.id.notif_date);
        ImageView markRead = (ImageView) listItemView.findViewById(R.id.mark_read);

        markRead.setImageResource(R.drawable.tick);
        markRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Marked as Read", Toast.LENGTH_SHORT).show();
            }
        });

        assert currentNotif != null;
        reason.setText(currentNotif.getTitle());
        repoName.setText(currentNotif.getRepositoryName());
        repoOwnerName.setText(currentNotif.getRepositoryOwnerName());

        if (currentNotif.getType() == "Commit")
            icon.setImageResource(R.drawable.commit);
        else if (currentNotif.getType() == "PullRequest")
            icon.setImageResource(R.drawable.pull_request);
        else if (currentNotif.getType() == "Issue")
            icon.setImageResource(R.drawable.issue);
        else
            icon.setImageResource(R.drawable.octoface);

        String datePattern = "yyyy-mm-dd hh:mm:ss";
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
        String currentTimeString = sdf.format(Calendar.getInstance().getTime());
        Date currentTime;
        long timeDifference = 0;
        try {
            currentTime = sdf.parse(currentTimeString);
            timeDifference = currentTime.getTime() - currentNotif.getDate().getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        timeDifference /= 1000; //Converting into seconds.

        if (timeDifference < 60)
            date.setText("Just Now");
        else if (timeDifference < 3600) {
            long minutes = timeDifference / 60;
            date.setText(String.valueOf(minutes).concat(" minutes ago"));
        } else {
            long hours = timeDifference / (60 * 60);
            date.setText(String.valueOf(hours).concat(" hours ago"));
        }

        return listItemView;
    }
}
