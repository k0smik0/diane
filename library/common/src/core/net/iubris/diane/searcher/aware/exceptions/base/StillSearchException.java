package net.iubris.diane.searcher.aware.exceptions.base;

import net.iubris.diane.searcher.aware.exceptions.AwareSearchException;

public class StillSearchException extends AwareSearchException {

	private static final long serialVersionUID = 4705813308459622846L;
	public StillSearchException(Throwable cause) {
		super(cause);
	}
	public StillSearchException(String string) {
		super(string);
	}
	public StillSearchException(Throwable cause,String string) {
		super(cause,string);
	}
}
