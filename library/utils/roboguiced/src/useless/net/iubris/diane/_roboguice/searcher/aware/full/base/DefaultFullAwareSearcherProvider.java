/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * DefaultFullAwareSearcherProvider.java is part of Diane.
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
package net.iubris.diane._roboguice.searcher.aware.full.base;

import javax.inject.Inject;
import javax.inject.Provider;

import net.iubris.diane.aware.location.state.three.ThreeStateLocationAwareLocationSupplier;
import net.iubris.diane.searcher.aware.full.base.DefaultFullAwareSearcher;
import net.iubris.diane.searcher.location.aware.full.LocalizedSearcherCacheNetworkAware;

public class DefaultFullAwareSearcherProvider<Result> implements Provider<DefaultFullAwareSearcher<Result>> {

	final private ThreeStateLocationAwareLocationSupplier threeStateLocationAwareLocationSupplier;
	final private LocalizedSearcherCacheNetworkAware<Result> localizedSearcherCacheNetworkAware;
	
	@Inject
	public DefaultFullAwareSearcherProvider(
			ThreeStateLocationAwareLocationSupplier threeStateLocationAwareLocationSupplier,
			LocalizedSearcherCacheNetworkAware<Result> localizedSearcherCacheNetworkAware) {
		this.threeStateLocationAwareLocationSupplier = threeStateLocationAwareLocationSupplier;
		this.localizedSearcherCacheNetworkAware = localizedSearcherCacheNetworkAware;
	}

	@Override
	public DefaultFullAwareSearcher<Result> get() {
		return new DefaultFullAwareSearcher<Result>(threeStateLocationAwareLocationSupplier, localizedSearcherCacheNetworkAware);
	}
}
