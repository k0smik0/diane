package net.iubris.diane.searcher.aware.location.exceptions.base;

import net.iubris.diane.searcher.aware.location.exceptions.LocationAwareSearchException;

public class LocationTooNearException extends LocationAwareSearchException {

	private static final long serialVersionUID = 548601733570074653L;

	public LocationTooNearException(String detailMessage) {
		super(detailMessage);
	}

	public LocationTooNearException(Throwable throwable) {
		super(throwable);
	}

	public LocationTooNearException(Throwable throwable, String detailMessage) {
		super(throwable,detailMessage);
	}

}
