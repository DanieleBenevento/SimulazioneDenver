package it.polito.tdp.model;

public class CentroCrimine {

	int district_id;
	double longitude;
	double latitude;
	int anno;
	
	public CentroCrimine(int district_id,double longitude, double latitude,int anno) {
		this.anno=anno;
		this.district_id=district_id;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	

	public int getAnno() {
		return anno;
	}



	public void setAnno(int anno) {
		this.anno = anno;
	}



	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public int getDistrict_id() {
		return district_id;
	}

	public void setDistrict_id(int district_id) {
		this.district_id = district_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
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
		CentroCrimine other = (CentroCrimine) obj;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		return true;
	}


	
	
	
	
}
