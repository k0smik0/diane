/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * DefaultLocalizedSearcherCacheNetworkAwareStrictChecking.java is part of Diane.
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
package net.iubris.diane.searcher.location.aware.full.base;

import java.util.Collection;

import javax.inject.Inject;

import net.iubris.diane.aware.cache.exceptions.base.CacheEmptyException;
import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.aware.cache.states.three.ThreeStateCacheAware;
import net.iubris.diane.aware.network.exceptions.base.NoNetworkException;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import net.iubris.diane.searcher.location.aware.cache.LocalizedSearcherCacheAwareStrictChecking;
import net.iubris.diane.searcher.location.aware.full.LocalizedSearcherCacheNetworkAwareStrictChecking;
import net.iubris.diane.searcher.location.aware.network.LocalizedSearcherNetworkAwareStrictChecking;
import android.location.Location;
import android.util.Log;

/**
 * @author  Massimiliano Leone - k0smik0
 * use {@link #setResult to assign a new value to (internal) result}
 */
public class DefaultLocalizedSearcherCacheNetworkAwareStrictChecking<SearchResult> 
implements LocalizedSearcherCacheNetworkAwareStrictChecking<SearchResult> {

	private final LocalizedSearcherCacheAwareStrictChecking<SearchResult> cacheAwareSearcher;
	private final LocalizedSearcherNetworkAwareStrictChecking<SearchResult> networkAwareSearcher;
	private final ThreeStateCacheAware cacheAware;
	
	private SearchResult result;
	private boolean foundByCache;
	
	@Inject
	public DefaultLocalizedSearcherCacheNetworkAwareStrictChecking(
			LocalizedSearcherCacheAwareStrictChecking<SearchResult> cacheAwareSearcher,
			LocalizedSearcherNetworkAwareStrictChecking<SearchResult> networkAwareSearcher,
			ThreeStateCacheAware cacheAware) {
		this.cacheAwareSearcher = cacheAwareSearcher;
		this.networkAwareSearcher = networkAwareSearcher;
		this.cacheAware = cacheAware;
	}	

	@Override
	public Void search(Location... locations) throws
			NoNetworkException, NetworkAwareSearchException,
			CacheTooOldException, CacheEmptyException, CacheAwareSearchException {
		
		
		if (cacheAware.useFirstlyCache()) {
//			boolean foundByCache;
			boolean cacheEmpty = false;
			boolean cacheTooOld = false;
			boolean cacheAwareException = false;
			try {
				foundByCache = searchByCache(locations);
				if (foundByCache) {
					if (result instanceof Collection) {
						Log.d("DefaultLocalizedSearcherCacheNetworkAwareStrictChecking", "found by cache: "+((Collection)result).size());
					} else
						Log.d("DefaultLocalizedSearcherCacheNetworkAwareStrictChecking", "found by cache: "+result);
					return null;
				}
			} catch (CacheEmptyException e) {
				// if here, cache is empty, so dummy catch and try network
				Log.d("DefaultLocalizedSearcherCacheNetworkAwareStrictChecking", "empty cache searching firstlyByCache");
				cacheEmpty = true;
			} catch (CacheTooOldException e) {
				Log.d("DefaultLocalizedSearcherCacheNetworkAwareStrictChecking", "cache too old searching firstlyByCache");
				cacheTooOld = true;
			} catch (CacheAwareSearchException e) {
				cacheAwareException = true;
			}
			
			// here we try network
			networkAwareSearcher.search(locations); // network ok - it could throw NetworkSearchException
			result = networkAwareSearcher.getResult();
			
			if (cacheEmpty)
				onCacheEmptyException();
			if (cacheTooOld)
				onCacheTooOldException();
			if (cacheAwareException)
				onCacheAwareSearchException();
				
			return null;
		} else {
			
			try { // trying firstly network
				networkAwareSearcher.search(locations); // network ok - it could throw NetworkSearchException
				result = networkAwareSearcher.getResult();
//				return null;
			} catch (NoNetworkException nne) { // no network
				// trying cache
				// trying cache or throw CacheTooOldException or CacheAwareSearchException
				// we care of these exception and let they throw...
				searchByCache(locations);
				// however old result is always better than no result, so assign to this.result					
				throw nne; // advice for no network if no cache* exceptions are throwed
			}
		}
		return null;
	}
	
	

	/**
	 * default: do nothing
	 */
	protected void onCacheAwareSearchException() {}
	protected void onCacheEmptyException() {}
	protected void onCacheTooOldException() {}

	@Override
	public boolean isFoundByCache() {
		return foundByCache;
	}
	
	/**
	 * 
	 * @param locations
	 * @return true if any result (any type or any collection subtype with size > 0); false if result is null
	 * @throws CacheEmptyException
	 * @throws CacheAwareSearchException
	 * @throws CacheTooOldException 
	 */
	@SuppressWarnings("rawtypes")
	private boolean searchByCache(Location... locations) throws CacheEmptyException, CacheAwareSearchException, CacheTooOldException {
		boolean cacheTooOld = false;
		SearchResult result = null;
		try {
			cacheAwareSearcher.search(locations); // trying cache or throw CacheTooOldException or CacheAwareSearchException
			result = cacheAwareSearcher.getResult();
		} catch(CacheTooOldException e) {
			cacheTooOld = true;
		}
		
		if (result!=null) {
			this.result = result;
			if (result instanceof Collection) {
				Collection c = (Collection)result;
				if (c.size()!=0) {
					return true;
				} else
					throw new CacheEmptyException();
			}
			if (cacheTooOld)
				throw new CacheTooOldException();
		}
		return false;
		
	}
	
	@Override
	public SearchResult getResult() {
		return result;
	}
	
	public void setResult(SearchResult result) {
		this.result = result;
	}
}
