package it.polito.tdp.model;

import org.jgrapht.graph.DefaultWeightedEdge;

public class EdgeAndCount implements Comparable<EdgeAndCount>{
	
	private DefaultWeightedEdge edge;
	private double count;
	
	public EdgeAndCount(DefaultWeightedEdge edge, double count) {
		super();
		this.edge = edge;
		this.count = count;
	}
	public DefaultWeightedEdge getEdgee() {
		return edge;
	}
	public void setEdgee(DefaultWeightedEdge edge) {
		this.edge = edge;
	}
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}
	@Override
	public int compareTo(EdgeAndCount o) {
		// TODO Auto-generated method stub
		return (int) (count-o.getCount());
	}
	
	

}
