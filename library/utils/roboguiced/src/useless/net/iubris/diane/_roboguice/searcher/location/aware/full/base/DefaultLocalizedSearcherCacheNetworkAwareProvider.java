/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * DefaultLocalizedSearcherCacheNetworkAwareProvider.java is part of Diane.
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
package net.iubris.diane._roboguice.searcher.location.aware.full.base;

import javax.inject.Inject;
import javax.inject.Provider;

import net.iubris.diane.searcher.location.aware.cache.LocalizedSearcherCacheAware;
import net.iubris.diane.searcher.location.aware.full.base.DefaultLocalizedSearcherCacheNetworkAware;
import net.iubris.diane.searcher.location.aware.network.LocalizedSearcherNetworkAware;

public class DefaultLocalizedSearcherCacheNetworkAwareProvider<Result> implements Provider<DefaultLocalizedSearcherCacheNetworkAware<Result>> {

	private final LocalizedSearcherCacheAware<Result> cacheAwareSearcher;
	private final LocalizedSearcherNetworkAware<Result> networkAwareSearcher;
	
	@Inject
	public DefaultLocalizedSearcherCacheNetworkAwareProvider(
			LocalizedSearcherCacheAware<Result> cacheAwareSearcher,
			LocalizedSearcherNetworkAware<Result> networkAwareSearcher) {
		this.cacheAwareSearcher = cacheAwareSearcher;
		this.networkAwareSearcher = networkAwareSearcher;
	}

	@Override
	public DefaultLocalizedSearcherCacheNetworkAware<Result> get() {
		return new DefaultLocalizedSearcherCacheNetworkAware<Result>(cacheAwareSearcher,networkAwareSearcher);
	}
}
