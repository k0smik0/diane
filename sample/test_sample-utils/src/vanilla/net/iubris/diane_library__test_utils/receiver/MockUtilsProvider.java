package net.iubris.diane_library__test_utils.receiver;

import net.iubris.diane_library__test_utils.injector.MockGpsLocationsInjector;
import net.iubris.diane_library__test_utils.locator.MockLocationProviderUpdater;
import net.iubris.polaris.locator.provider.LocationProvider;
import net.iubris.polaris.locator.updater.LocationUpdater;
import android.content.Context;
import android.location.LocationManager;

public class MockUtilsProvider {

	private static MockUtilsProvider instance;
	
	private final MockLocationProviderUpdater mockLocationProviderUpdater;
//	private final MockLocationUpdater mockLocationUpdater;
	private final MockGpsLocationsInjector mockGpsLocationsInjector;
	
	private MockUtilsProvider(MockLocationProviderUpdater mockLocationProviderUpdater, MockGpsLocationsInjector mockGpsLocationsInjector) {
		this.mockLocationProviderUpdater = mockLocationProviderUpdater;
		this.mockGpsLocationsInjector = mockGpsLocationsInjector;
	}
	public static MockUtilsProvider getInstance(Context context) {
		if (instance==null) {
			LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
			instance = new MockUtilsProvider( 
//					new MockLocationProviderUpdater(locationManager,context),
					new MockLocationProviderUpdater(locationManager, context),
					new MockGpsLocationsInjector(locationManager) );
		}
		return instance;
	}

	public LocationProvider getLocationProvider() {
		return mockLocationProviderUpdater;
	}
	
	public LocationUpdater getLocationUpdater() {
		return mockLocationProviderUpdater;
	}
	public MockGpsLocationsInjector getMockGpsLocationsInjector() {
		return mockGpsLocationsInjector;
	}
}
