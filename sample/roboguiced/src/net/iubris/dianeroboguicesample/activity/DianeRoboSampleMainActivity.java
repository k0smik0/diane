package net.iubris.dianeroboguicesample.activity;


import javax.inject.Inject;

import net.iubris.dianeroboguicesample.R;
import net.iubris.dianeroboguicesample.controller.DianeRoboSampleSearcher;
import net.iubris.dianeroboguicesample.task.DianeRoboSampleSearchAsyncTask;
import net.iubris.dianeroboguicesample.task.DianeRoboSampleSearchAsyncTaskOnlyCache;
import net.iubris.dianeroboguicesample.task.DianeRoboSampleSearchAsyncTaskOnlyNetwork;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class DianeRoboSampleMainActivity extends RoboActivity {
	
	@Inject private DianeRoboSampleSearcher dianeSampleSearcher;
	@Inject private DianeRoboSampleSearchAsyncTask dianeRoboSampleSearchAsyncTask;
	@Inject private DianeRoboSampleSearchAsyncTaskOnlyCache dianeRoboSampleSearchAsyncTaskOnlyCache;
	@Inject private DianeRoboSampleSearchAsyncTaskOnlyNetwork dianeRoboSampleSearchAsyncTaskOnlyNetwork;
	
	@InjectView(R.id.button_here) private Button buttonHere;
	@InjectView(R.id.button_auto) private Button buttonAuto;

	@InjectView(R.id.button_cache) private Button buttonCache;
	@InjectView(R.id.button_network) private Button buttonNetwork;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		dianeSampleSearcher = DianeSampleSearcherBuilder.buildController(this);
//		searchAsyncTask  = new DianeSampleSearchAsyncTask(this,dianeSampleSearcher);
		
		
		buttonAuto.setOnClickListener( new OnClickListener() {			
			@Override
			public void onClick(View v) {
				android.os.Debug.startMethodTracing("DianeRoboSample");
//				new DianeRoboSampleSearchAsyncTask(DianeRoboSampleMainActivity.this,dianeSampleSearcher).execute();
				dianeRoboSampleSearchAsyncTask.execute();
				android.os.Debug.stopMethodTracing();
			}
		});
		
		buttonHere.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(DianeRoboSampleMainActivity.this, dianeSampleSearcher.getLocationJustForExamplePurpose().toString(), Toast.LENGTH_LONG).show();
			}
		});
		
		buttonCache.setOnClickListener( new OnClickListener() {			
			@Override
			public void onClick(View v) {
//				new DianeRoboSampleSearchAsyncTaskOnlyCache(DianeRoboSampleMainActivity.this,dianeSampleSearcher).execute();
				dianeRoboSampleSearchAsyncTaskOnlyCache.execute();
			}
		});
		
		buttonNetwork.setOnClickListener( new OnClickListener() {			
			@Override
			public void onClick(View v) {
//				new DianeRoboSampleSearchAsyncTaskOnlyNetwork(DianeRoboSampleMainActivity.this,dianeSampleSearcher).execute();
				dianeRoboSampleSearchAsyncTaskOnlyNetwork.execute();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
