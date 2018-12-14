/**
 * 
 */
package org.craft.demo.dataprocessing.matcherservice;

import java.util.Set;

/**
 * @author asharma
 *
 */
public interface MatchMaker<T> {
	public Set<T> findSimilarData(T incomingData);
}
