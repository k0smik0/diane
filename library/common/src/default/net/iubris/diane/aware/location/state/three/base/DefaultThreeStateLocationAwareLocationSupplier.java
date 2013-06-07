package net.iubris.diane.aware.location.state.three.base;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.iubris.diane.aware.location.state.three.ThreeStateLocationAwareLocationSupplier;
import net.iubris.diane.aware.location.state.three.base.annotation.DistanceMaximumThreshold;
import net.iubris.diane.searcher.aware.location.exceptions.base.LocationNotSoUsefulException;
import net.iubris.polaris.locator.provider.LocationProvider;
import net.iubris.polaris.locator.utils.LocationUtils;
import net.iubris.polaris.locator.utils.exceptions.LocationNotSoFarException;
import android.location.Location;
import android.util.Log;

/**
 * @author  Massimiliano Leone - k0smik0
 */
@Singleton
public class DefaultThreeStateLocationAwareLocationSupplier implements ThreeStateLocationAwareLocationSupplier {

	private final LocationProvider locationProvider;
	private final Integer distanceMaximumThreshold;
//	private final Integer timeMaximumThreshold;
	
//	private Integer distanceMaximumThreshold
	
	/**
	 * @uml.property  name="location"
	 */
	private Location location;
//	private Location freshLocation;
	
	
	@Inject
	public DefaultThreeStateLocationAwareLocationSupplier(LocationProvider locationProvider, 
			@DistanceMaximumThreshold Integer distanceMaximumThreshold) {
		if (distanceMaximumThreshold <=0) 
			throw new NumberFormatException("Only positive value admitted for distanceMaximumThreshold");
//		if (timeMaximumThreshold <=0) 
//			throw new NumberFormatException("Only positive value admitted for timeMinimumThreshold");
		this.distanceMaximumThreshold = distanceMaximumThreshold;
//		this.timeMaximumThreshold = timeMaximumThreshold;
		this.locationProvider = locationProvider;
Log.d("DefaultThreeStateLocationAwareLocationSupplier:44","locationProvider: "+locationProvider.getClass().getSimpleName());
		
		// init
//		this.location = locationProvider.getLocation();
//		this.location = getFreshLocation();
//Log.d("DefaultThreeStateLocationAwareLocationSupplier:49","constructor - init new location: "+location);
	}

	/**
	 * @return
	 * @uml.property  name="location"
	 */
	@Override
	public Location getLocation() {
		return location;
	}

//	@Override
		
	/*	public Boolean isInNewerLocation() throws LocationNotNewerException {
		try {
			Location freshLoc = updateFreshLocation();
			try {
				if (isFreshLocationNewer()) {
					this.location = freshLoc;
					return true;
				}
			} catch (LocationNotSoNewerException e) {
				throw new LocationNotNewerException(e,"location is not so old or not far - no search done");			
			}			
		} catch (NullPointerException e) { //this.location == null -> init not correctly 
			this.location = this.freshLocation;
			return true;
		}
		return false;
		return false;
	}*/
	
	/**
	 * we have a three-state for location: useful, not useful, not so useful:<br/>
	 * <i>useful</i> if it is newer && it has higher accuracy<br/>
	 * <i>not so useful</i> if it is retrieved in same admitted area, according to constructor parameter "distanceMaximumThreshold"<br/>
	 * is (absolutely!) <i>not useful</i> if it doen't respect any of above rules<br/>
	 */
	@Override
	public boolean isLocationUseful() throws LocationNotSoUsefulException {
		// retrieve a newFreshLocation
		Location newFreshLocation = getFreshLocation();
Log.d("DefaultThreeStateLocationAwareLocationSupplier:91","location is: "+location);
Log.d("DefaultThreeStateLocationAwareLocationSupplier:92","newFreshLocation is: "+newFreshLocation);
//		LocationCheckers.checkIsNull
		if (location==null) { // it should never happen, but if so: any location is always preferable than null
			location = newFreshLocation;
Log.d("DefaultThreeStateLocationAwareLocationSupplier:96","location was null, returning newFreshLocation: "+newFreshLocation);			
			return true;
		}
		boolean locationBetter = LocationUtils.isLocationBetter(newFreshLocation, location, locationProvider.getMinimumTimeThreshold(), locationProvider.getMinimumDistanceThreshold());
		if (!locationBetter) {// it should never happen, but if so, return false
Log.d("DefaultThreeStateLocationAwareLocationSupplier:101","location is not better, returning false");		
			return false;
		}
		
		// ok, is better = freshness and higher accuracy => so, check for fareness and update location
		// check for nearness
		boolean locationFar = false;
		try {
			locationFar = LocationUtils.isLocationFar(newFreshLocation, location, distanceMaximumThreshold);
		} catch (LocationNotSoFarException e) {
			location = newFreshLocation; // always update location (1)
			throw new LocationNotSoUsefulException(e,"location is not so far - new search is not useful");
		}
		if (!locationFar) { // near, returning false
			location = newFreshLocation; // always update location (2)
Log.d("DefaultThreeStateLocationAwareLocationSupplier:115","location is near, returning false");
			return false;
		} 
Log.d("DefaultThreeStateLocationAwareLocationSupplier:119","location is far, returning true");
		// it is far, so return true
		location = newFreshLocation; // always update location (3)
		return true;
		/* OLD
		try {
			// isLocationFar act a three-state: isFar, !isFar, notSoFar (exception)
Log.d("DefaultThreeStateLocationAwareLocationSupplier:87",location.toString());
Log.d("DefaultThreeStateLocationAwareLocationSupplier:88",newFreshLocation.toString());
			boolean isFar = 	LocationUtils.isLocationFar(newFreshLocation, location, distanceMaximumThreshold);
			// first state, it should never happen, because newFreshLocation is always best location - however, we want act "secure", so this returning false
			if (isFar) {
				Log.d("DefaultThreeStateLocationAwareLocationSupplier:92","isFar");
				return false;
			}
			// ok, location is not far, so update this.location
			location = newFreshLocation;
			updateFreshLocation(newFreshLocation); // and always update freshLocation
Log.d("DefaultThreeStateLocationAwareLocationSupplier:96","new location: "+location);
			return true;
		} catch (LocationNotSoFarException e) {
			// here the third-state: location is newer and closer, but not so far from current location (= within threshold bound), so we throw an exception advising that new freshLocation is not useful, and a new search should not be made (should not, but if you really want, you do) 
			updateFreshLocation(newFreshLocation); // always update freshLocation
			location = freshLocation; // and always update location
			throw new LocationNotSoUsefulException(e,"location is not so far - new search is not useful");
		}
//		boolean isBetter = LocationUtils.isLocationBetter(newFreshLocation, freshLocation, timeMinimumThreshold, distanceMaximumThreshold);
		
//			throw new LocationNotNewerException(e,"location is not so old or not far - no search done");
		*/
	}
	/*
	public void isNewLocationBetter() {
		Location freshLocation = locationProvider.getLocation();
		
	}*/
	
	/*private boolean isFreshLocationNewer() throws LocationNotSoNewerException {
		return LocationUtils.isLocationBetter(freshLocation, location, timeMinimumThreshold, distanceMaximumThreshold);
	}*/

	/*protected Location getFreshLocation() throws LocationFreshNullException {
		Location freshLocation = locationProvider.getLocation();
		if (freshLocation==null)
			throw new LocationFreshNullException("getting a new location result null - anything wrong?");
		return freshLocation;
	}*/
	protected Location getFreshLocation() {
		/*if (locationProvider.getLocation() == null)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
Log.d(this.getClass().getSimpleName()+":174","getting location by "+locationProvider);
		return locationProvider.getLocation();
	}
	
//	protected void updateFreshLocation(Location freshLocation) {
//		this.freshLocation = freshLocation;
//	}

}

