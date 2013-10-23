/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * DianeSampleSearcherProvider.java is part of Diane.
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
package net.iubris.diane_demo__vanilla.provider;

import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.aware.cache.states.three.ThreeStateCacheAware;
import net.iubris.diane.aware.location.state.three.ThreeStateLocationAwareLocationSupplier;
import net.iubris.diane.searcher.location.aware.cache.LocalizedSearcherCacheAwareStrictChecking;
import net.iubris.diane.searcher.location.aware.full.LocalizedSearcherCacheNetworkAwareStrictChecking;
import net.iubris.diane.searcher.location.aware.network.LocalizedSearcherNetworkAwareStrictChecking;
import net.iubris.diane.utils.AbstractDianeSearcherProvider;
import net.iubris.diane_demo__vanilla.controller.DianeDemoVanillaLocalizedSearcherCacheAwareStrictChecking;
import net.iubris.diane_demo__vanilla.controller.DianeDemoVanillaLocalizedSearcherNetworkAwareStrictChecking;
import net.iubris.diane_demo__vanilla.controller.DianeDemoVanillaSearcher;
import net.iubris.diane_library__test_utils.receiver.MockUtilsProvider;
import net.iubris.polaris.locator.provider.LocationProvider;
import android.content.Context;

public class DianeDemoVanillaSearcherProvider extends AbstractDianeSearcherProvider<DianeDemoVanillaSearcher,String> {

	private LocationProvider locationProvider;

	public DianeDemoVanillaSearcherProvider(Context context) {
		super(context);
		locationProvider = MockUtilsProvider.getInstance(context).getLocationProvider();
	}
	
	@Override
	public DianeDemoVanillaSearcher get() {
		final ThreeStateLocationAwareLocationSupplier threeStateLocationAwareLocationSupplier = getThreeStateLocationAwareLocationSupplier();
		LocalizedSearcherCacheNetworkAwareStrictChecking<String> localizedSearcherCacheNetworkAware = getLocalizedSearcherCacheNetworkAwareStrictChecking();
		final LocalizedSearcherCacheAwareStrictChecking<String> localizedSearcherCacheAware = getLocalizedSearcherCacheAwareStrictChecking();
		final LocalizedSearcherNetworkAwareStrictChecking<String> localizedSearcherNetworkAware = getLocalizedSearcherNetworkAwareStrictChecking();
		
		return new DianeDemoVanillaSearcher(threeStateLocationAwareLocationSupplier, 
				localizedSearcherCacheNetworkAware,
				localizedSearcherCacheAware,
				localizedSearcherNetworkAware);
		
		/*
		return new DianeSampleSearcher(threeStateLocationAwareLocationSupplier, localizedSearcherCacheNetworkAware) {
			@Override
			public Location getLocationJustForExamplePurpose() {
				return threeStateLocationAwareLocationSupplier.getLocation();
			}
			@Override
			public Void searchByNetworkJustForExamplePurpose(Void... arg0) throws SearchException {
				localizedSearcherNetworkAware.search(threeStateLocationAwareLocationSupplier.getLocation());
				networkResult = localizedSearcherNetworkAware.getResult();
				return null;
			}
			@Override
			public Void searchByCacheJustForExamplePurpose(Void... arg0) throws CacheTooOldException, CacheAwareSearchException, SearchException {
				localizedSearcherCacheAware.search(threeStateLocationAwareLocationSupplier.getLocation());
				cacheResult = localizedSearcherCacheAware.getResult();
				return null;
			}
		};
		*/
	}

	@Override
	protected LocalizedSearcherCacheAwareStrictChecking<String> getLocalizedSearcherCacheAwareStrictChecking() {
		ThreeStateCacheAware tsca = new ThreeStateCacheAware() {
			@Override
			public Boolean isCacheAvailable() throws CacheTooOldException {
				return true;
			}
		};
		return new DianeDemoVanillaLocalizedSearcherCacheAwareStrictChecking(tsca);
		/*
		return new AbstractLocalizedSearcherCacheAwareStrictChecking<String>(tsca) {
			private String result;
			@Override
			public String getResult() {
				return result;
			}
			@Override
			protected void doSearch(Location location) throws CacheAwareSearchException {
				result = "a dummy string from internal cache";
			}
		};*/
	}

	@Override
	protected LocalizedSearcherNetworkAwareStrictChecking<String> getLocalizedSearcherNetworkAwareStrictChecking() {
		return new DianeDemoVanillaLocalizedSearcherNetworkAwareStrictChecking( getCheckerStateNetworkAware(), context );
/*		return new AbstractLocalizedSearcherNetworkAwareStrictChecking<String>( getCheckerStateNetworkAware() ) {
			private String result;
			@Override
			public String getResult() {
				return result;
			}
			@Override
			protected void doSearch(Location location) throws NetworkAwareSearchException {
				StringBuilder contents = new StringBuilder(2048);
				URL url = null;
				BufferedReader br = null;
				String line ="";
				contents.append("This is: www.google.com/robots.txt");
				contents.append("\n");
				try {
					url = new URL("http://www.google.com/robots.txt");
					br = new BufferedReader(new InputStreamReader(url.openStream()));					
					while (line != null) {
		                line = br.readLine();
		                contents.append(line+"\n");
		            }
				} catch (MalformedURLException e) {
					e.printStackTrace();
					throw new NetworkAwareSearchException(e, "malformed url");
				} catch (IOException e) {
					throw new NetworkAwareSearchException(e, "i/o exception!");
				}
	            result = contents.toString();
			}
		};
		*/
	}

	@Override
	protected LocationProvider getLocationProvider() {
		return locationProvider;
		/*return new LocationProvider() {
			@Override
			public Location getLocation() {
				Location location = new Location("dummy");
				location.setLatitude(44.494692);
				location.setLongitude(11.342728);
				return location;
			}

			@Override
			public Integer getMaximumDistanceThreshold() {
				return null;
			}

			@Override
			public Integer getMaximumTimeThreshold() {
				return null;
			}
		};*/
	}

}
