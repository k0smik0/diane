package net.iubris.diane_library__test_utils.locator;

import javax.inject.Inject;
import javax.inject.Singleton;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import net.iubris.diane_library__test_utils.injector.MockGpsLocationsInjector;
import net.iubris.polaris.locator.provider.LocationProvider;
import net.iubris.polaris.locator.updater.LocationUpdater;

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
		Log.d("MockLocationProviderUpdater:65","store new location: "+location);
		this.location = location;
	};
	
	private Location initLocation() {
		Location gpsLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (gpsLocation!=null) {
Log.d("MockLocationProviderUpdater:72", "found gps location: "+gpsLocation);
			return gpsLocation;
		}
		Location networkLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		if (networkLocation!=null) {
Log.d("MockLocationProvider:77", "found network location");
			return networkLocation;
		}
		Location passiveLocation = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
		if (passiveLocation!=null) {
Log.d("MockLocationProviderUpdater:82", "found passive location");
			return passiveLocation;
		}
		Location mockLocation = locationManager.getLastKnownLocation(MockGpsLocationsInjector.MOCK_GPS_PROVIDER);
Log.d("MockLocationProviderUpdater:88", "found mock location");
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
			Log.d("MockLocationProviderUpdater:104","received intent, action:"+intent.getAction()+" "+intent.getExtras());
			Location location = (Location)intent.getExtras().get(klc);
			if (location!=null) {
Log.d("MockLocationProviderUpdater:107","@MockLocationReceiver - onReceive");
Log.d("MockLocationProviderUpdater:108","@MockLocationReceiver - setting location "+location);
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
	
}
