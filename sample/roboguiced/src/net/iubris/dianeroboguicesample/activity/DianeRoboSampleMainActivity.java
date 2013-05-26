package net.iubris.dianeroboguicesample.activity;


import javax.inject.Inject;

import net.iubris.diane_library__test_utils.injector.MockGpsLocationsInjector;
import net.iubris.dianeroboguicesample.R;
import net.iubris.dianeroboguicesample.task.DianeRoboSampleSearchAsyncTask;
import net.iubris.dianeroboguicesample.task.DianeRoboSampleSearchAsyncTaskOnlyCache;
import net.iubris.dianeroboguicesample.task.DianeRoboSampleSearchAsyncTaskOnlyNetwork;
import net.iubris.polaris.locator.provider.LocationProvider;
import net.iubris.polaris.locator.updater.LocationUpdater;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class DianeRoboSampleMainActivity extends RoboActivity {
	
	@Inject private MockGpsLocationsInjector locationsInjector;
	@Inject private LocationUpdater locationUpdater;
	
	@Inject private LocationProvider locationProvider;
	
	@Inject private DianeRoboSampleSearchAsyncTask dianeRoboSampleSearchAsyncTask;
	@Inject private DianeRoboSampleSearchAsyncTaskOnlyCache dianeRoboSampleSearchAsyncTaskOnlyCache;
	@Inject private DianeRoboSampleSearchAsyncTaskOnlyNetwork dianeRoboSampleSearchAsyncTaskOnlyNetwork;
	
	@InjectView(R.id.button_here) private Button buttonHere;
	@InjectView(R.id.button_auto) private Button buttonAuto;
	@InjectView(R.id.button_cache) private Button buttonCache;
	@InjectView(R.id.button_network) private Button buttonNetwork;
	@InjectView(R.id.button_updates) private Button buttonUpdates;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		locationsInjector.startLocationsTest();
		
		buttonHere.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(DianeRoboSampleMainActivity.this, 
						locationProvider.getLocation().toString(),
						Toast.LENGTH_LONG).show();
			}
		});
		buttonAuto.setOnClickListener( new OnClickListener() {			
			@Override
			public void onClick(View v) {
//				android.os.Debug.startMethodTracing("DianeRoboSample");
				dianeRoboSampleSearchAsyncTask.execute();
//				android.os.Debug.stopMethodTracing();
			}
		});
		buttonNetwork.setOnClickListener( new OnClickListener() {			
			@Override
			public void onClick(View v) {
				dianeRoboSampleSearchAsyncTaskOnlyNetwork.execute();
			}
		});
		buttonCache.setOnClickListener( new OnClickListener() {			
			@Override
			public void onClick(View v) {
				dianeRoboSampleSearchAsyncTaskOnlyCache.execute();
			}
		});
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
