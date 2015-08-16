/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * CacheTooOldException.java is part of Diane.
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
package net.iubris.diane.aware.cache.exceptions.base;

import net.iubris.diane.aware.cache.exceptions.CacheStateException;

public class CacheEmptyException extends CacheStateException {


	public CacheEmptyException() {
		super();
	}

	public CacheEmptyException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public CacheEmptyException(String detailMessage) {
		super(detailMessage);
	}

	public CacheEmptyException(Throwable throwable) {
		super(throwable);
	}

	private static final long serialVersionUID = 668070615051073285L;



}
