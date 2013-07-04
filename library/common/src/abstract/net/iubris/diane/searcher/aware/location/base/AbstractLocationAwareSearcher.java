/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * AbstractLocationAwareSearcher.java is part of Diane.
 * 
 * Diane is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Diane is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with Diane ; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 ******************************************************************************/
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
