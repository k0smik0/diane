package net.iubris.dianeroboguicesample.controller;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.aware.location.state.three.ThreeStateLocationAwareLocationSupplier;
import net.iubris.diane.aware.network.exceptions.base.NoNetworkException;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.aware.full.base.DefaultFullAwareSearcher;
import net.iubris.diane.searcher.aware.location.exceptions.base.LocationNotSoUsefulException;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import net.iubris.diane.searcher.location.aware.cache.LocalizedSearcherCacheAwareStrictChecking;
import net.iubris.diane.searcher.location.aware.full.LocalizedSearcherCacheNetworkAwareStrictChecking;
import net.iubris.diane.searcher.location.aware.network.LocalizedSearcherNetworkAwareStrictChecking;
import android.location.Location;
import android.util.Log;

@Singleton
public class DianeRoboSampleSearcher extends DefaultFullAwareSearcher<String> {
	
	protected String cacheResult;
	protected String networkResult;
	
	private final LocalizedSearcherCacheAwareStrictChecking<String> localizedSearcherCacheAware;
	private final LocalizedSearcherNetworkAwareStrictChecking<String> localizedSearcherNetworkAware;
//	private final ThreeStateLocationAwareLocationSupplier threeStateLocationAwareLocationSupplier;

	@Inject
	public DianeRoboSampleSearcher(ThreeStateLocationAwareLocationSupplier locationAwareSupplier,
			LocalizedSearcherCacheNetworkAwareStrictChecking<String> awareSearcher,
			LocalizedSearcherCacheAwareStrictChecking<String> localizedSearcherCacheAware,
			LocalizedSearcherNetworkAwareStrictChecking<String> localizedSearcherNetworkAware) {
		super(locationAwareSupplier, awareSearcher);
		this.localizedSearcherCacheAware = localizedSearcherCacheAware;
		this.localizedSearcherNetworkAware = localizedSearcherNetworkAware;
Log.d("DianeRoboSampleSearcher:21","constructor");
	}
	
	public Location getLocationJustForExamplePurpose() {
		return locationAwareProvider.getLocation();
	}
	
	public Void searchByNetworkJustForExamplePurpose(Void... arg0) throws LocationNotSoUsefulException, NoNetworkException, NetworkAwareSearchException {
		localizedSearcherNetworkAware.search( locationAwareProvider.getLocation() );
		networkResult = localizedSearcherNetworkAware.getResult();
		return null;
	}	
	public Void searchByCacheJustForExamplePurpose(Void... arg0) throws CacheTooOldException, CacheAwareSearchException {
		localizedSearcherCacheAware.search( locationAwareProvider.getLocation() );
		cacheResult = localizedSearcherCacheAware.getResult();
		return null;
	}

	public String getResultByCache() {
		return cacheResult;
	}
	public String getResultByNetwork() {
		return networkResult;
	}
}
