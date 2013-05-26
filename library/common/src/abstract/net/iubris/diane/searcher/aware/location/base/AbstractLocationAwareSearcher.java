package net.iubris.diane.searcher.aware.location.base;

import net.iubris.diane.aware.location.state.three.ThreeStateLocationAwareLocationSupplier;
import net.iubris.diane.searcher.aware.location.LocationAwareSearcher;
import net.iubris.diane.searcher.aware.location.exceptions.LocationAwareSearchException;
import net.iubris.diane.searcher.aware.location.exceptions.base.LocationNotSoUsefulException;
import android.location.Location;

/**
 * @author  Massimiliano Leone - k0smik0
 */
public abstract class AbstractLocationAwareSearcher<SearchState, SearchResult> implements LocationAwareSearcher<SearchState, SearchResult> {

	/**
	 * @uml.property  name="locationAware"
	 * @uml.associationEnd  
	 */
	protected final ThreeStateLocationAwareLocationSupplier locationAware;
	
	public AbstractLocationAwareSearcher(ThreeStateLocationAwareLocationSupplier locationAware) {
		this.locationAware = locationAware;
	}

	@Override
	public SearchState search(Void... params) throws /*LocationFreshNullException,*/ LocationNotSoUsefulException, LocationAwareSearchException {
		boolean locationUseful = locationAware.isLocationUseful();
		if (locationUseful) {
			Location location = locationAware.getLocation();
			return doSearch(location);
		}
		return doNotSearch();
	}

	protected abstract SearchState doSearch(Location location) throws LocationAwareSearchException;
	protected abstract SearchState doNotSearch() throws LocationAwareSearchException;
}
