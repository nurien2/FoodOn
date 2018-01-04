package pack;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Notification {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	int id_Destinataire;
	
	int id_Destinateur;
	
	String text_notif;

	public Notification(int id_Destinataire, int id_Destinateur, String text_notif) {
		this.id_Destinataire = id_Destinataire;
		this.id_Destinateur = id_Destinateur;
		this.text_notif = text_notif;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_Destinataire() {
		return id_Destinataire;
	}

	public void setId_Destinataire(int id_Destinataire) {
		this.id_Destinataire = id_Destinataire;
	}

	public int getId_Destinateur() {
		return id_Destinateur;
	}

	public void setId_Destinateur(int id_Destinateur) {
		this.id_Destinateur = id_Destinateur;
	}

	public String getText_notif() {
		return text_notif;
	}

	public void setText_notif(String text_notif) {
		this.text_notif = text_notif;
	}
	
}
