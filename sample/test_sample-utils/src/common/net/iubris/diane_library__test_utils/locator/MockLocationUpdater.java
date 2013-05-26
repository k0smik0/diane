package net.iubris.diane_library__test_utils.locator;

import android.app.PendingIntent;
import android.location.LocationManager;
import net.iubris.diane_library__test_utils.injector.MockGpsLocationsInjector;
import net.iubris.polaris.locator.updater.LocationUpdater;

public class MockLocationUpdater implements LocationUpdater {

	private LocationManager locationManager;
	private PendingIntent pendingIntent;

	@Override
	public void startLocationUpdates() {
		locationManager.requestLocationUpdates(MockGpsLocationsInjector.MOCK_GPS_PROVIDER, 5, 10, pendingIntent);
	}

	@Override
	public void stopLocationUpdates() {
		locationManager.removeUpdates(pendingIntent);
	}

}
