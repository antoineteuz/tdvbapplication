package com.android.mytdvbapp.mytdvbapplication.network;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by antoinepelletier on 04/12/2017.
 */

public class NetworkUtils {

    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    private NetworkUtils() {
    }

    public static NetworkUtils getInstance() {
        return new NetworkUtils();
    }

    public void clearSubscription() {
        if (compositeSubscription != null && !compositeSubscription.isUnsubscribed()) {
            compositeSubscription.clear();
        }
    }

    public void unSubscription() {
        if (compositeSubscription != null && !compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
    }

    public void addSubscription(Subscription subscription) {
        if (compositeSubscription != null) {
            compositeSubscription.add(subscription);
        }
    }
}
