package net.iubris.diane._roboguice.providers.aware.network.state.checker.base;

import javax.inject.Inject;
import javax.inject.Provider;

import android.net.ConnectivityManager;

import net.iubris.diane.aware.network.state.checker.base.DefaultCheckerStateNetworkAware;

public class DefaultCheckerStateNetworkAwareProvider implements Provider<DefaultCheckerStateNetworkAware> {

	@Inject ConnectivityManager connectivityManager;
	
	@Override
	public DefaultCheckerStateNetworkAware get() {
		return new DefaultCheckerStateNetworkAware(connectivityManager);
	}
}
