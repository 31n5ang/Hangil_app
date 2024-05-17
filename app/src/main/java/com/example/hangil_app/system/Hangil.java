package com.example.hangil_app.system;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.hangil_app.R;

public class Hangil {
    /**TMAP App key**/
    public static final String APPKEY = Key.TMAP_APP_KEY;
    /**
     * API BASE URL
     * 형식 : http://example.com/api/
     */
    public static final String API_URL = Key.API_URL;

    public static final String SEARCH = "hangil.protocol.search";
    public static final String START_NODE_FLOOR = "hangil.protocol.start.node.floor";
    public static final String START_NODE_NUMBER = "hangil.protocol.start.node.number";
    public static final String START_NODE_ID = "hangil.protocol.start.node.id";
    public static final String START_NODE_NAME = "hangil.protocol.start.node.name";

    public static final String END_NODE_FLOOR = "hangil.protocol.end.node.floor";
    public static final String END_NODE_NUMBER = "hangil.protocol.end.node.number";
    public static final String END_NODE_ID = "hangil.protocol.end.node.id";
    public static final String END_NODE_NAME = "hangil.protocol.end.node.name";

    public static final String BUILDING_ID = "hangil.protocol.building";

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
                    if (onPositiveClickListener != null) onPositiveClickListener.run();
                }))
                .setNegativeButton(Hangil.DIALOG_NEGATIVE_TEXT, (((dialog, which) -> {
                    if (onNegativeClickListener != null) onNegativeClickListener.run();
                })))
                .create()
                .show();
    }

    public static void showToastShort(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT);
    }

    public static void showToastLong(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
