/**
 * 
 */
package org.craft.demo.dataimport.dbclients;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craft.demo.common.DatabaseType;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

/**
 * @author asharma
 *
 */
@Component
public abstract class BaseDBClient<DataModelType> implements DBClient<DataModelType> {
	
	private static final Logger LOGGER = LogManager.getLogger(BaseDBClient.class);

	protected List<DataModelType> readCSVData(final Class<DataModelType> dataModelType, 
			final Path databasePath, final DatabaseType databaseType) {
		try {
	        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
	        CsvMapper mapper = new CsvMapper();
	        MappingIterator<DataModelType> readValues = mapper.readerFor(dataModelType)
	        		.with(bootstrapSchema)
	        		.readValues(databasePath.toFile());
	        return readValues.readAll();
	    } catch (Exception e) {
	    	LOGGER.error("Error occurred while loading data from file: " + databasePath + 
	    			". Please verify that the provided file has valid data for \"" + databaseType + "\" database type.", e);
	        return Collections.emptyList();
	    }
	}	

}
