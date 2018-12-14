/**
 * 
 */
package org.craft.demo.datastream;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craft.demo.dataimport.model.CustomerDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author asharma
 *
 */
@Component
public class DataStreamService implements QueueDataStreamClient{

	private static final Logger LOGGER = LogManager.getLogger(DataStreamService.class);
	
	@Autowired
	private DataQueue dataStream;
	
	@Override
	public void publishData(final CustomerDataModel dataModel) {
		try {
			LOGGER.debug(()->"Pushing following Data into the data stream:\n" + dataModel);
			dataStream.offer(dataModel);
		} catch (InterruptedException ex) {
			LOGGER.error("Some unexpected error occured while pushing data to the data stream.", ex);
		}
	}

	@Override
	public void publishData(final List<CustomerDataModel> dataModels) {
		if(dataModels!=null) {
			dataModels.forEach(this::publishData);
		}
	}

	@Override
	public CustomerDataModel readData() {
		try {
			return dataStream.take();
		} catch (InterruptedException ex) {
			LOGGER.error("Some unexpected error occured while fetching data from the data stream.", ex);
		}
		return null;
	}

}
