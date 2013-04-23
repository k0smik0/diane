package net.iubris.dianeroboguicesample._roboguice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.inject.Inject;

import net.iubris.diane._roboguice.module.AbstractDianeModule;
import net.iubris.diane._roboguice.searcher.location.aware.full.base.DefaultLocalizedSearcherCacheNetworkAwareProvider;
import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.aware.cache.states.three.ThreeStateCacheAware;
import net.iubris.diane.aware.location.exceptions.base.LocationNotSoUsefulException;
import net.iubris.diane.aware.location.state.three.ThreeStateLocationAwareLocationSupplier;
import net.iubris.diane.aware.network.exceptions.base.NoNetworkException;
import net.iubris.diane.aware.network.state.checker.CheckerStateNetworkAware;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import net.iubris.diane.searcher.exceptions.SearchException;
import net.iubris.diane.searcher.location.aware.cache.LocalizedSearcherCacheAware;
import net.iubris.diane.searcher.location.aware.cache.base.AbstractLocalizedSearcherCacheAware;
import net.iubris.diane.searcher.location.aware.full.LocalizedSearcherCacheNetworkAware;
import net.iubris.diane.searcher.location.aware.network.LocalizedSearcherNetworkAware;
import net.iubris.diane.searcher.location.aware.network.base.AbstractLocalizedSearcherNetworkAware;
import net.iubris.dianeroboguicesample.controller.DianeRoboSampleSearcher;
import net.iubris.polaris.locator.provider.LocationProvider;
import android.location.Location;

import com.google.inject.Provides;
import com.google.inject.TypeLiteral;

public class DianeRoboSampleModule extends AbstractDianeModule {

	@Override
	public void configure() {
		super.configure();
	}
	
	@Override
	protected void bindThreeStateCacheAware() {} // empty; instead, here we use provides annotation
	@Provides
	public ThreeStateCacheAware providesThreeStateCacheAware() {
		return new ThreeStateCacheAware() {
			@Override
			public Boolean isCacheAvailable() throws CacheTooOldException {
				return true;
			}
		};
	}
	@Override
	protected void bindLocalizedSearcherCacheAware() {
//		bind(LocalizedSearcherCacheAware.class).to(somethingimplementing);
	} // empty; instead, here we use provides annotation
	@Provides @Inject 
	public LocalizedSearcherCacheAware<String> providesLocalizedSearcherCacheAware(ThreeStateCacheAware tsca) {
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
	protected void bindLocalizedSearcherNetworkAware() {} // empty; instead, here we use provides annotation
	@Provides @Inject
	public LocalizedSearcherNetworkAware<String> providesLocalizedSearcherNetworkAware(CheckerStateNetworkAware csna) {
		return new AbstractLocalizedSearcherNetworkAware<String>( csna ) {
			private String result;
			@Override
			public String getResult() {
				return result;
			}
			@Override
			public void doSearch(Location location) throws NetworkAwareSearchException {
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
	protected void bindLocationProvider() {} // empty; instead, here we use provides annotation
	@Provides
	public LocationProvider providesLocationProvider() {
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
	
	@Override
	protected void bindLocalizedSearcherCacheNetworkAware() {
		bind( new TypeLiteral<LocalizedSearcherCacheNetworkAware<String>>(){}).toProvider(new TypeLiteral<DefaultLocalizedSearcherCacheNetworkAwareProvider<String>>(){});
	}
	/*@Override
	protected void bindFullAwareSearcher() {
		bind( new TypeLiteral<FullAwareSearcher<String>>(){}).toProvider(new TypeLiteral<DefaultFullAwareSearcherProvider<String>>(){});
	}*/
	
	@Provides @Inject
	public DianeRoboSampleSearcher providesDianeSampleSearcher(ThreeStateLocationAwareLocationSupplier locationAware,
			LocalizedSearcherCacheNetworkAware<String> awareSearcher,
			final LocalizedSearcherCacheAware<String> localizedSearcherCacheAware,
			final LocalizedSearcherNetworkAware<String> localizedSearcherNetworkAware,
			final ThreeStateLocationAwareLocationSupplier threeStateLocationAwareLocationSupplier) {
		
		return new DianeRoboSampleSearcher(locationAware, awareSearcher) {
			@Override
			public Location getLocationJustForExamplePurpose() {
				return threeStateLocationAwareLocationSupplier.getLocation();
			}
			@Override
			public Void searchByCacheJustForExamplePurpose(Void... arg0)
					throws CacheTooOldException, CacheAwareSearchException,
					SearchException {
				localizedSearcherCacheAware.search( threeStateLocationAwareLocationSupplier.getLocation() );
				cacheResult = localizedSearcherCacheAware.getResult();
				return null;
			}
			@Override
			public Void searchByNetworkJustForExamplePurpose(Void... arg0)
					throws LocationNotSoUsefulException, NoNetworkException,
					NetworkAwareSearchException, SearchException {
				localizedSearcherNetworkAware.search( threeStateLocationAwareLocationSupplier.getLocation() );
				networkResult = localizedSearcherNetworkAware.getResult();
				return null;
			}
		};
	}

}
