/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * SearchAsyncTask.java is part of 'Diane'.
 * 
 * 'Diane' is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * 'Diane' is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with 'Diane' ; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 ******************************************************************************/
package net.iubris.diane.asynctask.base;

import java.util.concurrent.Executor;

import net.iubris.diane.asynctask.SearcherCallable;
import net.iubris.diane.aware.cache.exceptions.CacheStateException;
import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.aware.location.exceptions.LocationStateException;
import net.iubris.diane.aware.network.exceptions.NetworkStateException;
import net.iubris.diane.aware.network.exceptions.base.NoNetworkException;
import net.iubris.diane.searcher.Searcher;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.aware.exceptions.AwareSearchException;
import net.iubris.diane.searcher.aware.location.exceptions.LocationAwareSearchException;
import net.iubris.diane.searcher.aware.location.exceptions.base.LocationNotSoUsefulException;
import net.iubris.diane.searcher.aware.location.exceptions.base.LocationTooNearException;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import net.iubris.diane.searcher.exceptions.SearchException;
import net.iubris.etask.EnhancedSafeAsyncTaskContexted;
import android.content.Context;
import android.os.Handler;

public abstract class SearchAsyncTask 
<S extends Searcher<SearchParam, SearchStatus, SearchResult>, SearchParam, SearchStatus, SearchResult> 
extends EnhancedSafeAsyncTaskContexted<SearchResult> 
implements SearcherCallable<SearchResult> 
{

    protected SearchAsyncTask(Context context) {
        super(context);
    }
    protected SearchAsyncTask(Context context, Handler handler) {
        super(context,handler);
    }
    protected SearchAsyncTask(Context context, Handler handler, Executor executor) {
        super(context, handler, executor);
    }
    protected SearchAsyncTask(Context context, Executor executor) {
        super(context,executor);
    }
    

    @Override
    public abstract SearchResult call() throws 
    	/*LocationFreshNullException, LocationNotSoUsefulException, LocationStateException,
    	CacheTooOldException,CacheStateException,
    	NoNetworkException, NetworkStateException,
    	LocationAwareSearchException, NetworkAwareSearchException, CacheAwareSearchException, 
    	AwareSearchException, 	SearchException,*/ Exception;
	
    protected void onException(LocationTooNearException e) throws RuntimeException {}
    
    protected void onException(LocationNotSoUsefulException e) throws RuntimeException {
		//UIUtils.showShortToast(R.string.exception_location_not_newer_state);
		//startNextActivity();
	}
    protected void onException(LocationStateException e) throws RuntimeException {}

    protected void onException(CacheTooOldException e) throws RuntimeException {}
    protected void onException(CacheStateException e) throws RuntimeException {}
    
    protected void onException(NoNetworkException e) throws RuntimeException {
		//showToast(R.string.exception_no_network, e);		
		//startNextActivity();
	}
    protected void onException(NetworkStateException e) throws RuntimeException {}
    
    
	protected void onException(LocationAwareSearchException e) throws RuntimeException {
		//UIUtils.showShortToast(R.string.exception_location_aware_search, e);
	}	
	protected void onException(CacheAwareSearchException e) throws RuntimeException {}
	protected void onException(NetworkAwareSearchException e) throws RuntimeException {}
	
	protected void onException(AwareSearchException e) throws RuntimeException {}
	
	
	/**
	 * Calls onException(e) as default, and there calling onThrowable(e) and so Log.d.<br/>
	 * So, in UI, you could show a toast as: <br/> "something heavy wrong:<br/>please re-press button" 
	 * @param e
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
