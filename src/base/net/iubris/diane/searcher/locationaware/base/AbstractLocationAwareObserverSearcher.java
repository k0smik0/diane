/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * AbstractLocationAwareObserverSearcher.java is part of 'Diane'.
 * 
 * 'Diane' is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * 'Diane' is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with 'Diane' ; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 ******************************************************************************/
package net.iubris.diane.searcher.locationaware.base;

import net.iubris.diane.searcher.locationaware.LocationAwareSearcher;
import net.iubris.diane.searcher.locationaware.exceptions.location.LocationNotNewerStateException;
import net.iubris.diane.searcher.locationaware.utils.LocationUtils;
import net.iubris.kusor.locator.KLocator;
import net.iubris.kusor.observatory.observer.LocationObserver;
import net.iubris.kusor.updater.LocationUpdater;


import android.location.Location;
import android.util.Log;

public abstract class AbstractLocationAwareObserverSearcher<State,Result> 
implements LocationAwareSearcher<State,Result,Boolean>, LocationObserver, LocationUpdater {
	
	private static String TAG = "AbstractLocationAwareObserverSearcher";

	private  KLocator kLocator;
	private final Integer distanceMinimumThreshold;
	//private final Integer distanceFactorThreshold;
	private final long timeMinimumThreshold;
	
	protected Location location;
	protected Location freshLocation;
	
	
	public AbstractLocationAwareObserverSearcher(KLocator kLocator,
			Integer distanceMinimumThreshold, 
			//Integer distanceFactorThreshold,
			long timeMinimumThreshold) {		
		//if (distanceFactorThreshold*distanceMinimumThreshold <=0) throw new NumberFormatException("Only positive value");
		if (distanceMinimumThreshold <=0) throw new NumberFormatException("Only positive value");
		this.kLocator = kLocator;
		this.distanceMinimumThreshold = distanceMinimumThreshold;
		//this.distanceFactorThreshold = distanceFactorThreshold;
		this.timeMinimumThreshold = timeMinimumThreshold;
	}	

	@Override
	public Location getLocation() {
		//if (location == null ) return freshLocation;
Log.d(TAG,""+location);
		return location;
	}
	
	@Override
	public void onLocationUpdate(Location location) {
Log.d(TAG,"new location: "+location);		
		this.freshLocation = location;
Log.d(TAG,"freshLocation: "+freshLocation);
	}
	
	@Override
	public Boolean isInNewerLocation() throws LocationNotNewerStateException {
/*
		if ( isLocationNull() ) return true; // first call
 
		final Location newLocation = kLocator.getLocation();
		if (newLocation.distanceTo(this.location) > distanceFactorThreshold*distanceMinimumThreshold) {
			this.location = newLocation;
			return true;
		}
		return false;
	*/			
		try {			
			if (isFreshLocationOlder()) { // is older
Log.d(TAG,"location older")	;
				/*
				Runnable locationRunnable = new Runnable() {					
					@Override
					public void run() {
						while (isFreshLocationOlder()) {
							Ln.d("while");							
							updateFreshLocation();
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {}
						}
					}					
				};
				Thread locationThread = new Thread(locationRunnable);
				locationThread.start();*/
				
				/*Callable<Void > callableLocation = new Callable<Void >() {
					@Override
					public Void call() throws Exception {
					*/	updateFreshLocation();
/*						return null;
					}
				};
				FutureTask<Void > locationFutureTask = new FutureTask<Void >(callableLocation);
				try {
					locationFutureTask.get();
				} catch (InterruptedException e) {					
				} catch (ExecutionException e) {	}
	*/			
				if (LocationUtils.isLocationFarer(freshLocation, location, distanceMinimumThreshold)) { // is updated AND is farer	
					return setNewLocation(freshLocation);
				}
			}
		} catch (NullPointerException e) { //this.freshLocation == null -> first call
Log.d(TAG,"first freshLocation");			
			return setNewLocation( updateFreshLocation() );
		}
		throw new LocationNotNewerStateException("location is not so old or not far - no search done");
	}
	
	private Location updateFreshLocation() {
		return kLocator.getLocation();
	}
	
	private boolean isFreshLocationOlder() {
		return LocationUtils.isLocationOlder(freshLocation, timeMinimumThreshold);
	}
	
	
	
	public Boolean setNewLocation(Location newLocation) {
		this.location = newLocation;
Log.d(TAG,""+location);
		return true;
	}	

	@Override
	public void startLocationUpdates() {	
		LocationObserver locationObserver = this;
Log.d(TAG,""+locationObserver);
		kLocator.attachObserver(locationObserver);
		kLocator.startLocationUpdates();		
	}

	@Override
	public void stopLocationUpdates() {
		kLocator.detachObserver(this);
		kLocator.stopLocationUpdates();
	}
}
