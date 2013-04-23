package net.iubris.diane.searcher.location.aware.full.base;

import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.aware.network.exceptions.base.NoNetworkException;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import net.iubris.diane.searcher.exceptions.SearchException;
import net.iubris.diane.searcher.location.aware.cache.LocalizedSearcherCacheAware;
import net.iubris.diane.searcher.location.aware.full.LocalizedSearcherCacheNetworkAware;
import net.iubris.diane.searcher.location.aware.network.LocalizedSearcherNetworkAware;
import android.location.Location;

/**
 * @author  Massimiliano Leone - k0smik0
 */
public class DefaultLocalizedSearcherCacheNetworkAware<Result> implements LocalizedSearcherCacheNetworkAware<Result> {
	
	/**
	 * @uml.property  name="cacheAwareSearcher"
	 * @uml.associationEnd  
	 */
	private final LocalizedSearcherCacheAware<Result> cacheAwareSearcher;
	/**
	 * @uml.property  name="networkAwareSearcher"
	 * @uml.associationEnd  
	 */
	private final LocalizedSearcherNetworkAware<Result> networkAwareSearcher;
	private Result result;
	
	public DefaultLocalizedSearcherCacheNetworkAware(
			LocalizedSearcherCacheAware<Result> cacheAwareSearcher,
			LocalizedSearcherNetworkAware<Result> networkAwareSearcher) {
		this.cacheAwareSearcher = cacheAwareSearcher;
		this.networkAwareSearcher = networkAwareSearcher;
	}
	

	@Override
	public final Void search(Location... location) throws NoNetworkException, NetworkAwareSearchException, 
		CacheTooOldException, CacheAwareSearchException, SearchException {		
		/*try { // network ok
			networkAware.isConnected();
			return doNetworkSearch(location);
		} catch (NoNetworkException nne) { // trying by cache
			try {
				cacheAware.isCacheAvailable();
				doCacheSearch(location);				
				throw new NetworkAwareSearchException("no network found",nne);
			} catch (NoCacheAvailable nca) {
				throw new CacheAwareSearchException("no cache found", nca);
			}									
		}*/
		try { // trying network
			networkAwareSearcher.search(location); // network ok - it could throw NetworkSearchException
			result = networkAwareSearcher.getResult();
			return null;
		} catch (NoNetworkException nne) { // no network
//			try { // trying cache
				cacheAwareSearcher.search(location); // trying cache or throw NoCacheAvailable or CacheSearchException
				result = cacheAwareSearcher.getResult();
				throw nne; // but advice for no network
//			} catch (NoCacheAvailable nca) { // no cache
//				throw nca; // rethrow no cache
//			} catch (CacheSearchException casex) { // another cache-exception
//				throw casex; // rethrow it
//			}
		}
		// there is no catch for NetworkAwareSearchException because, when it occurs, simply let it throws 
	}
	
	@Override
	public Result getResult() {
		return result;
	}
}
