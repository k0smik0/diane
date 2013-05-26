package net.iubris.dianevanillasample.activity;

import net.iubris.diane_library__test_utils.injector.MockGpsLocationsInjector;
import net.iubris.diane_library__test_utils.receiver.MockUtilsProvider;
import net.iubris.dianevanillasample.R;
import net.iubris.dianevanillasample.controller.DianeSampleSearcher;
import net.iubris.dianevanillasample.provider.DianeSampleSearcherProvider;
import net.iubris.dianevanillasample.task.DianeSampleSearchAsyncTask;
import net.iubris.dianevanillasample.task.DianeSampleSearchAsyncTaskOnlyCache;
import net.iubris.dianevanillasample.task.DianeSampleSearchAsyncTaskOnlyNetwork;
import net.iubris.polaris.locator.provider.LocationProvider;
import net.iubris.polaris.locator.updater.LocationUpdater;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class DianeSampleMainActivity extends Activity {
	
	
	private MockGpsLocationsInjector locationsInjector;
	private LocationUpdater locationUpdater;
	
	private LocationProvider locationProvider;
	
	private DianeSampleSearcher dianeSampleSearcher;
	private DianeSampleSearchAsyncTask dianeSampleSearchAsyncTask;
	private DianeSampleSearchAsyncTaskOnlyCache dianeSampleSearchAsyncTaskOnlyCache;
	private DianeSampleSearchAsyncTaskOnlyNetwork dianeSampleSearchAsyncTaskOnlyNetwork;
	
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
		
		locationsInjector = utilsProvider.getMockGpsLocationsInjector();
		locationsInjector.startLocationsTest();
		
		dianeSampleSearcher = new DianeSampleSearcherProvider(this).get();
		dianeSampleSearchAsyncTask = new DianeSampleSearchAsyncTask(this,dianeSampleSearcher);
		dianeSampleSearchAsyncTaskOnlyCache = new DianeSampleSearchAsyncTaskOnlyCache(this, dianeSampleSearcher);
		dianeSampleSearchAsyncTaskOnlyNetwork = new DianeSampleSearchAsyncTaskOnlyNetwork(this, dianeSampleSearcher);
		
		buttonHere = (Button) findViewById(R.id.button_here);
		buttonHere.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(DianeSampleMainActivity.this, 
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
