package net.iubris.diane.searcher.location.aware.cache;

import android.location.Location;
import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.exceptions.SearchException;
import net.iubris.diane.searcher.location.LocalizedSearcher;

public interface LocalizedSearcherCacheAware<SearchResult> extends LocalizedSearcher<SearchResult> { 
	@Override
	public Void search(Location... location) throws CacheTooOldException, CacheAwareSearchException, SearchException;
}
