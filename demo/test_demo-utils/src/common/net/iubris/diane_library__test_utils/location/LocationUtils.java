/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * LocationUtils.java is part of Diane.
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
package net.iubris.diane_library__test_utils.location;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

public class LocationUtils {
	
	
	public static String parseLocation(Location location) {
		long locationTime = location.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.SSS",Locale.getDefault());
		String formattedDate = sdf.format( new Date(locationTime));
		DecimalFormat df = new DecimalFormat("##.######");
		String r = "latitude: " + df.format(location.getLatitude())
				+", longitude: " + df.format(location.getLongitude())+"\n"
				+"at: "+formattedDate+"\n"
				+"by: "+location.getProvider()+"\n"
				+"accuracy: "+location.getAccuracy();		
		return r;				
	}
	
	public static String geocodeLocation(Location location, Context context) throws IOException {
		String addressText = "";
		List<Address> fromLocation = new Geocoder(context,Locale.getDefault()).getFromLocation(location.getLatitude(), location.getLongitude(), 10);
		if (fromLocation != null && fromLocation.size() > 0) {
			Address address = fromLocation.get(0);
			addressText = String.format("%s, %s, %s",
					address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
					address.getLocality(),
					address.getCountryName());
		}			
		return addressText;
	}

}
