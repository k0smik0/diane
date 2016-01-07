/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * MockLocationProviderUpdater.java is part of Diane.
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
package net.iubris.diane_library__test_utils.locator;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.iubris.diane_library__test_utils.injector.MockGpsLocationsInjector;
import net.iubris.polaris.locator.core.provider.LocationProvider;
import net.iubris.polaris.locator.core.updater.LocationUpdater;
import net.iubris.polaris.locator.core.updater.OnLocationUpdatedCallback;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

@Singleton
public class MockLocationProviderUpdater implements LocationProvider, LocationUpdater {

	private final LocationManager locationManager;
	private final PendingIntent pendingIntent;
	private final Context context;	
	
	private Location location;
	private Integer maximumDistanceThreshold = 50;
	private Integer maximumTimeThreshold =  5*1000;
	private BroadcastReceiver receiver;

	final String locationUpdateAction = "net.iubris.diane_sample_utils.LOCATION_UPDATE_RECEIVED";
	private final String klc = LocationManager.KEY_LOCATION_CHANGED;

	@Inject
	public MockLocationProviderUpdater(LocationManager locationManager, Context context) {
		// common init
		this.locationManager = locationManager;
		this.context = context;
		
		// LocationUpdater init
//		Intent intent = new Intent(context, MockLocationReceiver.class);
		Intent intent = new Intent(locationUpdateAction);
		pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		receiver = new MockLocationReceiver();
		
		// LocationProvider init
		location = initLocation();
	}
	
	@Override
	public void startLocationUpdates() {
		locationManager.requestLocationUpdates(MockGpsLocationsInjector.MOCK_GPS_PROVIDER, 5, 10, pendingIntent);
		IntentFilter intentFilter = new IntentFilter(locationUpdateAction);
		context.registerReceiver(receiver, intentFilter);
	}
	@Override
	public void stopLocationUpdates() {
		context.unregisterReceiver(receiver);
		locationManager.removeUpdates(pendingIntent);
	}
	
	@Override
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
Log.d("MockLocationProviderUpdater:85","store new location: "+location);
		this.location = location;
	};
	
	private Location initLocation() {
		Location gpsLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (gpsLocation!=null) {
Log.d("MockLocationProviderUpdater:92", "found gps location: "+gpsLocation);
			return gpsLocation;
		}
		Location networkLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		if (networkLocation!=null) {
Log.d("MockLocationProvider:97", "found network location");
			return networkLocation;
		}
		Location passiveLocation = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
		if (passiveLocation!=null) {
Log.d("MockLocationProviderUpdater:102", "found passive location");
			return passiveLocation;
		}
		Location mockLocation = locationManager.getLastKnownLocation(MockGpsLocationsInjector.MOCK_GPS_PROVIDER);
Log.d("MockLocationProviderUpdater:106", "using mock location, we hope...");
		return mockLocation;
	}

	@Override
	public Integer getMinimumDistanceThreshold() {
		return maximumDistanceThreshold ;
	}

	@Override
	public Integer getMinimumTimeThreshold() {
		return maximumTimeThreshold ;
	}
	
	public class MockLocationReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			Log.d("MockLocationProviderUpdater:123","received intent, action:"+intent.getAction()+" "+intent.getExtras());
			Location location = (Location)intent.getExtras().get(klc);
			if (location!=null) {
Log.d("MockLocationProviderUpdater:126","@MockLocationReceiver - onReceive");
Log.d("MockLocationProviderUpdater:127","@MockLocationReceiver - setting location "+location);
				setLocation(location);
			}
			/*Location location = null;
			String klc = LocationManager.KEY_LOCATION_CHANGED;
			if (intent.hasExtra(klc)) {
				location = (Location) intent.getExtras().get(klc);
	Log.d("MockLocationReceiver@MockLocationProvider:75", "new fix! location: "+location);
				setLocation(location);
			}
			*/
		}
	}

	@Override
	public void startLocationUpdates(OnLocationUpdatedCallback arg0) {
		// TODO Auto-generated method stub
	}
	
}
