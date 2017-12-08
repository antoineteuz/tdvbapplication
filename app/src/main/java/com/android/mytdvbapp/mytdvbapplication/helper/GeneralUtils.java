package com.android.mytdvbapp.mytdvbapplication.helper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.android.mytdvbapp.mytdvbapplication.R;

/**
 * Created by antoinepelletier on 08/12/2017.
 */

public class GeneralUtils {

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
     * show alert dialog
     *
     * @param title
     * @param description
     * @param activity
     */
    public static void showAlertDialog(String title, String description, Activity activity) {
        // Build Alert Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title)
                .setMessage(description)
                .setCancelable(false)
                .setPositiveButton(activity.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /**
     * Return true if Internet is reachable
     *
     * @param context Application-specific resources
     * @return True if Internet is reachable
     */
    public static boolean isConnectedToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return connectivity != null && connectivity.getActiveNetworkInfo() != null
                && connectivity.getActiveNetworkInfo().isConnected();
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
