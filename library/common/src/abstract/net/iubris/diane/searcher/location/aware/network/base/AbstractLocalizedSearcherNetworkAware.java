package net.iubris.diane.searcher.location.aware.network.base;

import android.location.Location;
import net.iubris.diane.aware.network.exceptions.base.NoNetworkException;
import net.iubris.diane.aware.network.state.checker.CheckerStateNetworkAware;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import net.iubris.diane.searcher.location.aware.network.LocalizedSearcherNetworkAware;

/**
 * @author  Massimiliano Leone - k0smik0
 */
public abstract class AbstractLocalizedSearcherNetworkAware<Result> 
	implements LocalizedSearcherNetworkAware<Result> {

	/**
	 * @uml.property  name="networkAware"
	 * @uml.associationEnd  
	 */
	protected final CheckerStateNetworkAware networkAware;
	
	public AbstractLocalizedSearcherNetworkAware(CheckerStateNetworkAware networkAware) {
		this.networkAware = networkAware;
	}

	@Override
	public Void search(Location... location) throws NoNetworkException, NetworkAwareSearchException {
		networkAware.isConnected();
		doSearch(location[0]);
		return null;
	}

	protected abstract void doSearch(Location location) throws NetworkAwareSearchException;

}
