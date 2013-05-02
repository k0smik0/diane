package net.iubris.diane.utils;

import java.lang.reflect.InvocationTargetException;

import net.iubris.diane.aware.location.state.three.ThreeStateLocationAwareLocationSupplier;
import net.iubris.diane.aware.location.state.three.base.DefaultThreeStateLocationAwareLocationSupplier;
import net.iubris.diane.aware.network.state.checker.CheckerStateNetworkAware;
import net.iubris.diane.aware.network.state.checker.base.DefaultCheckerStateNetworkAware;
import net.iubris.diane.searcher.aware.full.base.DefaultFullAwareSearcher;
import net.iubris.diane.searcher.location.aware.cache.LocalizedSearcherCacheAware;
import net.iubris.diane.searcher.location.aware.full.LocalizedSearcherCacheNetworkAware;
import net.iubris.diane.searcher.location.aware.full.base.DefaultLocalizedSearcherCacheNetworkAware;
import net.iubris.diane.searcher.location.aware.network.LocalizedSearcherNetworkAware;
import net.iubris.polaris.locator.provider.LocationProvider;
import android.content.Context;
import android.net.ConnectivityManager;

public abstract class AbstractDianeSearcherProvider<DFAS extends DefaultFullAwareSearcher<Result>, Result> {
	
	protected static final int DEFAULT_DISTANCE_MAXIMUM_THRESHOLD = 100;
	
	private Class<DFAS> fasClass;
	private Context context;
	
	public AbstractDianeSearcherProvider(Class<DFAS> fasClass,Context context) {
		this.fasClass = fasClass;
		this.context = context;
	}

	public DFAS get() throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		DFAS fas = fasClass.getConstructor(ThreeStateLocationAwareLocationSupplier.class, LocalizedSearcherCacheNetworkAware.class).newInstance(getThreeStateLocationAwareLocationSupplier(), getLocalizedSearcherCacheNetworkAware());
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
	
	protected abstract LocalizedSearcherCacheAware<Result> getLocalizedSearcherCacheAware();
	protected abstract LocalizedSearcherNetworkAware<Result> getLocalizedSearcherNetworkAware();
	protected LocalizedSearcherCacheNetworkAware<Result> getLocalizedSearcherCacheNetworkAware() {
		return new DefaultLocalizedSearcherCacheNetworkAware<Result>(getLocalizedSearcherCacheAware(), getLocalizedSearcherNetworkAware());
	}

	protected CheckerStateNetworkAware getCheckerStateNetworkAware() {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		return new DefaultCheckerStateNetworkAware(cm);
	}
}
