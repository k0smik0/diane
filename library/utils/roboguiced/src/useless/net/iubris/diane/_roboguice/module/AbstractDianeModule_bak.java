/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * AbstractDianeModule_bak.java is part of Diane.
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
package net.iubris.diane._roboguice.module;

import net.iubris.diane.aware.location.state.three.ThreeStateLocationAwareLocationSupplier;
import net.iubris.diane.aware.location.state.three.base.DefaultThreeStateLocationAwareLocationSupplier;
import net.iubris.diane.aware.network.state.checker.CheckerStateNetworkAware;
import net.iubris.diane.aware.network.state.checker.base.DefaultCheckerStateNetworkAware;
import net.iubris.diane.searcher.aware.full.base.DefaultFullAwareSearcher;

import com.google.inject.AbstractModule;

public abstract class AbstractDianeModule_bak extends AbstractModule {
	
	private int distanceMaximumThreshold;
	
	public AbstractDianeModule_bak() {
		this.distanceMaximumThreshold = 200; // 200m is default
	}

	/**
	 * this just calls abstract method bind*SOME*Aware you have to implement, 
	 * and binds {@link DianeDistanceMaximumThreshold } to "200"  
	 */
	@Override
	protected void configure() {
		
		bindLocationProvider(); // abstract
		bindDianeDistanceMaximumThreshold();		
		bindThreeStateLocationAwareLocationSupplier();
		
		bindThreeStateCacheAware(); // abstract
		bindLocalizedSearcherCacheAwareStrictChecking(); // abstract
		bindCheckerStateNetworkAware();
		bindLocalizedSearcherNetworkAwareStrictChecking(); // abstract
		bindLocalizedSearcherCacheNetworkAwareStrictChecking();
		
		bindFullAwareSearcher();
	}
	
	/**
	 *  bind needed  for {@link DefaultThreeStateLocationAwareLocationSupplier } and descendents
	 */
	protected abstract void bindLocationProvider();
	/**
	 *  bind needed  for {@link DefaultThreeStateLocationAwareLocationSupplier } and descendents<br/>
	 *  default is:<br/>
	 *  bindConstant().annotatedWith(DianeDistanceMaximumThreshold.class).to(distanceMaximumThreshold);
	 *  with distanceMaximumThreshold=200<br/>
	 *  you can override this method or change distanceMaximumThreshold via setDistanceMaximumThreshold
	 */
	protected void bindDianeDistanceMaximumThreshold() {
		bindConstant().annotatedWith(DianeDistanceMaximumThreshold.class).to(distanceMaximumThreshold);
	}
	protected void setDistanceMaximumThreshold(int distanceMaximumThreshold) {
		this.distanceMaximumThreshold = distanceMaximumThreshold;
	}
	/**
	 *  bind needed  for {@link DefaultFullAwareSearcher } and descendents<br/>
	 *  default: binds to DefaultThreeStateLocationAwareLocationSupplier 
	 */
	protected void bindThreeStateLocationAwareLocationSupplier() {
		bind(ThreeStateLocationAwareLocationSupplier.class).to(DefaultThreeStateLocationAwareLocationSupplier.class);
	};
	
	
	/**
	 *  bind needed for {@link AbstractLocalizedSearcherCacheAware } and descendents<br/>
	 *  use TypeLiteral for binding!
	 */
	protected abstract void bindThreeStateCacheAware();
	/**
	 *  bind needed  for {@link DefaultLocalizedSearcherCacheNetworkAware } and descendents<br/>
	 *  use TypeLiteral for binding!
	 */
	protected abstract void bindLocalizedSearcherCacheAwareStrictChecking();
	
	/**
	 *  bind needed for {@link CheckerStateNetworkAware } and descendents<br/>
	 *  default: to DefaultCheckerStateNetworkAware
	 */
	protected void bindCheckerStateNetworkAware() {
		bind(CheckerStateNetworkAware.class).to(DefaultCheckerStateNetworkAware.class);
	}
	
	/**
	 *
	*  bind needed for {@link DefaultLocalizedSearcherCacheNetworkAware } and descendents
	*/
	protected abstract void bindLocalizedSearcherNetworkAwareStrictChecking();
	/**
	 *  bind needed for {@link DefaultFullAwareSearcher } and descendents<br/>
	 *  you can use DefaultLocalizedSearcherCacheNetworkAware with TypeLiterale, as above:<br/>
	 *  bind( new TypeLiteral<LocalizedSearcherCacheNetworkAwareStrict<YourResultParam>>(){}).to(new TypeLiteral<DefaultLocalizedSearcherCacheNetworkAware<YourResultParam>>(){});
	 */
	protected abstract void bindLocalizedSearcherCacheNetworkAwareStrictChecking();
//		bind( new TypeLiteral<LocalizedSearcherCacheNetworkAwareStrict<Result>>(){}).to(new TypeLiteral<DefaultLocalizedSearcherCacheNetworkAwareStrict<Result>>(){});
	
	
	/**
	 *  bind useful for FullAwareSearcher direct uses<br/>
	 *  you can use DefaultFullAwareSearcher with TypeLiterale, as above:<br/>
	 *  bind( new TypeLiteral<FullAwareSearcher<YourResultParam>>(){}).toProvider(new TypeLiteral<DefaultFullAwareSearcherProvider<YourResultParam>>(){});
	 */
	protected void bindFullAwareSearcher() {
//		bind( new TypeLiteral<FullAwareSearcher<Result>>(){}).toProvider(new TypeLiteral<DefaultFullAwareSearcher<Result>>(){});
	}
}
