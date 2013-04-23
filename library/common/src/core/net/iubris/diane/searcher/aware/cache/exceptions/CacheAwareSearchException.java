package net.iubris.diane.searcher.aware.cache.exceptions;

import net.iubris.diane.searcher.aware.exceptions.AwareSearchException;

public class CacheAwareSearchException extends AwareSearchException {
	
	
	public CacheAwareSearchException(String string) {
		super(string);
	}	
	public CacheAwareSearchException(Throwable cause) {
		super(cause);
	}
	public CacheAwareSearchException(Throwable cause,String string) {
		super(cause,string);
	}

	private static final long serialVersionUID = 7945431253492855063L;

}
