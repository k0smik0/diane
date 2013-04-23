package net.iubris.diane.aware.network.state.checker.base;

import android.net.ConnectivityManager;
import net.iubris.diane.aware.network.exceptions.base.NoNetworkException;
import net.iubris.diane.aware.network.state.checker.CheckerStateNetworkAware;
import net.iubris.diane.aware.network.utils.NetworkUtils;

public class DefaultCheckerStateNetworkAware implements CheckerStateNetworkAware {

	private ConnectivityManager connectivityManager;

	public DefaultCheckerStateNetworkAware(ConnectivityManager connectivityManager) {
		this.connectivityManager = connectivityManager;
	}

	@Override
	public Void isConnected() throws NoNetworkException {
		if (NetworkUtils.isInternetOn(connectivityManager))
			return null;
		throw new NoNetworkException("no network connection");		
	}

}
