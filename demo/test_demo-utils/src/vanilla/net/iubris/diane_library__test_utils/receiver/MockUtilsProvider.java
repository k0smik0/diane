/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * MockUtilsProvider.java is part of Diane.
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
package net.iubris.diane_library__test_utils.receiver;

import net.iubris.diane_library__test_utils.injector.MockGpsLocationsInjector;
import net.iubris.diane_library__test_utils.locator.MockLocationProviderUpdater;
import net.iubris.polaris.locator.core.provider.LocationProvider;
import net.iubris.polaris.locator.core.updater.LocationUpdater;
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
