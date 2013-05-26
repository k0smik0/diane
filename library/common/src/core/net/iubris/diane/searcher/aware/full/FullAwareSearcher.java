package net.iubris.diane.searcher.aware.full;

import net.iubris.diane.aware.cache.exceptions.CacheStateException;
import net.iubris.diane.aware.location.exceptions.LocationStateException;
import net.iubris.diane.aware.network.exceptions.NetworkStateException;
import net.iubris.diane.searcher.aware.cache.CacheAwareSearcher;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.aware.location.LocationAwareSearcher;
import net.iubris.diane.searcher.aware.location.exceptions.LocationAwareSearchException;
import net.iubris.diane.searcher.aware.network.NetworkAwareSearcher;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;

public interface FullAwareSearcher<Result> extends
LocationAwareSearcher<Void, Result>, NetworkAwareSearcher<Void, Result>, CacheAwareSearcher<Void, Result> {
//AwareSearcher<Void, Result> {

	@Override
	public Void search(Void... params) throws 
		LocationStateException,
		LocationAwareSearchException,
		CacheStateException,
		CacheAwareSearchException,
		NetworkStateException,
		NetworkAwareSearchException;
}
