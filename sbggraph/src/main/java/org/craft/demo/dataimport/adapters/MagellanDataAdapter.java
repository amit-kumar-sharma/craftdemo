/**
 * 
 */
package org.craft.demo.dataimport.adapters;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craft.demo.dataimport.model.CustomerDataModel;
import org.craft.demo.dataimport.model.MagellanDataModel;
import org.springframework.stereotype.Component;

/**
 * @author asharma
 *
 */
@Component
public class MagellanDataAdapter implements DataAdapter<MagellanDataModel, CustomerDataModel>{

	private static final Logger LOGGER = LogManager.getLogger(MagellanDataAdapter.class);
	
	/*
	 * (non-Javadoc)
	 * @see org.craft.demo.dataimport.adapters.DataAdapter#adaptData(java.lang.Object)
	 */
	public CustomerDataModel adaptData(MagellanDataModel sourceData) {
		LOGGER.trace("Adapting Data from " + MagellanDataModel.class.getSimpleName() + " to " + CustomerDataModel.class.getSimpleName());
		CustomerDataModel dataModel = new CustomerDataModel();
		dataModel.setEmail(sourceData.getEmail());
		dataModel.setPhone(sourceData.getPrimaryPhone());
		dataModel.setStreet(sourceData.getAddress1());
		dataModel.setZip(sourceData.getZip());
		dataModel.setName(sourceData.getFullName());
		LOGGER.trace("Done adapting Data from " + MagellanDataModel.class.getSimpleName() + " to " + CustomerDataModel.class.getSimpleName());
		return dataModel;
	}

	/*
	 * (non-Javadoc)
	 * @see org.craft.demo.dataimport.adapters.DataAdapter#adaptData(java.util.List)
	 */
	@Override
	public List<CustomerDataModel> adaptData(List<MagellanDataModel> sourceData) {
		List<CustomerDataModel> customerData = new ArrayList<>(sourceData== null? 0: sourceData.size());
		if(sourceData!= null) {
			sourceData.parallelStream().forEach(magellanDataModel -> customerData.add(this.adaptData(magellanDataModel)));
		}
		return customerData;
	}

}
