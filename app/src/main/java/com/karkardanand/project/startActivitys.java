package com.karkardanand.project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class startActivitys {
    public static void set(final Context context, View view, final Intent intent){
        Animation a = AnimationUtils.loadAnimation(context,R.anim.animzoomout);
        a.reset();
        view.clearAnimation();
        view.startAnimation(a);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                context.startActivity(intent);
            }
        },1000);
    }
}
