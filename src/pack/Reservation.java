package pack;

import java.util.Date;

public class Reservation {
	
	int idClient;
	int idResto;
	int nbPlaces;
	Date dateReservation;
	int creneauReservation;
	
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public int getIdresto() {
		return idResto;
	}
	public void setIdResto(int idResto) {
		this.idResto = idResto;
	}
	public int getNbPlaces() {
		return nbPlaces;
	}
	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}
	public Date getDateReservation() {
		return dateReservation;
	}
	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}
	public int getCreneauReservation() {
		return creneauReservation;
	}
	public void setCreneauReservation(int creneauReservation) {
		this.creneauReservation = creneauReservation;
	}
}
