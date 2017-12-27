package com.android.mytdvbapp.mytdvbapplication.base;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import java.io.Serializable;

/**
 * Created by antoinepelletier on 27/12/2017.
 */

public class AlertDialogFragment extends DialogFragment {

    public static final String FRAGMENT_TAG = "AlertDialogFragment";
    public static String MESSAGE = "args_message";
    public static String TITLE = "args_title";
    public static String LISTENER = "args_listener";
    public static String DISMISS_LISTENER = "args_dismiss_listener";
    public static String TITLE_POSITIVE_BUTTON = "args_title_positive_button";
    public static String TITLE_NEGATIVE_BUTTON = "args_title_negative_button";

    public static AlertDialogFragment newInstance(String message, String title, String titlePositiveButton, String titleNegativeButton, AlertDialogListener listener) {
        AlertDialogFragment fragment = new AlertDialogFragment();
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        args.putString(MESSAGE, message);
        args.putSerializable(LISTENER, listener);
        args.putSerializable(TITLE_POSITIVE_BUTTON, titlePositiveButton);
        args.putSerializable(TITLE_NEGATIVE_BUTTON, titleNegativeButton);
        fragment.setArguments(args);
        return fragment;
    }

    public static AlertDialogFragment newInstance(String message, String title, String titlePositiveButton, String titleNegativeButton, AlertDialogListener listener, AlertDialogDismissListener dismissListener) {
        AlertDialogFragment fragment = new AlertDialogFragment();
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        args.putString(MESSAGE, message);
        args.putSerializable(LISTENER, listener);
        args.putSerializable(TITLE_POSITIVE_BUTTON, titlePositiveButton);
        args.putSerializable(TITLE_NEGATIVE_BUTTON, titleNegativeButton);
        args.putSerializable(DISMISS_LISTENER, dismissListener);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final String title = getArguments().getString(TITLE);
        final String message = getArguments().getString(MESSAGE);
        final AlertDialogListener listener = (AlertDialogListener) getArguments().getSerializable(LISTENER);
        final String titlePositiveButton = getArguments().getString(TITLE_POSITIVE_BUTTON);
        final String titleNegativeButton = getArguments().getString(TITLE_NEGATIVE_BUTTON);
        final AlertDialogDismissListener dismissListener = (AlertDialogDismissListener) getArguments().getSerializable(DISMISS_LISTENER);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(titlePositiveButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null) {
                    listener.onPositiveButton();
                }
                if (dismissListener != null) {
                    dismissListener.onDismiss();
                }
                dismiss();
            }
        });
        if (titleNegativeButton != null) {
            builder.setNegativeButton(titleNegativeButton, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (listener != null) {
                        listener.onNegativeButton();
                    }
                    if (dismissListener != null) {
                        dismissListener.onDismiss();
                    }
                    dismiss();
                }
            });
        }
        return builder.create();
    }

    @Override
    public void onPause() {
        super.onPause();
        dismiss();
    }


    public interface AlertDialogListener extends Serializable {
        void onPositiveButton();

        void onNegativeButton();
    }

    public interface AlertDialogDismissListener extends Serializable {
        void onDismiss();
    }
}
