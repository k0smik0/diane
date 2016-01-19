/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * DianeSampleSearcher.java is part of Diane.
 * 
 * Diane is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Diane is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Diane; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 ******************************************************************************/
package net.iubris.diane_demo__vanilla.controller;

import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.aware.location.state.three.ThreeStateLocationAwareLocationSupplier;
import net.iubris.diane.aware.network.exceptions.base.NoNetworkException;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.aware.full.base.DefaultFullAwareSearcher;
import net.iubris.diane.searcher.aware.location.exceptions.base.LocationNotSoUsefulException;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import net.iubris.diane.searcher.exceptions.SearchException;
import net.iubris.diane.searcher.location.aware.cache.LocalizedSearcherCacheAwareStrictChecking;
import net.iubris.diane.searcher.location.aware.full.LocalizedSearcherCacheNetworkAwareStrictChecking;
import net.iubris.diane.searcher.location.aware.network.LocalizedSearcherNetworkAwareStrictChecking;
import android.location.Location;


public class DianeDemoVanillaSearcher extends DefaultFullAwareSearcher<String> {
	
	protected String cacheResult;
	protected String networkResult;
	private final LocalizedSearcherCacheAwareStrictChecking<String> localizedSearcherCacheAware;
	private final LocalizedSearcherNetworkAwareStrictChecking<String> localizedSearcherNetworkAware;

	public DianeDemoVanillaSearcher(ThreeStateLocationAwareLocationSupplier locationAwareSupplier,
			LocalizedSearcherCacheNetworkAwareStrictChecking<String> awareSearcher,
			LocalizedSearcherCacheAwareStrictChecking<String> localizedSearcherCacheAware,
			LocalizedSearcherNetworkAwareStrictChecking<String> localizedSearcherNetworkAware) {
		super(locationAwareSupplier, awareSearcher);
		this.localizedSearcherCacheAware = localizedSearcherCacheAware;
		this.localizedSearcherNetworkAware = localizedSearcherNetworkAware;
	}
	
	public Location getLocationJustForExamplePurpose(){
		return locationAwareSupplier.getLocation();
	}
	
	public Void searchByNetworkJustForExamplePurpose(Void... arg0) throws LocationNotSoUsefulException,
			NoNetworkException, NetworkAwareSearchException, SearchException {
		localizedSearcherNetworkAware.search( locationAwareSupplier.getLocation() );
		networkResult = localizedSearcherNetworkAware.getResult();
		return null;
	}	
	public Void searchByCacheJustForExamplePurpose(Void... arg0) throws CacheTooOldException, CacheAwareSearchException, SearchException  {
		localizedSearcherCacheAware.search( locationAwareSupplier.getLocation() );
		cacheResult = localizedSearcherCacheAware.getResult();
		return null;
	}

	public String getResultByCache() {
		return cacheResult;
	}
	public String getResultByNetwork() {
		return networkResult;
	}

	@Override
	protected void initResult() {
		setResult("");
	}
}
