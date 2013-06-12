package net.iubris.diane.searcher.aware.full.base;

import javax.inject.Inject;

import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.aware.location.state.three.ThreeStateLocationAwareLocationSupplier;
import net.iubris.diane.aware.network.exceptions.base.NoNetworkException;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.aware.exceptions.base.StillSearchException;
import net.iubris.diane.searcher.aware.full.FullAwareSearcher;
import net.iubris.diane.searcher.aware.location.exceptions.base.LocationNotSoUsefulException;
import net.iubris.diane.searcher.aware.location.exceptions.base.LocationTooNearException;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import net.iubris.diane.searcher.location.aware.full.LocalizedSearcherCacheNetworkAwareStrictChecking;
import android.location.Location;
import android.util.Log;

/**
 * @author    Massimiliano Leone - k0smik0
 * @uml.dependency   supplier="net.iubris.dianedev.searcher.location.base.LocalizedSearcherCacheNetworkAware"
 */
public class DefaultFullAwareSearcher<Result> implements FullAwareSearcher<Result> {

	protected final ThreeStateLocationAwareLocationSupplier locationAwareProvider;
	protected final LocalizedSearcherCacheNetworkAwareStrictChecking<Result> localizedSearcherCacheNetworkAware;
	
	protected Result result;
	private boolean searching = false;
	private boolean isFirstSearch = true;
	
	@Inject
	public DefaultFullAwareSearcher(ThreeStateLocationAwareLocationSupplier locationAware,
			LocalizedSearcherCacheNetworkAwareStrictChecking<Result> awareSearcher) {
		this.locationAwareProvider = locationAware;
		this.localizedSearcherCacheNetworkAware = awareSearcher;
	}

	@Override
	public Void search(Void... params) throws 
		LocationTooNearException, LocationNotSoUsefulException, 
		CacheTooOldException, NoNetworkException,
		CacheAwareSearchException, NetworkAwareSearchException, StillSearchException {
		
		if (searching==true) throw new StillSearchException("a search is still active");
		searching=true;
		
		if (isFirstSearch) { // first search
			try {
				locationAwareProvider.isLocationUseful();
			} catch(LocationNotSoUsefulException e) {} // first search, so we don't care about usefulness
			isFirstSearch = false;
			return doSearch();
		}
		
		boolean locationUseful = locationAwareProvider.isLocationUseful(); // if location is null, wait - if it is not useful (not so newer OR not so far OR etc), throws LocationNotSoUsefulException
		if (locationUseful) { // if location is not useful (not so newer OR not so far OR etc), throws LocationNotSoUsefulException
			//ok, we have "true", so start our search
			return doSearch();			
		}			
		// we are here because not throwed LocationNotSoUsefulException nor above "if" returned true: 
		// so location is definitely near and we throw below LocationTooNearException
Log.d("DefaultFullAwareSearcher:69","locationUseful is false, throwing LocationTooNearException");
		searching=false;
		throw new LocationTooNearException("location is too near, a new search is absolutely useless");
	}
	
	private Void doSearch() throws CacheTooOldException, NoNetworkException, CacheAwareSearchException, NetworkAwareSearchException {
		Location location = locationAwareProvider.getLocation();
Log.d("DefaultFullAwareSearcher:72","using location: "+location);
		// use non-aware localizedsearcher as delegate
		localizedSearcherCacheNetworkAware.search(location);
		result = localizedSearcherCacheNetworkAware.getResult();
		searching = false;
		return null;
	}
	
	@Override
	public Result getResult() {
		return result;
	}
	
}
