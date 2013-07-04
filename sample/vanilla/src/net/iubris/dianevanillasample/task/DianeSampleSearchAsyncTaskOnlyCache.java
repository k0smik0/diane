/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * DianeSampleSearchAsyncTaskOnlyCache.java is part of Diane.
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
package net.iubris.dianevanillasample.task;

import net.iubris.diane.asynctask.base.SearchAsyncTask;
import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.searcher.aware.cache.exceptions.CacheAwareSearchException;
import net.iubris.diane.searcher.aware.location.exceptions.base.LocationNotSoUsefulException;
import net.iubris.diane.searcher.exceptions.SearchException;
import net.iubris.dianevanillasample.controller.DianeSampleSearcher;
import android.content.Context;
import android.widget.Toast;

public class DianeSampleSearchAsyncTaskOnlyCache extends SearchAsyncTask<DianeSampleSearcher, Void, Void, String> {
	
	final private DianeSampleSearcher dianeSampleSearcher;
	
//	private String result;
	
	public DianeSampleSearchAsyncTaskOnlyCache(Context context, DianeSampleSearcher dianeSampleController) {
		super(context);
		this.dianeSampleSearcher = dianeSampleController;
	}
	@Override
	public String call() throws LocationNotSoUsefulException, CacheTooOldException, CacheAwareSearchException, SearchException {
		dianeSampleSearcher.searchByCacheJustForExamplePurpose();		
		return dianeSampleSearcher.getResultByCache();
	}
	@Override
	protected void onSuccess(String result) throws Exception {
		Toast.makeText(context, result, Toast.LENGTH_LONG).show();
	}
	
	@Override
	protected void onException(CacheTooOldException e) throws RuntimeException {
		Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
//		Toast.makeText(context, dianeSampleSearcher.getResult(), Toast.LENGTH_LONG).show();
	};
	
/*	@Override
	protected void onException(Exception e) throws RuntimeException {
		Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
		Toast.makeText(context, dianeSampleSearcher.getResult(), Toast.LENGTH_LONG).show();
	}*/
	
	/*@Override
	protected void onException(NoNetworkException e) throws RuntimeException {
		Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
		Toast.makeText(context, dianeSampleSearcher.getResult(), Toast.LENGTH_LONG).show();
//		super.onException(e);
	}
	@Override
	protected void onException(NetworkAwareSearchException e) throws RuntimeException {
		Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
	}*/

}
