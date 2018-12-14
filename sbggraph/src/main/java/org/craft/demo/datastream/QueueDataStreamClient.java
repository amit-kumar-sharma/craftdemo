/**
 * 
 */
package org.craft.demo.datastream;

import java.util.List;

import org.craft.demo.dataimport.model.CustomerDataModel;

/**
 * @author asharma
 *	Every Data Stream Client interface should implement this interface.
 *  
 */
public interface QueueDataStreamClient {
	/**
	 * Saves the given customer data Model in the DatStream for further Downstream processing for populating the graph DB.
	 * @param dataModel data model to save.
	 */
	public void publishData(CustomerDataModel dataModel);
	
	/**
	 * Saves the given customer data Models in the DatStream for further Downstream processing for populating the graph DB.
	 * @param dataModels data models to save.
	 */
	public void publishData(List<CustomerDataModel> dataModels);
	
	/**
	 * Reads the next available data from the Datastream.
	 * 
	 * return returns the next available data if exists otherwise blocks and wait for the data to come.
	 */
	public CustomerDataModel readData();

}
