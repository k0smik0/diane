package net.iubris.dianeroboguicesample.controller.searcher;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import net.iubris.diane.aware.network.state.checker.CheckerStateNetworkAware;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import net.iubris.diane.searcher.location.aware.network.base.AbstractLocalizedSearcherNetworkAwareStrictChecking;
import net.iubris.diane_library__test_utils.model.LocationInfo;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

public class DianeRoboSampleLocalizedSearcherNetworkAwareStrictChecking extends AbstractLocalizedSearcherNetworkAwareStrictChecking<String> {

	private String result;
	private final Context context;
	private LocationInfo locationInfo;

	@Inject
	public DianeRoboSampleLocalizedSearcherNetworkAwareStrictChecking(Context context, CheckerStateNetworkAware checkerStateNetworkAware) {
		super(checkerStateNetworkAware);
		this.context = context;
	}

	@Override
	public String getResult() {
		return result;
	}

	@Override
	public void doSearch(Location location) throws NetworkAwareSearchException {
		try {
//			geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 10);
			locationInfo = new LocationInfo(location);
//Log.d("DianeRoboSampleLocalizedSearcherNetworkAwareStrictChecking:39",""+fromLocation.size());
//Log.d("DianeRoboSampleLocalizedSearcherNetworkAwareStrictChecking:48",addressText);

			String addressText = geocodingLocation(location);
			locationInfo.setAddress(addressText);
			
			result = locationInfo.getInfo();
		} catch (IOException e1) {
//			e1.printStackTrace();
			result = locationInfo.getInfo();
			throw new NetworkAwareSearchException(e1);
		}	
		/*
		StringBuilder contents = new StringBuilder(2048);
		URL url = null;
		BufferedReader br = null;
		String line ="";
		contents.append("This is: www.google.com/robots.txt");
		contents.append("\n");
		try {
			url = new URL("http://www.google.com/robots.txt");
			br = new BufferedReader(new InputStreamReader(url.openStream()));					
			while (line != null) {
	            line = br.readLine();
	            contents.append(line+"\n");
	        }
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new NetworkAwareSearchException(e, "malformed url");
		} catch (IOException e) {
			throw new NetworkAwareSearchException(e, "i/o exception!");
		}
	    result = result+"\n\n"+contents.toString();
	    */
	}
	
	private String geocodingLocation(Location location) throws IOException {
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