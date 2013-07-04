package net.iubris.dianeroboguicesample.activity;


import javax.inject.Inject;

import net.iubris.diane_library__test_utils.LocationUtils;
import net.iubris.diane_library__test_utils.injector.MockGpsLocationsInjector;
import net.iubris.dianeroboguicesample.R;
import net.iubris.dianeroboguicesample.task.AwareSearchTask;
import net.iubris.dianeroboguicesample.task.CacheSearchTask;
import net.iubris.dianeroboguicesample.task.NetworkSearchTask;
import net.iubris.polaris.locator.provider.LocationProvider;
import net.iubris.polaris.locator.updater.LocationUpdater;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DianeRoboSampleMainActivity extends RoboActivity {
	
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
	
	public void onAutoClick(View v) {
		awareSearchTask.execute();
	}
	public void onHereClick(View v) {
		textView.setText( textView.getText()
				+LocationUtils.parseLocation(locationProvider.getLocation())
				+"\n\n");
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
		try {
		locationsInjector.stopLocationsTest();
		} catch (IllegalArgumentException e) {
			Toast.makeText(this, "IllegalArgumentException: "+e.getMessage(), Toast.LENGTH_LONG).show();
//			e.printStackTrace();
		}
		super.onDestroy();
	}

}
