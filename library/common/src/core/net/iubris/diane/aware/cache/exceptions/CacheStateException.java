package net.iubris.diane.aware.cache.exceptions;

import net.iubris.diane.aware.exceptions.StateException;

public class CacheStateException extends StateException {

	public CacheStateException(String message) {
		super(message);
	}

	public CacheStateException(Throwable cause, String string) {
		super(cause, string);
	}

	public CacheStateException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = 5735468856758684161L;
}
