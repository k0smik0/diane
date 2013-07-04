package net.iubris.dianevanillasample.controller;

import net.iubris.diane.aware.cache.states.three.ThreeStateCacheAware;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.location.aware.cache.base.AbstractLocalizedSearcherCacheAwareStrictChecking;
import net.iubris.diane_library__test_utils.injector.MockLocations;
import net.iubris.diane_library__test_utils.model.LocationInfo;
import android.location.Location;
import android.util.Pair;

public class DianaSampleLocalizedSearcherCacheAwareStrictChecking extends AbstractLocalizedSearcherCacheAwareStrictChecking<String> {

	private String result;
	
	public DianaSampleLocalizedSearcherCacheAwareStrictChecking(ThreeStateCacheAware arg0) {
		super(arg0);
	}

	@Override
	public String getResult() {
		return result;
	}

	@Override
	protected void doSearch(Location location) throws CacheAwareSearchException {
		LocationInfo locationInfo = new LocationInfo(location);
		Pair<Double,Double> latlng = new Pair<Double, Double>(location.getLatitude(),location.getLongitude());
		
		if (MockLocations.locationsMap.containsKey(latlng))
			locationInfo.setAddress( MockLocations.locationsMap.get(latlng) +" [from cache]");
		
		result = locationInfo.getInfo();
	}
}