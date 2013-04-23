package net.iubris.diane.searcher.aware.location.base;

import net.iubris.diane.aware.location.exceptions.base.LocationNotSoUsefulException;
import net.iubris.diane.aware.location.state.three.ThreeStateLocationAware;
import net.iubris.diane.searcher.aware.location.LocationAwareSearcher;
import net.iubris.diane.searcher.aware.location.exceptions.LocationAwareSearchException;
import net.iubris.diane.searcher.exceptions.SearchException;

/**
 * @author  Massimiliano Leone - k0smik0
 */
public abstract class AbstractLocationAwareSearcher<SearchState, SearchResult> implements LocationAwareSearcher<SearchState, SearchResult> {

	/**
	 * @uml.property  name="locationAware"
	 * @uml.associationEnd  
	 */
	protected final ThreeStateLocationAware locationAware;
	
	public AbstractLocationAwareSearcher(ThreeStateLocationAware locationAware) {
		this.locationAware = locationAware;
	}

	@Override
	public SearchState search(Void... params) throws LocationNotSoUsefulException, LocationAwareSearchException, SearchException {
		locationAware.isLocationUseful();
		return doSearch();
	}

	protected abstract SearchState doSearch() throws LocationAwareSearchException;
}
