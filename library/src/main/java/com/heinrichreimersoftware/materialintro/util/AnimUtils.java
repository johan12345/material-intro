/*
 * Copyright 2015 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.heinrichreimersoftware.materialintro.util;

import android.content.Context;
import android.os.Build;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import com.heinrichreimersoftware.materialintro.R;

/**
 * Utility methods for working with animations.
 */
public class AnimUtils {

    private AnimUtils() { }

    private static Interpolator fastOutSlowIn;
    private static Interpolator accelerateDecelerate;

    public static Interpolator getFastOutSlowInInterpolator(Context context) {
        if (fastOutSlowIn == null) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                fastOutSlowIn = AnimationUtils.loadInterpolator(context,
                        android.R.interpolator.fast_out_slow_in);
            }
            else {
                fastOutSlowIn = new FastOutSlowInInterpolator();
            }
        }
        return fastOutSlowIn;
    }

    public static Interpolator getAccelerateDecelerateInterpolator(Context context) {
        if (accelerateDecelerate == null) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                accelerateDecelerate = AnimationUtils.loadInterpolator(context,
                        android.R.interpolator.accelerate_decelerate);
            }
            else {
                accelerateDecelerate = new AccelerateDecelerateInterpolator();
            }
        }
        return accelerateDecelerate;
    }

    public static Animation getFadeInAnimation(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return AnimationUtils.loadAnimation(context, R.anim.fade_in);
        } else {
            AlphaAnimation anim = new AlphaAnimation(0, 1);
            anim.setDuration(200);
            anim.setInterpolator(new DecelerateInterpolator());
            return anim;
        }
    }

    public static Animation getFadeOutAnimation(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return AnimationUtils.loadAnimation(context, R.anim.fade_out);
        } else {
            AlphaAnimation anim = new AlphaAnimation(1, 0);
            anim.setDuration(200);
            anim.setInterpolator(new AccelerateInterpolator());
            return anim;
        }
    }

    public static void applyShakeAnimation(Context context, View view) {
        Animation shake;
        shake = AnimationUtils.loadAnimation(context, R.anim.shake);
        view.startAnimation(shake);
    }
}