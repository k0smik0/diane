package net.iubris.diane.searcher.location.aware.network;

import net.iubris.diane.aware.network.exceptions.base.NoNetworkException;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import android.location.Location;

public interface LocalizedSearcherNetworkAwareStrictChecking<SearchResult> extends
		LocalizedSearcherNetworkAware<SearchResult> {

	@Override
	public Void search(Location... location) throws 
		NoNetworkException,
			NetworkAwareSearchException;
}
