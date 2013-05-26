package net.iubris.diane.searcher.location.aware.network;

import net.iubris.diane.aware.network.exceptions.NetworkStateException;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import net.iubris.diane.searcher.location.LocalizedSearcher;
import android.location.Location;

public interface LocalizedSearcherNetworkAware<Result> extends LocalizedSearcher<Result> {
	@Override
	public Void search(Location... location) throws
		NetworkStateException
//		NoNetworkException
//,StateException
		, NetworkAwareSearchException 
//, SearchException
		;

}
