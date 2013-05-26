package net.iubris.diane.searcher.location.aware.network.base;

import android.location.Location;
import net.iubris.diane.aware.network.exceptions.base.NoNetworkException;
import net.iubris.diane.aware.network.state.checker.CheckerStateNetworkAware;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import net.iubris.diane.searcher.location.aware.network.LocalizedSearcherNetworkAwareStrictChecking;

/**
 * @author  Massimiliano Leone - k0smik0
 */
public abstract class AbstractLocalizedSearcherNetworkAwareStrictChecking<Result> 
	implements LocalizedSearcherNetworkAwareStrictChecking<Result> {

	/**
	 * @uml.property  name="networkAware"
	 * @uml.associationEnd  
	 */
	protected final CheckerStateNetworkAware networkAware;
	
	public AbstractLocalizedSearcherNetworkAwareStrictChecking(CheckerStateNetworkAware networkAware) {
		this.networkAware = networkAware;
	}

	@Override
	public Void search(Location... locations) throws NoNetworkException, NetworkAwareSearchException {
		networkAware.isConnected();
		doSearch(locations[0]);
		return null;
	}

	protected abstract void doSearch(Location location) throws NetworkAwareSearchException;

}
