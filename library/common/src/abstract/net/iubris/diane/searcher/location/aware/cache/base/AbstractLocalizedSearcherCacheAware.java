package net.iubris.diane.searcher.location.aware.cache.base;

import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.aware.cache.states.three.ThreeStateCacheAware;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.location.aware.cache.LocalizedSearcherCacheAware;
import android.location.Location;

/**
 * @author  Massimiliano Leone - k0smik0
 */
public abstract class AbstractLocalizedSearcherCacheAware<Result> 
	implements LocalizedSearcherCacheAware<Result> {
	
	/**
	 * @uml.property  name="cacheAware"
	 * @uml.associationEnd  
	 */
	protected final ThreeStateCacheAware cacheAware;

	public AbstractLocalizedSearcherCacheAware(ThreeStateCacheAware cacheAware) {
		this.cacheAware = cacheAware;
	}

	@Override
	public Void search(Location... location) throws CacheTooOldException, CacheAwareSearchException {
		cacheAware.isCacheAvailable();
		doSearch(location[0]);
		return null;
	}

	protected abstract void doSearch(Location location) throws CacheAwareSearchException;
}
