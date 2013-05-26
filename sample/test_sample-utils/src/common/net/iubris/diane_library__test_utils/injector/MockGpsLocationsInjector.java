package net.iubris.diane_library__test_utils.injector;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.util.Log;
import android.util.Pair;

//@ContextSingleton
@Singleton
public class MockGpsLocationsInjector {

//	public static String MOCK_GPS_PROVIDER = "MOCK_GPS_PROVIDER";
	public static String MOCK_GPS_PROVIDER = LocationManager.GPS_PROVIDER;
	
//	private final Map<Pair<Double, Double>, String> mockLocations = new LinkedHashMap<Pair<Double,Double>, String>();
	private final LocationManager locationManager;
	
	private boolean rotate;

	private final LocationProvider gpsProvider;

	@Inject
	public MockGpsLocationsInjector(LocationManager locationManager) {
		this.locationManager = locationManager;
		
		gpsProvider = locationManager.getProvider(LocationManager.GPS_PROVIDER);
		
/*		mockLocations.put(new Pair<Double,Double>(44.493287,11.376921),"via Massarenti 352");
		mockLocations.put(new Pair<Double,Double>(44.493138,11.375912),"via Massarenti 250");
		
		mockLocations.put(new Pair<Double,Double>(44.493207,11.360119),"via Massarenti 54");
		mockLocations.put(new Pair<Double,Double>(44.49398,11.3568370),"via San Vitale 89");
		
		mockLocations.put(new Pair<Double,Double>(44.494282,11.352047),"via petroni 1");
		mockLocations.put(new Pair<Double,Double>(44.496176,11.35077),"piazza giuseppe verdi 4");
		
		mockLocations.put(new Pair<Double,Double>(44.494531,11.342696),"Piazza del Nettuno 2");
		mockLocations.put(new Pair<Double,Double>(44.493968,11.344762),"via degli Orefici 6");*/
	}
	
	public void startLocationsTest() {
		restoreGps();
		clearProvider();
		addProvider();
		
		rotate = true;
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		executorService.execute( new Runnable() {
			@Override
			public void run() {
				while (rotate) {
					for (Pair<Double, Double> latlng: MockLocations.locationsMap.keySet()) {
//						Toast.makeText(context, "we are in: "+latlng.getKey(), Toast.LENGTH_SHORT).show();
						Double latitude = latlng.first;
						Double longitude = latlng.second;
Log.d("MockLocationsInjector:66",".\n."+new Date().toGMTString()+"\n");
Log.d("MockLocationsInjector:67","injecting "+MockLocations.locationsMap.get(latlng)+" ("+latitude+","+longitude+")");				
						setLocation(latitude,longitude);
						try {
							Thread.sleep(7*1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		
	}
	public void stopLocationsTest() {
		rotate = false;
		clearProvider();
		restoreGps();
	}
	public boolean isRunning() {
		return rotate;
	}
	
	private void addProvider() {
		if (!locationManager.isProviderEnabled(MOCK_GPS_PROVIDER)) {
Log.d("MockGpsLocationsInjector:90","adding and enabling "+MOCK_GPS_PROVIDER);
			locationManager.addTestProvider(MOCK_GPS_PROVIDER, false, false, true, false, false, true, true, 0, 5);
			locationManager.setTestProviderEnabled(MOCK_GPS_PROVIDER, true);
		}
	}
	private void clearProvider() {
		/*List<String> allProviders = locationManager.getProviders(false);
		for (String ap: allProviders) 
			Log.d("MockGpsLocationsInjector:96",ap);
		List<String> enabledProviders = locationManager.getProviders(true);
		for (String ep: enabledProviders) 
			Log.d("MockGpsLocationsInjector:99",ep+" enabled");
		*/
		
		if (locationManager.isProviderEnabled(MOCK_GPS_PROVIDER)) {
			locationManager.clearTestProviderEnabled(MOCK_GPS_PROVIDER);
			locationManager.clearTestProviderLocation(MOCK_GPS_PROVIDER);
			locationManager.clearTestProviderStatus(MOCK_GPS_PROVIDER);
		}
		
		/*
		try {
			if (locationManager.isProviderEnabled(MOCK_GPS_PROVIDER)) {
				locationManager.setTestProviderEnabled(MOCK_GPS_PROVIDER, false);
				locationManager.removeTestProvider(MOCK_GPS_PROVIDER);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}*/
	}
	private void restoreGps() {
		locationManager.addTestProvider(gpsProvider.getName(), 
				gpsProvider.requiresNetwork(), gpsProvider.requiresSatellite(), gpsProvider.requiresCell(), 
				gpsProvider.hasMonetaryCost(), gpsProvider.supportsAltitude(), gpsProvider.supportsSpeed(), 
				gpsProvider.supportsBearing(), gpsProvider.getPowerRequirement(), gpsProvider.getAccuracy());
		locationManager.setTestProviderEnabled(LocationManager.GPS_PROVIDER, true);
	}
	
	
//	@SuppressLint("NewApi")
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
	
	/*public Map<Pair<Double, Double>, String> getMockLocations() {
		return mockLocations;
	}*/
	
}
