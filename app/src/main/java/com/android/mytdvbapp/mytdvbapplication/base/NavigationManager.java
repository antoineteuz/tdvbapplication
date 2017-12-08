package com.android.mytdvbapp.mytdvbapplication.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.android.mytdvbapp.mytdvbapplication.R;

import java.util.List;

/**
 * Created by antoinepelletier on 04/12/2017.
 */

public class NavigationManager {

    private static final String TAG = "NavigationManager";

    interface NavigationListener {
        void onBackStackChanged();
    }

    private FragmentManager mFragmentManager;
    private NavigationListener mNavigationListener;

    private @IdRes
    int mContainerViewId;

    /**
     * initialise
     *
     * @param fragmentManager current fragment manager
     */
    @MainThread
    public void init(@NonNull FragmentManager fragmentManager, @IdRes int containerViewId) {
        mFragmentManager = fragmentManager;
        mContainerViewId = containerViewId;
        mFragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (mNavigationListener != null) {
                    mNavigationListener.onBackStackChanged();
                }
            }
        });
    }

    /**
     * open fragment
     *
     * @param fragment designated fragment
     */
    @MainThread
    public void open(@NonNull AbstractFragment fragment) {
        if (mFragmentManager != null) {
            mFragmentManager.beginTransaction()
                    .replace(mContainerViewId, fragment)
                    .addToBackStack(fragment.getID())
                    .commit();
        }
    }

    /**
     * open fragment with arguments
     *
     * @param fragment designated fragment
     * @param bundle   given parameters
     */
    @MainThread
    public void open(@NonNull AbstractFragment fragment, Bundle bundle) {
        if (mFragmentManager != null) {
            fragment.setArguments(bundle);
            mFragmentManager.beginTransaction()
                    .replace(mContainerViewId, fragment)
                    .addToBackStack(fragment.getID())
                    .commit();
        }
    }


    public void openNoStack(@NonNull AbstractFragment fragment) {
        if (mFragmentManager != null) {
            mFragmentManager.beginTransaction()
                    .replace(mContainerViewId, fragment)
                    .disallowAddToBackStack()
                    .commit();
        }
    }

    /**
     * tweak to avoid adding fragment to stack if already exists
     *
     * @param target
     * @return true if exist
     */
    private boolean contains(AbstractFragment target) {
        List<Fragment> fragList = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            fragList = mFragmentManager.getFragments();
        }
        for (int i = 0; i < fragList.size(); i++) {

            if (!(fragList.get(i) instanceof AbstractFragment)) continue;

            AbstractFragment des = (AbstractFragment) fragList.get(i);
            if (des == null || TextUtils.isEmpty(des.getID())) {
                continue;
            }
            if (target.getID().equals(des.getID())) return true;
        }
        return false;
    }

    /**
     * used to show fragment without keep it in stack,
     * using a custom in/out animation
     *
     * @param fragment designated fragment
     */
    @SuppressLint("ResourceType")
    @MainThread
    public void show(@NonNull AbstractFragment fragment) {
        if (mFragmentManager != null) {
            mFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_bottom_to_top, android.R.animator.fade_out)
                    .replace(mContainerViewId, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }


    /**
     * refresh current fragment
     */
    @MainThread
    public void refreshCurrentFragment() {
        Fragment currentFragment = mFragmentManager.findFragmentById(mContainerViewId);
        if (currentFragment != null) {
            mFragmentManager.beginTransaction()
                    .detach(currentFragment)
                    .attach(currentFragment)
                    .commit();
        }

    }

    /**
     * perform a back and refresh current fragment
     */
    @MainThread
    public void backAndRefresh() {
        if (mFragmentManager.popBackStackImmediate()) {
            refreshCurrentFragment();
        }
    }

    /**
     * pop all fragment in stack
     */
    @MainThread
    private void popEveryFragment() {
        int backStackCount = mFragmentManager.getBackStackEntryCount();
        for (int i = 0; i < backStackCount; i++) {
            int backStackId = mFragmentManager.getBackStackEntryAt(i).getId();
            mFragmentManager.popBackStack(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    /**
     * handle fragment navigation back
     *
     * @param baseActivity current activity
     */
    @MainThread
    public void navigateBack(@NonNull Activity baseActivity) {
        if (mFragmentManager.getBackStackEntryCount() > 1) {
            Log.d(TAG, "items in backstack: [ " + mFragmentManager.getBackStackEntryCount() + " ]");
            Fragment currentFrag = mFragmentManager.findFragmentById(mContainerViewId);
            if (currentFrag != null) {
                mFragmentManager.popBackStack();
            } else {
                baseActivity.finish();
            }
        } else {
            baseActivity.finish();
        }
    }
}
