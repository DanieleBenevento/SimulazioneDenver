package it.polito.tdp.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Evento implements Comparable<Evento>{
	
	private double incident_id;
	private int district_id;
	private String offense_type_id;
	private String offense_category_id;
	private String incident_address;
	private LocalDate reported_date;
	private LocalTime reported_time;
	private TipoEvento type;
	private Duration durata;
	private Agente agente;
	
	public enum TipoEvento{
		INIZIO,
		FINE
	}
	
	public Evento(double incident_id, int district_id, String offense_type_id, String offense_category_id,
			String incident_address, LocalDate reported_date,LocalTime reported_time,TipoEvento tipo,Agente a) {
		if(offense_category_id.equals("all_other_crimes")) {
		if(Math.random()>0.5) {
			this.durata=Duration.of(2, ChronoUnit.HOURS);
		}
		else {
			this.durata=Duration.of(1, ChronoUnit.HOURS);
		}
		}
		else {
			this.durata=Duration.of(2, ChronoUnit.HOURS);
		}
		
		this.incident_id = incident_id;
		this.district_id = district_id;
		this.offense_type_id = offense_type_id;
		this.offense_category_id = offense_category_id;
		this.incident_address = incident_address;
		this.reported_date = reported_date;
		this.reported_time = reported_time;
		this.type=tipo;
		this.agente=a;
	}
	
	

	public Agente getAgente() {
		return agente;
	}



	public void setAgente(Agente agente) {
		this.agente = agente;
	}



	public TipoEvento getType() {
		return type;
	}



	public void setType(TipoEvento type) {
		this.type = type;
	}



	public Duration getDurata() {
		return durata;
	}



	public void setDurata(Duration durata) {
		this.durata = durata;
	}



	public LocalTime getReported_time() {
		return reported_time;
	}



	public void setReported_time(LocalTime reported_time) {
		this.reported_time = reported_time;
	}



	public double getIncident_id() {
		return incident_id;
	}

	public void setIncident_id(int incident_id) {
		this.incident_id = incident_id;
	}

	public int getDistrict_id() {
		return district_id;
	}

	public void setDistrict_id(int district_id) {
		this.district_id = district_id;
	}

	public String getOffense_type_id() {
		return offense_type_id;
	}

	public void setOffense_type_id(String offense_type_id) {
		this.offense_type_id = offense_type_id;
	}

	public String getOffense_category_id() {
		return offense_category_id;
	}

	public void setOffense_category_id(String offense_category_id) {
		this.offense_category_id = offense_category_id;
	}

	public String getIncident_address() {
		return incident_address;
	}

	public void setIncident_address(String incident_address) {
		this.incident_address = incident_address;
	}

	public LocalDate getReported_date() {
		return reported_date;
	}

	public void setReported_date(LocalDate reported_date) {
		this.reported_date = reported_date;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(incident_id);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Evento other = (Evento) obj;
		if (Double.doubleToLongBits(incident_id) != Double.doubleToLongBits(other.incident_id))
			return false;
		return true;
	}



	@Override
	public int compareTo(Evento altro) {
		
		return reported_time.compareTo(altro.getReported_time());
	}
	
	

}
