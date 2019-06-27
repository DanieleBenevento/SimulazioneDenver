package it.polito.tdp.model;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import org.jgrapht.Graphs;

import it.polito.tdp.db.EventsDao;
import it.polito.tdp.model.Evento.TipoEvento;

public class Simulatore {
	
	Model model=new Model();
	int N;
	int year;
	int month;
	int day;
	int MAL_GESTITI;
	int TOT_EVENTI;
	int BEN_GESTITI;
	int DistrettoPolizia;
	List<Agente>listaAgenti;
	List<Evento>listaEventi;
	EventsDao dao= new EventsDao();
	PriorityQueue<Evento> queue;
	
	public Simulatore(int n,int anno, int mese,int giorno) {
		this.N=n;
		this.year=anno;
		this.month=mese;
		this.day=giorno;
		this.listaAgenti=new ArrayList<Agente>();
		this.listaEventi=new ArrayList<Evento>();
		queue=new PriorityQueue<Evento>();
	}
	
	
	
	public int getMAL_GESTITI() {
		return MAL_GESTITI;
	}



	public void setMAL_GESTITI(int mAL_GESTITI) {
		MAL_GESTITI = mAL_GESTITI;
	}



	public int getTOT_EVENTI() {
		return TOT_EVENTI;
	}



	public void setTOT_EVENTI(int tOT_EVENTI) {
		TOT_EVENTI = tOT_EVENTI;
	}



	public int getBEN_GESTITI() {
		return BEN_GESTITI;
	}



	public void setBEN_GESTITI(int bEN_GESTITI) {
		BEN_GESTITI = bEN_GESTITI;
	}



	public void init() {
		
		this.DistrettoPolizia=dao.CalcolaDistrettoPolizia(year);
		for(int i=1;i<=N;i++) {
			Agente a= new Agente(i,DistrettoPolizia);
			listaAgenti.add(a);
		}
		
		this.MAL_GESTITI=0;
		this.BEN_GESTITI=0;
		this.TOT_EVENTI=0;
		
		listaEventi.clear();
		listaEventi.addAll(dao.ListaEventiDelGiorno(year, month, day));
		Collections.sort(listaEventi);
		
		queue.addAll(listaEventi);
	}
	
	public void run() {
		List<Distretto> list= new ArrayList<Distretto>();
		boolean flag;
		model.creaGrafo(year);
		while(!queue.isEmpty()) {
		Evento e=queue.poll();
		
		if(e.getType()==TipoEvento.INIZIO) {
		this.TOT_EVENTI++;
		flag=false;
		list.clear();
		list.add(model.getDistrictMap().get(e.getDistrict_id()));
		for(EdgeAndCount ed: model.getEdgeAndCount(model.getGrafo(), model.getDistrictMap().get(e.getDistrict_id())) ) {
			list.add(Graphs.getOppositeVertex(model.getGrafo(),ed.getEdgee(),model.getDistrictMap().get(e.getDistrict_id())));	
		}
		
		for(Distretto i:list) {
			if(flag==false) {
			for(Agente a:listaAgenti) {
			if(a.getPosizioneDistretto()==i.getId() && a.isLibero()==true) {
				a.setPosizioneDistretto(e.getDistrict_id());
				a.setLibero(false);
				e.setAgente(a);
				flag=true;
				double tempo;
				if(a.getPosizioneDistretto()==e.getDistrict_id()) {
					tempo=0;
				}else
				 tempo=(model.getGrafo().getEdgeWeight(model.getGrafo().getEdge(i, model.getDistrictMap().get(e.getDistrict_id()))))/60;
     				if(tempo< 0.25) {
					this.BEN_GESTITI++;
					}
					else {
					this.MAL_GESTITI++;	
					}
					break;
				}
			}
			}
			else
				break;
		}
		if(flag==false) {
			this.MAL_GESTITI++;
		}
		else if(flag==true) {
		queue.add(new Evento(e.getIncident_id(),e.getDistrict_id(),e.getOffense_type_id(),e.getOffense_category_id(),e.getIncident_address(),e.getReported_date(),e.getReported_time().plus(e.getDurata()),TipoEvento.FINE,e.getAgente()));
		}
		}
		else if(e.getType()==TipoEvento.FINE) {
			e.getAgente().setLibero(true);
		}
		}
	}

	
}
