package com.sdsmdg.hareshkh.githubapp.helpers;

import android.app.ProgressDialog;
import android.content.Context;

public class ProgressDialogHelper {

    ProgressDialog mProgressDialog;

    public ProgressDialogHelper (Context context) {
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
    }

    public void showDialog() {

        if(mProgressDialog != null && !mProgressDialog.isShowing())
            mProgressDialog.show();
    }

    public void hideDialog() {

        if(mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }
}
