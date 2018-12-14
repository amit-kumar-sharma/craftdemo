/**
 * 
 */
package org.craft.demo.dataimport.adapters;

import java.util.List;

/**
 * @author asharma
 *
 */
public interface DataAdapter<SourceType, DestinationType> {
	/**
	 * The adapter converts one source of data format into another format, compatible for processing.
	 * 
	 * @param sourceData
	 * @return
	 */
	public DestinationType adaptData(SourceType sourceData);
	
	/**
	 * The adapter converts one source of data format into another format, compatible for processing.
	 * 
	 * @param sourceData
	 * @return
	 */
	public List<DestinationType> adaptData(List<SourceType> sourceData);
}
