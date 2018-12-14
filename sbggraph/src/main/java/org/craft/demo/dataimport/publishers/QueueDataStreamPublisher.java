/**
 * 
 */
package org.craft.demo.dataimport.publishers;

import java.util.List;

import org.craft.demo.dataimport.model.CustomerDataModel;
import org.craft.demo.datastream.QueueDataStreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author asharma
 *
 */
@Component
public class QueueDataStreamPublisher implements DataStreamPublisher<CustomerDataModel> {
	
	@Autowired
	private QueueDataStreamClient dataStreamClient; 

	/*
	 * (non-Javadoc)
	 * @see org.intuit.craft.demo.dataimport.publishers.DataStreamClient#publishData(java.lang.Object)
	 */
	@Override
	public void publishData(CustomerDataModel dataModel) {
		dataStreamClient.publishData(dataModel);
	}

	/*
	 * (non-Javadoc)
	 * @see org.intuit.craft.demo.dataimport.publishers.DataStreamClient#publishData(java.util.List)
	 */
	@Override
	public void publishData(List<CustomerDataModel> dataModels) {
		dataStreamClient.publishData(dataModels);		
	}

}
