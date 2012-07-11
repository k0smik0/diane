/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * LocationUtils.java is part of 'Diane'.
 * 
 * 'Diane' is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * 'Diane' is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with 'Diane' ; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 ******************************************************************************/
package net.iubris.diane.searcher.locationaware.utils;

import android.location.Location;
import android.util.Log;

public class LocationUtils {
	public static boolean isLocationOlder(Location location, long timeMinimumThreshold ){		 
Log.d("LocationUtils.isLocationOlder","current time: "+System.currentTimeMillis());
Log.d("LocationUtils.isLocationOlder","location time: "+location.getTime());
Log.d("LocationUtils.isLocationOlder","timeMinimumThreshold: "+timeMinimumThreshold);
Log.d("LocationUtils.isLocationOlder","difference: "+ (System.currentTimeMillis() - location.getTime()) );
		if(System.currentTimeMillis() - location.getTime() > timeMinimumThreshold) return true;
		return false;
	}
	public static boolean isLocationFarer(Location actualLocation, Location oldLocation, float distanceMinimumThreshold){
		if (actualLocation.distanceTo(oldLocation) > distanceMinimumThreshold) return true;
		return false;
	}
}
