package pack;

import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;

public class Proprietaire extends Client {

	@OneToMany(mappedBy="proprietaire", fetch = FetchType.EAGER)
	List<Restaurant> restaurants;
	List<Commande> commandes;
	
	public Proprietaire(String prenom, String nom, String mdp, String adresseMail, String adresse, List<String> gouts) {
		super(prenom, nom, mdp, adresseMail, adresse, gouts);
	}
	
	public void ajouterRestaurant(Restaurant resto) {
		this.restaurants.add(resto);
	}
	
	public void accepterCommande() {};

}
