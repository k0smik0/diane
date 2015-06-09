/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * AwareSearchTask.java is part of Diane.
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
package net.iubris.diane_demo__roboguiced.task;

import javax.inject.Inject;

import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.aware.network.exceptions.base.NoNetworkException;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.aware.exceptions.base.StillSearchException;
import net.iubris.diane.searcher.aware.location.exceptions.LocationAwareSearchException;
import net.iubris.diane.searcher.aware.location.exceptions.base.LocationNotSoUsefulException;
import net.iubris.diane.searcher.aware.location.exceptions.base.LocationTooNearException;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import net.iubris.diane.tasks.search.RoboSearchAwareAsyncTask;
import net.iubris.diane_demo__roboguiced.controller.DianeDemoRoboAwareSearcher;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

public class AwareSearchTask extends RoboSearchAwareAsyncTask<DianeDemoRoboAwareSearcher, Void, Void, String> {
	
	final private DianeDemoRoboAwareSearcher dianeSampleAwareSearcher;
	private TextView textView;
	
	@Inject
	public AwareSearchTask(Context context, DianeDemoRoboAwareSearcher dianeSampleController) {
		super(context);
		this.dianeSampleAwareSearcher = dianeSampleController;
	}
	public void setTextView(TextView textView) {
		this.textView = textView;
	}
	@Override
	protected void onPreExecute() throws Exception {
		Toast.makeText(context, "...searching...", Toast.LENGTH_SHORT).show();
//Debug.startMethodTracing(Environment.getExternalStorageDirectory().getPath()+"/traces/diane_sample_roboguiced__aware_search_task");		
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
//Debug.stopMethodTracing();
		textView.setText( textView.getText()
				+result+"\n\n");
//		Toast.makeText(context, result, Toast.LENGTH_LONG).show();
	}
	
	protected void onException(StillSearchException e) throws RuntimeException {
		ExceptionUtils.showException(e,textView);
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
