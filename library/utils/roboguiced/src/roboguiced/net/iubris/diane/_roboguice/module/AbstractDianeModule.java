package net.iubris.diane._roboguice.module;

import net.iubris.diane._roboguice.aware.location.state.three.base.DianeDistanceMaximumThreshold;
import net.iubris.diane._roboguice.providers.aware.location.state.three.base.DefaultThreeStateLocationAwareLocationSupplierProvider;
import net.iubris.diane._roboguice.providers.aware.network.state.checker.base.DefaultCheckerStateNetworkAwareProvider;
import net.iubris.diane.aware.location.state.three.ThreeStateLocationAwareLocationSupplier;
import net.iubris.diane.aware.location.state.three.base.DefaultThreeStateLocationAwareLocationSupplier;
import net.iubris.diane.aware.network.state.checker.CheckerStateNetworkAware;
import net.iubris.diane.searcher.aware.full.base.DefaultFullAwareSearcher;
import net.iubris.diane.searcher.location.aware.cache.base.AbstractLocalizedSearcherCacheAware;
import net.iubris.diane.searcher.location.aware.full.base.DefaultLocalizedSearcherCacheNetworkAware;

import com.google.inject.AbstractModule;

public abstract class AbstractDianeModule extends AbstractModule {
	
	private final int distanceMaximumThreshold;
	
	public AbstractDianeModule() {
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
		bindLocalizedSearcherCacheAware(); // abstract
		bindCheckerStateNetworkAware();
		bindLocalizedSearcherNetworkAware(); // abstract
		bindLocalizedSearcherCacheNetworkAware(); 
		
		bindFullAwareSearcher();
	}
	
	/**
	 *  bond needed  for {@link DefaultThreeStateLocationAwareLocationSupplier } and descendents
	 */
	protected abstract void bindLocationProvider();
	/**
	 *  bond needed  for {@link DefaultThreeStateLocationAwareLocationSupplier } and descendents<br/>
	 *  default is 200
	 */
	protected void bindDianeDistanceMaximumThreshold() {
		bindConstant().annotatedWith(DianeDistanceMaximumThreshold.class).to(distanceMaximumThreshold);
	}
	/**
	 *  bond needed  for {@link DefaultFullAwareSearcher } and descendents<br/>
	 *  default: binds to DefaultThreeStateLocationAwareLocationSupplier 
	 */
	protected void bindThreeStateLocationAwareLocationSupplier() {
		bind(ThreeStateLocationAwareLocationSupplier.class).toProvider(DefaultThreeStateLocationAwareLocationSupplierProvider.class);
	};
	
	
	/**
	 *  bond needed for {@link AbstractLocalizedSearcherCacheAware } and descendents
	 */
	protected abstract void bindThreeStateCacheAware();
	/**
	 *  bond needed  for {@link DefaultLocalizedSearcherCacheNetworkAware } and descendents
	 */
	protected abstract void bindLocalizedSearcherCacheAware();
	
	/**
	 *  bond needed for {@link CheckerStateNetworkAware } and descendents<br/>
	 *  default: to DefaultCheckerStateNetworkAware
	 */
	protected void bindCheckerStateNetworkAware() {
		bind(CheckerStateNetworkAware.class).toProvider(DefaultCheckerStateNetworkAwareProvider.class);
	}
	
	/**
	 *  bond needed for {@link DefaultLocalizedSearcherCacheNetworkAware } and descendents
	 */
	protected abstract void bindLocalizedSearcherNetworkAware();
	/**
	 *  bond needed for {@link DefaultFullAwareSearcher } and descendents
	 *  default: DefaultLocalizedSearcherCacheNetworkAware
	 */
	protected <Result> void bindLocalizedSearcherCacheNetworkAware() {
//		bind( new TypeLiteral<LocalizedSearcherCacheNetworkAware<Result>>(){}).toProvider(new TypeLiteral<DefaultLocalizedSearcherCacheNetworkAwareProvider<Result>>(){});
	}
	
	/**
	 *  bond useful for FullAwareSearcher direct uses
	 *  default: DefaultFullAwareSearcher
	 */
	protected <Result> void bindFullAwareSearcher() {
//		bind( new TypeLiteral<FullAwareSearcher<Result>>(){}).toProvider(new TypeLiteral<DefaultFullAwareSearcherProvider<Result>>(){});
	}
}
