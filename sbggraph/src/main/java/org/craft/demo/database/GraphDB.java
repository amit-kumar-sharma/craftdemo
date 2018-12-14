/**
 * 
 */
package org.craft.demo.database;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.craft.demo.dataimport.model.GraphDataModel;
import org.springframework.stereotype.Component;

/**
 * @author asharma
 *
 */
@Component
public class GraphDB {
	private Set<Vertex> vertices = new HashSet<>();
	private Set<Edge> sameAsEdges = new HashSet<>();
	private Map<Vertex, Set<Vertex>> probableDuplicates = new HashMap<>();
	
	public void addData(GraphDataModel graphDataModel) {
		if(graphDataModel==null || graphDataModel.getGraphData() == null) {
			throw new IllegalArgumentException("\"graphData\" can not be null. Please provide valid data for the Graph DB.");
		}
		graphDataModel.getGraphData().entrySet().forEach(entry -> {
			final Vertex newVertex = entry.getKey();
			vertices.add(newVertex);
			if(!Objects.isNull(entry.getValue())) {
				probableDuplicates.computeIfAbsent(newVertex, key -> new HashSet<Vertex>());
				entry.getValue().forEach( sameAsVertex -> {
					sameAsEdges.add(new Edge(newVertex, sameAsVertex));
					sameAsEdges.add(new Edge(sameAsVertex, newVertex));
					probableDuplicates.get(newVertex).add(sameAsVertex);
					probableDuplicates.computeIfAbsent(sameAsVertex, key -> new HashSet<Vertex>()).add(newVertex);
				});
			}
		});
	}

	/**
	 * @return the vertices
	 */
	public Set<Vertex> getVertices() {
		return Collections.unmodifiableSet(vertices);
	}

	/**
	 * @return the sameAsEdges
	 */
	public Set<Edge> getSameAsEdges() {
		return Collections.unmodifiableSet(sameAsEdges);
	}
	
	/**
	 * Get all the graph data with there probable duplicates.
	 * @return
	 */
	public Map<Vertex, Set<Vertex>> getProbableDuplicates(){
		return Collections.unmodifiableMap(probableDuplicates);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("GraphDB [vertices=%s, sameAsEdges=%s]", vertices, sameAsEdges);
	}
	
}
