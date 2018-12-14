/**
 * 
 */
package org.craft.demo.dataimport.service;

import java.nio.file.Path;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craft.demo.dataimport.adapters.DataAdapter;
import org.craft.demo.dataimport.adapters.OrionDataAdapter;
import org.craft.demo.dataimport.dbclients.DBClient;
import org.craft.demo.dataimport.dbclients.OrionDBClient;
import org.craft.demo.dataimport.model.CustomerDataModel;
import org.craft.demo.dataimport.model.OrionDataModel;
import org.craft.demo.dataimport.publishers.DataStreamPublisher;
import org.craft.demo.dataimport.publishers.QueueDataStreamPublisher;
import org.springframework.beans.factory.BeanFactory;

/**
 * @author asharma
 *
 */
public class OrionDataImportTask implements DataImportTask {
	
	private static final Logger LOGGER = LogManager.getLogger(OrionDataImportTask.class);
	private final Path databasePath;
	private final DBClient<OrionDataModel> dbClient;
	private final DataAdapter<OrionDataModel, CustomerDataModel> dataAdapter;
	private final DataStreamPublisher<CustomerDataModel> dataStreamPublisher; 
	
	public OrionDataImportTask(final BeanFactory factory, final Path databasePath) {
		this.databasePath = databasePath;
		this.dataAdapter = factory.getBean(OrionDataAdapter.class);
		this.dbClient = factory.getBean(OrionDBClient.class);
		this.dataStreamPublisher = factory.getBean(QueueDataStreamPublisher.class);
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		LOGGER.info(()->"Starting import from the Orion DB.");
		List<OrionDataModel> orionData = dbClient.readData(databasePath);
		if(orionData != null && !orionData.isEmpty()) {
			List<CustomerDataModel> customerData = dataAdapter.adaptData(orionData);
			if(customerData != null && !customerData.isEmpty()) {
				LOGGER.info(()-> "Following records fetched from the Orion DB: \n" + customerData);
				LOGGER.info(()-> "Pushing these records into the Data Stream.");
				dataStreamPublisher.publishData(customerData);
				LOGGER.info(()-> "Done Pushing these records into the Data Stream.");
			}
		}
		LOGGER.info(()->"Finished import from the Orion DB.");
	}

}
