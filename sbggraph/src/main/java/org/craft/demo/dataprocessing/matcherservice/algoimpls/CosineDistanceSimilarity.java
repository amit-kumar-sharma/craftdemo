/**
 * 
 */
package org.craft.demo.dataprocessing.matcherservice.algoimpls;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.text.similarity.CosineSimilarity;
import org.craft.demo.dataimport.model.CustomerDataModel;
import org.craft.demo.dataprocessing.matcherservice.MatchMakingAlgo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author asharma
 *
 */
@Component
public class CosineDistanceSimilarity implements MatchMakingAlgo<CustomerDataModel>{

	@Value( "${name.match.threshold:0.6}" )
	private String nameThreshold;
	@Value( "${street.match.threshold:0.6}" )
	private String streetThreshold;
	@Value( "${zip.match.threshold:0.7}" )
	private String zipThreshold;
	@Value( "${phone.match.threshold:0.7}" )
	private String phoneThreshold;
	@Value( "${email.match.threshold:0.7}" )
	private String emailThreshold;
	@Value( "${min.attribute.meet.threshold.count:3}" )
	private String minAttributeMeetingThreshold;
	
	private Double getEmailThreshold() {
		try {
			return Double.parseDouble(emailThreshold);
		} catch(NumberFormatException numberFormatException) {
			return 1.0;
		}
	}
	
	private Double getPhoneThreshold() {
		try {
			return Double.parseDouble(phoneThreshold);
		} catch(NumberFormatException numberFormatException) {
			return 0.7;
		}
	}
	
	private Double getZipThreshold() {
		try {
			return Double.parseDouble(zipThreshold);
		} catch(NumberFormatException numberFormatException) {
			return 0.7;
		}
	}
	
	private Double getSteetThreshold() {
		try {
			return Double.parseDouble(streetThreshold);
		} catch(NumberFormatException numberFormatException) {
			return 0.6;
		}
	}
	
	private Double getNameThreshold() {
		try {
			return Double.parseDouble(nameThreshold);
		} catch(NumberFormatException numberFormatException) {
			return 0.6;
		}
	}
	
	private Integer getMinAttributeMeetingThresholdThreshold() {
		try {
			return Integer.parseInt(minAttributeMeetingThreshold);
		} catch(NumberFormatException numberFormatException) {
			return 3;
		}
	}
	
	@Override
	public boolean areSimilar(final CustomerDataModel dataOne, final CustomerDataModel dataTwo) {
		final CosineSimilarity cosineSimilarity = new CosineSimilarity();
		if (dataOne == dataTwo)
			return true;
		if (dataOne!= null && dataTwo == null)
			return false;
		if (dataOne== null && dataTwo != null)
			return false;
		int numberOfAttributesMeetingThreshold = 0;
		if (areSimilar(dataOne.getEmail(), dataTwo.getEmail(), getEmailThreshold(), cosineSimilarity)) {
			numberOfAttributesMeetingThreshold++;
		}
		if (areSimilar(dataOne.getPhone(), dataTwo.getPhone(), getPhoneThreshold(), cosineSimilarity)) {
			numberOfAttributesMeetingThreshold++;
		}
		if (areSimilar(dataOne.getStreet(), dataTwo.getStreet(), getSteetThreshold(), cosineSimilarity)) {
			numberOfAttributesMeetingThreshold++;
		} 
		if (areSimilar(dataOne.getZip(), dataTwo.getZip(), getZipThreshold(), cosineSimilarity)) {
			numberOfAttributesMeetingThreshold++;
		} 
		if (areSimilar(dataOne.getName(), dataTwo.getName(), getNameThreshold(), cosineSimilarity)) {
			numberOfAttributesMeetingThreshold++;
		}
		return numberOfAttributesMeetingThreshold>=getMinAttributeMeetingThresholdThreshold();
	}

	private boolean areSimilar(final String value1, final String value2, final Double cosineSimilarityThreashold, 
			final CosineSimilarity cosineSimilarity) {
		if(Objects.equals(value1, value2))
			return true;
		if (value1!= null && value2 == null)
			return false;
		if (value1== null && value2 != null)
			return false;
        Map<CharSequence, Integer> leftVector = Arrays.stream(value1.split("")).collect(Collectors.toMap(c -> c, c -> 1, Integer::sum));
        Map<CharSequence, Integer> rightVector = Arrays.stream(value2.split("")).collect(Collectors.toMap(c -> c, c -> 1, Integer::sum));
		return cosineSimilarity.cosineSimilarity(leftVector, rightVector) >= cosineSimilarityThreashold;
	}
	
}
