package net.iubris.diane.searcher;

import net.iubris.diane.searcher.exceptions.SearchException;

public interface Searcher<SearchParams,SearchState,SearchResult> {
	public SearchState search(SearchParams... params) throws SearchException;
	public SearchResult getResult();
}
