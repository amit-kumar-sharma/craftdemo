/**
 * 
 */
package org.craft.demo.dataimport.service;

import java.nio.file.Path;

import org.craft.demo.common.DatabaseType;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author asharma
 *
 * Factory class to get the data import task objects.
 */
@Component
public class DataImportTaskFactory {
	
	@Autowired
	private BeanFactory factory;

	/**
	 * Provides a database specific data import task.
	 * 
	 * @param databaseType
	 * @param databasePath
	 * @return
	 */
	public DataImportTask getDataImportTask(DatabaseType databaseType, Path databasePath) {
		switch(databaseType) {
		case ORION:
			return new OrionDataImportTask(factory, databasePath);
		case MAGELLAN:
			return new MagellanDataImportTask(factory, databasePath);
		case GRAPHDB:
		default:
			throw new IllegalArgumentException("Support for this datatype has not been added yet.");
		}
	}

}
