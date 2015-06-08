/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * SearchAsyncTask.java is part of Diane.
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
import net.iubris.diane.searcher.aware.exceptions.base.StillSearchException;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import net.iubris.diane.searcher.exceptions.SearchException;
import net.iubris.etask.EnhancedSafeAsyncTaskContexted;
import android.content.Context;
import android.os.Handler;

/**
 * A search task network/cache aware (but not location)<br/> 
 * @param <S> extends {@link Searcher}
 * @param <SearchParam> {@link Searcher#search(SearchParams...) } arguments type
 * @param <SearchStatus> {@link Searcher#search(SearchParams...) } result type - consider it as a state search execution
 * @param <SearchResult> {@link Searcher#getResult()} result type
 */
public abstract class SearchLocalizedAsyncTask 
<S extends Searcher<SearchParam, SearchStatus, SearchResult>, SearchParam, SearchStatus, SearchResult> 
extends EnhancedSafeAsyncTaskContexted<SearchResult> 
implements SearcherCallable<SearchResult> 
{

    protected SearchLocalizedAsyncTask(Context context) {
        super(context);
    }
    protected SearchLocalizedAsyncTask(Context context, Handler handler) {
        super(context,handler);
    }
    protected SearchLocalizedAsyncTask(Context context, Handler handler, Executor executor) {
        super(context, handler, executor);
    }
    protected SearchLocalizedAsyncTask(Context context, Executor executor) {
        super(context,executor);
    }
    

    @Override
    public abstract SearchResult call() throws 
    	/*LocationFreshNullException, LocationNotSoUsefulException, LocationStateException,
    	CacheTooOldException,CacheStateException,
    	NoNetworkException, NetworkStateException,
    	LocationAwareSearchException, NetworkAwareSearchException, CacheAwareSearchException, 
    	AwareSearchException, 	SearchException,*/ Exception;
	
    protected void onException(StillSearchException e) throws RuntimeException {}
    
    protected void onException(LocationStateException e) throws RuntimeException {}

    protected void onException(CacheTooOldException e) throws RuntimeException {}
    protected void onException(CacheStateException e) throws RuntimeException {}
    
    protected void onException(NoNetworkException e) throws RuntimeException {
		//showToast(R.string.exception_no_network, e);		
		//startNextActivity();
	}
    protected void onException(NetworkStateException e) throws RuntimeException {}
    
    
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
