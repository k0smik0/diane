package net.iubris.diane.searcher.location.aware.cache;

import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import android.location.Location;

public interface LocalizedSearcherCacheAwareStrictChecking<SearchResult> extends
		LocalizedSearcherCacheAware<SearchResult> {
	@Override
	public Void search(Location... locations) throws CacheTooOldException,
		CacheAwareSearchException;
}
