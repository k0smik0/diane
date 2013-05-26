package net.iubris.diane.searcher.location.aware.cache;

import net.iubris.diane.aware.cache.exceptions.CacheStateException;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.location.LocalizedSearcher;
import android.location.Location;

public interface LocalizedSearcherCacheAware<SearchResult> extends LocalizedSearcher<SearchResult> { 
	@Override
	public Void search(Location... locations) throws 
//		CacheTooOldException,
		CacheStateException,
		CacheAwareSearchException
//		,StateException,
//		,SearchException
		;
}
