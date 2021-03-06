package pack;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Client {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String prenom;
	String nom;
	String pseudo;
	String mdp;
	String adresseMail;
	String adresse;
	String gouts;
	String image;
	//List<String> gouts;
	@OneToOne
	Commande commandeEnCours;
	//List<String> notifications;
	
	@OneToMany(mappedBy="client")
	List<CommentairePlat> commentairesPlat;
	
	@OneToMany(mappedBy="client")
	List<CommentaireResto> commentairesResto;
	
	public Client() {};
	
	public Client(String prenom, String nom, String pseudo, String mdp, String adresseMail, String adresse, String gouts) {
		this.prenom = prenom;
		this.nom = nom;
		this.pseudo = pseudo;
		this.mdp = mdp;
		this.adresseMail = adresseMail;
		this.adresse = adresse;
		this.gouts = gouts;
		this.setImage("img/avatar.png");
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
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getGouts() {
		return gouts;
	}
	public void setGouts(String gouts) {
		this.gouts = gouts;
	}
	public Commande getCommandeEnCours() {
		return commandeEnCours;
	}
	public void setCommandeEnCours(Commande commandeEnCours) {
		this.commandeEnCours = commandeEnCours;
	}
	/*public List<String> getNotifications() {
		return notifications;
	}
	public void setNotifications(List<String> notifications) {
		this.notifications = notifications;
	}*/
	
	public void commander() {};
	
	public void reserver() {};
	
	
	public String getImage() {
				return image;
			}
		
			public void setImage(String image) {
				this.image = image;
			};
	
}