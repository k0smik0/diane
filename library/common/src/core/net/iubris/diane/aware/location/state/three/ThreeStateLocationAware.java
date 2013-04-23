package net.iubris.diane.aware.location.state.three;

import net.iubris.diane.aware.location.LocationAware;
import net.iubris.diane.aware.location.exceptions.base.LocationNotSoUsefulException;

/**
 * ThreeState* because we want emulate a three-state behaviour in "isNewLocationUseful", returning true,false or throw LocationNotSoUsefulException
 * @author Massimiliano Leone - k0smik0
 */
public interface ThreeStateLocationAware extends LocationAware<Boolean> {
	public boolean isLocationUseful() throws LocationNotSoUsefulException;
}
