/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * ThreeStateLocationAware.java is part of Diane.
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
package net.iubris.diane.aware.location.state.three;

import net.iubris.diane.aware.location.LocationAware;
import net.iubris.diane.searcher.aware.location.exceptions.base.LocationNotSoUsefulException;

/**
 * ThreeState* because we want emulate a three-state behaviour in "isNewLocationUseful", returning true,false or throw LocationNotSoUsefulException
 * @author Massimiliano Leone - k0smik0
 */
public interface ThreeStateLocationAware extends LocationAware<Boolean> {
	/**
	 * @return true is useful, false elsewhere
	 * @throws LocationNotSoUsefulException if new location is (obviously) not so useful, according implementation logic
	 */
	@Override
	public boolean isNewLocationUseful() throws /*LocationFreshNullException,*/ LocationNotSoUsefulException;
}
