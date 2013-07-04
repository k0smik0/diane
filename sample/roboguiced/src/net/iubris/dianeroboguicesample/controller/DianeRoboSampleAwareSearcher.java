package net.iubris.dianeroboguicesample.controller;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.iubris.diane.aware.location.state.three.ThreeStateLocationAwareLocationSupplier;
import net.iubris.diane.searcher.aware.full.base.DefaultFullAwareSearcher;
import net.iubris.diane.searcher.location.aware.full.LocalizedSearcherCacheNetworkAwareStrictChecking;

@Singleton
public class DianeRoboSampleAwareSearcher extends DefaultFullAwareSearcher<String> {
	
//	protected String cacheResult;
//	protected String networkResult;
	
//	private final LocalizedSearcherCacheAwareStrictChecking<String> localizedSearcherCacheAware;
//	private final LocalizedSearcherNetworkAwareStrictChecking<String> localizedSearcherNetworkAware;
//	private final ThreeStateLocationAwareLocationSupplier threeStateLocationAwareLocationSupplier;

	@Inject
	public DianeRoboSampleAwareSearcher(ThreeStateLocationAwareLocationSupplier locationAwareSupplier,
			LocalizedSearcherCacheNetworkAwareStrictChecking<String> awareSearcher
//			,LocalizedSearcherCacheAwareStrictChecking<String> localizedSearcherCacheAware,
//			LocalizedSearcherNetworkAwareStrictChecking<String> localizedSearcherNetworkAware
			) {
		super(locationAwareSupplier, awareSearcher);
//		this.localizedSearcherCacheAware = localizedSearcherCacheAware;
//		this.localizedSearcherNetworkAware = localizedSearcherNetworkAware;
//Log.d("DianeRoboSampleSearcher:21","constructor");
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
