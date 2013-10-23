/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * DianeSampleSearcherBuilder.java is part of Diane.
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
package net.iubris.dianeroboguicesample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import net.iubris.dianeroboguicesample.controller.DianeRoboSampleSearcher;
import net.iubris.polaris.locator.provider.LocationProvider;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;

public class DianeSampleSearcherBuilder {
	
//	static interface ExampleLocalizedSearcherCacheNetworkAware extends LocalizedSearcherCacheNetworkAware<String> {
//		public LocalizedSearcherNetworkAware<String> getLocalizedSearcherNetworkAwareJustForExamplePurpose();
//		public LocalizedSearcherCacheAware<String> getLocalizedSearcherCacheAwareJustForExamplePurpose();
//	}
	
	static public DianeRoboSampleSearcher buildController(Context context) {
		ThreeStateCacheAware tsca = new ThreeStateCacheAware() {
			@Override
			public Boolean isCacheAvailable() throws CacheTooOldException {
				return true;
			}
		};
		final LocalizedSearcherCacheAware<String> lsca = new AbstractLocalizedSearcherCacheAware<String>(tsca) {
			private String result;
			@Override
			public String getResult() {
				return result;
			}
			@Override
			protected void doSearch(Location location) throws CacheAwareSearchException {
				result = "a dummy string from internal cache";
			}
		};
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		DefaultCheckerStateNetworkAware dcsna = new DefaultCheckerStateNetworkAware(cm);
		final LocalizedSearcherNetworkAware<String> lsna = new AbstractLocalizedSearcherNetworkAware<String>(dcsna) {
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
		 
		/*
		ExampleLocalizedSearcherCacheNetworkAware lscna = new ExampleLocalizedSearcherCacheNetworkAware() {
			
			private DefaultLocalizedSearcherCacheNetworkAware<String> dlscna = new DefaultLocalizedSearcherCacheNetworkAware<String>(lsca,lsna);
			
			@Override
			public Void search(Location... arg0) throws NoNetworkException,
					NetworkAwareSearchException, CacheTooOldException,
					CacheAwareSearchException, SearchException {
				return dlscna.search();
			}
			@Override
			public String getResult() {
				return dlscna.getResult();
			}
			@Override
			public LocalizedSearcherNetworkAware<String> getLocalizedSearcherNetworkAwareJustForExamplePurpose() {
				return lsna;
			}
			@Override
			public LocalizedSearcherCacheAware<String> getLocalizedSearcherCacheAwareJustForExamplePurpose() {
				return lsca;
			}
		};*/
			/*	DefaultLocalizedSearcherCacheNetworkAware<String>(lsca,lsna) {
			public LocalizedSearcherNetworkAware<String> getLocalizedSearcherNetworkAwareJustForExamplePurpose() {
				return lsna;
			}
			public LocalizedSearcherCacheAware<String> getLocalizedSearcherCacheAwareJustForExamplePurpose() {
				return lsca;
			}	*/
		
		final LocalizedSearcherCacheNetworkAware<String> lscna = new DefaultLocalizedSearcherCacheNetworkAware<String>(lsca,lsna);
		
		
		LocationProvider mockLocationProvider = new LocationProvider() {
			@Override
			public Location getLocation() {
				Location location = new Location(LocationManager.GPS_PROVIDER);
				location.setLatitude(44.494692);
				location.setLongitude(11.342728);
				return location;
			}
		};
		Integer maximumDistanceThreshold = 100; 
		final DefaultThreeStateLocationAware dtsla = new DefaultThreeStateLocationAware(mockLocationProvider,maximumDistanceThreshold);
		
		
		return new DianeRoboSampleSearcher(dtsla, lscna) {
			public Location getLocationJustForExamplePurpose() {
				return dtsla.getLocation();
			}
			@Override
			public Void searchByNetworkJustForExamplePurpose(Void... arg0) throws SearchException {
				lsna.search(dtsla.getLocation());
				networkResult = lsna.getResult();
				return null;
			}
			@Override
			public Void searchByCacheJustForExamplePurpose(Void... arg0) throws CacheTooOldException, CacheAwareSearchException, SearchException {
				lsca.search(dtsla.getLocation());
				cacheResult = lsca.getResult();
				return null;
			}			
		};		
	}
	
}
