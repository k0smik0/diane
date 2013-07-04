package net.iubris.diane_library__test_utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

}
