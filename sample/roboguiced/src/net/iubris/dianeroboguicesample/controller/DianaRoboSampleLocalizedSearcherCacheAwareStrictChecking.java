package net.iubris.dianeroboguicesample.controller;

import javax.inject.Inject;

import net.iubris.diane.aware.cache.states.three.ThreeStateCacheAware;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.location.aware.cache.base.AbstractLocalizedSearcherCacheAwareStrictChecking;
import net.iubris.diane_library__test_utils.injector.MockLocations;
import net.iubris.diane_library__test_utils.model.LocationInfo;
import android.location.Location;
import android.util.Pair;

public class DianaRoboSampleLocalizedSearcherCacheAwareStrictChecking extends AbstractLocalizedSearcherCacheAwareStrictChecking<String> {

	private String result;
//	private final Map<Pair<Double,Double>,String> geolocationsToAddress = new HashMap<Pair<Double,Double>, String>();
	
	
	@Inject
	DianaRoboSampleLocalizedSearcherCacheAwareStrictChecking(ThreeStateCacheAware arg0) {
		super(arg0);
//		geolocationsToAddress.put(new Pair<Double,Double>(44.493287,11.376921),"via Massarenti 352");
//		geolocationsToAddress.put(new Pair<Double,Double>(44.493138,11.375912),"via Massarenti 250");
//		geolocationsToAddress.put(new Pair<Double,Double>(44.494531,11.342696),"Piazza del Nettuno 2");
//		geolocationsToAddress.put(new Pair<Double,Double>(44.493968,11.344762),"via degli Orefici 6");
	}

	@Override
	public String getResult() {
		return result;
	}

	@Override
	protected void doSearch(Location location) throws CacheAwareSearchException {
		LocationInfo locationInfo = LocationInfo.buildLocationInfo(location);
		Pair<Double,Double> latlng = new Pair<Double, Double>(location.getLatitude(),location.getLongitude());
		
		if (MockLocations.locationsMap.containsKey(latlng))
			locationInfo.setAddress( MockLocations.locationsMap.get(latlng) +" [from cache]");
		
//		if (geolocationsToAddress.containsKey(latlng))
//			locationInfo.setAddress( geolocationsToAddress.get(latlng) +" [from cache]");
//			result = "a dummy string from internal cache";
		result = locationInfo.toString();
	}
}