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

import javax.inject.Inject;

import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.aware.network.exceptions.base.NoNetworkException;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import net.iubris.diane.searcher.location.aware.cache.LocalizedSearcherCacheAwareStrictChecking;
import net.iubris.diane.searcher.location.aware.full.LocalizedSearcherCacheNetworkAwareStrictChecking;
import net.iubris.diane.searcher.location.aware.network.LocalizedSearcherNetworkAwareStrictChecking;
import android.location.Location;

/**
 * @author  Massimiliano Leone - k0smik0
 */
public class DefaultLocalizedSearcherCacheNetworkAwareStrictChecking<SearchResult> implements LocalizedSearcherCacheNetworkAwareStrictChecking<SearchResult> {
	
	private final LocalizedSearcherCacheAwareStrictChecking<SearchResult> cacheAwareSearcher;
	private final LocalizedSearcherNetworkAwareStrictChecking<SearchResult> networkAwareSearcher;
	protected SearchResult result;
	
	@Inject
	public DefaultLocalizedSearcherCacheNetworkAwareStrictChecking(
			LocalizedSearcherCacheAwareStrictChecking<SearchResult> cacheAwareSearcher,
			LocalizedSearcherNetworkAwareStrictChecking<SearchResult> networkAwareSearcher) {
		this.cacheAwareSearcher = cacheAwareSearcher;
		this.networkAwareSearcher = networkAwareSearcher;
	}	

	@Override
	public Void search(Location... location) throws 
			NoNetworkException, NetworkAwareSearchException, 
			CacheTooOldException, CacheAwareSearchException {
		try { // trying network
			networkAwareSearcher.search(location); // network ok - it could throw NetworkSearchException
			result = networkAwareSearcher.getResult();
			return null;
		} catch (NoNetworkException nne) { // no network
//			try { // trying cache
				cacheAwareSearcher.search(location); // trying cache or throw CacheTooOldException or CacheAwareSearchException
				result = cacheAwareSearcher.getResult();
				throw nne; // but advice for no network
		}
	}
	
	@Override
	public SearchResult getResult() {
		return result;
	}
}
