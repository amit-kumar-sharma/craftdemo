/**
 * 
 */
package org.craft.demo.dataimport.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craft.demo.common.DatabaseType;
import org.craft.demo.dataimport.exceptions.DataImportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author asharma
 *
 */
@Service
public class DataImportServiceImpl implements DataImportService {
	
	private static final Logger LOGGER = LogManager.getLogger(DataImportServiceImpl.class);
	
	@Autowired
	private DataImportTaskFactory dataImportTaskProvider;   
	
	/*
	 * (non-Javadoc)
	 * @see org.craft.demo.dataimport.DataImportService#importData(java.util.Map)
	 */
	@Override
	public void importData(Map<DatabaseType, Path> databasesPaths) throws DataImportException {
		LOGGER.info(()-> "Starting the import processing. Validating the given DB Paths.");
		List<Path> dataPathsToProcess = new ArrayList<>();
		List<Path> invalidDatapaths = new ArrayList<>();
		databasesPaths.values().forEach( dataPath -> {
			if(Files.isRegularFile(dataPath) && Files.exists(dataPath) && Files.isReadable(dataPath)) {
				dataPathsToProcess.add(dataPath);
			} else {
				invalidDatapaths.add(dataPath);
			}
		});
		if(!invalidDatapaths.isEmpty()) {
			LOGGER.warn(()-> "Following data locations are not valid or can not be accessed by the application." + invalidDatapaths);
			throw new DataImportException("Following data locations are not valid or can not be accessed by the application." + invalidDatapaths);
		}
		if(dataPathsToProcess.isEmpty()) {
			LOGGER.warn(()-> "No Files received for import process. Exiting.");
			throw new DataImportException("No Files received for import process. Please provide valid locations for data import.");
		}
		databasesPaths.entrySet().parallelStream().forEach(databaseEntry -> {
			DataImportTaskExecutor.executeTask(dataImportTaskProvider.getDataImportTask(databaseEntry.getKey(), databaseEntry.getValue()));
		});
	}

}
