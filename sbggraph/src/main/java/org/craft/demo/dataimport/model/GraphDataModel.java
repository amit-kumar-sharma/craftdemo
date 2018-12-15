/**
 * 
 */
package org.craft.demo.dataimport.model;

import java.util.Map;
import java.util.Set;

import org.craft.demo.analyticalstore.Vertex;

/**
 * @author asharma
 *
 */
public class GraphDataModel {
	
	private Map<Vertex, Set<Vertex>> graphData = null;

	/**
	 * @param graphData
	 */
	public GraphDataModel(Map<Vertex, Set<Vertex>> graphData) {
		super();
		this.graphData = graphData;
	}

	/**
	 * @return the graphData
	 */
	public Map<Vertex, Set<Vertex>> getGraphData() {
		return graphData;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("GraphDataModel [graphData=%s]", getGraphData());
	}

}
