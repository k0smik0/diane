/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * FullAwareSearcher.java is part of Diane.
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
package net.iubris.diane.searcher.aware.full;

import net.iubris.diane.aware.cache.exceptions.CacheStateException;
import net.iubris.diane.aware.location.exceptions.LocationStateException;
import net.iubris.diane.aware.network.exceptions.NetworkStateException;
import net.iubris.diane.searcher.aware.cache.CacheAwareSearcher;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.aware.exceptions.base.StillSearchException;
import net.iubris.diane.searcher.aware.location.LocationAwareSearcher;
import net.iubris.diane.searcher.aware.location.exceptions.LocationAwareSearchException;
import net.iubris.diane.searcher.aware.network.NetworkAwareSearcher;
import net.iubris.diane.searcher.aware.network.exceptions.NetworkAwareSearchException;

public interface FullAwareSearcher<SearchResult> extends
//AwareSearcher<Void, SearchResult>, //{
LocationAwareSearcher<Void, SearchResult>, NetworkAwareSearcher<Void, SearchResult>, CacheAwareSearcher<Void, SearchResult> {


	@Override
	public Void search(Void... params) throws 
		LocationStateException,
		LocationAwareSearchException,
		CacheStateException,
		CacheAwareSearchException,
		NetworkStateException,
		NetworkAwareSearchException,
		StillSearchException;
	
	void resetSearchState();
}
