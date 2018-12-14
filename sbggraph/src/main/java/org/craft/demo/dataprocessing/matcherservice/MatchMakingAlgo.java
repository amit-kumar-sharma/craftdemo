/**
 * 
 */
package org.craft.demo.dataprocessing.matcherservice;

/**
 * @author asharma
 *
 */
public interface MatchMakingAlgo<T> {
	public boolean areSimilar(T dataOne, T dataTwo);
}
