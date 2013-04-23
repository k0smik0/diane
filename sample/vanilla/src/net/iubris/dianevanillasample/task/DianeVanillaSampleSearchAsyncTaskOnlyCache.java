package net.iubris.dianevanillasample.task;

import net.iubris.diane.asynctask.base.AbstractSearchAsyncTask;
import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.aware.location.exceptions.base.LocationNotSoUsefulException;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.exceptions.SearchException;
import net.iubris.dianevanillasample.controller.DianeRoboSampleSearcher;
import android.content.Context;
import android.widget.Toast;

public class DianeVanillaSampleSearchAsyncTaskOnlyCache extends AbstractSearchAsyncTask<DianeRoboSampleSearcher, Void, Void, String> {
	
	final private DianeRoboSampleSearcher dianeSampleSearcher;
	
//	private String result;
	
	public DianeVanillaSampleSearchAsyncTaskOnlyCache(Context context, DianeRoboSampleSearcher dianeSampleController) {
		super(context);
		this.dianeSampleSearcher = dianeSampleController;
	}
	@Override
	public String call() throws LocationNotSoUsefulException, CacheTooOldException, CacheAwareSearchException, SearchException {
		dianeSampleSearcher.searchByCacheJustForExamplePurpose();		
		return dianeSampleSearcher.getResultByCache();
	}
	@Override
	protected void onSuccess(String result) throws Exception {
		Toast.makeText(context, result, Toast.LENGTH_LONG).show();
	}
	
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
