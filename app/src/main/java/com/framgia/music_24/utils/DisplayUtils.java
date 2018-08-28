package com.framgia.music_24.utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;
import com.framgia.music_24.R;

/**
 * Created by CuD HniM on 18/08/23.
 */
public class DisplayUtils {

    public static void makeToast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    public static void addFragment(FragmentManager manager, Fragment fragment, int id, String tag) {
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragmentByTag = manager.findFragmentByTag(tag);
        if (fragmentByTag != null) {
            manager.beginTransaction()
                    .show(fragmentByTag)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        } else {
            transaction.add(id, fragment, tag);
            if (id != R.id.frame_main_layout) {
                transaction.addToBackStack(tag);
            }
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();
        }
    }
}
