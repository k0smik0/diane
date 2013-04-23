package net.iubris.dianevanillasample.controller;

import android.location.Location;
import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.aware.location.exceptions.base.LocationNotSoUsefulException;
import net.iubris.diane.aware.location.state.three.ThreeStateLocationAwareLocationSupplier;
import net.iubris.diane.aware.network.exceptions.base.NoNetworkException;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.aware.full.base.DefaultFullAwareSearcher;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import net.iubris.diane.searcher.exceptions.SearchException;
import net.iubris.diane.searcher.location.aware.full.LocalizedSearcherCacheNetworkAware;


public class DianeRoboSampleSearcher extends DefaultFullAwareSearcher<String> {
	
	protected String cacheResult;
	protected String networkResult;

	public DianeRoboSampleSearcher(ThreeStateLocationAwareLocationSupplier locationAware,
			LocalizedSearcherCacheNetworkAware<String> awareSearcher) {
		super(locationAware, awareSearcher);
	}
	
	public Location getLocationJustForExamplePurpose(){
		return null;
	}
	
	public Void searchByNetworkJustForExamplePurpose(Void... arg0) throws LocationNotSoUsefulException,
			NoNetworkException, NetworkAwareSearchException, SearchException {
		return null;
	}	
	public Void searchByCacheJustForExamplePurpose(Void... arg0) throws CacheTooOldException, CacheAwareSearchException, SearchException  {
		return null;
	}

	public String getResultByCache() {
		return cacheResult;
	}
	public String getResultByNetwork() {
		return networkResult;
	}
}
