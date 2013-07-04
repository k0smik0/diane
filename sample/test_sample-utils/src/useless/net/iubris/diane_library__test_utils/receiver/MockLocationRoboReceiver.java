/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * MockLocationRoboReceiver.java is part of Diane.
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


import javax.inject.Inject;

import net.iubris.diane_library__test_utils.locator.MockLocationProvider;
import roboguice.receiver.RoboBroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

public class MockLocationRoboReceiver extends RoboBroadcastReceiver {
	
	@Inject MockLocationProvider mockLocatorProvider; //singleton
	
	@Override
	protected void handleReceive(Context context, Intent intent) {
		Location location = null;
		String klc = LocationManager.KEY_LOCATION_CHANGED;
		if (intent.hasExtra(klc)) {
			location = (Location) intent.getExtras().get(klc);
Log.d("MockLocationRoboReceiver:24", "new fix! location: "+location);
			mockLocatorProvider.setLocation(location);
		}
	}

}
