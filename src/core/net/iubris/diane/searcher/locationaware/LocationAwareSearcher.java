/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * LocationAwareSearcher.java is part of diane.
 * 
 * diane is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * diane is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with diane ; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 ******************************************************************************/
package net.iubris.diane.searcher.locationaware;

import net.iubris.diane.searcher.Searcher;
import net.iubris.diane.searcher.exceptions.SearchException;
import net.iubris.diane.searcher.locationaware.exceptions.location.LocationNotNewerStateException;
import net.iubris.diane.searcher.locationaware.exceptions.location.LocationStateException;
import net.iubris.diane.searcher.locationaware.exceptions.search.LocationAwareSearchException;
import android.location.Location;

public interface LocationAwareSearcher<SearchState, SearchResult, LocationState> extends Searcher<SearchState,SearchResult> {
	public Location getLocation();
	public LocationState isInNewerLocation() throws LocationNotNewerStateException, LocationStateException;
	public SearchState search() throws LocationAwareSearchException, SearchException;
	
	//State locationAwareSearch(boolean isInNewerLocation) throws LocationAwareSearchException;
	//State locationAwareSearch(Location location) throws LocationAwareSearchException;
}
