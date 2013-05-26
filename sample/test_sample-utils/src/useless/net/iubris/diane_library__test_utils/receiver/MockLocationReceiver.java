package net.iubris.diane_library__test_utils.receiver;


import javax.inject.Inject;

import net.iubris.diane_library__test_utils.locator.MockLocationProvider;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

public class MockLocationReceiver extends BroadcastReceiver {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Location location = null;
		String klc = LocationManager.KEY_LOCATION_CHANGED;
		if (intent.hasExtra(klc)) {
			location = (Location) intent.getExtras().get(klc);
Log.d("MockLocationReceiver:24", "new fix! location: "+location);
			mockLocatorProvider.setLocation(location);
		}
	}
}