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
import net.iubris.diane.aware.cache.exceptions.CacheStateException;
import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.aware.network.exceptions.NetworkStateException;
import net.iubris.diane.aware.network.exceptions.base.NoNetworkException;
import net.iubris.diane.searcher.Searcher;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.aware.exceptions.AwareSearchException;
import net.iubris.diane.searcher.aware.exceptions.base.StillSearchException;
import net.iubris.diane.searcher.aware.full.exception.NoNetworkAndCacheEmptyException;
import net.iubris.diane.searcher.aware.location.exceptions.base.LocationNotSoUsefulException;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import net.iubris.diane.searcher.exceptions.SearchException;
import net.iubris.etask.roboguiced.RoboEnhancedAsyncTask;
import android.content.Context;
import android.os.Handler;

/**
 * A search task network/cache aware (but not location)<br/> 
 * @param <S> extends {@link Searcher}
 * @param <SearchParam> {@link Searcher#search(SearchParams...) } arguments type
 * @param <SearchStatus> {@link Searcher#search(SearchParams...) } result type - consider it as a state search execution
 * @param <SearchResult> {@link Searcher#getResult()} result type
 */
public abstract class RoboSearchLocalizedAsyncTask 
<S extends Searcher<SearchParam, SearchStatus, SearchResult>, SearchParam, SearchStatus, SearchResult>
extends RoboEnhancedAsyncTask<SearchResult> 
implements SearcherCallable<SearchResult> {

    protected RoboSearchLocalizedAsyncTask(Context context) {
        super(context);
    }
    protected RoboSearchLocalizedAsyncTask(Context context, Handler handler) {
        super(context,handler);
    }
    protected RoboSearchLocalizedAsyncTask(Context context, Handler handler, Executor executor) {
        super(context, handler, executor);
    }
    protected RoboSearchLocalizedAsyncTask(Context context, Executor executor) {
        super(context,executor);
    }
    

    /*@Override
    public abstract SearchResult call() throws
    	LocationFreshNullException,
    	LocationNotSoUsefulException, LocationStateException,
    	CacheTooOldException,CacheStateException,
    	NoNetworkException, NetworkStateException,
    	LocationAwareSearchException, NetworkAwareSearchException, CacheAwareSearchException, 
    	AwareSearchException, 	SearchException, 
    	Exception;*/
	
    /**
	 * Default: do nothing
	 */
    protected void onException(StillSearchException e) throws RuntimeException {}
    /**
	 * Default: do nothing
	 */
    protected void onException(LocationNotSoUsefulException e) throws RuntimeException {
		//UIUtils.showShortToast(R.string.exception_location_not_newer_state);
		//startNextActivity();
	}

    /**
	 * Default: do nothing
	 */
    protected void onException(CacheTooOldException e) throws RuntimeException {}
    /**
	 * Default: do nothing
	 */
    protected void onException(CacheStateException e) throws RuntimeException {}
    
    /**
	 * Default: do nothing
	 */
    protected void onException(NoNetworkException e) throws RuntimeException {
		//showToast(R.string.exception_no_network, e);		
		//startNextActivity();
	}
    /**
	 * Default: do nothing
	 */
    protected void onException(NetworkStateException e) throws RuntimeException {}
    
    
	/**
	 * Default: do nothing
	 */
	protected void onException(CacheAwareSearchException e) throws RuntimeException {}
	/**
	 * Default: do nothing
	 */
	protected void onException(NetworkAwareSearchException e) throws RuntimeException {}
	
	/**
	 * default: do nothing
	 */
	protected void onException(NoNetworkAndCacheEmptyException arg0) throws RuntimeException {}
	
	/**
	 * Default: do nothing
	 */
	protected void onException(AwareSearchException e) throws RuntimeException {}
	
	
	/**
	 * Calls onException(e) as default, and there calling onThrowable(e) and so Log.d.<br/>
	 * So, in UI, you could show a toast as: "something heavy wrong: please re-press button"<br/>
	 * Default: do nothing 
	 * @param e
	 * default: do nothing
	 */
	protected  void onException(SearchException e) throws RuntimeException {}
	
	   
    
	/*@Override
	protected void onException(SearchException e) {
		e.printStackTrace();
		Toast.makeText(context, "something heavy wrong by google"+e.getMessage(), Toast.LENGTH_SHORT).show();
	}*/
	
	/*@Override 
    protected void onException(Exception e) { 
		e.printStackTrace();		
        Toast.makeText(context, "something wrong:\n"+e.getMessage(), Toast.LENGTH_SHORT).show(); 
    }*/
	
	/*@Override
	protected void onPreExecute() throws Exception {		
		//progressDialog.show();
	}
	*/
	/*
	@Override
	protected void onFinally() throws RuntimeException {
		//if (progressDialog.isShowing()) 
		//	progressDialog.dismiss();
					
	}*/
		
	/*private void showToast(int resourceId, Exception e) {		
		UIUtils.buildShortToast(context, UIUtils.getResourceString(resourceId,context) +"\n\n"+e.getMessage()).show();
	}
	private void showToast(int resourceId) {
		UIUtils.buildShortToast(context, UIUtils.getResourceString(resourceId,context)).show();
	}*/
}
