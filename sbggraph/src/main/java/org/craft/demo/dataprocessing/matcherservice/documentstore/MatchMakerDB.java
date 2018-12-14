/**
 * 
 */
package org.craft.demo.dataprocessing.matcherservice.documentstore;

import java.util.Set;

/**
 * @author asharma
 *
 * This is a simple represnataion of some DB that the MatchMaker Service may use for optimal access of existing data for comparision 
 * purposes. 
 */
public interface MatchMakerDB<T> {
	
	public void optimizeAndSave(T dataToOptimizeAndStore);
	
	public Set<T> getRelevantData(T dataToMatch);
}
