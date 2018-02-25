package com.bgcomm.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtils {
    public static boolean isNetworkPresent(Context context) {
        boolean networkPresent = false;
        final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(cm != null) {
            final NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            networkPresent = (networkInfo != null && networkInfo.isConnectedOrConnecting());
        }
        return networkPresent;
    }
}
