package com.limelight.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.limelight.R;

public class MoonlightToast {
    public static Toast makeText(Context context, CharSequence text, int duration) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View mainLayout = inflater.inflate(R.layout.toast_layout, null);

        TextView antialiasText = mainLayout.findViewById(R.id.antialias_toast_text);
        antialiasText.setText(text);

        Toast toast = new Toast(context.getApplicationContext());
        toast.setDuration(duration);
        toast.setView(mainLayout);
        toast.show();

        return toast;
    }
    public static Toast makeText(Context context, int resId, int duration) {
        return makeText(context, context.getResources().getText(resId), duration);
    }
}
