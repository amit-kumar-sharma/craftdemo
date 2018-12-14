/**
 * 
 */
package org.craft.demo.dataimport.dbclients;

import java.nio.file.Path;
import java.util.List;

import org.craft.demo.common.DatabaseType;
import org.craft.demo.dataimport.model.OrionDataModel;
import org.springframework.stereotype.Component;

/**
 * @author asharma
 *
 * Reads data from Orion DB.
 */
@Component
public class OrionDBClient extends BaseDBClient<OrionDataModel>{
	
	private static final DatabaseType DATABASE_TYPE = DatabaseType.ORION;

	@Override
	public List<OrionDataModel> readData(Path databasePath) {
		return super.readCSVData(OrionDataModel.class, databasePath, DATABASE_TYPE);
	}

	@Override
	public void writeData(OrionDataModel data) {
		throw new UnsupportedOperationException("This method is not yet supported by this client.");		
	}

}