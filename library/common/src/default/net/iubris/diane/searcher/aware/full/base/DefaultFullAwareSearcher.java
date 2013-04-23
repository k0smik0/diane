package net.iubris.diane.searcher.aware.full.base;

import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.aware.location.exceptions.base.LocationNotSoUsefulException;
import net.iubris.diane.aware.location.state.three.ThreeStateLocationAwareLocationSupplier;
import net.iubris.diane.aware.network.exceptions.base.NoNetworkException;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.aware.full.FullAwareSearcher;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import net.iubris.diane.searcher.exceptions.SearchException;
import net.iubris.diane.searcher.location.aware.full.LocalizedSearcherCacheNetworkAware;
import android.location.Location;

/**
 * @author    Massimiliano Leone - k0smik0
 * @uml.dependency   supplier="net.iubris.dianedev.searcher.location.base.LocalizedSearcherCacheNetworkAware"
 */
public class DefaultFullAwareSearcher<Result> implements FullAwareSearcher<Result> {

	/**
	 * @uml.property  name="locationAware"
	 * @uml.associationEnd  
	 */
	private final ThreeStateLocationAwareLocationSupplier locationAwareProvider;
	/**
	 * @uml.property  name="localizedSearcherCacheNetworkAware"
	 * @uml.associationEnd  
	 */
	private final LocalizedSearcherCacheNetworkAware<Result> localizedSearcherCacheNetworkAware;
	
	public DefaultFullAwareSearcher(ThreeStateLocationAwareLocationSupplier locationAware,
			LocalizedSearcherCacheNetworkAware<Result> awareSearcher) {
		this.locationAwareProvider = locationAware;
		this.localizedSearcherCacheNetworkAware = awareSearcher;
	}

	@Override
	public Void search(Void... params) throws LocationNotSoUsefulException, NoNetworkException, CacheTooOldException, NetworkAwareSearchException, CacheAwareSearchException, SearchException {
		locationAwareProvider.isLocationUseful(); // if it is not newer, throw LocationNotSoUsefulException
		Location location = locationAwareProvider.getLocation();
		// use non-aware localizedsearcher as delegate
		localizedSearcherCacheNetworkAware.search(location);
		return null;
	}
	
	@Override
	public Result getResult() {
		return localizedSearcherCacheNetworkAware.getResult();
	}
	
}
