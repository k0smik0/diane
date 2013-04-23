package net.iubris.diane._roboguice.searcher.aware.full.base;

import javax.inject.Inject;
import javax.inject.Provider;

import net.iubris.diane.aware.location.state.three.ThreeStateLocationAwareLocationSupplier;
import net.iubris.diane.searcher.aware.full.base.DefaultFullAwareSearcher;
import net.iubris.diane.searcher.location.aware.full.LocalizedSearcherCacheNetworkAware;

public class DefaultFullAwareSearcherProvider<Result> implements Provider<DefaultFullAwareSearcher<Result>> {

	final private ThreeStateLocationAwareLocationSupplier threeStateLocationAwareLocationSupplier;
	final private LocalizedSearcherCacheNetworkAware<Result> localizedSearcherCacheNetworkAware;
	
	@Inject
	public DefaultFullAwareSearcherProvider(
			ThreeStateLocationAwareLocationSupplier threeStateLocationAwareLocationSupplier,
			LocalizedSearcherCacheNetworkAware<Result> localizedSearcherCacheNetworkAware) {
		this.threeStateLocationAwareLocationSupplier = threeStateLocationAwareLocationSupplier;
		this.localizedSearcherCacheNetworkAware = localizedSearcherCacheNetworkAware;
	}

	@Override
	public DefaultFullAwareSearcher<Result> get() {
		return new DefaultFullAwareSearcher<Result>(threeStateLocationAwareLocationSupplier, localizedSearcherCacheNetworkAware);
	}
}
