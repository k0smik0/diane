package net.iubris.diane_library__test_utils.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import net.iubris.diane_library__test_utils.LocationUtils;
import android.location.Location;
import android.util.Log;

public class LocationInfo {
	private Location location;
//	private double latitude;
//	private double longitude;
//	private String locationDate;
//	private long diffTime;
	private String address;
	
	private final long locationTime;
	
	
	/*
	public static LocationInfo buildLocationInfo(Location location){
		long now = new Date().getTime();
		long locationTime = location.getTime();		
		long diffTime = now - locationTime;
		String locationDate = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.SSS",Locale.getDefault()).format(new Date (locationTime));
		LocationInfo locationInfo = new LocationInfo(location.getLatitude(),location.getLongitude(),locationDate,diffTime,"sorry, no reverse geocoding");
		return locationInfo;
	}*/
	
	public LocationInfo(Location location) {
		this.location = location;
		locationTime = location.getTime();
		address = "";
	}

	/*
	private LocationInfo(double latitude, double longitude, String locationDate, long diffTime, String address) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.locationDate = locationDate;
		this.diffTime = diffTime;
		this.address = address;
	}*/
	
/*
	public double getLatitude() {
		return latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public String getLocationDate() {
		return locationDate;
	}
	public long getDiffTime() {
		return diffTime;
	}
	public String getAddress() {
		return address;
	}
	*/
	public void setAddress(String address) {
		this.address = address;
	}
	
//	@Override
	public String getInfo() {
		Date now = new Date();
		long nowTime = now.getTime();
//		Log.d("LocationInfo:71", ""+nowTime);
//		Log.d("LocationInfo:71", ""+locationTime);
		long diffTime = nowTime - locationTime;
		Log.d("LocationInfo:71", ""+diffTime);
//		long diffInSecond = diffTime/1000;
//		Log.d("LocationInfo:71", ""+diffInSecond);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.SSS",Locale.getDefault());
		
		String formattedDiff = formatDiff(diffTime);		
		
		String toString = "Hi, it's "+(sdf.format(now))+" and\n"
				+"your location is:\n"
				+LocationUtils.parseLocation(location)+"\n"
//				+diffInSecond+" seconds ago:\n"
				+"("+formattedDiff+" ago).\n"
				+"address: "+address;
//Log.d("LocationInfo:67",toString);
		return toString;
	}
	
	private String formatDiff(long milliseconds) {
		String daysString = "";
		String hoursString = "";
		String minutesString = "";
		String secondsString = "";
		
		String s = ""+milliseconds;
		String millisecondsString = s.substring(s.length()-3)+"ms";
//		Log.d("LocationInfo:100",milliseconds+" "+millisecondsString);
		
		int minuteStep = 1000*60;
		int hourStep = minuteStep*60;
		int dayStep = hourStep*24;
		
		if (milliseconds >= 1000) // at least 1 second
			secondsString = (int) (milliseconds / 1000)%60+"s ";
		if (milliseconds >= minuteStep) // at least 1 minute
			minutesString = (int) (milliseconds / minuteStep)%60+"m ";
		if (milliseconds >= hourStep) // at least 1 hour
			hoursString = (int) (milliseconds / hourStep)%24+"h ";
		if (milliseconds >= dayStep) // at least 1 day
			daysString = (int) (milliseconds / hourStep)%7+"d ";
		
		String r = daysString+hoursString+minutesString+secondsString+millisecondsString;
//		Log.d("LocationInfo:115",r);
		return r;
	}
}