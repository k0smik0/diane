/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * RoboSearchAsyncTask.java is part of 'Diane'.
 * 
 * 'Diane' is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * 'Diane' is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with 'Diane' ; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 ******************************************************************************/
package net.iubris.diane.asynctask;

import java.util.concurrent.Executor;

import roboguice.RoboGuice;
import net.iubris.diane.searcher.Searcher;
import android.content.Context;
import android.os.Handler;

public abstract class RoboSearchAsyncTask
<SearchResult, S extends Searcher<SearchStatus, SearchResult>, SearchStatus> 
extends SearchAsyncTask<SearchResult, S , SearchStatus> {

	protected RoboSearchAsyncTask(Context context) {
		super(context);
		RoboGuice.getInjector(context).injectMembers(this);
	}
	
	protected RoboSearchAsyncTask(Context context, Handler handler) {
        super(context,handler);
        RoboGuice.getInjector(context).injectMembers(this);
    }

    protected RoboSearchAsyncTask(Context context, Handler handler, Executor executor) {
        super(context, handler, executor);
        RoboGuice.getInjector(context).injectMembers(this);
    }

    protected RoboSearchAsyncTask(Context context, Executor executor) {
        super(context,executor);
        RoboGuice.getInjector(context).injectMembers(this);
    }	
}
