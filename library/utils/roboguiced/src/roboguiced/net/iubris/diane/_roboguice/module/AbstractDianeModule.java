package net.iubris.diane._roboguice.module;

import net.iubris.diane.aware.location.state.three.ThreeStateLocationAwareLocationSupplier;
import net.iubris.diane.aware.location.state.three.base.DefaultThreeStateLocationAwareLocationSupplier;
import net.iubris.diane.aware.location.state.three.base.annotation.DistanceMaximumThreshold;
import net.iubris.diane.aware.network.state.checker.CheckerStateNetworkAware;
import net.iubris.diane.aware.network.state.checker.base.DefaultCheckerStateNetworkAware;
import net.iubris.diane.searcher.aware.full.base.DefaultFullAwareSearcher;

import com.google.inject.AbstractModule;

public abstract class AbstractDianeModule extends AbstractModule {
	
	private final int distanceMaximumThreshold;
//	private final int timeMaximumThreshold;
	
	public AbstractDianeModule() {
		this.distanceMaximumThreshold = 200; // 200 meters is default
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
//	protected abstract LocationProvider providesLocationProvider(LocationProvider locationProviderConcrete);
	/**
	 *  bind needed  for {@link DefaultThreeStateLocationAwareLocationSupplier } and descendents<br/>
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
	 *  bind needed  for {@link DefaultThreeStateLocationAwareLocationSupplier } and descendents<br/>
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
	 *  bind needed  for {@link DefaultFullAwareSearcher } and descendents<br/>
	 *  default: binds to DefaultThreeStateLocationAwareLocationSupplier 
	 */
	protected void bindThreeStateLocationAwareLocationSupplier() {
		bind(ThreeStateLocationAwareLocationSupplier.class).to(DefaultThreeStateLocationAwareLocationSupplier.class);
//		bind(ThreeStateLocationAwareLocationSupplier.class).in(Singleton.class);
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
