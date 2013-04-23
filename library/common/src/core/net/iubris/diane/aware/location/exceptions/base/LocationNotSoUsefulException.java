package net.iubris.diane.aware.location.exceptions.base;

import net.iubris.diane.aware.location.exceptions.LocationStateException;

public class LocationNotSoUsefulException extends LocationStateException {

	private static final long serialVersionUID = -3352834444363066886L;
	public LocationNotSoUsefulException(String string) {
		super(string);
	}
	public LocationNotSoUsefulException(Throwable cause, String string) {
		super(cause, string);
	}
	public LocationNotSoUsefulException(Throwable cause) {
		super(cause);
	}

}
