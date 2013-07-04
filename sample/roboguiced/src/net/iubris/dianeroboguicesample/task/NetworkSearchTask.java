/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * NetworkSearchTask.java is part of Diane.
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
package net.iubris.dianeroboguicesample.task;

import javax.inject.Inject;

import net.iubris.diane.asynctask.base.RoboSearchAsyncTask;
import net.iubris.diane.aware.cache.exceptions.CacheStateException;
import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.aware.location.exceptions.LocationStateException;
import net.iubris.diane.aware.network.exceptions.NetworkStateException;
import net.iubris.diane.aware.network.exceptions.base.NoNetworkException;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.aware.exceptions.AwareSearchException;
import net.iubris.diane.searcher.aware.location.exceptions.LocationAwareSearchException;
import net.iubris.diane.searcher.aware.location.exceptions.base.LocationNotSoUsefulException;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import net.iubris.diane.searcher.exceptions.SearchException;
import net.iubris.dianeroboguicesample.controller.DianeRoboSampleAwareSearcher;
import net.iubris.dianeroboguicesample.controller.DianeRoboSampleNetworkSearcher;
import android.content.Context;
import android.widget.TextView;
//import android.widget.Toast;
import android.widget.Toast;

public class NetworkSearchTask extends RoboSearchAsyncTask<DianeRoboSampleAwareSearcher, Void, Void, String> {
	
//	final private DianeRoboSampleAwareSearcher dianeSampleSearcher;
	final private DianeRoboSampleNetworkSearcher networkSearcher;
	private TextView textView;
	
//	private String result;
	@Inject
	public NetworkSearchTask(Context context, DianeRoboSampleNetworkSearcher networkSearcher) {
		super(context);
		this.networkSearcher = networkSearcher;
	}
	@Override
	protected void onPreExecute() throws Exception {
		Toast.makeText(context, "...searching...", Toast.LENGTH_SHORT).show();
	}
	@Override
	public String call() throws LocationNotSoUsefulException,
			LocationStateException, CacheTooOldException, CacheStateException,
			NoNetworkException, NetworkStateException,
			LocationAwareSearchException, NetworkAwareSearchException,
			CacheAwareSearchException, AwareSearchException, SearchException,
			Exception {
		networkSearcher.search();
		return networkSearcher.getResult();
	}
	@Override
	protected void onSuccess(String result) throws Exception {
//		Toast.makeText(context, result, Toast.LENGTH_LONG).show();
		textView.setText( textView.getText()
				+"only network result:\n"+result+"\n\n");
	}
	
	@Override
	protected void onException(NoNetworkException e) throws RuntimeException {
//		Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
		textView.setText( textView.getText()
				+e.getMessage()+"\n\n");
	}
	@Override
	protected void onException(NetworkAwareSearchException e) throws RuntimeException {
		ExceptionUtils.showException(e,textView);
	}
	
	public void setTextView(TextView textView) {
		this.textView = textView;		
	}
}
