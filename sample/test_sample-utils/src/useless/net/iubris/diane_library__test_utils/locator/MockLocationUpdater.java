package net.iubris.diane_library__test_utils.locator;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.iubris.diane_library__test_utils.injector.MockGpsLocationsInjector;
import net.iubris.diane_library__test_utils.locator.MockLocationProviderUpdater.MockLocationReceiver;
import net.iubris.polaris.locator.updater.LocationUpdater;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;

@Singleton
public class MockLocationUpdater implements LocationUpdater {

	private final LocationManager locationManager;
	private final PendingIntent pendingIntent;
	
	@Inject
	public MockLocationUpdater(Context context, LocationManager locationManager) {
		this.locationManager = locationManager;
		Intent intent = new Intent(context, MockLocationReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
	}

	@Override
	public void startLocationUpdates() {
        locationManager.requestLocationUpdates(MockGpsLocationsInjector.MOCK_GPS_PROVIDER, 5, 10, pendingIntent);
	}

	@Override
	public void stopLocationUpdates() {
		locationManager.removeUpdates(pendingIntent);
	}
}
