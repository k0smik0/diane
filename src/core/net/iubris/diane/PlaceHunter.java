/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * PlaceHunter.java is part of diane.
 * 
 * diane is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * diane is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with diane ; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 ******************************************************************************/
package net.iubris.diane;

import android.location.Location;
import net.iubris.kusor.KLocator;

public abstract class PlaceHunter<B> {
	
	private final KLocator locator;
	//private final Searcher<B> searcher;
	private final Integer distanceFactorThreshold;
	private final Integer distanceMinimumThreshold;
	private Location location;
	
	protected PlaceHunter(KLocator locator,
			//Searcher<B> searcher,
			Integer distanceMinimumThreshold, 
			Integer distanceFactorThreshold) {		
		if (distanceFactorThreshold*distanceMinimumThreshold <=0) throw new NumberFormatException("Only positive value");
		this.locator = locator;
		//this.searcher = searcher;
		this.distanceMinimumThreshold = distanceMinimumThreshold;
		this.distanceFactorThreshold = distanceFactorThreshold;		
		// init
		this.location = locator.getLocation();		
	}

	private long bootyTime;
	private B booty;
	
	
	public void hunt() {
		if (isNewerLocation()) {
			//searcher.search(location);
			booty = searchAndHunt();
			update();
		}
	}
	
	private boolean isNewerLocation() {
		final Location location = locator.getLocation();
		if (location.distanceTo(this.location) > distanceFactorThreshold*distanceMinimumThreshold) {
			this.location = location;
			return true;
		}
		return false;
	}
	
	abstract public B searchAndHunt();
	
	private void update() {
		bootyTime = System.currentTimeMillis();
	}
	
	public long getTime() {
		return bootyTime;
	}
	
	public B getBooty() {
		return booty;
	}

}
