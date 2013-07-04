package net.iubris.diane.searcher.location.aware.full.base;

import javax.inject.Inject;

import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.aware.network.exceptions.base.NoNetworkException;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import net.iubris.diane.searcher.location.aware.cache.LocalizedSearcherCacheAwareStrictChecking;
import net.iubris.diane.searcher.location.aware.full.LocalizedSearcherCacheNetworkAwareStrictChecking;
import net.iubris.diane.searcher.location.aware.network.LocalizedSearcherNetworkAwareStrictChecking;
import android.location.Location;

/**
 * @author  Massimiliano Leone - k0smik0
 */
public class DefaultLocalizedSearcherCacheNetworkAwareStrictChecking<SearchResult> implements LocalizedSearcherCacheNetworkAwareStrictChecking<SearchResult> {
	
	private final LocalizedSearcherCacheAwareStrictChecking<SearchResult> cacheAwareSearcher;
	private final LocalizedSearcherNetworkAwareStrictChecking<SearchResult> networkAwareSearcher;
	protected SearchResult result;
	
	@Inject
	public DefaultLocalizedSearcherCacheNetworkAwareStrictChecking(
			LocalizedSearcherCacheAwareStrictChecking<SearchResult> cacheAwareSearcher,
			LocalizedSearcherNetworkAwareStrictChecking<SearchResult> networkAwareSearcher) {
		this.cacheAwareSearcher = cacheAwareSearcher;
		this.networkAwareSearcher = networkAwareSearcher;
	}	

	@Override
	public Void search(Location... location) throws 
			NoNetworkException, NetworkAwareSearchException, 
			CacheTooOldException, CacheAwareSearchException {
		try { // trying network
			networkAwareSearcher.search(location); // network ok - it could throw NetworkSearchException
			result = networkAwareSearcher.getResult();
			return null;
		} catch (NoNetworkException nne) { // no network
//			try { // trying cache
				cacheAwareSearcher.search(location); // trying cache or throw CacheTooOldException or CacheAwareSearchException
				result = cacheAwareSearcher.getResult();
				throw nne; // but advice for no network
		}
	}
	
	@Override
	public SearchResult getResult() {
		return result;
	}
}
