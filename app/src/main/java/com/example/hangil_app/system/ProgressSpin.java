package com.example.hangil_app.system;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import com.example.hangil_app.R;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;

public class ProgressSpin extends Dialog {
    private final Context context;
    public ProgressSpin(@NonNull Context context) {
        super(context);
        this.context = context;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_progress);

        ProgressBar progressSpin = (ProgressBar) findViewById(R.id.spinKit);
        Sprite pulseRing = new ThreeBounce();
        progressSpin.setIndeterminateDrawable(pulseRing);
    }
}
