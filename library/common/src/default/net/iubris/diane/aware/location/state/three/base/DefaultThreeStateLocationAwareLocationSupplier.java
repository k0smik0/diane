package net.iubris.diane.aware.location.state.three.base;

import javax.inject.Inject;

import net.iubris.diane.aware.location.exceptions.base.LocationNotSoUsefulException;
import net.iubris.diane.aware.location.state.three.ThreeStateLocationAwareLocationSupplier;
import net.iubris.diane.aware.location.state.three.base.annotation.DianeDistanceMaximumThreshold;
import net.iubris.polaris.locator.provider.LocationProvider;
import net.iubris.polaris.locator.utils.LocationUtils;
import net.iubris.polaris.locator.utils.exceptions.LocationNotSoFarException;
import android.location.Location;

/**
 * @author  Massimiliano Leone - k0smik0
 */
public class DefaultThreeStateLocationAwareLocationSupplier implements ThreeStateLocationAwareLocationSupplier {

	private final LocationProvider locationProvider;
	private final Integer distanceMaximumThreshold;
	
	/**
	 * @uml.property  name="location"
	 */
	private Location location;
	private Location freshLocation;
	
	@Inject
	public DefaultThreeStateLocationAwareLocationSupplier(LocationProvider locationProvider, 
			@DianeDistanceMaximumThreshold Integer distanceMaximumThreshold) {
		if (distanceMaximumThreshold <=0) 
			throw new NumberFormatException("Only positive value");
		this.locationProvider = locationProvider;
		this.distanceMaximumThreshold = distanceMaximumThreshold;
		
		// init
		this.location = locationProvider.getLocation();
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
	
	@Override
	public boolean isLocationUseful() throws LocationNotSoUsefulException {
//		updateFreshLocation();
		Location newFreshLocation = locationProvider.getLocation();
		if (location==null) { // any location is always preferable than null
			location = newFreshLocation;
			return true;
		}
		try {
			boolean isFar = 	LocationUtils.isLocationFar(newFreshLocation, location, distanceMaximumThreshold);
			if (isFar) {
				return false;
			}
			updateFreshLocation(newFreshLocation);
			location = freshLocation;
			return true;
		} catch (LocationNotSoFarException e) {
			updateFreshLocation(newFreshLocation);
			throw new LocationNotSoUsefulException(e,"location is not so far - new search is not useful");
		}
//		boolean isBetter = LocationUtils.isLocationBetter(newFreshLocation, freshLocation, timeMinimumThreshold, distanceMaximumThreshold);
		
//			throw new LocationNotNewerException(e,"location is not so old or not far - no search done");		 
	}
	/*
	public void isNewLocationBetter() {
		Location freshLocation = locationProvider.getLocation();
		
	}*/
	
	/*private boolean isFreshLocationNewer() throws LocationNotSoNewerException {
		return LocationUtils.isLocationBetter(freshLocation, location, timeMinimumThreshold, distanceMaximumThreshold);
	}*/
	protected void updateFreshLocation(Location location) {
		freshLocation = location;
	}

}
