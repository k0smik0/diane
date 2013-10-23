/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * DianeRoboSampleModule.java is part of Diane.
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
package net.iubris.diane_demo__roboguiced._roboguice;



import net.iubris.diane._roboguice.module.AbstractDianeModule;
import net.iubris.diane.aware.cache.exceptions.base.CacheTooOldException;
import net.iubris.diane.aware.cache.states.three.ThreeStateCacheAware;
import net.iubris.diane.searcher.location.aware.cache.LocalizedSearcherCacheAwareStrictChecking;
import net.iubris.diane.searcher.location.aware.full.LocalizedSearcherCacheNetworkAwareStrictChecking;
import net.iubris.diane.searcher.location.aware.full.base.DefaultLocalizedSearcherCacheNetworkAwareStrictChecking;
import net.iubris.diane.searcher.location.aware.network.LocalizedSearcherNetworkAwareStrictChecking;
import net.iubris.diane_demo__roboguiced.controller.searcher.DianeDemoRoboLocalizedSearcherCacheAwareStrictChecking;
import net.iubris.diane_demo__roboguiced.controller.searcher.DianeDemoRoboLocalizedSearcherNetworkAwareStrictChecking;
import net.iubris.diane_library__test_utils._roboguice.module.DianeTestUtilModule;

import com.google.inject.Provides;
import com.google.inject.TypeLiteral;

public class DianeDemoRoboModule extends AbstractDianeModule {

	@Override
	protected void bindThreeStateCacheAware() {} // empty; instead, here we use provides annotation
	@Provides
	public ThreeStateCacheAware providesThreeStateCacheAware() {
		return new ThreeStateCacheAware() {
			@Override
			public Boolean isCacheAvailable() throws CacheTooOldException {
				double random = Math.random();
				if (random < 0.33) return true;
				if (random > 0.66) return false;
				throw new CacheTooOldException("cache too old: "+random);
			}
		};
	}
	@Override
	protected void bindLocalizedSearcherCacheAwareStrictChecking() {
		bind( new TypeLiteral<LocalizedSearcherCacheAwareStrictChecking<String>>(){}).to( new TypeLiteral<DianeDemoRoboLocalizedSearcherCacheAwareStrictChecking>(){});
	} 
	
	@Override
	protected void bindLocalizedSearcherNetworkAwareStrictChecking() {
		bind( new TypeLiteral<LocalizedSearcherNetworkAwareStrictChecking<String>>(){}).to( new TypeLiteral<DianeDemoRoboLocalizedSearcherNetworkAwareStrictChecking>(){});
	}
	
	// a dummy location provider from DianeTestUtil
	@Override
	protected void bindLocationProvider() {
		install( new DianeTestUtilModule() );
	}
	
	@Override
	protected void bindLocalizedSearcherCacheNetworkAwareStrictChecking() {
		bind( new TypeLiteral<LocalizedSearcherCacheNetworkAwareStrictChecking<String>>(){}).to(new TypeLiteral<DefaultLocalizedSearcherCacheNetworkAwareStrictChecking<String>>(){});
	}
}
