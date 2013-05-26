package net.iubris.dianeroboguicesample.task;

import javax.inject.Inject;

import net.iubris.diane.asynctask.base.RoboSearchAsyncTask;
import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.dianeroboguicesample.controller.DianeRoboSampleSearcher;
import android.content.Context;
import android.widget.Toast;

public class DianeRoboSampleSearchAsyncTaskOnlyCache extends RoboSearchAsyncTask<DianeRoboSampleSearcher, Void, Void, String> {
	
	final private DianeRoboSampleSearcher dianeSampleSearcher;
	
//	private String result;
	@Inject
	public DianeRoboSampleSearchAsyncTaskOnlyCache(Context context, DianeRoboSampleSearcher dianeSampleController) {
		super(context);
		this.dianeSampleSearcher = dianeSampleController;
	}
	@Override
	public String call() 
			throws 
//		LocationNotSoUsefulException, 
		CacheTooOldException, 
		CacheAwareSearchException,
		Exception 
		{
		dianeSampleSearcher.searchByCacheJustForExamplePurpose();		
		return dianeSampleSearcher.getResultByCache();
	}
	@Override
	protected void onSuccess(String result) throws Exception {
		Toast.makeText(context, "only cache result:\n"+result, Toast.LENGTH_LONG).show();
	}
	
	@Override
	protected void onException(CacheTooOldException e) throws RuntimeException {
		Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
//		Toast.makeText(context, dianeSampleSearcher.getResult(), Toast.LENGTH_LONG).show();
	};
	
/*	@Override
	protected void onException(Exception e) throws RuntimeException {
		Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
		Toast.makeText(context, dianeSampleSearcher.getResult(), Toast.LENGTH_LONG).show();
	}*/
	
	/*@Override
	protected void onException(NoNetworkException e) throws RuntimeException {
		Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
		Toast.makeText(context, dianeSampleSearcher.getResult(), Toast.LENGTH_LONG).show();
//		super.onException(e);
	}
	@Override
	protected void onException(NetworkAwareSearchException e) throws RuntimeException {
		Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
	}*/

}
