package net.iubris.dianevanillasample.activity;

import net.iubris.dianevanillasample.R;
import net.iubris.dianevanillasample.controller.DianeRoboSampleSearcher;
import net.iubris.dianevanillasample.provider.DianeRoboSampleSearcherProvider;
import net.iubris.dianevanillasample.task.DianeVanillaSampleSearchAsyncTask;
import net.iubris.dianevanillasample.task.DianeVanillaSampleSearchAsyncTaskOnlyCache;
import net.iubris.dianevanillasample.task.DianeVanillaSampleSearchAsyncTaskOnlyNetwork;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class DianeVanillaSampleMainActivity extends Activity {
	
	private DianeRoboSampleSearcher dianeSampleSearcher;
	
	private Button buttonHere;
	private Button buttonAuto;
//	private DianeSampleSearchAsyncTask searchAsyncTask;

	private Button buttonCache;
	private Button buttonNetwork;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		dianeSampleSearcher = new DianeRoboSampleSearcherProvider(DianeRoboSampleSearcher.class,this).get();
//		searchAsyncTask  = new DianeSampleSearchAsyncTask(this,dianeSampleSearcher);
		
		
		buttonAuto = (Button) findViewById(R.id.button_auto);
		buttonAuto.setOnClickListener( new OnClickListener() {			
			@Override
			public void onClick(View v) {
				android.os.Debug.startMethodTracing("DianeVanillaSample");
				new DianeVanillaSampleSearchAsyncTask(DianeVanillaSampleMainActivity.this,dianeSampleSearcher).execute();
				android.os.Debug.stopMethodTracing();
			}
		});
		
		buttonHere = (Button) findViewById(R.id.button_here);
		buttonHere.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(DianeVanillaSampleMainActivity.this, dianeSampleSearcher.getLocationJustForExamplePurpose().toString(), Toast.LENGTH_LONG).show();
			}
		});
		
		buttonCache = (Button) findViewById(R.id.button_cache);
		buttonCache.setOnClickListener( new OnClickListener() {			
			@Override
			public void onClick(View v) {
				new DianeVanillaSampleSearchAsyncTaskOnlyCache(DianeVanillaSampleMainActivity.this,dianeSampleSearcher).execute();
			}
		});
		
		buttonNetwork = (Button) findViewById(R.id.button_network);
		buttonNetwork.setOnClickListener( new OnClickListener() {			
			@Override
			public void onClick(View v) {
				new DianeVanillaSampleSearchAsyncTaskOnlyNetwork(DianeVanillaSampleMainActivity.this,dianeSampleSearcher).execute();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
