/**
 * 
 */
package org.craft.demo.dataimport.service;

import java.nio.file.Path;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craft.demo.dataimport.adapters.DataAdapter;
import org.craft.demo.dataimport.adapters.MagellanDataAdapter;
import org.craft.demo.dataimport.dbclients.DBClient;
import org.craft.demo.dataimport.dbclients.MagellanDBClient;
import org.craft.demo.dataimport.model.CustomerDataModel;
import org.craft.demo.dataimport.model.MagellanDataModel;
import org.craft.demo.dataimport.publishers.DataStreamPublisher;
import org.craft.demo.dataimport.publishers.QueueDataStreamPublisher;
import org.springframework.beans.factory.BeanFactory;

/**
 * @author asharma
 *
 */
public class MagellanDataImportTask implements DataImportTask {
	private static final Logger LOGGER = LogManager.getLogger(MagellanDataImportTask.class);
	private final Path databasePath;
	private final DBClient<MagellanDataModel> dbClient;
	private final DataAdapter<MagellanDataModel, CustomerDataModel> dataAdapter;
	private final DataStreamPublisher<CustomerDataModel> dataStreamPublisher; 

	public MagellanDataImportTask(final BeanFactory factory, final Path databasePath) {
		this.databasePath = databasePath;
		this.dataAdapter = factory.getBean(MagellanDataAdapter.class);
		this.dbClient = factory.getBean(MagellanDBClient.class);
		this.dataStreamPublisher = factory.getBean(QueueDataStreamPublisher.class);
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		LOGGER.info(()->"Starting import from the Magellan DB.");
		List<MagellanDataModel> magellanData = dbClient.readData(databasePath);
		if(magellanData != null && !magellanData.isEmpty()) {
			List<CustomerDataModel> customerData = dataAdapter.adaptData(magellanData);
			if(customerData != null && !customerData.isEmpty()) {
				LOGGER.info(()-> "Following records fetched from the Magellan DB: \n" + customerData);
				LOGGER.info(()-> "Pushing these records into the Data Stream.");
				dataStreamPublisher.publishData(customerData);
				LOGGER.info(()-> "Done Pushing these records into the Data Stream.");
			}
		}
		LOGGER.info(()->"Finished import from the Magellan DB.");
	}

}
