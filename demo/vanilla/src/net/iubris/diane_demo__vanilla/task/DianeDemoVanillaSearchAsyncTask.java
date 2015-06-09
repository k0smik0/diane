/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * DianeSampleSearchAsyncTask.java is part of Diane.
 * 
 * Diane is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Diane is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Diane; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 ******************************************************************************/
package net.iubris.diane_demo__vanilla.task;

import net.iubris.diane.asynctask.base.SearchAwareAsyncTask;
import net.iubris.diane.aware.cache.exceptions.CacheStateException;
import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.aware.location.exceptions.LocationStateException;
import net.iubris.diane.aware.network.exceptions.NetworkStateException;
import net.iubris.diane.aware.network.exceptions.base.NoNetworkException;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.aware.exceptions.AwareSearchException;
import net.iubris.diane.searcher.aware.exceptions.base.StillSearchException;
import net.iubris.diane.searcher.aware.location.exceptions.LocationAwareSearchException;
import net.iubris.diane.searcher.aware.location.exceptions.base.LocationNotSoUsefulException;
import net.iubris.diane.searcher.aware.location.exceptions.base.LocationTooNearException;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import net.iubris.diane.searcher.exceptions.SearchException;
import net.iubris.diane_demo__vanilla.controller.DianeDemoVanillaSearcher;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class DianeDemoVanillaSearchAsyncTask extends SearchAwareAsyncTask<DianeDemoVanillaSearcher, Void, Void, String> {
	
	final private DianeDemoVanillaSearcher dianeSampleSearcher;
	
//	private String result;
	
	public DianeDemoVanillaSearchAsyncTask(Context context, DianeDemoVanillaSearcher dianeSampleController) {
		super(context);
		this.dianeSampleSearcher = dianeSampleController;
	}
	@Override
	public String call() throws StillSearchException, LocationNotSoUsefulException,
			LocationStateException, CacheTooOldException, CacheStateException,
			NoNetworkException, NetworkStateException,
			LocationAwareSearchException, NetworkAwareSearchException,
			CacheAwareSearchException, AwareSearchException, SearchException,
			Exception {
		dianeSampleSearcher.search();
		return dianeSampleSearcher.getResult();
	}
	@Override
	protected void onSuccess(String result) throws Exception {
		Toast.makeText(context, result, Toast.LENGTH_LONG).show();
	}
	protected void onException(StillSearchException e) throws RuntimeException {
		showException(e);
	}
//	@Override
	protected void onException(NoNetworkException e) throws RuntimeException {
		showException(e,"from cache: \n"+dianeSampleSearcher.getResult());
	};
	
	@Override
	protected void onException(LocationTooNearException e) throws RuntimeException {
		showException(e);
	}
	@Override
	protected void onException(LocationNotSoUsefulException e) throws RuntimeException {
		showException(e);
	}
	@Override
	protected void onException(LocationAwareSearchException arg0) throws RuntimeException {
		showException(arg0);
	}
	@Override
	protected void onException(CacheTooOldException e) throws RuntimeException {
		showException(e);
	};
	@Override
	protected void onException(Exception e) throws RuntimeException {
		showException(e);
//		e.printStackTrace();
	}
	
	private void showException(Exception exception) {
		String exceptionMessage = getExceptionMessage(exception);
		Log.d("DianeRoboSampleSearchAsyncTask:77", "onException("+exception.getClass().getSimpleName()+"): "+exceptionMessage);
		Toast.makeText(context, exceptionMessage, Toast.LENGTH_SHORT).show();
	}
	private void showException(Exception exception,String suffix) {
		String exceptionMessage = getExceptionMessage(exception);
		Log.d("DianeRoboSampleSearchAsyncTask:82", "onException("+exception.getClass().getSimpleName()+"): "+exceptionMessage);
		Toast.makeText(context,exceptionMessage+"\n\n"+suffix, Toast.LENGTH_SHORT).show();
	}
	private String getExceptionMessage(Exception e) {
		Throwable cause = e.getCause();
		String message = e.getMessage();
		if (cause!=null)
			message.concat("\ncaused from: "+cause.getMessage());
		return message;
	}

}
