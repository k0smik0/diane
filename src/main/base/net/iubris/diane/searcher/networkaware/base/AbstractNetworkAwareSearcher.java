/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * AbstractNetworkAwareSearcher.java is part of 'Diane'.
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
package net.iubris.diane.searcher.networkaware.base;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import net.iubris.diane.searcher.networkaware.NetworkAwareSearcher;
import net.iubris.diane.searcher.networkaware.exceptions.state.NetworkStateException;

public abstract class AbstractNetworkAwareSearcher<SearchState, SearchResult> implements NetworkAwareSearcher<SearchState, SearchResult, Boolean> {

	private final ConnectivityManager connectivityManager;
	
	public AbstractNetworkAwareSearcher(ConnectivityManager connectivityManager) {
		this.connectivityManager = connectivityManager;
	}		
	
	@Override
	public Boolean isConnected() throws NetworkStateException {
		return isInternetOn(connectivityManager);	
	}
	
	public static final boolean isInternetOn(ConnectivityManager connectivityManager) {		
		// ARE WE CONNECTED TO THE NET
		if ( connectivityManager.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED ||
				connectivityManager.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTING ||
				connectivityManager.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING ||
				connectivityManager.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED ) {
			// MESSAGE TO SCREEN FOR TESTING (IF REQ)
			//Toast.makeText(this, connectionType + ” connected”, Toast.LENGTH_SHORT).show();
			return true;
		} else if ( connectivityManager.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED ||  connectivityManager.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED  ) {
			//System.out.println(“Not Connected”);
			return false;
		}
		return false;
	}
}
