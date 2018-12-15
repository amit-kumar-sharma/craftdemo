/**
 * 
 */
package org.craft.demo.analyticalstore;

import java.util.Objects;

/**
 * @author asharma
 *
 */
public class Edge {
	private Vertex vertex;
	private Vertex sameAs;
	/**
	 * @param vertex
	 * @param sameAs
	 */
	public Edge(Vertex vertex, Vertex sameAs) {
		super();
		this.vertex = vertex;
		this.sameAs = sameAs;
	}
	/**
	 * @return the vertex
	 */
	public Vertex getVertex() {
		return vertex;
	}
	/**
	 * @param vertex the vertex to set
	 */
	public void setVertex(Vertex vertex) {
		this.vertex = vertex;
	}
	/**
	 * @return the sameAs
	 */
	public Vertex getSameAs() {
		return sameAs;
	}
	/**
	 * @param sameAs the sameAs to set
	 */
	public void setSameAs(Vertex sameAs) {
		this.sameAs = sameAs;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(sameAs, vertex);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Edge)) {
			return false;
		}
		Edge other = (Edge) obj;
		return Objects.equals(sameAs, other.sameAs) && Objects.equals(vertex, other.vertex);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Edge [vertex=%s, sameAs=%s]", vertex, sameAs);
	}
	
}
