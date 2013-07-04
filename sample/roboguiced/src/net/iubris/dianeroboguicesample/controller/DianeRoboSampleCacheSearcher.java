package net.iubris.dianeroboguicesample.controller;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.iubris.diane.aware.cache.exceptions.CacheStateException;
import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.aware.cache.states.three.ThreeStateCacheAware;
import net.iubris.diane.aware.location.state.three.ThreeStateLocationAwareLocationSupplier;
import net.iubris.diane.searcher.aware.cache.base.AbstractCacheAwareSearcher;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.exceptions.SearchException;
import net.iubris.diane.searcher.location.aware.cache.LocalizedSearcherCacheAwareStrictChecking;
import android.util.Log;

@Singleton
public class DianeRoboSampleCacheSearcher extends AbstractCacheAwareSearcher<Void,String> {
	
	protected String result;
//	protected String networkResult;
	
	
//	private final LocalizedSearcherNetworkAwareStrictChecking<String> localizedSearcherNetworkAware;
	private final ThreeStateLocationAwareLocationSupplier locationAwareSupplier;
	private final LocalizedSearcherCacheAwareStrictChecking<String> localizedSearcherCacheAware;

	@Inject
	public DianeRoboSampleCacheSearcher(ThreeStateCacheAware threeStateCacheAware,
			ThreeStateLocationAwareLocationSupplier locationAwareSupplier,
			LocalizedSearcherCacheAwareStrictChecking<String> localizedSearcherCacheAware) {
//			LocalizedSearcherCacheNetworkAwareStrictChecking<String> awareSearcher,
//			LocalizedSearcherCacheAwareStrictChecking<String> localizedSearcherCacheAware
		super(threeStateCacheAware);
		this.locationAwareSupplier = locationAwareSupplier;
		this.localizedSearcherCacheAware = localizedSearcherCacheAware;
//		this.localizedSearcherNetworkAware = localizedSearcherNetworkAware;
Log.d("DianeRoboSampleSearcher:21","constructor");
	}
	
	/*public Location getLocationJustForExamplePurpose() {
		return locationAwareProvider.getLocation();
	}*/
	
/*
	public Void searchByNetworkJustForExamplePurpose(Void... arg0) throws LocationNotSoUsefulException, NoNetworkException, NetworkAwareSearchException {
		localizedSearcherNetworkAware.search( locationAwareProvider.getLocation() );
		networkResult = localizedSearcherNetworkAware.getResult();
		return null;
	}	
	public Void search(Void... arg0) throws CacheTooOldException, CacheAwareSearchException {
		localizedSearcherCacheAware.search( locationAwareProvider.getLocation() );
		cacheResult = localizedSearcherCacheAware.getResult();
		return null;
	}*/
	
	@Override
	public Void search(Void... arg0) throws CacheTooOldException,
			CacheStateException, CacheAwareSearchException, SearchException {
		locationAwareSupplier.isNewLocationUseful();
		return super.search(arg0);
	}

	@Override
	public String getResult() {
		return result;
	}

	@Override
	protected Void canNotSearch() throws CacheAwareSearchException {
		result = "sorry, can not search in cache";
		return null;
	}

	@Override
	protected Void doSearch() throws CacheTooOldException, CacheAwareSearchException {
//		threeStateLocationAwareLocationSupplier.getLocation();
		localizedSearcherCacheAware.search( locationAwareSupplier.getLocation() );
		result = localizedSearcherCacheAware.getResult();
		return null;
	}
}
