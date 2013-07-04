package net.iubris.diane_library__test_utils._roboguice.module;

import net.iubris.diane_library__test_utils.locator.MockLocationProviderUpdater;
import net.iubris.polaris._roboguice.module.PolarisModule;
import net.iubris.polaris.locator.provider.LocationProvider;
import net.iubris.polaris.locator.updater.LocationUpdater;


public class DianeTestUtilModule extends PolarisModule {

	@Override
	protected void bindLocationProvider() {
		bind(LocationProvider.class).to(MockLocationProviderUpdater.class);
	}

	@Override
	protected void bindLocationUpdater() {
		bind(LocationUpdater.class).to(MockLocationProviderUpdater.class);
	}

}
