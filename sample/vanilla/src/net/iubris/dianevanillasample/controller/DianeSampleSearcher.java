package net.iubris.dianevanillasample.controller;

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


public class DianeSampleSearcher extends DefaultFullAwareSearcher<String> {
	
	protected String cacheResult;
	protected String networkResult;
	private final LocalizedSearcherCacheAwareStrictChecking<String> localizedSearcherCacheAware;
	private final LocalizedSearcherNetworkAwareStrictChecking<String> localizedSearcherNetworkAware;

	public DianeSampleSearcher(ThreeStateLocationAwareLocationSupplier locationAwareProvider,
			LocalizedSearcherCacheNetworkAwareStrictChecking<String> awareSearcher,
			LocalizedSearcherCacheAwareStrictChecking<String> localizedSearcherCacheAware,
			LocalizedSearcherNetworkAwareStrictChecking<String> localizedSearcherNetworkAware) {
		super(locationAwareProvider, awareSearcher);
		this.localizedSearcherCacheAware = localizedSearcherCacheAware;
		this.localizedSearcherNetworkAware = localizedSearcherNetworkAware;
	}
	
	public Location getLocationJustForExamplePurpose(){
		return locationAwareProvider.getLocation();
	}
	
	public Void searchByNetworkJustForExamplePurpose(Void... arg0) throws LocationNotSoUsefulException,
			NoNetworkException, NetworkAwareSearchException, SearchException {
		localizedSearcherNetworkAware.search( locationAwareProvider.getLocation() );
		networkResult = localizedSearcherNetworkAware.getResult();
		return null;
	}	
	public Void searchByCacheJustForExamplePurpose(Void... arg0) throws CacheTooOldException, CacheAwareSearchException, SearchException  {
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
