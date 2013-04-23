package net.iubris.dianevanillasample.task;

import net.iubris.diane.asynctask.base.AbstractSearchAsyncTask;
import net.iubris.diane.aware.cache.exceptions.CacheStateException;
import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.aware.location.exceptions.LocationStateException;
import net.iubris.diane.aware.location.exceptions.base.LocationNotSoUsefulException;
import net.iubris.diane.aware.network.exceptions.NetworkStateException;
import net.iubris.diane.aware.network.exceptions.base.NoNetworkException;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.aware.exceptions.AwareSearchException;
import net.iubris.diane.searcher.aware.location.exceptions.LocationAwareSearchException;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import net.iubris.diane.searcher.exceptions.SearchException;
import net.iubris.dianevanillasample.controller.DianeRoboSampleSearcher;
import android.content.Context;
import android.widget.Toast;

public class DianeVanillaSampleSearchAsyncTaskOnlyNetwork extends AbstractSearchAsyncTask<DianeRoboSampleSearcher, Void, Void, String> {
	
	final private DianeRoboSampleSearcher dianeSampleSearcher;
	
//	private String result;
	
	public DianeVanillaSampleSearchAsyncTaskOnlyNetwork(Context context, DianeRoboSampleSearcher dianeSampleController) {
		super(context);
		this.dianeSampleSearcher = dianeSampleController;
	}
	@Override
	public String call() throws LocationNotSoUsefulException,
			LocationStateException, CacheTooOldException, CacheStateException,
			NoNetworkException, NetworkStateException,
			LocationAwareSearchException, NetworkAwareSearchException,
			CacheAwareSearchException, AwareSearchException, SearchException,
			Exception {
		dianeSampleSearcher.searchByNetworkJustForExamplePurpose();
		return dianeSampleSearcher.getResultByNetwork();
	}
	@Override
	protected void onSuccess(String result) throws Exception {
		Toast.makeText(context, result, Toast.LENGTH_LONG).show();
	}
	
	@Override
	protected void onException(NoNetworkException e) throws RuntimeException {
		Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
//		Toast.makeText(context, dianeSampleSearcher.getResult(), Toast.LENGTH_LONG).show();
	}
	
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
