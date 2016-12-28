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

import com.sdsmdg.hareshkh.githubapp.R;

import java.util.ArrayList;

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
        ImageView markRead = (ImageView) listItemView.findViewById(R.id.mark_read);

        markRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Marked as Read", Toast.LENGTH_SHORT).show();
            }
        });

        return listItemView;
    }
}
