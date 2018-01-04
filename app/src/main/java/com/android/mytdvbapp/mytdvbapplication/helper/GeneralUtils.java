package com.android.mytdvbapp.mytdvbapplication.helper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.android.mytdvbapp.mytdvbapplication.R;
import com.android.mytdvbapp.mytdvbapplication.base.AlertDialogFragment;

/**
 * Created by antoinepelletier on 08/12/2017.
 */

public class GeneralUtils {

    private static String TAG = "GeneralUtils";

    /**
     * start activity animation
     *
     * @param context
     */
    public static void beginStartActivityAnimation(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            ((Activity) context).overridePendingTransition(R.anim.move_right_in,
                    R.anim.move_left_out);
        }
    }

    /**
     * Show dialog
     **/
    public static void showAlertDialog(Context context, String title, String message) {
        showAlertDialog(context, title, message, null);
    }

    /**
     * Show dialog with just OK button
     */
    public static void showAlertDialog(Context context, String title, String message, AlertDialogFragment.AlertDialogListener callback) {
        showAlertDialog(context, title, message, true, callback);
    }


    /**
     * Show dialog witth a callback and an optional Cancel button
     **/
    public static void showAlertDialog(Context context, String title, String message, boolean showCancelButton, AlertDialogFragment.AlertDialogListener callback) {
        AlertDialogFragment alertDialog = AlertDialogFragment.newInstance(
                message,
                title,
                context.getResources().getString(R.string.ok),
                showCancelButton ? context.getResources().getString(R.string.btn_cancel) : null,
                callback);

        alertDialog.setCancelable(false);
        if (context instanceof FragmentActivity) {
            try {
                alertDialog.show(((FragmentActivity) context).getSupportFragmentManager(), AlertDialogFragment.FRAGMENT_TAG);
            } catch (Exception e) {
            }
        } else {
            Log.e(TAG, "Error trying to call showAlertDialog from outside of an Activity");
        }
    }

    public static boolean isConnectInternet(Context context) {
        ConnectivityManager conManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conManager.getActiveNetworkInfo();
        if ((networkInfo == null) || (!networkInfo.isConnected()) || (!networkInfo.isAvailable())) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @param context
     * @param text
     */
    public static void showShortToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param context
     * @param text
     */
    public static void showLongToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    /**
     * @param context
     * @param linkUrl
     */
    public static void openExternalURLByBrowser(Context context, String linkUrl) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkUrl));
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to hide keyboard
     *
     * @param activity : current activity
     */
    public static void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
