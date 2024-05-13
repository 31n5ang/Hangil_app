package com.example.hangil_app.system;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import com.example.hangil_app.R;

public class Hangil {
    /**TMAP App key**/
    public static final String APPKEY = "Ndc3c4Kz0i7Cu5TBb93hF35hivbzqERV5N7XhOiy";
    /**API Server URL**/
    public static final String API_URL = "http://~/api/";
    public static final String SEARCH = "hangil.proto.search";
    public static final String API = "hangil.tag.API";
    public static final String TMAP = "hangil.tag.TMAP";
    public static final String TEST = "hangil.tag.test";
    public static final String WIFI = "hangil.tag.wifi";


    public static final String DIALOG_TITLE = "안내";
    public static final String DIALOG_POSITIVE_TEXT = "좋아요";
    public static final String DIALOG_NEGATIVE_TEXT = "싫어요";

    public static void suggestGuideDialog(
            Context context,
            String message,
            final OnPositiveClickListener onPositiveClickListener,
            final OnNegativeClickListener onNegativeClickListener
    ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder
                .setTitle(Hangil.DIALOG_TITLE)
                .setIcon(R.drawable.hangil_logo)
                .setMessage(message)
                .setPositiveButton(Hangil.DIALOG_POSITIVE_TEXT, ((dialog, which) -> {
                    onPositiveClickListener.run();
                }))
                .setNegativeButton(Hangil.DIALOG_NEGATIVE_TEXT, (((dialog, which) -> {
                    onNegativeClickListener.run();
                })))
                .create()
                .show();
    }
}
