/**
 * 
 */
package org.craft.demo.dataimport.service;

import java.nio.file.Path;
import java.util.Map;

import org.craft.demo.common.DatabaseType;
import org.craft.demo.dataimport.exceptions.DataImportException;

/**
 * @author asharma
 *
 */
public interface DataImportService {
	
	public void importData(Map<DatabaseType, Path> databasesPaths) throws DataImportException;
}
