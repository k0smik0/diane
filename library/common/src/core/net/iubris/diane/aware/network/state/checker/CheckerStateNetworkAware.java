package net.iubris.diane.aware.network.state.checker;

import net.iubris.diane.aware.network.NetworkAware;
import net.iubris.diane.aware.network.exceptions.base.NoNetworkException;

public interface CheckerStateNetworkAware extends NetworkAware<Void> {
	@Override
	public Void isConnected() throws NoNetworkException;
}
