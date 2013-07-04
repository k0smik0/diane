/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * DianaSampleLocalizedSearcherNetworkAwareStrictChecking.java is part of Diane.
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
			LocationInfo locationInfo = new LocationInfo(location); 
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
			result = locationInfo.getInfo();
		} catch (IOException e1) {
			e1.printStackTrace();
			throw new NetworkAwareSearchException(e1);
		}
	}
}
