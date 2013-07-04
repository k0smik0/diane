package net.iubris.diane.searcher.location.aware.full;

import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.aware.network.exceptions.base.NoNetworkException;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import android.location.Location;

public interface LocalizedSearcherCacheNetworkAwareStrictChecking<SearchResult> extends
		LocalizedSearcherCacheNetworkAware<SearchResult> {
	
	@Override
	public Void search(Location... locations) 
			throws 
		NoNetworkException,
		NetworkAwareSearchException, 
		CacheTooOldException,
		CacheAwareSearchException;
}
