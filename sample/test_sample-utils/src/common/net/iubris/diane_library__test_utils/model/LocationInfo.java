package net.iubris.diane_library__test_utils.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import android.location.Location;
import android.util.Log;

public class LocationInfo {
	final double latitude;
	final double longitude;
	final String locationDate;
	final long diffTime;
	String address;
	
	public static LocationInfo buildLocationInfo(Location location){
		long locationTime = location.getTime();
		long now = new Date().getTime();
		long diffTime = now - locationTime;
		String locationDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss",Locale.getDefault()).format(new Date (locationTime));
		LocationInfo locationInfo = new LocationInfo(location.getLatitude(),location.getLongitude(),locationDate,diffTime,"sorry, no reverse geocoding");
		return locationInfo;
	}

	public LocationInfo(double latitude, double longitude, String locationDate, long diffTime, String address) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.locationDate = locationDate;
		this.diffTime = diffTime;
		this.address = address;
	}

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
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		long convert = TimeUnit.SECONDS.convert(diffTime, TimeUnit.MILLISECONDS);
		long now = new Date().getTime();
		String toString = "Hi, it's "+(new Date(now)).toGMTString()+" and\nYou are at "+latitude+","+longitude+",\ntaken at "+locationDate+",   "+convert+" seconds ago:\n"+address;
		Log.d("LocationInfo:67",toString);
		return toString;
	}
}