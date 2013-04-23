package net.iubris.diane.searcher.location.aware.full;

import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.aware.network.exceptions.base.NoNetworkException;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import net.iubris.diane.searcher.exceptions.SearchException;
import net.iubris.diane.searcher.location.aware.cache.LocalizedSearcherCacheAware;
import net.iubris.diane.searcher.location.aware.network.LocalizedSearcherNetworkAware;
import android.location.Location;

public interface LocalizedSearcherCacheNetworkAware<Result>
// extends LocalizedSearcher<Result> {
	extends LocalizedSearcherNetworkAware<Result>, 
	LocalizedSearcherCacheAware<Result> {

	@Override
	public Void search(Location... location) throws 
		NoNetworkException, NetworkAwareSearchException, 
		CacheTooOldException, CacheAwareSearchException, 
		SearchException;
}
