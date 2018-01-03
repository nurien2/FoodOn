package pack;

import java.util.Date;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Commande {

	@Id
	int id;
	

	@OneToOne
	Client client;
	
	@ManyToOne
	Restaurant restaurant;
	HashMap<Plat,Integer> plats = new HashMap<Plat,Integer>();
	Date DateCommande;
	
	public Commande(Client client, Restaurant resto, Plat plat, int quantite) {
		this.client = client;
		this.restaurant = resto;
		this.plats.put(plat,quantite);
		this.DateCommande = new Date();
		
	}
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
	public HashMap<Plat, Integer> getPlats() {
		return plats;
	}
	public void setPlats(HashMap<Plat, Integer> plats) {
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
	
	public void accepterCommande() {};
	
	public void refuserCommande() {};
	
	public void annulerCommande() {};
	
	public void ajouterPlat(Plat plat, int quantite) {
		if (!this.plats.containsKey(plat)) {
			this.plats.put(plat,quantite);
		} else {
			this.plats.put(plat,this.plats.get(plat) + quantite);
		}
	}
	
	public void retirerPlat(Plat plat, int quantite) {
		if (this.plats.containsKey(plat)) {
			this.plats.put(plat,this.plats.get(plat) - quantite);
			if (this.plats.get(plat) <= 0) {
				this.plats.remove(plat);
			}
		}
	}
	
	
}
