package net.iubris.diane._roboguice.providers.aware.location.state.three.base;

import javax.inject.Inject;
import javax.inject.Provider;

import net.iubris.diane._roboguice.aware.location.state.three.base.DianeDistanceMaximumThreshold;
import net.iubris.diane.aware.location.state.three.base.DefaultThreeStateLocationAwareLocationSupplier;
import net.iubris.polaris.locator.provider.LocationProvider;

public class DefaultThreeStateLocationAwareLocationSupplierProvider implements Provider<DefaultThreeStateLocationAwareLocationSupplier> {

	@Inject LocationProvider locationProvider;
	@Inject @DianeDistanceMaximumThreshold Integer distanceMaximumThreshold;
	
	@Override
	public DefaultThreeStateLocationAwareLocationSupplier get() {
		return new DefaultThreeStateLocationAwareLocationSupplier(locationProvider, distanceMaximumThreshold);
	}
}
