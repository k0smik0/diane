/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * MockGpsLocationsInjector.java is part of Diane.
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
package net.iubris.diane_library__test_utils.injector;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.util.Log;
import android.util.Pair;

@Singleton
public class MockGpsLocationsInjector {

//	public static String MOCK_GPS_PROVIDER = "MOCK_GPS_PROVIDER";
	public static String MOCK_GPS_PROVIDER = LocationManager.GPS_PROVIDER;
	
	private final LocationManager locationManager;
	private final LocationProvider gpsProvider;
	private final ExecutorService locationInjectExecutorService;
	
	private int locationInjectionInterval = 2;
	private boolean rotate;

	private final List<Pair<Double,Double>> locations;
	private final int locationsListBound;
	private int locationsIndex=0;

	@Inject
	public MockGpsLocationsInjector(LocationManager locationManager) {
		this.locationManager = locationManager;
		gpsProvider = locationManager.getProvider(LocationManager.GPS_PROVIDER);
		locationInjectExecutorService = Executors.newFixedThreadPool(1);
		locations = new ArrayList<Pair<Double,Double>>();
		Set<Pair<Double, Double>> keySet = MockLocations.locationsMap.keySet();
		locations.addAll( keySet );
		locationsListBound = keySet.size()-1;
	}
	
	public void startLocationsTest() {
Log.d("MockGpsLocationsInjector:39","start inject location");
		restoreGps();
		clearProvider();
		addProvider();
		rotate = true;
		locationInjectExecutorService.execute( locationInjectionRunnable );
	}
	public void stopLocationsTest() {
Log.d("MockGpsLocationsInjector:50","stop inject location");
		rotate = false;
		try {
Log.d("MockGpsLocationsInjector:53","wait for stop inject location");			
			locationInjectExecutorService.awaitTermination(1, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
			locationInjectExecutorService.shutdownNow();
		}
//		locationInjectExecutorService.shutdown();
		clearProvider();
		restoreGps();
	}
	public boolean isRunning() {
		return rotate;
	}
	/**
	 * @param locationInjectionInterval seconds - default is 10
	 */
	public void setLocationInjectionInterval(int locationInjectionInterval) {
		this.locationInjectionInterval = locationInjectionInterval;
	}

	private void addProvider() {
		if (!locationManager.isProviderEnabled(MOCK_GPS_PROVIDER)) {
Log.d("MockGpsLocationsInjector:85","adding and enabling "+MOCK_GPS_PROVIDER);
			locationManager.addTestProvider(MOCK_GPS_PROVIDER, false, false, true, false, false, true, true, 0, 5);
			locationManager.setTestProviderEnabled(MOCK_GPS_PROVIDER, true);
		}
	}
	private void clearProvider() {
		if (locationManager.isProviderEnabled(MOCK_GPS_PROVIDER)) {
			locationManager.clearTestProviderEnabled(MOCK_GPS_PROVIDER);
			locationManager.clearTestProviderLocation(MOCK_GPS_PROVIDER);
			locationManager.clearTestProviderStatus(MOCK_GPS_PROVIDER);
		}
	}
	private void restoreGps() {
		locationManager.addTestProvider(gpsProvider.getName(), 
				gpsProvider.requiresNetwork(), gpsProvider.requiresSatellite(), gpsProvider.requiresCell(), 
				gpsProvider.hasMonetaryCost(), gpsProvider.supportsAltitude(), gpsProvider.supportsSpeed(), 
				gpsProvider.supportsBearing(), gpsProvider.getPowerRequirement(), gpsProvider.getAccuracy());
		locationManager.setTestProviderEnabled(LocationManager.GPS_PROVIDER, true);
	}
	
	
	@SuppressLint("NewApi")
	private void setLocation(double latitude, double longitude) {
//		Location gpsMockLocation = new Location(MOCK_GPS_PROVIDER);
		Location gpsMockLocation = new Location(LocationManager.GPS_PROVIDER);
		gpsMockLocation.setLatitude(latitude);
		gpsMockLocation.setLongitude(longitude);
		gpsMockLocation.setAltitude(0);
		gpsMockLocation.setAccuracy(5);
		long now = new Date().getTime();
//Log.d("MockGpsLocationsInjector:96",""+now+" "+(new Date(now)).toGMTString());
		if (Build.VERSION.SDK_INT >=17)
			gpsMockLocation.setElapsedRealtimeNanos(now);
		gpsMockLocation.setTime(now);
//		locationManager.setTestProviderLocation(MOCK_GPS_PROVIDER, gpsMockLocation);
		locationManager.setTestProviderLocation(LocationManager.GPS_PROVIDER, gpsMockLocation);
	}
	
	
	private Runnable locationInjectionRunnable = new Runnable() {
		private void injectLocation(final List<Pair<Double, Double>> locations, final int locationsIndex) {
			Pair<Double, Double> latlng = locations.get( locationsIndex );
			Double latitude = latlng.first;
			Double longitude = latlng.second;
//			Log.d("MockLocationsInjector:130",".\n."+new Date().toGMTString()+"\n");
			Log.d("MockLocationsInjector:131","injecting "+MockLocations.locationsMap.get(latlng)+" ("+latitude+","+longitude+")");				
			setLocation(latitude,longitude);
		}
		@Override
		public void run() {
			while (rotate) {
Log.d("MockGpsLocationsInjector:137", "locationInjectionRunnable - rotate: "+rotate);				
				if (locationsIndex==locationsListBound)
					locationsIndex=0;
				injectLocation(locations, locationsIndex);
				locationsIndex++;
				try {
					Thread.sleep(locationInjectionInterval*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
Log.d("MockGpsLocationsInjector:143", "locationInjectionRunnable - rotate: "+rotate+" - exiting runnable");
			
			/*while (rotate) {
				for (Pair<Double, Double> latlng: MockLocations.locationsMap.keySet()) {
					Double latitude = latlng.first;
					Double longitude = latlng.second;
Log.d("MockLocationsInjector:115",".\n."+new Date().toGMTString()+"\n");
Log.d("MockLocationsInjector:116","injecting "+MockLocations.locationsMap.get(latlng)+" ("+latitude+","+longitude+")");				
					setLocation(latitude,longitude);
					try {
						Thread.sleep(locationInjectionInterval*1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}*/
		}
	};
}
