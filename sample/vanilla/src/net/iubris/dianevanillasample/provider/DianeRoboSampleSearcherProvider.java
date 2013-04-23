package net.iubris.dianevanillasample.provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.location.Location;
import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.aware.cache.states.three.ThreeStateCacheAware;
import net.iubris.diane.aware.location.state.three.ThreeStateLocationAwareLocationSupplier;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import net.iubris.diane.searcher.exceptions.SearchException;
import net.iubris.diane.searcher.location.aware.cache.LocalizedSearcherCacheAware;
import net.iubris.diane.searcher.location.aware.cache.base.AbstractLocalizedSearcherCacheAware;
import net.iubris.diane.searcher.location.aware.full.LocalizedSearcherCacheNetworkAware;
import net.iubris.diane.searcher.location.aware.network.LocalizedSearcherNetworkAware;
import net.iubris.diane.searcher.location.aware.network.base.AbstractLocalizedSearcherNetworkAware;
import net.iubris.diane.utils.AbstractDianeSearcherProvider;
import net.iubris.dianevanillasample.controller.DianeRoboSampleSearcher;
import net.iubris.polaris.locator.provider.LocationProvider;

public class DianeRoboSampleSearcherProvider extends AbstractDianeSearcherProvider<DianeRoboSampleSearcher,String> {

	public DianeRoboSampleSearcherProvider(Class<DianeRoboSampleSearcher> dfasClass,Context context) {
		super(dfasClass,context);
	}
	
	@Override
	public DianeRoboSampleSearcher get() {
		final ThreeStateLocationAwareLocationSupplier threeStateLocationAwareLocationSupplier = getThreeStateLocationAwareLocationSupplier();
		LocalizedSearcherCacheNetworkAware<String> localizedSearcherCacheNetworkAware = getLocalizedSearcherCacheNetworkAware();
		final LocalizedSearcherCacheAware<String> localizedSearcherCacheAware = getLocalizedSearcherCacheAware();
		final LocalizedSearcherNetworkAware<String> localizedSearcherNetworkAware = getLocalizedSearcherNetworkAware();
		return new DianeRoboSampleSearcher(threeStateLocationAwareLocationSupplier, localizedSearcherCacheNetworkAware) {
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
	}

	@Override
	protected LocalizedSearcherCacheAware<String> getLocalizedSearcherCacheAware() {
		ThreeStateCacheAware tsca = new ThreeStateCacheAware() {
			@Override
			public Boolean isCacheAvailable() throws CacheTooOldException {
				return true;
			}
		};
		return new AbstractLocalizedSearcherCacheAware<String>(tsca) {
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
	}

	@Override
	protected LocalizedSearcherNetworkAware<String> getLocalizedSearcherNetworkAware() {
		return new AbstractLocalizedSearcherNetworkAware<String>( getCheckerStateNetworkAware() ) {
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
	}

	@Override
	protected LocationProvider getLocationProvider() {
		return new LocationProvider() {
			@Override
			public Location getLocation() {
				Location location = new Location("dummy");
				location.setLatitude(44.494692);
				location.setLongitude(11.342728);
				return location;
			}
		};
	}

}
