package net.iubris.diane.searcher.aware;

import net.iubris.diane.searcher.Searcher;
import net.iubris.diane.searcher.aware.exceptions.AwareSearchException;
import net.iubris.diane.searcher.aware.exceptions.base.StillSearchException;
import net.iubris.diane.searcher.exceptions.SearchException;

public interface AwareSearcher<SearchState, SearchResult> extends Searcher<Void, SearchState, SearchResult> {
	@Override
	public SearchState search(Void... param) throws StillSearchException, AwareSearchException, SearchException;
}
