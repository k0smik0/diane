package net.iubris.diane.searcher.aware.full;

import net.iubris.diane.searcher.aware.cache.CacheAwareSearcher;
import net.iubris.diane.searcher.aware.location.LocationAwareSearcher;
import net.iubris.diane.searcher.aware.network.NetworkAwareSearcher;
import net.iubris.diane.searcher.exceptions.SearchException;

public interface FullAwareSearcher<Result> extends
LocationAwareSearcher<Void, Result>, NetworkAwareSearcher<Void, Result>, CacheAwareSearcher<Void, Result> {
//AwareSearcher<Void, Result> {

	@Override
	public Void search(Void... params) throws SearchException;
}
