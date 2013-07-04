/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * DianeTestUtilModule.java is part of Diane.
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
