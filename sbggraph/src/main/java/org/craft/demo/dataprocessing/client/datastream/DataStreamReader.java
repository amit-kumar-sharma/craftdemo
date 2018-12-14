/**
 * 
 */
package org.craft.demo.dataprocessing.client.datastream;

import org.craft.demo.dataimport.model.CustomerDataModel;

/**
 * @author asharma
 *	Every Data Stream Client interface should implement this interface.
 *  
 */
public interface DataStreamReader {
	
	/**
	 * Reads the next available data from the Datastream.
	 * 
	 * return returns the next available data if exists otherwise blocks and wait for the data to come.
	 */
	public CustomerDataModel readData();

}
