/*******************************************************************************
 * Copyleft LGPL 2015 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * NetworkUtils.java is part of Jason.
 ******************************************************************************/
package net.iubris.diane.aware.network.utils;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtils {
	public static final boolean isInternetOn(ConnectivityManager connectivityManager) {		
		// ARE WE CONNECTED TO THE NET
		if ( connectivityManager.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED ||
				connectivityManager.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTING ||
				connectivityManager.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING ||
				connectivityManager.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED ) {
			// MESSAGE TO SCREEN FOR TESTING (IF REQ)
			//Toast.makeText(this, connectionType + ' connected', Toast.LENGTH_SHORT).show();
			return true;
		} else if ( connectivityManager.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED ||  
				connectivityManager.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED  ) {
			//System.out.println('Not Connected');
			return false;
		}
		return false;
	}
}
