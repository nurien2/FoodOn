package pack;

import java.util.Date;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Commande {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	

	@OneToOne(fetch=FetchType.EAGER)
	Client client;
	
	@ManyToOne(fetch=FetchType.EAGER)
	Proprietaire proprio;
	
	@ManyToOne
	Restaurant restaurant;
	
	HashMap<Integer,Integer> plats = new HashMap<Integer,Integer>();
	Date DateCommande;
	
	public Commande(Client client, Restaurant resto, Plat plat, int quantite) {
		this.client = client;
		this.restaurant = resto;
		this.plats.put(plat.getId(),quantite);
		this.DateCommande = new Date();
		
	}
	public Commande() {};
	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public HashMap<Integer, Integer> getPlats() {
		return plats;
	}
	public void setPlats(HashMap<Integer, Integer> plats) {
		this.plats = plats;
	}
	public Date getDateCommande() {
		return DateCommande;
	}
	public void setDateCommande(Date dateCommande) {
		DateCommande = dateCommande;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Proprietaire getPropio() {
		return proprio;
	}
	public void setPropio(Proprietaire propio) {
		this.proprio = propio;
	}
	public void accepterCommande() {};
	
	public void refuserCommande() {};
	
	public void annulerCommande() {};
	
	public void ajouterPlat(Plat plat, int quantite) {
		if (!this.plats.containsKey(plat.getId())) {
			this.plats.put(plat.getId(),quantite);
		} else {
			this.plats.put(plat.getId(),this.plats.get(plat.getId()) + quantite);
		}
	}
	
	public void retirerPlat(Plat plat, int quantite) {
		if (this.plats.containsKey(plat.getId())) {
			this.plats.put(plat.getId(),this.plats.get(plat.getId()) - quantite);
			if (this.plats.get(plat.getId()) <= 0) {
				this.plats.remove(plat.getId());
			}
		}
	}
	
	
}
