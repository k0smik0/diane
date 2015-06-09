/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * SearchAsyncTask.java is part of Diane.
 ******************************************************************************/
package net.iubris.diane.asynctask.base;

import java.util.concurrent.Executor;

import net.iubris.diane.asynctask.SearcherCallable;
import net.iubris.diane.searcher.Searcher;
import net.iubris.diane.searcher.aware.location.exceptions.LocationAwareSearchException;
import net.iubris.diane.searcher.aware.location.exceptions.base.LocationNotSoUsefulException;
import net.iubris.diane.searcher.aware.location.exceptions.base.LocationTooNearException;
import android.content.Context;
import android.os.Handler;

/**
 * A search task full (location/network/cache aware)<br/> 
 * @param <S> extends {@link Searcher}
 * @param <SearchParam> {@link Searcher#search(SearchParams...) } arguments type
 * @param <SearchStatus> {@link Searcher#search(SearchParams...) } result type - consider it as a state search execution
 * @param <SearchResult> {@link Searcher#getResult()} result type
 */
public abstract class SearchAwareAsyncTask 
<S extends Searcher<SearchParam, SearchStatus, SearchResult>, SearchParam, SearchStatus, SearchResult> 
//extends EnhancedSafeAsyncTaskContexted<SearchResult>
extends SearchLocalizedAsyncTask<Searcher<SearchParam,SearchStatus,SearchResult>, SearchParam, SearchStatus, SearchResult>
implements SearcherCallable<SearchResult> 
{

    protected SearchAwareAsyncTask(Context context) {
        super(context);
    }
    protected SearchAwareAsyncTask(Context context, Handler handler) {
        super(context,handler);
    }
    protected SearchAwareAsyncTask(Context context, Handler handler, Executor executor) {
        super(context, handler, executor);
    }
    protected SearchAwareAsyncTask(Context context, Executor executor) {
        super(context,executor);
    }
    

    protected void onException(LocationTooNearException e) throws RuntimeException {}
    protected void onException(LocationNotSoUsefulException e) throws RuntimeException {}
    protected void onException(LocationAwareSearchException e) throws RuntimeException {}	
}
