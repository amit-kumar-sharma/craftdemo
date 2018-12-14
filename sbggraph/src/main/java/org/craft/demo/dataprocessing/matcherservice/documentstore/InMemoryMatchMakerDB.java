/**
 * 
 */
package org.craft.demo.dataprocessing.matcherservice.documentstore;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

/**
 * @author asharma
 *
 */
@Component
public class InMemoryMatchMakerDB<T> implements MatchMakerDB<T> {
	private Set<T> matchMakerData = new HashSet<>();

	@Override
	public void optimizeAndSave(T dataToOptimizeAndStore) {
		// For now it is doing nothing but in real scenario we may do some optimizations here like hashing and indexing based on some
		//attributes that are a must to match and then filter the entire search universe based on that.
		matchMakerData.add(dataToOptimizeAndStore);
	}

	@Override
	public Set<T> getRelevantData(T dataToMatch) {
		// For now it is doing nothing but in real scenario we may do some optimizations here for example fetching people that are
		//in the same zip code etc.
		return matchMakerData;
	}
	
}
