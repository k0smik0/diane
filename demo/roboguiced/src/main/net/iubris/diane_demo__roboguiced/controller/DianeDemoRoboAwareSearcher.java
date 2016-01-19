/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * DianeRoboSampleAwareSearcher.java is part of Diane.
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
package net.iubris.diane_demo__roboguiced.controller;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.iubris.diane.aware.location.state.three.ThreeStateLocationAwareLocationSupplier;
import net.iubris.diane.searcher.aware.full.base.DefaultFullAwareSearcher;
import net.iubris.diane.searcher.location.aware.full.LocalizedSearcherCacheNetworkAwareStrictChecking;

@Singleton
public class DianeDemoRoboAwareSearcher extends DefaultFullAwareSearcher<String> {
	
//	protected String cacheResult;
//	protected String networkResult;
	
//	private final LocalizedSearcherCacheAwareStrictChecking<String> localizedSearcherCacheAware;
//	private final LocalizedSearcherNetworkAwareStrictChecking<String> localizedSearcherNetworkAware;
//	private final ThreeStateLocationAwareLocationSupplier threeStateLocationAwareLocationSupplier;

	@Inject
	public DianeDemoRoboAwareSearcher(ThreeStateLocationAwareLocationSupplier locationAwareSupplier,
			LocalizedSearcherCacheNetworkAwareStrictChecking<String> awareSearcher
//			,LocalizedSearcherCacheAwareStrictChecking<String> localizedSearcherCacheAware,
//			LocalizedSearcherNetworkAwareStrictChecking<String> localizedSearcherNetworkAware
			) {
		super(locationAwareSupplier, awareSearcher);
//		this.localizedSearcherCacheAware = localizedSearcherCacheAware;
//		this.localizedSearcherNetworkAware = localizedSearcherNetworkAware;
//Log.d("DianeRoboSampleSearcher:21","constructor");
	}

	@Override
	protected void initResult() {
		setResult("");
		// TODO Auto-generated method stub
		
	}
	
//	public Location getLocationJustForExamplePurpose() {
//		return locationAwareProvider.getLocation();
//	}
	
	/*public Void searchByNetworkJustForExamplePurpose(Void... arg0) throws LocationNotSoUsefulException, NoNetworkException, NetworkAwareSearchException {
		localizedSearcherNetworkAware.search( locationAwareProvider.getLocation() );
		networkResult = localizedSearcherNetworkAware.getResult();
		return null;
	}*/	
/*	public Void searchByCacheJustForExamplePurpose(Void... arg0) throws CacheTooOldException, CacheAwareSearchException {
		localizedSearcherCacheAware.search( locationAwareProvider.getLocation() );
		cacheResult = localizedSearcherCacheAware.getResult();
		return null;
	}*/

//	public String getResultByCache() {
//		return cacheResult;
//	}
//	public String getResultByNetwork() {
//		return networkResult;
//	}
}
