package com.deligence.omdbmovierating.utility

import android.content.Context
import android.location.LocationManager
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import com.deligence.omdbmovierating.R

object NetworkHelper {

    /**
     * Checks if is online.
     * If it returns false, show toast using NetworkHelper.noNetworkToast(Context) methode
     * @param cxt the cxt
     * @return true, if is online
     */
    fun isOnline(cxt: Context): Boolean {
        val cm = cxt
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo

        if (netInfo != null && netInfo.isAvailable && netInfo.isConnected) {
            Log.d("", "mode is online")
            return true
        }
        return false
    }

    /**
     * not Implemented yet
     */
    fun noNetworkDialog() {

    }

    fun noNetworkToast(context: Context) {
        Toast.makeText(context, getNoConnectionString(context), Toast.LENGTH_LONG).show()
    }

    fun getNoConnectionString(context: Context): String {

        return context.resources.getString(R.string.no_network)
    }

    fun isGpsEnabled(context: Context): Boolean {
        val lm = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }
}





















