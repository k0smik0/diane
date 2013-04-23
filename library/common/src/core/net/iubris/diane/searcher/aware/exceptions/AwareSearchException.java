package net.iubris.diane.searcher.aware.exceptions;

import net.iubris.diane.searcher.exceptions.SearchException;

public class AwareSearchException extends SearchException {

	private static final long serialVersionUID = 5021429999829203659L;

	public AwareSearchException(Throwable cause) {
		super(cause);
	}
	public AwareSearchException(String string) {
		super(string);
	}
	public AwareSearchException(Throwable cause,String string) {
		super(cause,string);
	}

}
