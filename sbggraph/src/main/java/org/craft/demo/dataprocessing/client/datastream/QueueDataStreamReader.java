/**
 * 
 */
package org.craft.demo.dataprocessing.client.datastream;

import org.craft.demo.dataimport.model.CustomerDataModel;
import org.craft.demo.datastream.QueueDataStreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author asharma
 *	Every Data Stream Client interface should implement this interface.
 *  
 */
@Component
public class QueueDataStreamReader implements DataStreamReader{
	
	@Autowired
	private QueueDataStreamClient dataStreamClient;
	
	/**
	 * Reads the next available data from the Datastream.
	 * 
	 * return returns the next available data if exists otherwise blocks and wait for the data to come.
	 */
	public CustomerDataModel readData() {
		return dataStreamClient.readData();
	}

}
