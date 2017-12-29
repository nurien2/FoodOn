package pack;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Client {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	String prenom;
	String nom;
	String pseudo;
	String mdp;
	String adresseMail;
	String adresse;
	//Collection<String> gouts;
	//@OneToOne(mappedBy="client")
	//Commande commandeEnCours;
	//Collection<String> notifications;
	
	public Client(String prenom, String nom, String pseudo, String mdp, String adresseMail, String adresse, Collection<String> gouts) {
		this.prenom = prenom;
		this.nom = nom;
		this.pseudo = pseudo;
		this.mdp = mdp;
		this.adresseMail = adresseMail;
		this.adresse = adresse;
//		this.gouts = gouts;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public String getAdresseMail() {
		return adresseMail;
	}
	public void setAdresseMail(String adresseMail) {
		this.adresseMail = adresseMail;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
//	public Collection<String> getGouts() {
//		return gouts;
//	}
//	public void setGouts(Collection<String> gouts) {
//		this.gouts = gouts;
//	}
//	public Commande getCommandeEnCours() {
//		return commandeEnCours;
//	}
//	public void setCommandeEnCours(Commande commandeEnCours) {
//		this.commandeEnCours = commandeEnCours;
//	}
//	public Collection<String> getNotifications() {
//		return notifications;
//	}
//	public void setNotifications(Collection<String> notifications) {
//		this.notifications = notifications;
//	}
	
	public void commander() {};
	
	public void reserver() {};
	
}