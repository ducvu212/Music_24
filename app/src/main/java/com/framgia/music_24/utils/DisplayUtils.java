package com.framgia.music_24.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by CuD HniM on 18/08/23.
 */
public class DisplayUtils {

    public static void addFragment(FragmentManager manager, Fragment fragment, int id, String tag) {
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragmentByTag = manager.findFragmentByTag(tag);
        if (fragmentByTag != null) {
            manager.beginTransaction().show(fragmentByTag).commit();
        } else {
            transaction.add(id, fragment, tag);
            transaction.commit();
        }
    }
}
