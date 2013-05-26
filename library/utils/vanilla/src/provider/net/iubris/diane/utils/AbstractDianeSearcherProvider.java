package net.iubris.diane.utils;

import java.lang.reflect.InvocationTargetException;

import net.iubris.diane.aware.location.state.three.ThreeStateLocationAwareLocationSupplier;
import net.iubris.diane.aware.location.state.three.base.DefaultThreeStateLocationAwareLocationSupplier;
import net.iubris.diane.aware.network.state.checker.CheckerStateNetworkAware;
import net.iubris.diane.aware.network.state.checker.base.DefaultCheckerStateNetworkAware;
import net.iubris.diane.searcher.aware.full.base.DefaultFullAwareSearcher;
import net.iubris.diane.searcher.location.aware.cache.LocalizedSearcherCacheAwareStrictChecking;
import net.iubris.diane.searcher.location.aware.full.LocalizedSearcherCacheNetworkAware;
import net.iubris.diane.searcher.location.aware.full.LocalizedSearcherCacheNetworkAwareStrictChecking;
import net.iubris.diane.searcher.location.aware.full.base.DefaultLocalizedSearcherCacheNetworkAwareStrictChecking;
import net.iubris.diane.searcher.location.aware.network.LocalizedSearcherNetworkAwareStrictChecking;
import net.iubris.polaris.locator.provider.LocationProvider;
import android.content.Context;
import android.net.ConnectivityManager;

public abstract class AbstractDianeSearcherProvider<DFAS extends DefaultFullAwareSearcher<Result>, Result> {
	
	protected static final int DEFAULT_DISTANCE_MAXIMUM_THRESHOLD = 200;
	
	private Class<DFAS> fasClass;
	protected final Context context;
	
//	public 
	
	public AbstractDianeSearcherProvider(Class<DFAS> fasClass, Context context) {
		this.fasClass = fasClass;
		this.context = context;
	}
	public AbstractDianeSearcherProvider(Context context) {
		this.context = context;
	}

	public DFAS get() throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		DFAS fas = fasClass.getConstructor(ThreeStateLocationAwareLocationSupplier.class, LocalizedSearcherCacheNetworkAware.class).newInstance(getThreeStateLocationAwareLocationSupplier(), getLocalizedSearcherCacheNetworkAwareStrictChecking());
		return fas;
//		return new DefaultFullAwareSearcher<Result>(createThreeStateLocationAwareLocationSupplier(), createLocalizedSearcherCacheNetworkAware());
	}

	protected abstract LocationProvider getLocationProvider();
	protected static int getDistanceMaximumThreshold() {
		return AbstractDianeSearcherProvider.DEFAULT_DISTANCE_MAXIMUM_THRESHOLD;
	};
	protected ThreeStateLocationAwareLocationSupplier getThreeStateLocationAwareLocationSupplier() {
		return new DefaultThreeStateLocationAwareLocationSupplier(getLocationProvider(), getDistanceMaximumThreshold());
	}
	
	protected abstract LocalizedSearcherCacheAwareStrictChecking<Result> getLocalizedSearcherCacheAwareStrictChecking();
	protected abstract LocalizedSearcherNetworkAwareStrictChecking<Result> getLocalizedSearcherNetworkAwareStrictChecking();
	protected LocalizedSearcherCacheNetworkAwareStrictChecking<Result> getLocalizedSearcherCacheNetworkAwareStrictChecking() {
		return new DefaultLocalizedSearcherCacheNetworkAwareStrictChecking<Result>(getLocalizedSearcherCacheAwareStrictChecking(), getLocalizedSearcherNetworkAwareStrictChecking());
	}

	protected CheckerStateNetworkAware getCheckerStateNetworkAware() {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		return new DefaultCheckerStateNetworkAware(cm);
	}
}
