/**
 * 
 */
package org.craft.demo.dataimport.dbclients;

import java.nio.file.Path;
import java.util.List;

/**
 * @author asharma
 * 
 * Every DB Client should implement this interface.
 */
public interface DBClient<T> {
	
	public List<T> readData(Path databasePath);
	
	public void writeData(T data);
}
