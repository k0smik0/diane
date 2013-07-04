package net.iubris.diane.searcher.location.aware.cache.base;

import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.aware.cache.states.three.ThreeStateCacheAware;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.location.aware.cache.LocalizedSearcherCacheAwareStrictChecking;
import android.location.Location;

/**
 * @author  Massimiliano Leone - k0smik0
 */
public abstract class AbstractLocalizedSearcherCacheAwareStrictChecking<SearchResult> 
	implements LocalizedSearcherCacheAwareStrictChecking<SearchResult> {
	
	/**
	 * @uml.property  name="cacheAware"
	 * @uml.associationEnd  
	 */
	protected final ThreeStateCacheAware cacheAware;

	public AbstractLocalizedSearcherCacheAwareStrictChecking(ThreeStateCacheAware cacheAware) {
		this.cacheAware = cacheAware;
	}

	@Override
	public Void search(Location... location) throws CacheTooOldException, CacheAwareSearchException {
		if (cacheAware.isCacheAvailable());
			doSearch(location[0]);
		return null;
	}

	protected abstract void doSearch(Location location) throws CacheAwareSearchException;
}
