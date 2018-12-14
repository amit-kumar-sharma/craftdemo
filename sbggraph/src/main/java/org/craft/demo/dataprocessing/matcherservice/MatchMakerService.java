/**
 * 
 */
package org.craft.demo.dataprocessing.matcherservice;

import java.util.HashSet;
import java.util.Set;

import org.craft.demo.dataimport.model.CustomerDataModel;
import org.craft.demo.dataprocessing.matcherservice.documentstore.MatchMakerDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author asharma
 *
 */
@Component
public class MatchMakerService implements MatchMaker<CustomerDataModel>{
	@Autowired
	private MatchMakingAlgoProvider matchMakingAlgoProvider;
	@Autowired
	private MatchMakerDB<CustomerDataModel> matchMakerDB;

	@Override
	public Set<CustomerDataModel> findSimilarData(CustomerDataModel dataToMatch) {
		Set<CustomerDataModel> relevantDatas = matchMakerDB.getRelevantData(dataToMatch);
		MatchMakingAlgo<CustomerDataModel> matchMakingAlgo = matchMakingAlgoProvider.provideMatchMakingAlgo(MatchMakingAlgoType.COSINE_SIMILARITY);
		Set<CustomerDataModel> similarData = new HashSet<>();
		if(relevantDatas!=null){
			//Remove yourself, it might be a re-ingest of the same data.
			relevantDatas.remove(dataToMatch);
			relevantDatas.parallelStream().forEach(relevantData -> {
				if(matchMakingAlgo.areSimilar(dataToMatch, relevantData)) {
					similarData.add(relevantData);
				}
			});
		}
		matchMakerDB.optimizeAndSave(dataToMatch);
		return similarData;
	}

}
