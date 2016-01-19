/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * DianeRoboSampleMainActivity.java is part of Diane.
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
package net.iubris.diane_demo__roboguiced.activity;



import javax.inject.Inject;

import net.iubris.diane_demo__roboguiced.R;
import net.iubris.diane_demo__roboguiced.task.AwareSearchTask;
import net.iubris.diane_demo__roboguiced.task.CacheSearchTask;
import net.iubris.diane_demo__roboguiced.task.NetworkSearchTask;
import net.iubris.diane_library__test_utils.injector.MockGpsLocationsInjector;
import net.iubris.diane_library__test_utils.location.LocationUtils;
import net.iubris.polaris.locator.core.exceptions.LocationNullException;
import net.iubris.polaris.locator.core.provider.LocationProvider;
import net.iubris.polaris.locator.core.updater.LocationUpdater;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DianeDemoRoboMainActivity extends RoboActivity {
	
	@Inject private MockGpsLocationsInjector locationsInjector;
	@Inject private LocationUpdater locationUpdater;
	
	@Inject private LocationProvider locationProvider;
	
	@Inject private AwareSearchTask awareSearchTask;
	@Inject private CacheSearchTask cacheSearchTask;
	@Inject private NetworkSearchTask networkSearchTask;
	
	@InjectView(R.id.button_here) Button buttonHere;
	@InjectView(R.id.button_auto) Button buttonAuto;
	@InjectView(R.id.button_cache) Button buttonCache;
	@InjectView(R.id.button_network) Button buttonNetwork;
	@InjectView(R.id.button_updates) Button buttonUpdates;
	
	@InjectView(R.id.text_field_result) private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
//Debug.startMethodTracing(Environment.getExternalStorageDirectory().getPath()+"/traces/diane_sample_roboguiced__startup");		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textView.setMovementMethod(new ScrollingMovementMethod());
		
		awareSearchTask.setTextView(textView);
		networkSearchTask.setTextView(textView);
		cacheSearchTask.setTextView(textView);
		
//		locationsInjector.startLocationsTest();
		
		/*buttonHere.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				textView.setText( textView.getText()
						+LocationUtils.parseLocation(locationProvider.getLocation())
						+"\n\n");
//				Toast.makeText(DianeRoboSampleMainActivity.this, 
//						locationProvider.getLocation().toString(),
//						Toast.LENGTH_LONG).show();
			}
		});*/
		/*buttonAuto.setOnClickListener( new OnClickListener() {			
			@Override
			public void onClick(View v) {
//				android.os.Debug.startMethodTracing("DianeRoboSample");
				awareSearchTask.execute();
//				android.os.Debug.stopMethodTracing();
			}
		});*/
		/*buttonNetwork.setOnClickListener( new OnClickListener() {			
			@Override
			public void onClick(View v) {
				networkSearchTask.execute();
			}
		});*/
		/*buttonCache.setOnClickListener( new OnClickListener() {			
			@Override
			public void onClick(View v) {
				cacheSearchTask.execute();
			}
		});*/
		/*buttonUpdates.setOnClickListener( new OnClickListener() {			
			@Override
			public void onClick(View v) {
				if (locationsInjector.isRunning()) {
					locationsInjector.stopLocationsTest();
					buttonUpdates.setText("Start Fake Locations");
				} else {
					locationsInjector.startLocationsTest();
					buttonUpdates.setText("Stop Fake Locations");
				}
			}
		});*/
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		locationUpdater.startLocationUpdates();
//Debug.stopMethodTracing();
	}
	
	public void onAutoClick(View v) {
		awareSearchTask.execute();
	}
	public void onHereClick(View v) {
		try {
			textView.setText( textView.getText()
					+LocationUtils.parseLocation(locationProvider.getLocation())
					+"\n\n");
		} catch (LocationNullException e) {
			Toast.makeText(DianeDemoRoboMainActivity.this, "sorry,  but location is senselessly unvailable",Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
	}
	public void onNetworkClick(View v) {
		networkSearchTask.execute();
	}
	public void onCacheClick(View v) {
		cacheSearchTask.execute();
	}
	public void onFakeLocationClick(View v) {
		if (locationsInjector.isRunning()) {
			locationsInjector.stopLocationsTest();
			buttonUpdates.setText("Start Fake Locations");
		} else {
			locationsInjector.startLocationsTest();
			buttonUpdates.setText("Stop Fake Locations");
		}
	}
	
	@Override
	protected void onStop() {
		locationUpdater.stopLocationUpdates();
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		try {
		locationsInjector.stopLocationsTest();
		} catch (IllegalArgumentException e) {
			Toast.makeText(this, "IllegalArgumentException: "+e.getMessage(), Toast.LENGTH_LONG).show();
//			e.printStackTrace();
		}
		super.onDestroy();
	}

}
