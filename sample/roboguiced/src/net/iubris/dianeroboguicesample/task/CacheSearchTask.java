package net.iubris.dianeroboguicesample.task;

import javax.inject.Inject;

import net.iubris.diane.asynctask.base.RoboSearchAsyncTask;
import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.dianeroboguicesample.controller.DianeRoboSampleAwareSearcher;
import net.iubris.dianeroboguicesample.controller.DianeRoboSampleCacheSearcher;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

public class CacheSearchTask extends RoboSearchAsyncTask<DianeRoboSampleAwareSearcher, Void, Void, String> {
	
//	final private DianeRoboSampleAwareSearcher dianeSampleSearcher;
	final private DianeRoboSampleCacheSearcher dianeRoboSampleCacheSearcher;
	
	private TextView textView;
	
//	private String result;
	@Inject
	public CacheSearchTask(Context context,
//			DianeRoboSampleAwareSearcher dianeSampleController,
			DianeRoboSampleCacheSearcher dianeRoboSampleCacheSearcher
			) {
		super(context);
		this.dianeRoboSampleCacheSearcher = dianeRoboSampleCacheSearcher;
	}
	@Override
	protected void onPreExecute() throws Exception {
		Toast.makeText(context, "...searching...", Toast.LENGTH_SHORT).show();
	}
	@Override
	public String call() 
			throws 
//		LocationNotSoUsefulException, 
		CacheTooOldException, 
		CacheAwareSearchException,
		Exception {
		dianeRoboSampleCacheSearcher.search();		
		return dianeRoboSampleCacheSearcher.getResult();
	}
	@Override
	protected void onSuccess(String result) throws Exception {
//		Toast.makeText(context, "only cache result:\n"+result, Toast.LENGTH_LONG).show();
		textView.setText( textView.getText()
				+"only cache result:\n"+result+"\n\n");
	}
	
	@Override
	protected void onException(CacheTooOldException e) throws RuntimeException {
//		Toast.makeText(context, dianeSampleSearcher.getResult(), Toast.LENGTH_LONG).show();
//		Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
		textView.setText( textView.getText()
				+e.getMessage()+"\n\n");
	}
	public void setTextView(TextView textView) {
		this.textView = textView;
	};
	
/*	@Override
	protected void onException(Exception e) throws RuntimeException {
		Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
		Toast.makeText(context, dianeSampleSearcher.getResult(), Toast.LENGTH_LONG).show();
	}*/
}
