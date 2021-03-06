/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * DefaultFullAwareSearcher.java is part of Diane.
 * 
 * Diane is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Diane is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with Diane ; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 ******************************************************************************/
package net.iubris.diane.searcher.aware.full.base;


import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import net.iubris.diane.aware.cache.exceptions.base.CacheEmptyException;
import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.aware.location.exceptions.base.LocationFreshNullException;
import net.iubris.diane.aware.location.state.three.ThreeStateLocationAwareLocationSupplier;
import net.iubris.diane.aware.network.exceptions.base.NoNetworkException;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.aware.exceptions.base.StillSearchException;
import net.iubris.diane.searcher.aware.full.FullAwareSearcher;
import net.iubris.diane.searcher.aware.location.exceptions.base.LocationNotSoUsefulException;
import net.iubris.diane.searcher.aware.location.exceptions.base.LocationTooNearException;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;
import net.iubris.diane.searcher.location.aware.full.LocalizedSearcherCacheNetworkAwareStrictChecking;
//import net.iubris.diane.searcher.location.aware.full.base.NoNetworkAndCacheEmptyException;
import android.location.Location;
import android.util.Log;

/**
 * @author Massimiliano Leone - k0smik0
 * @uml.dependency   supplier="net.iubris.dianedev.searcher.location.base.LocalizedSearcherCacheNetworkAware"
 */
public abstract class DefaultFullAwareSearcher<SearchResult> implements FullAwareSearcher<SearchResult> {

	protected final ThreeStateLocationAwareLocationSupplier locationAwareSupplier;
	protected final LocalizedSearcherCacheNetworkAwareStrictChecking<SearchResult> localizedSearcherCacheNetworkAware;
	
	private SearchResult result;
	private boolean searching = false;
	private boolean isFirstSearch = true;
	private Location location;
	
	private Timer timer;
	private final int MAX_COUNTDOWN = 30*1000;
	private int searchCounter;
	
	// improve: to handle externally (in LocalizedSearcherCacheNetworkAware) 
	//	when some exception occurred and an empty result is returned
//	private boolean emptyResultForException = false;
	
	@Inject
	public DefaultFullAwareSearcher(ThreeStateLocationAwareLocationSupplier locationAwareSupplier,
			LocalizedSearcherCacheNetworkAwareStrictChecking<SearchResult> awareSearcher) {
		this.locationAwareSupplier = locationAwareSupplier;
		this.localizedSearcherCacheNetworkAware = awareSearcher;
		initResult();
	}
	private TimerTask buildTimerTask() {
		return new TimerTask() {
			@Override
			public void run() {
				searchCounter--;
				if (searchCounter<0) {
					Log.d("DefaultFullAwareSearcher","resetting state after countdown elapsed");
					resetSearchState();
				}				
			}
			@Override
			public boolean cancel() {
				resetSearchState();
				return true;
			}
		};
	}
	
	
	private void startTimer() {
		searchCounter = MAX_COUNTDOWN;
		timer = new Timer(true);
		Log.d("DefaultFullAwareSearcher.startTimer","timer: "+timer);
		timer.scheduleAtFixedRate(buildTimerTask(), 0, 1000);
	}
	
	

	@Override
	public synchronized Void search(Void... params) throws 
		LocationFreshNullException,
		LocationTooNearException, LocationNotSoUsefulException, 
		CacheTooOldException, CacheEmptyException, NoNetworkException,
		CacheAwareSearchException, /*NoNetworkAndCacheEmptyException,*/ NetworkAwareSearchException, StillSearchException {
		
		if (searching==true) {
			throw new StillSearchException("a searching is still active");
		}
		
		searching=true;
		startTimer();
		
		if (isFirstSearch) { // first search
			try {
				locationAwareSupplier.isNewLocationUseful();
			} catch(LocationNotSoUsefulException e) {} // first search, so we don't care about usefulness
			isFirstSearch = false;
			return doSearch();
		}
		
		boolean locationUseful = locationAwareSupplier.isNewLocationUseful(); // if location is null, wait - if it is not useful (not so newer OR not so far OR etc), throws LocationNotSoUsefulException
		if (locationUseful) { // if location is not useful (not so newer OR not so far OR etc), throws LocationNotSoUsefulException
			//ok, we have "true", so start our search
			return doSearch();
		}			
		// we are here because not throwed LocationNotSoUsefulException nor above "if" returned true: 
		// so location is definitely near and we throw below LocationTooNearException
//Log.d("DefaultFullAwareSearcher:69","locationUseful is false, throwing LocationTooNearException");
		searching=false;
		Log.d("DefaultFullAwareSearcher","location is too near");
		location = locationAwareSupplier.getLocation();
		resetSearchState();
		throw new LocationTooNearException("location is too near, a new search is absolutely useless");
	}
	
	private Void doSearch() throws CacheTooOldException, CacheEmptyException, NoNetworkException, /*NoNetworkAndCacheEmptyException,*/ CacheAwareSearchException, NetworkAwareSearchException {
		this.location = locationAwareSupplier.getLocation();
Log.d("DefaultFullAwareSearcher.doSearch","using location: "+location);
		// use non-aware localizedsearcher as delegate
		try {
			localizedSearcherCacheNetworkAware.search(location);
			result = localizedSearcherCacheNetworkAware.getResult();
			resetSearchState();
		} catch (NoNetworkException | NetworkAwareSearchException | 
				 CacheEmptyException | CacheTooOldException | CacheAwareSearchException
				e) {
			SearchResult result = localizedSearcherCacheNetworkAware.getResult();
			if (result!=null) {
				this.result = result;
			}
			resetSearchState();
			throw e;
		}
		resetSearchState();
		return null;
	}
	@Override
	public Location getLocation() {
		return location;
	}
	
	@Override
	public void resetSearchState() {
		searching = false;
		if (timer!=null) {
			Log.d(this.getClass().getSimpleName()+".resetSearchState","timer not null, cancelling it");
			timer.cancel();
		}
	}
	@Override
	public int getSearchTimeElapse() {
		return searchCounter;
	}
	
//	@Override
	public void resetSearchStateExceptive() {
		searching = false;
//		emptyResultForException  = true;
	}
	
	@Override
	public boolean isFoundByCache() {
		return localizedSearcherCacheNetworkAware.isFoundByCache();
	}
	
	@Override
	public synchronized SearchResult getResult() {
		return result;
	}
	
	public synchronized void setResult(SearchResult result) {
		this.result = result;
	}
	protected abstract void initResult();
}
