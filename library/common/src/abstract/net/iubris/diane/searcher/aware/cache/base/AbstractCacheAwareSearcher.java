package net.iubris.diane.searcher.aware.cache.base;

import net.iubris.diane.aware.cache.exceptions.CacheStateException;
import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.aware.cache.states.three.ThreeStateCacheAware;
import net.iubris.diane.searcher.aware.cache.CacheAwareSearcher;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.exceptions.SearchException;

/**
 * @author  Massimiliano Leone - k0smik0
 */
public abstract class AbstractCacheAwareSearcher<SearchState, SearchResult> implements
		CacheAwareSearcher<SearchState, SearchResult> {

	/**
	 * @uml.property  name="cacheAware"
	 * @uml.associationEnd  
	 */
	protected final ThreeStateCacheAware cacheAware;

	public AbstractCacheAwareSearcher(ThreeStateCacheAware cacheAware) {
		this.cacheAware = cacheAware;
	}

	@Override
	public SearchState search(Void... param) throws CacheTooOldException, CacheStateException, CacheAwareSearchException, SearchException {
		Boolean cacheAvailable = cacheAware.isCacheAvailable();
		if (cacheAvailable)
			return doSearch();
		return canNotSearch();
	}

	protected abstract SearchState doSearch() throws CacheStateException, CacheAwareSearchException;
	protected abstract SearchState canNotSearch() throws CacheStateException, CacheAwareSearchException;
}
