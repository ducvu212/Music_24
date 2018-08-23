package com.framgia.music_24.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import com.framgia.music_24.R;

/**
 * Created by CuD HniM on 18/08/22.
 */
public class SearchViewAnimate {

    private static final float MAX_ALPHA = 1.0f;
    private static final float MIN_ALPHA = 0.0f;
    private static final int DURATION = 250;
    private static final int TWO = 2;
    private static final int ZERO = 0;

    public static void animateSearchToolbar(final Context context, int numberOfMenuIcon,
            boolean containsOverflow, boolean show, final Toolbar toolbar,
            final DrawerLayout drawerLayout) {

        toolbar.setBackgroundColor(ContextCompat.getColor(context, android.R.color.white));
        drawerLayout.setStatusBarBackgroundColor(
                ContextCompat.getColor(context, R.color.quantum_grey_600));

        if (show) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                @SuppressLint("PrivateResource") int width =
                        toolbar.getWidth() - (containsOverflow ? context.getResources()
                                .getDimensionPixelSize(
                                        R.dimen.abc_action_button_min_width_overflow_material)
                                : ZERO) - ((context.getResources()
                                .getDimensionPixelSize(R.dimen.abc_action_button_min_width_material)
                                * numberOfMenuIcon) / TWO);
                Animator createCircularReveal = ViewAnimationUtils.createCircularReveal(toolbar,
                        isRtl(context.getResources()) ? toolbar.getWidth() - width : width,
                        toolbar.getHeight() / TWO, MIN_ALPHA, (float) width);
                createCircularReveal.setDuration(DURATION);
                createCircularReveal.start();
            } else {
                TranslateAnimation translateAnimation =
                        new TranslateAnimation(MIN_ALPHA, MIN_ALPHA, (float) (-toolbar.getHeight()),
                                MIN_ALPHA);
                translateAnimation.setDuration(DURATION);
                toolbar.clearAnimation();
                toolbar.startAnimation(translateAnimation);
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                @SuppressLint("PrivateResource") int width =
                        toolbar.getWidth() - (containsOverflow ? context.getResources()
                                .getDimensionPixelSize(
                                        R.dimen.abc_action_button_min_width_overflow_material)
                                : ZERO) - ((context.getResources()
                                .getDimensionPixelSize(R.dimen.abc_action_button_min_width_material)
                                * numberOfMenuIcon) / TWO);
                Animator createCircularReveal = ViewAnimationUtils.createCircularReveal(toolbar,
                        isRtl(context.getResources()) ? toolbar.getWidth() - width : width,
                        toolbar.getHeight() / TWO, (float) width, MIN_ALPHA);
                createCircularReveal.setDuration(DURATION);
                createCircularReveal.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        toolbar.setBackgroundColor(getThemeColor(context, R.attr.colorPrimary));
                        drawerLayout.setStatusBarBackgroundColor(
                                getThemeColor(context, R.attr.colorPrimaryDark));
                    }
                });
                createCircularReveal.start();
            } else {
                AlphaAnimation alphaAnimation = new AlphaAnimation(MAX_ALPHA, MIN_ALPHA);
                Animation translateAnimation =
                        new TranslateAnimation(MIN_ALPHA, MIN_ALPHA, MIN_ALPHA,
                                (float) (-toolbar.getHeight()));
                AnimationSet animationSet = new AnimationSet(true);
                animationSet.addAnimation(alphaAnimation);
                animationSet.addAnimation(translateAnimation);
                animationSet.setDuration(DURATION);
                animationSet.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        toolbar.setBackgroundColor(getThemeColor(context, R.attr.colorPrimary));
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                toolbar.startAnimation(animationSet);
            }
            drawerLayout.setStatusBarBackgroundColor(
                    getThemeColor(context, R.attr.colorPrimaryDark));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static boolean isRtl(Resources resources) {
        return resources.getConfiguration().getLayoutDirection() == View.LAYOUT_DIRECTION_RTL;
    }

    private static int getThemeColor(Context context, int id) {
        Resources.Theme theme = context.getTheme();
        TypedArray typedArray = theme.obtainStyledAttributes(new int[] { id });
        int result = typedArray.getColor(ZERO, ZERO);
        typedArray.recycle();
        return result;
    }
}
