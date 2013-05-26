package net.iubris.diane.searcher.location.aware.full;

import net.iubris.diane.aware.cache.exceptions.CacheStateException;
import net.iubris.diane.aware.network.exceptions.NetworkStateException;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import net.iubris.diane.searcher.location.LocalizedSearcher;
import android.location.Location;

public interface LocalizedSearcherCacheNetworkAware<Result>
 extends LocalizedSearcher<Result> { // use composition
//	extends LocalizedSearcherNetworkAware<Result>, LocalizedSearcherCacheAware<Result> { // use inheritance

	@Override
	public Void search(Location... locations) throws 
//		NoNetworkException,
		NetworkStateException,
		NetworkAwareSearchException, 
//		CacheTooOldException,
		CacheStateException,
		CacheAwareSearchException
//		, Exception
		;
//		StateException; 
}
