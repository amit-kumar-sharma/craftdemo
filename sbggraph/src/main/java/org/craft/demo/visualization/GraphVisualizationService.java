/**
 * 
 */
package org.craft.demo.visualization;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.craft.demo.analyticalstore.Vertex;
import org.craft.demo.dataimport.dbclients.DBClient;
import org.craft.demo.dataimport.model.GraphDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author asharma
 *
 */
@Service
public class GraphVisualizationService implements VisualizationService<Map<Vertex, Set<Vertex>>>{

	@Autowired
	private DBClient<GraphDataModel> graphDBClient;
	
	public Map<Vertex, Set<Vertex>> getVisualizationData() {
		List<GraphDataModel> readData = graphDBClient.readData(/*not applicable for this as this is a in memory DB.*/null);
		if(readData!=null && !readData.isEmpty()) {
			return readData.get(0).getGraphData();
		}
		return null;
	}
}
