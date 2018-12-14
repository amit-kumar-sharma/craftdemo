/**
 * 
 */
package org.craft.demo.dataimport.publishers;

import java.util.List;

/**
 * @author asharma
 *
 */
public interface DataStreamPublisher<T> {

	public void publishData(T dataModel);
	
	public void publishData(List<T> dataModels);
}
