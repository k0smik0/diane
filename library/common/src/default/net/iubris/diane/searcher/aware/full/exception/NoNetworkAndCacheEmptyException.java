package net.iubris.diane.searcher.aware.full.exception;

import net.iubris.diane.searcher.aware.exceptions.AwareSearchException;

public class NoNetworkAndCacheEmptyException extends AwareSearchException {

	private static final long serialVersionUID = 6648696438900611230L;

	public NoNetworkAndCacheEmptyException() {
		super();
	}

	public NoNetworkAndCacheEmptyException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public NoNetworkAndCacheEmptyException(String detailMessage) {
		super(detailMessage);
	}

	public NoNetworkAndCacheEmptyException(Throwable throwable) {
		super(throwable);
	}
}
