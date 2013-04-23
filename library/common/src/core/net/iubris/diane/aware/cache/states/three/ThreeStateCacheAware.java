package net.iubris.diane.aware.cache.states.three;

import net.iubris.diane.aware.cache.CacheAware;
import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;

/**
 * ThreeState* because we want emulate a three-state behaviour in "isCacheAvailable", returning true,false or throw CacheTooOldException
 * @author Massimiliano Leone - k0smik0
 */
public interface ThreeStateCacheAware extends CacheAware<Boolean> {

	@Override
	public Boolean isCacheAvailable() throws CacheTooOldException;

}
