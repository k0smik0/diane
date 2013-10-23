/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * MockLocations.java is part of Diane.
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
package net.iubris.diane_library__test_utils.injector;

import java.util.LinkedHashMap;
import java.util.Map;

import android.util.Pair;

public class MockLocations {

	static public final Map<Pair<Double, Double>, String> locationsMap = new LinkedHashMap<Pair<Double,Double>, String>();
	static {
		/*
		locationsMap.put(new Pair<Double,Double>(44.493287,11.376921),"via Massarenti 352");
		locationsMap.put(new Pair<Double,Double>(44.493138,11.375912),"via Massarenti 250");
		
		locationsMap.put(new Pair<Double,Double>(44.493207,11.360119),"via Massarenti 54");
		locationsMap.put(new Pair<Double,Double>(44.49398,11.3568370),"via San Vitale 89");
		
		locationsMap.put(new Pair<Double,Double>(44.494282,11.352047),"via petroni 1");
		locationsMap.put(new Pair<Double,Double>(44.496176,11.35077),"piazza giuseppe verdi 4");
		
		locationsMap.put(new Pair<Double,Double>(44.494531,11.342696),"Piazza del Nettuno 2");
		locationsMap.put(new Pair<Double,Double>(44.493968,11.344762),"via degli Orefici 6");
*/		
		locationsMap.put(new Pair<Double,Double>(44.493099,11.377171),"");
		locationsMap.put(new Pair<Double,Double>(44.493099,11.377170),"");
		locationsMap.put(new Pair<Double,Double>(44.493287,11.376921),"via Massarenti 352");
		locationsMap.put(new Pair<Double,Double>(44.493138,11.375912),"via Massarenti 250");
		locationsMap.put(new Pair<Double,Double>(44.493290,11.375110),"");
		locationsMap.put(new Pair<Double,Double>(44.493290,11.375111),"");
		locationsMap.put(new Pair<Double,Double>(44.493290,11.375110),"");
		locationsMap.put(new Pair<Double,Double>(44.493351,11.374280),"");
		locationsMap.put(new Pair<Double,Double>(44.493320,11.374140),"");
		locationsMap.put(new Pair<Double,Double>(44.493340,11.372920),"");
		locationsMap.put(new Pair<Double,Double>(44.492771,11.369520),"");
		locationsMap.put(new Pair<Double,Double>(44.492722,11.368930),"");
		locationsMap.put(new Pair<Double,Double>(44.492699,11.367670),"");
		locationsMap.put(new Pair<Double,Double>(44.492531,11.365960),"");
		locationsMap.put(new Pair<Double,Double>(44.492352,11.365440),"");
		locationsMap.put(new Pair<Double,Double>(44.492180,11.364770),"");
		locationsMap.put(new Pair<Double,Double>(44.492199,11.364280),"");
		locationsMap.put(new Pair<Double,Double>(44.492310,11.363620),"");
		locationsMap.put(new Pair<Double,Double>(44.493221,11.360110),"");
		locationsMap.put(new Pair<Double,Double>(44.493225,11.360106),"");
		locationsMap.put(new Pair<Double,Double>(44.493221,11.360110),"");
		locationsMap.put(new Pair<Double,Double>(44.493207,11.360119),"via Massarenti 54");
		locationsMap.put(new Pair<Double,Double>(44.493771,11.358320),"");
		locationsMap.put(new Pair<Double,Double>(44.493969,11.357330),"");
		locationsMap.put(new Pair<Double,Double>(44.493980,11.356980),"");
		locationsMap.put(new Pair<Double,Double>(44.493980,11.356837),"via San Vitale 89");
		locationsMap.put(new Pair<Double,Double>(44.494011,11.356640),"");
		locationsMap.put(new Pair<Double,Double>(44.494015,11.356636),"");
		locationsMap.put(new Pair<Double,Double>(44.494011,11.356640),"");
		locationsMap.put(new Pair<Double,Double>(44.494030,11.356500),"");
		locationsMap.put(new Pair<Double,Double>(44.494068,11.356020),"");
		locationsMap.put(new Pair<Double,Double>(44.494091,11.353730),"");
		locationsMap.put(new Pair<Double,Double>(44.494190,11.352060),"");
		locationsMap.put(new Pair<Double,Double>(44.494331,11.352050),"");
		locationsMap.put(new Pair<Double,Double>(44.494282,11.352047),"via petroni 1");
		locationsMap.put(new Pair<Double,Double>(44.494598,11.352010),"");
		locationsMap.put(new Pair<Double,Double>(44.494801,11.351920),"");
		locationsMap.put(new Pair<Double,Double>(44.494961,11.351690),"");
		locationsMap.put(new Pair<Double,Double>(44.496176,11.35077),"piazza giuseppe verdi 4");
		locationsMap.put(new Pair<Double,Double>(44.496052,11.350570),"");
		locationsMap.put(new Pair<Double,Double>(44.496037,11.350555),"");
		locationsMap.put(new Pair<Double,Double>(44.496052,11.350570),"");
		locationsMap.put(new Pair<Double,Double>(44.496170,11.350440),"");
		locationsMap.put(new Pair<Double,Double>(44.495110,11.347520),"");
		locationsMap.put(new Pair<Double,Double>(44.494911,11.347180),"");
		locationsMap.put(new Pair<Double,Double>(44.494469,11.346780),"");
		locationsMap.put(new Pair<Double,Double>(44.494389,11.346600),"");
		locationsMap.put(new Pair<Double,Double>(44.494221,11.346470),"");
		locationsMap.put(new Pair<Double,Double>(44.494621,11.344080),"");
		locationsMap.put(new Pair<Double,Double>(44.494759,11.343020),"");
		locationsMap.put(new Pair<Double,Double>(44.494531,11.342696),"Piazza del Nettuno 2");
		locationsMap.put(new Pair<Double,Double>(44.494862,11.342600),"");
		locationsMap.put(new Pair<Double,Double>(44.494339,11.342440),"");
		locationsMap.put(new Pair<Double,Double>(44.494289,11.342540),"");
		locationsMap.put(new Pair<Double,Double>(44.494289,11.342535),"");
		locationsMap.put(new Pair<Double,Double>(44.494289,11.342540),"");
		locationsMap.put(new Pair<Double,Double>(44.494080,11.342500),"");
		locationsMap.put(new Pair<Double,Double>(44.493851,11.343760),"");
		locationsMap.put(new Pair<Double,Double>(44.494122,11.343800),"");
		locationsMap.put(new Pair<Double,Double>(44.493968,11.344762),"via degli Orefici 6");
		locationsMap.put(new Pair<Double,Double>(44.493999,11.344570),"");
		locationsMap.put(new Pair<Double,Double>(44.494003,11.344574),"");
	}
}
