/**
 * 
 */
package org.craft.demo.datastream;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craft.demo.dataimport.model.CustomerDataModel;
import org.springframework.stereotype.Component;

/**
 * @author asharma
 *
 */
@Component
public class DataQueue {
	private static final Logger LOGGER = LogManager.getLogger(DataQueue.class);
	private final BlockingQueue<CustomerDataModel> dataStream = new LinkedBlockingQueue<>(); 
	
	public void offer(CustomerDataModel dataModel) throws InterruptedException {
		dataStream.put(dataModel);
	}
	
	public void offer(List<CustomerDataModel> dataModels) throws InterruptedException {
		if(dataModels==null) {
			return;
		}
		dataModels.forEach(dataModel -> {
			try {
				dataStream.put(dataModel);
			} catch (InterruptedException ex) {
				LOGGER.error("Unexpected error happened while pushing data to the Data Stream: " , ex);
			}
		});
	}
	
	public CustomerDataModel take() throws InterruptedException {
		return dataStream.take();
	}
}
