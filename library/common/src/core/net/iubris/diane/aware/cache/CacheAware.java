package net.iubris.diane.aware.cache;

import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;

public interface CacheAware<CacheState> {
	public CacheState isCacheAvailable() throws CacheTooOldException;
}
