package net.iubris.diane.aware.cache.exceptions.base;

import net.iubris.diane.aware.cache.exceptions.CacheStateException;

public class CacheTooOldException extends CacheStateException {


	public CacheTooOldException(String message) {
		super(message);
	}

	public CacheTooOldException(Throwable cause, String string) {
		super(cause, string);
	}

	public CacheTooOldException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = -2658941881111601558L;

}
