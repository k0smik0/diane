package net.iubris.dianeroboguicesample.task;

import javax.inject.Inject;

import net.iubris.diane.asynctask.base.RoboSearchAsyncTask;
import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.aware.network.exceptions.base.NoNetworkException;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.aware.exceptions.base.StillSearchException;
import net.iubris.diane.searcher.aware.location.exceptions.LocationAwareSearchException;
import net.iubris.diane.searcher.aware.location.exceptions.base.LocationNotSoUsefulException;
import net.iubris.diane.searcher.aware.location.exceptions.base.LocationTooNearException;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import net.iubris.dianeroboguicesample.controller.DianeRoboSampleAwareSearcher;
import android.content.Context;
//import android.widget.Toast;
import android.widget.TextView;
import android.widget.Toast;

public class AwareSearchTask extends RoboSearchAsyncTask<DianeRoboSampleAwareSearcher, Void, Void, String> {
	
	final private DianeRoboSampleAwareSearcher dianeSampleAwareSearcher;
	private TextView textView;
	
	@Inject
	public AwareSearchTask(Context context, DianeRoboSampleAwareSearcher dianeSampleController) {
		super(context);
		this.dianeSampleAwareSearcher = dianeSampleController;
	}
	public void setTextView(TextView textView) {
		this.textView = textView;
	}
	@Override
	protected void onPreExecute() throws Exception {
		Toast.makeText(context, "...searching...", Toast.LENGTH_SHORT).show();
	}
	@Override
	public String call() throws StillSearchException, LocationTooNearException, LocationNotSoUsefulException, 
		CacheTooOldException, NoNetworkException, 
		CacheAwareSearchException, NetworkAwareSearchException { 
			dianeSampleAwareSearcher.search();
		return dianeSampleAwareSearcher.getResult();
	}
	@Override
	protected void onSuccess(String result) throws Exception {
		textView.setText( textView.getText()
				+result+"\n\n");
//		Toast.makeText(context, result, Toast.LENGTH_LONG).show();
	}
	
	protected void onException(StillSearchException e) throws RuntimeException {
		ExceptionUtils.showException(e,textView,dianeSampleAwareSearcher);
	}
	@Override
	protected void onException(NoNetworkException e) throws RuntimeException {
		ExceptionUtils.showException(e,"from cache: \n"+dianeSampleAwareSearcher.getResult(), textView,dianeSampleAwareSearcher);
	}
	@Override
	protected void onException(LocationTooNearException e) throws RuntimeException {
		ExceptionUtils.showException(e,textView,dianeSampleAwareSearcher);
	}
	@Override
	protected void onException(LocationNotSoUsefulException e) throws RuntimeException {
		ExceptionUtils.showException(e,textView,dianeSampleAwareSearcher);
	}
	@Override
	protected void onException(LocationAwareSearchException e) throws RuntimeException {
		ExceptionUtils.showException(e,textView,dianeSampleAwareSearcher);
	}
	@Override
	protected void onException(CacheTooOldException e) throws RuntimeException {
		ExceptionUtils.showException(e,textView,dianeSampleAwareSearcher);
	};
	@Override
	protected void onException(NetworkAwareSearchException e) throws RuntimeException {
		textView.setText( textView.getText()
				+dianeSampleAwareSearcher.getResult()+"\n\n");
		ExceptionUtils.showException(e,"no address geocoding because the exception:",textView,dianeSampleAwareSearcher);
	}
	@Override
	protected void onGenericException(Exception e) throws RuntimeException {
		ExceptionUtils.showException(e,textView,dianeSampleAwareSearcher);
//		e.printStackTrace();
	}
	protected void onException(RuntimeException e) {
		ExceptionUtils.showException(e,textView,dianeSampleAwareSearcher);
	}
	
	
	
	
}
