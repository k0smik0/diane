package net.iubris.dianeroboguicesample.task;

import javax.inject.Inject;

import net.iubris.diane.asynctask.base.RoboSearchAsyncTask;
import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.aware.network.exceptions.base.NoNetworkException;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.aware.location.exceptions.LocationAwareSearchException;
import net.iubris.diane.searcher.aware.location.exceptions.base.LocationNotSoUsefulException;
import net.iubris.diane.searcher.aware.location.exceptions.base.LocationTooNearException;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import net.iubris.dianeroboguicesample.controller.DianeRoboSampleSearcher;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class DianeRoboSampleSearchAsyncTask extends RoboSearchAsyncTask<DianeRoboSampleSearcher, Void, Void, String> {
	
	final private DianeRoboSampleSearcher dianeSampleSearcher;
	
	@Inject
	public DianeRoboSampleSearchAsyncTask(Context context, DianeRoboSampleSearcher dianeSampleController) {
		super(context);
		this.dianeSampleSearcher = dianeSampleController;
	}
	@Override
	public String call() throws LocationTooNearException, LocationNotSoUsefulException, 
		CacheTooOldException, NoNetworkException, 
		CacheAwareSearchException, NetworkAwareSearchException { 
			dianeSampleSearcher.search();
		return dianeSampleSearcher.getResult();
	}
	@Override
	protected void onSuccess(String result) throws Exception {
		Toast.makeText(context, result, Toast.LENGTH_LONG).show();
	}
	
	@Override
	protected void onException(NoNetworkException e) throws RuntimeException {
		showException(e,"from cache: \n"+dianeSampleSearcher.getResult());
	}
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
	protected void onGenericException(Exception e) throws RuntimeException {
		showException(e);
//		e.printStackTrace();
	}
	protected void onException(RuntimeException e) {
		showException(e);
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
