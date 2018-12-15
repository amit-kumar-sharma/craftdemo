/**
 * 
 */
package org.craft.demo.dataprocessing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craft.demo.analyticalstore.Vertex;
import org.craft.demo.dataimport.dbclients.DBClient;
import org.craft.demo.dataimport.model.CustomerDataModel;
import org.craft.demo.dataimport.model.GraphDataModel;
import org.craft.demo.dataprocessing.client.backupsystem.BackupSystemClient;
import org.craft.demo.dataprocessing.client.datastream.DataStreamReader;
import org.craft.demo.dataprocessing.matcherservice.MatchMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author asharma
 *
 */
@Service("OnlineDataProcessingServie")
public class OnlineDataProcessingServie {
	
	private static final Logger LOGGER = LogManager.getLogger(OnlineDataProcessingServie.class);
	@Autowired
	private DataStreamReader dataStreamReader;
	@Autowired
	private BackupSystemClient<CustomerDataModel> backupSystemClient;
	@Autowired
	private MatchMaker<CustomerDataModel> matchMakingService;
	@Autowired
	private DBClient<GraphDataModel> graphDBClient;
	
	@PostConstruct
	private void initializeProcessing() {
		Thread onlineDataprocessingThread = new Thread(()->{
			while(true) {
				//Read from the Stream
				CustomerDataModel customerData = dataStreamReader.readData();
				LOGGER.info(()-> "OnlineDataProcessingServie is processing following data." + customerData);
				LOGGER.info(()-> "OnlineDataProcessingServie is backing up following data for offline processing." + customerData);
				//Backup for on demand offline processing.
				backupSystemClient.backupData(customerData);
				LOGGER.info(()-> "OnlineDataProcessingServie is done with backing up following data for offline processing." + customerData);
				//Find the matching data from the existing graph Data.
				LOGGER.info(()-> "OnlineDataProcessingServie is finding the matching data for: " + customerData);
				final Set<CustomerDataModel> similarDatas = matchMakingService.findSimilarData(customerData);
				LOGGER.info(()-> "OnlineDataProcessingServie found following matching data:\n" +  similarDatas + "\nfor: " + customerData);
				
				final Map<Vertex, Set<Vertex>> graphData = new HashMap<>();
				final Set<Vertex> similarDataVerticies = new HashSet<>();
				graphData.put(new Vertex(customerData), similarDataVerticies);
				if(similarDatas!=null) {
					similarDatas.forEach(similarData -> {
						similarDataVerticies.add(new Vertex(similarData));	
					});
				}
				GraphDataModel graphDataModel = new GraphDataModel(graphData);
				LOGGER.info(()-> "OnlineDataProcessingServie prepared following data to add to the GraphDB: " + graphDataModel);
				//Store this new information in the Graph DB.
				graphDBClient.writeData(graphDataModel);
				LOGGER.info(()-> "OnlineDataProcessingServie added following data to the GraphDB: " + graphDataModel);
				LOGGER.info(()-> "OnlineDataProcessingServie is done processing following data." + customerData);
			}
		});
		//This will allow the application to exit cleanly
		onlineDataprocessingThread.setDaemon(true);
		onlineDataprocessingThread.start();
	}
	
}
