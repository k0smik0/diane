package net.iubris.diane.searcher.location.aware.network;

import android.location.Location;
import net.iubris.diane.aware.network.exceptions.base.NoNetworkException;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import net.iubris.diane.searcher.exceptions.SearchException;
import net.iubris.diane.searcher.location.LocalizedSearcher;

public interface LocalizedSearcherNetworkAware<Result> extends LocalizedSearcher<Result> {
	@Override
	public Void search(Location... location) throws NoNetworkException, NetworkAwareSearchException, SearchException;
}
