/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * DianeRoboSampleNetworkSearcher.java is part of Diane.
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
package net.iubris.dianeroboguicesample.controller;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.iubris.diane.aware.location.state.three.ThreeStateLocationAwareLocationSupplier;
import net.iubris.diane.aware.network.exceptions.NetworkStateException;
import net.iubris.diane.aware.network.exceptions.base.NoNetworkException;
import net.iubris.diane.aware.network.state.checker.base.DefaultCheckerStateNetworkAware;
import net.iubris.diane.searcher.aware.network.base.AbstractNetworkAwareSearcher;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import net.iubris.diane.searcher.exceptions.SearchException;
import net.iubris.diane.searcher.location.aware.network.LocalizedSearcherNetworkAwareStrictChecking;

@Singleton
public class DianeRoboSampleNetworkSearcher extends AbstractNetworkAwareSearcher<Void,String> {
	
	private String result;
	
	private final ThreeStateLocationAwareLocationSupplier locationAwareSupplier;
	private final LocalizedSearcherNetworkAwareStrictChecking<String> localizedSearcherNetworkAware;

	@Inject
	public DianeRoboSampleNetworkSearcher(DefaultCheckerStateNetworkAware networkAware,
			ThreeStateLocationAwareLocationSupplier locationAwareSupplier,
			LocalizedSearcherNetworkAwareStrictChecking<String> localizedSearcherNetworkAware) {
		super(networkAware);
		this.locationAwareSupplier = locationAwareSupplier;
		this.localizedSearcherNetworkAware = localizedSearcherNetworkAware;
	}
	
	@Override
	public String getResult() {
		return result;
	}
	
@Override
public Void search(Void... arg0) throws NoNetworkException,
		NetworkStateException, NetworkAwareSearchException, SearchException {
	locationAwareSupplier.isNewLocationUseful();
	return super.search(arg0);
}
	@Override
	protected Void doSearch() throws NoNetworkException, NetworkAwareSearchException {
		localizedSearcherNetworkAware.search( locationAwareSupplier.getLocation() );
		result = localizedSearcherNetworkAware.getResult();
		return null;
	}
	
	
}
