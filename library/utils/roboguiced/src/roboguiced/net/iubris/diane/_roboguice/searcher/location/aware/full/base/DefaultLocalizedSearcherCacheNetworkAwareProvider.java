package net.iubris.diane._roboguice.searcher.location.aware.full.base;

import javax.inject.Inject;
import javax.inject.Provider;

import net.iubris.diane.searcher.location.aware.cache.LocalizedSearcherCacheAware;
import net.iubris.diane.searcher.location.aware.full.base.DefaultLocalizedSearcherCacheNetworkAware;
import net.iubris.diane.searcher.location.aware.network.LocalizedSearcherNetworkAware;

public class DefaultLocalizedSearcherCacheNetworkAwareProvider<Result> implements Provider<DefaultLocalizedSearcherCacheNetworkAware<Result>> {

	private final LocalizedSearcherCacheAware<Result> cacheAwareSearcher;
	private final LocalizedSearcherNetworkAware<Result> networkAwareSearcher;
	
	@Inject
	public DefaultLocalizedSearcherCacheNetworkAwareProvider(
			LocalizedSearcherCacheAware<Result> cacheAwareSearcher,
			LocalizedSearcherNetworkAware<Result> networkAwareSearcher) {
		this.cacheAwareSearcher = cacheAwareSearcher;
		this.networkAwareSearcher = networkAwareSearcher;
	}

	@Override
	public DefaultLocalizedSearcherCacheNetworkAware<Result> get() {
		return new DefaultLocalizedSearcherCacheNetworkAware<Result>(cacheAwareSearcher,networkAwareSearcher);
	}
}
