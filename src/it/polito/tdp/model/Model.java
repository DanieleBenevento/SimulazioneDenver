package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.db.EventsDao;

public class Model {
	
	EventsDao dao;
	Graph<Distretto,DefaultWeightedEdge> grafo;
	Map<Integer,Distretto> districtMap;

	
	public Model() {
		dao=new EventsDao();
		
	}
	
	public List<Integer> getAllYears(){
		return dao.listAllYears();
	}
	
	public List<Integer> getAllMonths(int anno){
		return dao.listAllMonth(anno);
	}
	
	public List<Integer> getAllDays(int anno, int mese){
		return dao.listAllDays(anno,mese);
	}
	
	public Map<Integer,Distretto> getDistrictMap(){
		return this.districtMap;
	}
	
	public void creaGrafo(int anno) {
		
		grafo=new SimpleWeightedGraph<Distretto,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		this.districtMap=new HashMap<Integer,Distretto>();
		Graphs.addAllVertices(grafo, dao.listAllDistricts(anno,this.districtMap));
	
		
		for(Distretto d:grafo.vertexSet()) {
			for(Distretto d1:grafo.vertexSet()) {
				if(d.getId()!=d1.getId() && !grafo.containsEdge(grafo.getEdge(d1, d)) && !grafo.containsEdge(grafo.getEdge(d, d1))) {
				grafo.addEdge(d, d1); 
				LatLng l1=new LatLng(d.getCentro().getLatitude(),d.getCentro().getLongitude());
				LatLng l2=new LatLng(d1.getCentro().getLatitude(),d1.getCentro().getLongitude());
			    double peso=LatLngTool.distance(l1,l2,LengthUnit.KILOMETER);
				grafo.setEdgeWeight(d, d1, peso); 
				}	
			}
		}
	}
	
	public List<EdgeAndCount> getEdgeAndCount(Graph<Distretto,DefaultWeightedEdge> grafo, Distretto d){
		
			List<EdgeAndCount> list=new ArrayList<>();
			for(DefaultWeightedEdge dwe: grafo.edgesOf(d)) {
				list.add(new EdgeAndCount(dwe,grafo.getEdgeWeight(dwe)));
			}
			Collections.sort(list);
		
		return list;
	}
	

	public Graph<Distretto, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}
	
	public List<Integer> ordinaDistretti(int j,Graph<Integer,DefaultWeightedEdge> grafo){
		List<Integer>list=new ArrayList<Integer>();
		List<Integer>l=new ArrayList<Integer>();
		list.clear();
		l.clear();
		l.addAll(Graphs.neighborListOf(grafo, j));
		while(!l.isEmpty()) {
			int posizione=-1;
			double minimo=Double.MAX_VALUE;
		for(Integer i:l) {
			if(grafo.edgeSet().contains(grafo.getEdge(j, i))) {
			if(grafo.getEdgeWeight(grafo.getEdge(j, i))<minimo) {
				posizione=i;
				minimo=grafo.getEdgeWeight(grafo.getEdge(j, i));
			}
			}else {
				if(grafo.getEdgeWeight(grafo.getEdge(i, j))<minimo) {
					posizione=i;
					minimo=grafo.getEdgeWeight(grafo.getEdge(i, j));
				}	
			}
		}
		list.add(posizione);
		l.remove(l.indexOf(posizione));
		}
		return list;
	}
	
	public int getDistrettoPolizia(int anno) {
		return dao.CalcolaDistrettoPolizia(anno);
	}
	
	
	
}
