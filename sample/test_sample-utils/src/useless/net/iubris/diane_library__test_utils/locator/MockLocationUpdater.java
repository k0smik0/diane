/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * MockLocationUpdater.java is part of Diane.
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
