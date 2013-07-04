package net.iubris.diane.searcher.aware.location.base;

import net.iubris.diane.aware.location.exceptions.LocationStateException;
import net.iubris.diane.aware.location.state.three.ThreeStateLocationAwareLocationSupplier;
import net.iubris.diane.searcher.aware.location.LocationAwareSearcher;
import net.iubris.diane.searcher.aware.location.exceptions.LocationAwareSearchException;
import net.iubris.diane.searcher.aware.location.exceptions.base.LocationNotSoUsefulException;
import net.iubris.diane.searcher.exceptions.SearchException;
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
	public SearchState search(Void... params) throws /*LocationFreshNullException,*/ LocationNotSoUsefulException, LocationStateException, LocationAwareSearchException, SearchException {
		boolean locationUseful = locationAware.isNewLocationUseful();
		if (locationUseful) {
			Location location = locationAware.getLocation();
			return doSearch(location);
		}
		return canNotSearch();
	}

	protected abstract SearchState doSearch(Location location) throws LocationStateException, LocationAwareSearchException;
	protected abstract SearchState canNotSearch() throws LocationStateException, LocationAwareSearchException;
}
