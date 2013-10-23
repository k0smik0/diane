/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * DianaRoboSampleLocalizedSearcherCacheAwareStrictChecking.java is part of Diane.
 * 
 * Diane is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Diane is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Diane; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 ******************************************************************************/
package net.iubris.diane_demo__roboguiced.controller.searcher;

import javax.inject.Inject;

import net.iubris.diane.aware.cache.states.three.ThreeStateCacheAware;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.location.aware.cache.base.AbstractLocalizedSearcherCacheAwareStrictChecking;
import net.iubris.diane_library__test_utils.injector.MockLocations;
import net.iubris.diane_library__test_utils.model.LocationInfo;
import android.location.Location;
import android.util.Pair;

public class DianeDemoRoboLocalizedSearcherCacheAwareStrictChecking extends AbstractLocalizedSearcherCacheAwareStrictChecking<String> {

	private String result;
	
	@Inject
	DianeDemoRoboLocalizedSearcherCacheAwareStrictChecking(ThreeStateCacheAware threeStateCacheAware) {
		super(threeStateCacheAware);
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
