package pack;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Proprietaire extends Client {

	@OneToMany(mappedBy="proprietaire", fetch = FetchType.EAGER)
	List<Restaurant> restaurants;
	//List<Commande> commandes;
	
	public Proprietaire() {};
	
	public Proprietaire(String prenom, String nom, String pseudo, String mdp, String adresseMail, String adresse, String gouts) {
		super(prenom, nom, pseudo, mdp, adresseMail, adresse, gouts);
	}
	
	
	
	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	public void ajouterRestaurant(Restaurant resto) {
		this.restaurants.add(resto);
	}
	
	public void accepterCommande() {}

	public void addResto(Restaurant resto) {
		this.restaurants.add(resto);		
	};

}
