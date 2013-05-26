package net.iubris.dianeroboguicesample._roboguice;



import net.iubris.diane._roboguice.module.AbstractDianeModule;
import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.aware.cache.states.three.ThreeStateCacheAware;
import net.iubris.diane.searcher.location.aware.cache.LocalizedSearcherCacheAwareStrictChecking;
import net.iubris.diane.searcher.location.aware.full.LocalizedSearcherCacheNetworkAwareStrictChecking;
import net.iubris.diane.searcher.location.aware.full.base.DefaultLocalizedSearcherCacheNetworkAwareStrictChecking;
import net.iubris.diane.searcher.location.aware.network.LocalizedSearcherNetworkAwareStrictChecking;
import net.iubris.diane_library__test_utils._roboguice.module.DianeTestUtilModule;
import net.iubris.dianeroboguicesample.controller.DianaRoboSampleLocalizedSearcherCacheAwareStrictChecking;
import net.iubris.dianeroboguicesample.controller.DianeRoboSampleLocalizedSearcherNetworkAwareStrictChecking;

import com.google.inject.Provides;
import com.google.inject.TypeLiteral;

public class DianeRoboSampleModule extends AbstractDianeModule {

	@Override
	protected void bindThreeStateCacheAware() {} // empty; instead, here we use provides annotation
	@Provides
	public ThreeStateCacheAware providesThreeStateCacheAware() {
		return new ThreeStateCacheAware() {
			@Override
			public Boolean isCacheAvailable() throws CacheTooOldException {
				double random = Math.random();
				if (random < 0.33) return true;
				if (random > 0.66) return false;
				throw new CacheTooOldException("cache too old: "+random);
			}
		};
	}
	@Override
	protected void bindLocalizedSearcherCacheAwareStrictChecking() {
		bind( new TypeLiteral<LocalizedSearcherCacheAwareStrictChecking<String>>(){}).to( new TypeLiteral<DianaRoboSampleLocalizedSearcherCacheAwareStrictChecking>(){});
	} 
	
	@Override
	protected void bindLocalizedSearcherNetworkAwareStrictChecking() {
		bind( new TypeLiteral<LocalizedSearcherNetworkAwareStrictChecking<String>>(){}).to( new TypeLiteral<DianeRoboSampleLocalizedSearcherNetworkAwareStrictChecking>(){});
	}
	
	// a dummy location provider from DianeTestUtil
	@Override
	protected void bindLocationProvider() {
		install( new DianeTestUtilModule() );
	}
	
	@Override
	protected void bindLocalizedSearcherCacheNetworkAwareStrictChecking() {
		bind( new TypeLiteral<LocalizedSearcherCacheNetworkAwareStrictChecking<String>>(){}).to(new TypeLiteral<DefaultLocalizedSearcherCacheNetworkAwareStrictChecking<String>>(){});
	}
	/*
	@Provides @Inject @Singleton
	public DianeRoboSampleSearcher providesDianeSampleSearcher(ThreeStateLocationAwareLocationSupplier locationAware,
			LocalizedSearcherCacheNetworkAwareStrictChecking<String> awareSearcher,
			final LocalizedSearcherCacheAwareStrictChecking<String> localizedSearcherCacheAware,
			final LocalizedSearcherNetworkAwareStrictChecking<String> localizedSearcherNetworkAware,
			final ThreeStateLocationAwareLocationSupplier threeStateLocationAwareLocationSupplier) {
		
		return new DianeRoboSampleSearcher(locationAware, awareSearcher) {
			@Override
			public Location getLocationJustForExamplePurpose() {
				return threeStateLocationAwareLocationSupplier.getLocation();
			}
			@Override
			public Void searchByCacheJustForExamplePurpose(Void... arg0) throws CacheTooOldException, CacheAwareSearchException, Exception {
				localizedSearcherCacheAware.search( threeStateLocationAwareLocationSupplier.getLocation() );
				cacheResult = localizedSearcherCacheAware.getResult();
				return null;
			}
			@Override
			public Void searchByNetworkJustForExamplePurpose(Void... arg0)
					throws LocationNotSoUsefulException, NoNetworkException,
					NetworkAwareSearchException, Exception {
				localizedSearcherNetworkAware.search( threeStateLocationAwareLocationSupplier.getLocation() );
				networkResult = localizedSearcherNetworkAware.getResult();
				return null;
			}
		};
		
	}*/

}
