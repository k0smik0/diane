/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * AbstractLocalizedSearcherCacheAwareStrictChecking.java is part of Diane.
 * 
 * Diane is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Diane is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with Diane ; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 ******************************************************************************/
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
