package net.iubris.diane.searcher.aware.network.base;

import net.iubris.diane.aware.network.exceptions.base.NoNetworkException;
import net.iubris.diane.aware.network.state.checker.CheckerStateNetworkAware;
import net.iubris.diane.searcher.aware.network.NetworkAwareSearcher;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import net.iubris.diane.searcher.exceptions.SearchException;

/**
 * @author  Massimiliano Leone - k0smik0
 */
public abstract class AbstractNetworkAwareSearcher<SearchState, SearchResult> implements NetworkAwareSearcher<SearchState,SearchResult> {

	/**
	 * @uml.property  name="networkAware"
	 * @uml.associationEnd  
	 */
	protected final CheckerStateNetworkAware networkAware;
	
	public AbstractNetworkAwareSearcher(CheckerStateNetworkAware networkAware) {
		this.networkAware = networkAware;
	}

	@Override
	public SearchState search(Void... params) throws NoNetworkException, NetworkAwareSearchException, SearchException {
		networkAware.isConnected();
		return doSearch();
	}
	
	protected abstract SearchState doSearch() throws NetworkAwareSearchException;
}
