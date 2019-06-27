package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.db.EventsDao;

public class Distretto {

	private int id;
	private CentroCrimine centro;
	
	private EventsDao dao=new EventsDao();
	
	public Distretto(int id,int anno) {
		
		this.id = id;
		this.centro = dao.getAllCentroCrimine(anno).get(id-1);
	
}
	




	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CentroCrimine getCentro() {
		return centro;
	}

	public void setCentro(CentroCrimine centro) {
		this.centro = centro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Distretto other = (Distretto) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ""+id ;
	}
	
	
	
	
}
