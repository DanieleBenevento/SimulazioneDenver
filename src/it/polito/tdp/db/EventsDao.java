package it.polito.tdp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.model.CentroCrimine;
import it.polito.tdp.model.Distretto;
import it.polito.tdp.model.Event;
import it.polito.tdp.model.Evento;
import it.polito.tdp.model.Evento.TipoEvento;


public class EventsDao {
	
	public List<Event> listAllEvents(){
		String sql = "SELECT * FROM events" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Event> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Event(res.getLong("incident_id"),
							res.getInt("offense_code"),
							res.getInt("offense_code_extension"), 
							res.getString("offense_type_id"), 
							res.getString("offense_category_id"),
							res.getTimestamp("reported_date").toLocalDateTime(),
							res.getString("incident_address"),
							res.getDouble("geo_lon"),
							res.getDouble("geo_lat"),
							res.getInt("district_id"),
							res.getInt("precinct_id"), 
							res.getString("neighborhood_id"),
							res.getInt("is_crime"),
							res.getInt("is_traffic")));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}



public List<Integer> listAllYears(){
	String sql = "SELECT DISTINCT year(reported_date) FROM events ORDER BY year(reported_date) " ;
	try {
		Connection conn = DBConnect.getConnection() ;

		PreparedStatement st = conn.prepareStatement(sql) ;
		
		List<Integer> list = new ArrayList<Integer>() ;
		
		ResultSet res = st.executeQuery() ;
		
		while(res.next()) {
			try {
				list.add(res.getInt("year(reported_date)"));
						
			} catch (Throwable t) {
				t.printStackTrace();
				System.out.println(res.getInt("id"));
			}
		}
		
		conn.close();
		return list ;

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null ;
	}
}


public List<Integer> listAllMonth(int anno){
	String sql = "SELECT DISTINCT MONTH(a1.reported_date) FROM events AS a1 WHERE YEAR(a1.reported_date)= ? ORDER BY Month(a1.reported_date)  " ;
	try {
		Connection conn = DBConnect.getConnection() ;

		PreparedStatement st = conn.prepareStatement(sql) ;
		
		st.setInt(1, anno);
		
		List<Integer> list = new ArrayList<Integer>() ;
		
		ResultSet res = st.executeQuery() ;
		
		while(res.next()) {
			try {
				list.add(res.getInt("MONTH(a1.reported_date)"));
						
			} catch (Throwable t) {
				t.printStackTrace();
				System.out.println(res.getInt("id"));
			}
		}
		
		conn.close();
		return list ;

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null ;
	}
}

public List<Integer> listAllDays(int anno, int mese){
	String sql = "SELECT DISTINCT DAY(a1.reported_date) FROM events AS a1 WHERE YEAR(a1.reported_date)= ? AND MONTH(a1.reported_date)= ? ORDER BY Day(a1.reported_date) " ;
	try {
		Connection conn = DBConnect.getConnection() ;

		PreparedStatement st = conn.prepareStatement(sql) ;
		
		st.setInt(1, anno);
		
		st.setInt(2, mese);
		
		List<Integer> list = new ArrayList<Integer>() ;
		
		ResultSet res = st.executeQuery() ;
		
		while(res.next()) {
			try {
				list.add(res.getInt("DAY(a1.reported_date)"));
						
			} catch (Throwable t) {
				t.printStackTrace();
				System.out.println(res.getInt("id"));
			}
		}
		
		conn.close();
		return list ;

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null ;
	}
}

public List<CentroCrimine> getAllCentroCrimine(int anno){
		String sql = "SELECT district_id,AVG(geo_lon), AVG(geo_lat) FROM EVENTS WHERE YEAR(reported_date)=? GROUP BY district_id" ;

	try {
		Connection conn = DBConnect.getConnection() ;

		PreparedStatement st = conn.prepareStatement(sql) ;
		
		st.setInt(1, anno);
		
		List<CentroCrimine> list=new ArrayList<>();
				
		ResultSet res = st.executeQuery() ;
		
		while(res.next()) {
			try {
				CentroCrimine c=new CentroCrimine(res.getInt("district_id"),res.getDouble("AVG(geo_lon)"),res.getDouble("AVG(geo_lat)"),anno);
				list.add(c);
				
			} catch (Throwable t) {
				t.printStackTrace();
				System.out.println(res.getInt("id"));
			}
		}
		
		conn.close();
		return list;

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null ;
	}
}
public int CalcolaDistrettoPolizia(int anno){
	String sql = " SELECT district_id, COUNT(district_id) AS c FROM events WHERE is_crime = 1 AND YEAR(reported_date)=? GROUP BY district_id  " ;
	try {
		Connection conn = DBConnect.getConnection() ;

		PreparedStatement st = conn.prepareStatement(sql) ;
		
		st.setInt(1, anno);
		
		List<Integer> list = new ArrayList<Integer>() ;
		
		ResultSet res = st.executeQuery() ;
		
	double minimo= Double.MAX_VALUE ;
		
		int distretto=0;
		
		while(res.next()) {
			try {
				if(res.getInt("c")<minimo) {
					distretto=res.getInt("district_id");
					minimo=res.getInt("c");
				}
						
			} catch (Throwable t) {
				t.printStackTrace();
				System.out.println(res.getInt("id"));
			}
		}
		
		conn.close();
		return distretto ;

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return -1;	
	}
	
}

public List<Evento> ListaEventiDelGiorno(int anno,int mese,int giorno){
	String sql = " SELECT incident_id, district_id,offense_type_id,offense_category_id,incident_address,reported_date FROM events WHERE is_crime = 1 AND YEAR(reported_date)=? AND MONTH(reported_date)=? AND DAY(reported_date)=?  " ;
	try {
		Connection conn = DBConnect.getConnection() ;

		PreparedStatement st = conn.prepareStatement(sql) ;
		
		st.setInt(1, anno);
		
		st.setInt(2, mese);
		
		st.setInt(3, giorno);
		
		List<Evento> list = new ArrayList<Evento>() ;
		
		ResultSet res = st.executeQuery() ;
		
		while(res.next()) {
			try { 
				Evento e=new Evento(res.getDouble("incident_id"),res.getInt("district_id"),res.getString("offense_type_id"),res.getString("offense_category_id"),res.getString("incident_address"),res.getDate("reported_date").toLocalDate(),res.getTime("reported_date").toLocalTime(),TipoEvento.INIZIO,null);
						list.add(e);
			} catch (Throwable t) {
				t.printStackTrace();
				System.out.println(res.getInt("id"));
			}
		}
		
		conn.close();
		return list ;

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;	
	}
	
}

public List<Distretto> listAllDistricts(int anno,Map<Integer,Distretto>m){
	String sql = "SELECT DISTINCT district_id FROM EVENTS ORDER BY district_id" ;
	try {
		Connection conn = DBConnect.getConnection() ;

		PreparedStatement st = conn.prepareStatement(sql) ;
		
		List<Distretto> list = new ArrayList<Distretto>() ;
		
		ResultSet res = st.executeQuery() ;
		
		while(res.next()) {
			try {
				Distretto d=new Distretto(res.getInt("district_id"),anno);
				list.add(d);
				m.put(d.getId(), d);		
				
			} catch (Throwable t) {
				t.printStackTrace();
				System.out.println(res.getInt("id"));
			}
		}
		
		conn.close();
		return list ;

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null ;
	}
}

}