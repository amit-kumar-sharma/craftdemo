/**
 * 
 */
package org.craft.demo.dataimport.dbclients;

import java.nio.file.Path;
import java.util.List;

import org.craft.demo.common.DatabaseType;
import org.craft.demo.dataimport.model.MagellanDataModel;
import org.springframework.stereotype.Component;

/**
 * @author asharma
 *	
 * Reads data from Magellan DB.
 */
@Component
public class MagellanDBClient extends BaseDBClient<MagellanDataModel>{
	
	private static final DatabaseType DATABASE_TYPE = DatabaseType.MAGELLAN;

	@Override
	public List<MagellanDataModel> readData(Path databasePath) {
		return super.readCSVData(MagellanDataModel.class, databasePath, DATABASE_TYPE);
	}

	@Override
	public void writeData(MagellanDataModel data) {
		throw new UnsupportedOperationException("This method is not yet supported by this client.");
	}

}
