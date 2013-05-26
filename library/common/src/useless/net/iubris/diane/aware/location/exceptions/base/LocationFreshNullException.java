package net.iubris.diane.aware.location.exceptions.base;

import net.iubris.diane.aware.location.exceptions.LocationStateException;

public class LocationFreshNullException extends LocationStateException {

	private static final long serialVersionUID = 1L;

	public LocationFreshNullException(String string) {
		super(string);
	}
	public LocationFreshNullException(Throwable cause, String string) {
		super(cause, string);
	}
	public LocationFreshNullException(Throwable cause) {
		super(cause);
	}
}
