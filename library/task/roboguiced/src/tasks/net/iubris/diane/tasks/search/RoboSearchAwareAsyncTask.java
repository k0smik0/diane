/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * RoboSearchAsyncTask.java is part of Diane.
 * 
 * Diane is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Diane is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with Diane ; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 ******************************************************************************/
package net.iubris.diane.tasks.search;

import java.util.concurrent.Executor;

import net.iubris.diane.asynctask.SearcherCallable;
import net.iubris.diane.aware.location.exceptions.LocationStateException;
import net.iubris.diane.searcher.Searcher;
import net.iubris.diane.searcher.aware.location.exceptions.LocationAwareSearchException;
import net.iubris.diane.searcher.aware.location.exceptions.base.LocationTooNearException;
import android.content.Context;
import android.os.Handler;

/**
 * A search task full (location/network/cache) aware <br/> 
 * @param <S> extends {@link Searcher}
 * @param <SearchParam> {@link Searcher#search(SearchParams...) } arguments type
 * @param <SearchStatus> {@link Searcher#search(SearchParams...) } result type - consider it as a state search execution
 * @param <SearchResult> {@link Searcher#getResult()} result type
 */
public abstract class RoboSearchAwareAsyncTask 
<S extends Searcher<SearchParam, SearchStatus, SearchResult>, SearchParam, SearchStatus, SearchResult>
extends RoboSearchLocalizedAsyncTask<S,SearchParam,SearchStatus,SearchResult> 
implements SearcherCallable<SearchResult> 
{

    protected RoboSearchAwareAsyncTask(Context context) {
        super(context);
    }
    protected RoboSearchAwareAsyncTask(Context context, Handler handler) {
        super(context,handler);
    }
    protected RoboSearchAwareAsyncTask(Context context, Handler handler, Executor executor) {
        super(context, handler, executor);
    }
    protected RoboSearchAwareAsyncTask(Context context, Executor executor) {
        super(context,executor);
    }
    

    /**
	 * Default: do nothing
	 */
    protected void onException(LocationTooNearException e) throws RuntimeException {}
    /**
	 * Default: do nothing
	 */
    protected void onException(LocationStateException e) throws RuntimeException {}
    /**
	 * Default: do nothing
	 */
	protected void onException(LocationAwareSearchException e) throws RuntimeException {}	
}
