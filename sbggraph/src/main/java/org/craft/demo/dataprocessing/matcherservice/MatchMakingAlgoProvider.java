/**
 * 
 */
package org.craft.demo.dataprocessing.matcherservice;

import org.craft.demo.dataimport.model.CustomerDataModel;
import org.craft.demo.dataprocessing.matcherservice.algoimpls.CosineDistanceSimilarity;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author asharma
 *
 */
@Component
public class MatchMakingAlgoProvider {
	@Autowired
	private BeanFactory beanFactory;
	
	public MatchMakingAlgo<CustomerDataModel> provideMatchMakingAlgo(final MatchMakingAlgoType matchMakingAlgoType){
		switch(matchMakingAlgoType) {
		case COSINE_SIMILARITY:
		default:
			return beanFactory.getBean(CosineDistanceSimilarity.class);
		}
	}
}
