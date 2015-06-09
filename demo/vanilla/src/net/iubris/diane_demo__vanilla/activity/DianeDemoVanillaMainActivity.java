/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * DianeSampleMainActivity.java is part of Diane.
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
package net.iubris.diane_demo__vanilla.activity;

import net.iubris.diane_demo__vanilla.provider.DianeDemoVanillaSearcherProvider;
import net.iubris.diane_demo__vanilla.controller.DianeDemoVanillaSearcher;
import net.iubris.diane_demo__vanilla.task.DianeDemoVanillaSearchAsyncTask;
import net.iubris.diane_demo__vanilla.task.DianeDemoVanillaSearchAsyncTaskOnlyCache;
import net.iubris.diane_demo__vanilla.task.DianeDemoVanillaSearchAsyncTaskOnlyNetwork;
import net.iubris.diane_library__test_utils.injector.MockGpsLocationsInjector;
import net.iubris.diane_library__test_utils.receiver.MockUtilsProvider;
import net.iubris.diane_demo__vanilla.R;
import net.iubris.polaris.locator.provider.LocationProvider;
import net.iubris.polaris.locator.updater.LocationUpdater;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class DianeDemoVanillaMainActivity extends Activity {
	
	private MockGpsLocationsInjector locationsInjector;
	private LocationUpdater locationUpdater;
	
	private LocationProvider locationProvider;
	
	private DianeDemoVanillaSearcher dianeSampleSearcher;
	private DianeDemoVanillaSearchAsyncTask dianeSampleSearchAsyncTask;
	private DianeDemoVanillaSearchAsyncTaskOnlyCache dianeSampleSearchAsyncTaskOnlyCache;
	private DianeDemoVanillaSearchAsyncTaskOnlyNetwork dianeSampleSearchAsyncTaskOnlyNetwork;
	
	private Button buttonHere;
	private Button buttonAuto;
	private Button buttonCache;
	private Button buttonNetwork;
	private Button buttonUpdates;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		MockUtilsProvider utilsProvider = MockUtilsProvider.getInstance(this);
		
		locationUpdater = utilsProvider.getLocationUpdater();
		
		locationProvider = utilsProvider.getLocationProvider();
		
		locationsInjector = utilsProvider.getMockGpsLocationsInjector();
		locationsInjector.startLocationsTest();
		
		dianeSampleSearcher = new DianeDemoVanillaSearcherProvider(this).get();
		dianeSampleSearchAsyncTask = new DianeDemoVanillaSearchAsyncTask(this,dianeSampleSearcher);
		dianeSampleSearchAsyncTaskOnlyCache = new DianeDemoVanillaSearchAsyncTaskOnlyCache(this, dianeSampleSearcher);
		dianeSampleSearchAsyncTaskOnlyNetwork = new DianeDemoVanillaSearchAsyncTaskOnlyNetwork(this, dianeSampleSearcher);
		
		buttonHere = (Button) findViewById(R.id.button_here);
		buttonHere.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(DianeDemoVanillaMainActivity.this, 
						locationProvider.getLocation().toString(),
						Toast.LENGTH_LONG).show();
			}
		});
		
		buttonAuto = (Button) findViewById(R.id.button_auto);
		buttonAuto.setOnClickListener( new OnClickListener() {			
			@Override
			public void onClick(View v) {
//				android.os.Debug.startMethodTracing("DianeVanillaSample");
				dianeSampleSearchAsyncTask.execute();
//				android.os.Debug.stopMethodTracing();
			}
		});
		buttonNetwork = (Button) findViewById(R.id.button_network);
		buttonNetwork.setOnClickListener( new OnClickListener() {			
			@Override
			public void onClick(View v) {
				dianeSampleSearchAsyncTaskOnlyNetwork.execute();
			}
		});
		buttonCache = (Button) findViewById(R.id.button_cache);
		buttonCache.setOnClickListener( new OnClickListener() {			
			@Override
			public void onClick(View v) {
				dianeSampleSearchAsyncTaskOnlyCache.execute();
			}
		});
		
		buttonUpdates = (Button) findViewById(R.id.button_updates);
		buttonUpdates.setOnClickListener( new OnClickListener() {			
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
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		locationUpdater.startLocationUpdates();
	}
	
	@Override
	protected void onStop() {
		locationUpdater.stopLocationUpdates();
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		locationsInjector.stopLocationsTest();
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
