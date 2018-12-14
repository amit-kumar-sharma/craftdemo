/**
 * 
 */
package org.craft.demo.dataimport.exceptions;

/**
 * @author asharma
 *
 */
public class DataImportException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DataImportException(String errorMessage) {
		super(errorMessage);
	}

}
