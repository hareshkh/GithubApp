package com.sdsmdg.hareshkh.githubapp.helpers;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdsmdg.hareshkh.githubapp.R;

import java.util.ArrayList;

public class RepositoryListAdapter extends ArrayAdapter<RepositoryListItem> {

    public RepositoryListAdapter(Activity context, ArrayList<RepositoryListItem> listItems) {
        super(context, 0, listItems);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.repo_list_item, parent, false);
        }

        RepositoryListItem currentRepo = getItem(position);
        ImageView icon = (ImageView) listItemView.findViewById(R.id.repo_icon);
        ImageView star = (ImageView) listItemView.findViewById(R.id.star_container);
        TextView repoName = (TextView) listItemView.findViewById(R.id.repo_name);
        TextView repoOwnerName = (TextView) listItemView.findViewById(R.id.repo_owner_name);
        TextView starCount = (TextView) listItemView.findViewById(R.id.star_count);

        assert currentRepo != null;
        repoName.setText(currentRepo.getName());
        repoOwnerName.setText(currentRepo.getOwner());
        starCount.setText(String.valueOf(currentRepo.getStarCount()));
        star.setImageResource(R.drawable.star);

        if (!currentRepo.isForked()) {
            icon.setImageResource(R.drawable.repo);
        } else {
            icon.setImageResource(R.drawable.repo_forked);
        }

        return listItemView;
    }
}
