/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * DefaultThreeStateLocationAwareLocationSupplier.java is part of Diane.
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
package net.iubris.diane.aware.location.state.three.base;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.iubris.diane.aware.location.state.three.ThreeStateLocationAwareLocationSupplier;
import net.iubris.diane.aware.location.state.three.base.annotation.DistanceMaximumThreshold;
import net.iubris.diane.searcher.aware.location.exceptions.base.LocationNotSoUsefulException;
import net.iubris.polaris.locator.core.provider.LocationProvider;
import net.iubris.polaris.locator.utils.LocationStrategiesUtils;
import net.iubris.polaris.locator.utils.exceptions.LocationNotSoFarException;
import android.location.Location;

/**
 * @author  Massimiliano Leone - k0smik0
 */
@Singleton
public class DefaultThreeStateLocationAwareLocationSupplier implements ThreeStateLocationAwareLocationSupplier {

	private final LocationProvider locationProvider;
	private final Integer distanceMaximumThreshold;
	
	private Location location;
	
	@Inject
	public DefaultThreeStateLocationAwareLocationSupplier(LocationProvider locationProvider, 
			@DistanceMaximumThreshold Integer distanceMaximumThreshold) {
		if (distanceMaximumThreshold <=0) 
			throw new NumberFormatException("Only positive value admitted for distanceMaximumThreshold");
		this.distanceMaximumThreshold = distanceMaximumThreshold;
		this.locationProvider = locationProvider;
//Log.d("DefaultThreeStateLocationAwareLocationSupplier:44","locationProvider: "+locationProvider.getClass().getSimpleName());
	}

	/**
	 * @return
	 * @uml.property  name="location"
	 */
	@Override
	public Location getLocation() {
		return location;
	}
	
	/**
	 * we have a three-state for location: useful, not useful, not so useful:<br/>
	 * <i>useful</i> if it is newer && it has higher accuracy<br/>
	 * <i>not so useful</i> if it is retrieved in same admitted area, according to constructor parameter "distanceMaximumThreshold"<br/>
	 * is (absolutely!) <i>not useful</i> if it doen't respect any of above rules<br/>
	 */
	@Override
	public boolean isNewLocationUseful() throws LocationNotSoUsefulException {
		// retrieve a newFreshLocation
		Location newFreshLocation = getFreshLocation();
//Log.d("DefaultThreeStateLocationAwareLocationSupplier:91","location is: "+location);
//Log.d("DefaultThreeStateLocationAwareLocationSupplier:92","newFreshLocation is: "+newFreshLocation);
//		LocationCheckers.checkIsNull
		if (location==null) { // it should never happen, but if so: any location is always preferable than null
			location = newFreshLocation;
//Log.d("DefaultThreeStateLocationAwareLocationSupplier:96","location was null, returning newFreshLocation: "+newFreshLocation);			
			return true;
		}
		boolean locationBetter = LocationStrategiesUtils.isLocationBetter(newFreshLocation, location, locationProvider.getMinimumTimeThreshold(), locationProvider.getMinimumDistanceThreshold());
		if (!locationBetter) {// it should never happen, but if so, return false
//Log.d("DefaultThreeStateLocationAwareLocationSupplier:101","location is not better, returning false");		
			return false;
		}
		
		// ok, is better = freshness and higher accuracy => so, check for fareness and update location
		// check for nearness
		boolean locationFar = false;
		try {
			locationFar = LocationStrategiesUtils.isLocationFar(newFreshLocation, location, distanceMaximumThreshold);
		} catch (LocationNotSoFarException e) {
			location = newFreshLocation; // always update location (1)
			throw new LocationNotSoUsefulException("location is not so far - new search is not useful",e);
		}
		if (!locationFar) { // near, returning false
			location = newFreshLocation; // always update location (2)
//Log.d("DefaultThreeStateLocationAwareLocationSupplier:115","location is near, returning false");
			return false;
		} 
//Log.d("DefaultThreeStateLocationAwareLocationSupplier:119","location is far, returning true");
		// it is far, so return true
		location = newFreshLocation; // always update location (3)
		return true;
	}
	
	protected Location getFreshLocation() {
//Log.d(this.getClass().getSimpleName()+":89","getting location by "+locationProvider);
//Log.d(this.getClass().getSimpleName()+":89","format location to 6 decimal places ");
//		DecimalFormat dec = new DecimalFormat("#.######");
		return locationProvider.getLocation();
	}
}
