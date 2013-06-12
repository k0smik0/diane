package net.iubris.diane.aware.network.state.checker.base;

import javax.inject.Inject;

import net.iubris.diane.aware.network.exceptions.base.NoNetworkException;
import net.iubris.diane.aware.network.state.checker.CheckerStateNetworkAware;
import net.iubris.diane.aware.network.utils.NetworkUtils;
import android.net.ConnectivityManager;

public class DefaultCheckerStateNetworkAware implements CheckerStateNetworkAware {

	private ConnectivityManager connectivityManager;

	@Inject
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
