/**
 * 
 */
package org.craft.demo.dataimport.adapters;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craft.demo.dataimport.model.CustomerDataModel;
import org.craft.demo.dataimport.model.OrionDataModel;
import org.springframework.stereotype.Component;

/**
 * @author asharma
 *
 */
@Component
public class OrionDataAdapter implements DataAdapter<OrionDataModel, CustomerDataModel>{

	private static final Logger LOGGER = LogManager.getLogger(OrionDataAdapter.class);
	
	/*
	 * (non-Javadoc)
	 * @see org.intuit.craft.demo.dataimport.adapters.DataAdapter#adaptData(java.lang.Object)
	 */
	public CustomerDataModel adaptData(OrionDataModel sourceData) {
		LOGGER.trace("Adapting Data from " + OrionDataModel.class.getSimpleName() + " to " + CustomerDataModel.class.getSimpleName());
		CustomerDataModel dataModel = new CustomerDataModel();
		dataModel.setEmail(sourceData.getEmail());
		dataModel.setPhone(sourceData.getPhone());
		dataModel.setStreet(sourceData.getStreet());
		dataModel.setZip(sourceData.getZip());
		dataModel.setName(sourceData.getName());
		LOGGER.trace("Done adapting Data from " + OrionDataModel.class.getSimpleName() + " to " + CustomerDataModel.class.getSimpleName());
		return dataModel;
	}

	/*
	 * (non-Javadoc)
	 * @see org.intuit.craft.demo.dataimport.adapters.DataAdapter#adaptData(java.util.List)
	 */
	@Override
	public List<CustomerDataModel> adaptData(List<OrionDataModel> sourceData) {
		List<CustomerDataModel> customerData = new ArrayList<>(sourceData== null? 0: sourceData.size());
		if(sourceData!= null) {
			sourceData.parallelStream().forEach(orionDataModel -> customerData.add(this.adaptData(orionDataModel)));
		}
		return customerData;
	}
}
