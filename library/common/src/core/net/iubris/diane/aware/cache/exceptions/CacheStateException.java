/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * CacheStateException.java is part of Diane.
 * 
 * Diane is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Diane is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with Diane ; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 ******************************************************************************/
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
