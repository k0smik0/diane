package net.iubris.dianevanillasample;

import android.content.Context;

public class DianeSampleSearcherBuilder {
	static public DianeSampleSearcher buildController(Context context) {
		return new DianeSampleFactory(context).create();
	}
}
