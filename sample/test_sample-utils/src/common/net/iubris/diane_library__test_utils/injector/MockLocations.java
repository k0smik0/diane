package net.iubris.diane_library__test_utils.injector;

import java.util.LinkedHashMap;
import java.util.Map;

import android.util.Pair;

public class MockLocations {

	static public final Map<Pair<Double, Double>, String> locationsMap = new LinkedHashMap<Pair<Double,Double>, String>();
	static {
		locationsMap.put(new Pair<Double,Double>(44.493287,11.376921),"via Massarenti 352");
		locationsMap.put(new Pair<Double,Double>(44.493138,11.375912),"via Massarenti 250");
		
		locationsMap.put(new Pair<Double,Double>(44.493207,11.360119),"via Massarenti 54");
		locationsMap.put(new Pair<Double,Double>(44.49398,11.3568370),"via San Vitale 89");
		
		locationsMap.put(new Pair<Double,Double>(44.494282,11.352047),"via petroni 1");
		locationsMap.put(new Pair<Double,Double>(44.496176,11.35077),"piazza giuseppe verdi 4");
		
		locationsMap.put(new Pair<Double,Double>(44.494531,11.342696),"Piazza del Nettuno 2");
		locationsMap.put(new Pair<Double,Double>(44.493968,11.344762),"via degli Orefici 6");
	}
}
