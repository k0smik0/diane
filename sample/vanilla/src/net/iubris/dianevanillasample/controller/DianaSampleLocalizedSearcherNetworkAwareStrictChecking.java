package net.iubris.dianevanillasample.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import net.iubris.diane.aware.network.state.checker.CheckerStateNetworkAware;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import net.iubris.diane.searcher.location.aware.network.base.AbstractLocalizedSearcherNetworkAwareStrictChecking;
import net.iubris.diane_library__test_utils.model.LocationInfo;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

public class DianaSampleLocalizedSearcherNetworkAwareStrictChecking extends AbstractLocalizedSearcherNetworkAwareStrictChecking<String> {
	
	private String result;
	private final Context context;
	
	public DianaSampleLocalizedSearcherNetworkAwareStrictChecking(CheckerStateNetworkAware networkAware, Context context) {
		super(networkAware);
		this.context = context;
	}
	
	@Override
	public String getResult() {
		return result;
	}
	@Override
	protected void doSearch(Location location) throws NetworkAwareSearchException {
		try {
//			geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 10);
			List<Address> fromLocation = new Geocoder(context,Locale.getDefault()).getFromLocation(location.getLatitude(), location.getLongitude(), 10);
			LocationInfo locationInfo = LocationInfo.buildLocationInfo(location); 
Log.d("DianeSampleLocalizedSearcherNetworkAwareStrictChecking:36",""+fromLocation.size());
			if (fromLocation != null && fromLocation.size() > 0) {
		        Address address = fromLocation.get(0);
		        String addressText = String.format("%s, %s, %s",
		                address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
		                address.getLocality(),
		                address.getCountryName());
Log.d("DianeSampleLocalizedSearcherNetworkAwareStrictChecking:43",addressText);
		        locationInfo.setAddress(addressText);
			}
			result = locationInfo.toString();
		} catch (IOException e1) {
			e1.printStackTrace();
			throw new NetworkAwareSearchException(e1);
		}
	}
}
