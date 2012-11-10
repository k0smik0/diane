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
package net.iubris.diane.asynctask;

import java.util.concurrent.Executor;

import net.iubris.diane.asynctask.SearcherCallable;
import net.iubris.diane.searcher.Searcher;
import net.iubris.diane.searcher.exceptions.SearchException;
import net.iubris.diane.searcher.locationaware.exceptions.search.LocationAwareSearchException;
import net.iubris.diane.searcher.locationaware.exceptions.state.LocationNotNewerStateException;
import net.iubris.diane.searcher.networkaware.exceptions.state.NoNetworkException;
import net.iubris.etask.EnhancedSafeAsyncTaskContexted;
import android.content.Context;
import android.os.Handler;

public abstract class SearchAsyncTask 
<S extends Searcher<SearchStatus, SearchResult>, SearchStatus, SearchResult> 
extends EnhancedSafeAsyncTaskContexted<SearchStatus> 
implements SearcherCallable<SearchStatus>{

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
    public abstract SearchStatus call() throws LocationAwareSearchException,LocationNotNewerStateException,NoNetworkException,SearchException,Exception;
		
	protected void onException(LocationAwareSearchException e) {
		//UIUtils.showShortToast(R.string.exception_location_aware_search, e);
	}
	
	protected void onException(LocationNotNewerStateException e) {
		//UIUtils.showShortToast(R.string.exception_location_not_newer_state);
		//startNextActivity();
	}

	/**
	 * Calls onException(e) as default, and there calling onThrowable(e) and so Log.d.<br/>
	 * So, in UI, you could show a toast as: <br/> "something heavy wrong:<br/>please re-press button" 
	 * @param e
	 */
	protected void onException(NoNetworkException e) {
		//showToast(R.string.exception_no_network, e);		
		//startNextActivity();
	}
	
	/**
	 * Calls onException(e) as default, and there calling onThrowable(e) and so Log.d.<br/>
	 * So, in UI, you could show a toast as: <br/> "something heavy wrong:<br/>please re-press button" 
	 * @param e
	 */
	protected  void onException(SearchException e) {

	}
	//@Override
	
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
