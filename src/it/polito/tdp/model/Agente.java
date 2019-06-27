package it.polito.tdp.model;

public class Agente {
	
	private int id;
	private int posizioneDistretto;
	private boolean libero;
	
	public Agente(int id, int posizioneDistretto) {
		
		this.id = id;
		this.posizioneDistretto = posizioneDistretto;
		this.libero=true;
	}

	
	
	public boolean isLibero() {
		return libero;
	}



	public void setLibero(boolean libero) {
		this.libero = libero;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPosizioneDistretto() {
		return posizioneDistretto;
	}

	public void setPosizioneDistretto(int posizioneDistretto) {
		this.posizioneDistretto = posizioneDistretto;
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
		Agente other = (Agente) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	

}
