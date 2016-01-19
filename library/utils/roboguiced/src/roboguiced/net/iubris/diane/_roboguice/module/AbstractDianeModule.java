/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * AbstractDianeModule.java is part of Diane.
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

import net.iubris.diane.aware.cache.states.three.SearchingByCacheBehaviour;
import net.iubris.diane.aware.location.state.three.base.DefaultThreeStateLocationAwareLocationSupplier;
import net.iubris.diane.aware.location.state.three.ThreeStateLocationAwareLocationSupplier;
import net.iubris.diane.aware.location.state.three.base.annotation.DistanceMaximumThreshold;
import net.iubris.diane.aware.network.state.checker.CheckerStateNetworkAware;
import net.iubris.diane.aware.network.state.checker.base.DefaultCheckerStateNetworkAware;
import net.iubris.diane.searcher.aware.full.base.DefaultFullAwareSearcher;

import com.google.inject.AbstractModule;

/**
 * default DistanceMaximumThreshold = 200; 
 */
public abstract class AbstractDianeModule extends AbstractModule {
	
	private final int distanceMaximumThreshold;
//	private final int timeMaximumThreshold;
	
	private static final int DistanceMaximumThreshold = 200;
	
	public AbstractDianeModule() {
		this.distanceMaximumThreshold = DistanceMaximumThreshold; // 200 meters is default
//		this.timeMaximumThreshold = 2; // 2 minutes is default
	}
	
	/*public AbstractDianeModule(int distanceMaximumThreshold, int timeMaximumThreshold) {
		this.distanceMaximumThreshold = distanceMaximumThreshold;
//		this.timeMaximumThreshold = timeMaximumThreshold;
	}*/
	public AbstractDianeModule(int distanceMaximumThreshold) {
		this.distanceMaximumThreshold = distanceMaximumThreshold;
//		this.timeMaximumThreshold = 2;
	}
	/*
	public AbstractDianeModule(Integer timeMaximumThreshold) {
		this.timeMaximumThreshold = timeMaximumThreshold;
		distanceMaximumThreshold = 200;
	}*/

	/**
	 * this just calls abstract method bind*SOME*Aware you have to implement, 
	 * and binds {@link DianeDistanceMaximumThreshold } to "200"  
	 */
	@Override
	protected void configure() {
		
		bindLocationProvider(); // abstract
		bindDistanceMaximumThreshold();		
		bindThreeStateLocationAwareLocationSupplier();
		
		bindSearchByCacheBehaviourForThreeStateCacheAware();
		bindThreeStateCacheAware(); // abstract		
		bindLocalizedSearcherCacheAwareStrictChecking(); // abstract
		bindCheckerStateNetworkAware();
		bindLocalizedSearcherNetworkAwareStrictChecking(); // abstract
		bindLocalizedSearcherCacheNetworkAwareStrictChecking();
		
		bindFullAwareSearcher(); // do nothing
		
//		bindLocationNullAllWrongString(); // provides an english message
//		bindLocationNullEnableGPSString(); // provides an english message
	}
	
	/**
	 *  binding neededd  for {@link DefaultThreeStateLocationAwareLocationSupplier } and descendents
	 */
	protected abstract void bindLocationProvider();
//	protected abstract LocationProvider providesLocationProvider(LocationProvider locationProviderConcrete);
	/**
	 *  binding neededd  for {@link DefaultThreeStateLocationAwareLocationSupplier } and descendents<br/>
	 *  default is:<br/>
	 *  bindConstant().annotatedWith(DistanceMaximumThreshold.class).to(distanceMaximumThreshold);
	 *  with distanceMaximumThreshold=200<br/>
	 *  you can override this method or change distanceMaximumThreshold using constructor
	 */
	protected void bindDistanceMaximumThreshold() {
//		bindConstant().annotatedWith(DistanceMaximumThreshold.class).to(distanceMaximumThreshold);
		bind(Integer.class).annotatedWith(DistanceMaximumThreshold.class).toInstance(Integer.valueOf(distanceMaximumThreshold));
	}
	/**
	 *  binding neededd  for {@link DefaultThreeStateLocationAwareLocationSupplier } and descendents<br/>
	 *  default is:<br/>
	 *  bindConstant().annotatedWith(TimeMaximumThreshold.class).to(timeMaximumThreshold);<br/>
	 *  bind(Integer.class).annotatedWith(TimeMaximumThreshold.class).to(timeMaximumThreshold);
	 *  with timeMaximumThreshold=2<br/>
	 *  you can override this method or change timeMaximumThreshold using constructor
	 */
	/*protected void bindTimeMaximumThreshold() {
//		bindConstant().annotatedWith(TimeMaximumThreshold.class).to(timeMaximumThreshold);
		bind(Integer.class).annotatedWith(TimeMaximumThreshold.class).toInstance(Integer.valueOf(timeMaximumThreshold));
	}*/
	
	/*protected void setDistanceMaximumThreshold(int distanceMaximumThreshold) {
		this.distanceMaximumThreshold = distanceMaximumThreshold;
	}*/
	
	/**
	 * as default, bind to a anonymous instance returning "false" for "useFirstlyCache"
	 */
	protected void bindSearchByCacheBehaviourForThreeStateCacheAware() {
		bind(SearchingByCacheBehaviour.class).toInstance(
		new SearchingByCacheBehaviour() {
			@Override
			public boolean useFirstlyCache() {
				return false;
			}
		});
	}
	
	/**
	 *  binding neededd  for {@link DefaultFullAwareSearcher } and descendents<br/>
	 *  default: binds to DefaultThreeStateLocationAwareLocationSupplier 
	 */
	protected void bindThreeStateLocationAwareLocationSupplier() {
		bind(ThreeStateLocationAwareLocationSupplier.class).to(DefaultThreeStateLocationAwareLocationSupplier.class);
//		bind(ThreeStateLocationAwareLocationSupplier.class).in(Singleton.class);
	};
	
	
	/**
	 *  binding neededd for {@link AbstractLocalizedSearcherCacheAware } and descendents<br/>
	 *  use TypeLiteral for binding!
	 */
	protected abstract void bindThreeStateCacheAware();
	/**
	 *  binding neededd  for {@link DefaultLocalizedSearcherCacheNetworkAware } and descendents<br/>
	 *  use TypeLiteral for binding!
	 */
	protected abstract void bindLocalizedSearcherCacheAwareStrictChecking();
	
	/**
	 *  binding needed for {@link CheckerStateNetworkAware } and descendents<br/>
	 *  default: to DefaultCheckerStateNetworkAware
	 */
	protected void bindCheckerStateNetworkAware() {
		bind(CheckerStateNetworkAware.class).to(DefaultCheckerStateNetworkAware.class);
	}
	
	/**
	 *
	*  binding neededd for {@link DefaultLocalizedSearcherCacheNetworkAware } and descendents
	*/
	protected abstract void bindLocalizedSearcherNetworkAwareStrictChecking();
	/**
	 *  binding needed for {@link DefaultFullAwareSearcher } and descendents<br/>
	 *  you can use DefaultLocalizedSearcherCacheNetworkAware with TypeLiterale, as above:<br/>
	 *  bind( new TypeLiteral<LocalizedSearcherCacheNetworkAwareStrict<YourResultParam>>(){}).to(new TypeLiteral<DefaultLocalizedSearcherCacheNetworkAware<YourResultParam>>(){});
	 */
	protected abstract void bindLocalizedSearcherCacheNetworkAwareStrictChecking();
//		bind( new TypeLiteral<LocalizedSearcherCacheNetworkAwareStrict<Result>>(){}).to(new TypeLiteral<DefaultLocalizedSearcherCacheNetworkAwareStrict<Result>>(){});
	
	
	/**
	 *  binding useful for FullAwareSearcher direct uses<br/>
	 *  you can use DefaultFullAwareSearcher with TypeLiteral, as below:<br/>
	 *  bind( new TypeLiteral<FullAwareSearcher<YourResultParam>>(){}).toProvider(new TypeLiteral<DefaultFullAwareSearcherProvider<YourResultParam>>(){});
	 */
	protected void bindFullAwareSearcher() {
//		bind( new TypeLiteral<FullAwareSearcher<Result>>(){}).toProvider(new TypeLiteral<DefaultFullAwareSearcher<Result>>(){});
	}
	
//	/**
//	 * binding needed for {@link GetFreshLocationTask}<br/>
//	 * default: in english
//	 */
//	protected void bindLocationNullAllWrongString() {
//		bind(String.class).annotatedWith(LocationNullAllWrongString.class).toInstance("location is null, something was wrong.");
//	}
//	protected void bindLocationNullEnableGPS() {
//		bind(String.class).annotatedWith(LocationNullEnableGPSString.class).toInstance("location is null, you could enable your GPS.");
//	}
//	
//	/**
//	 * binding needed for {@link GetFreshLocationTask}<br/>
//	 * default: do nothing 
//	 */
//	protected void bindLocationNullEnableGPSString() {}
}
