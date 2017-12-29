package pack;

import java.util.Collection;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Proprietaire extends Client {

	@LazyCollection(LazyCollectionOption.FALSE)    
	@OneToMany(mappedBy="proprietaire", cascade = CascadeType.ALL)
	Collection<Restaurant> restaurants;
//	@LazyCollection(LazyCollectionOption.FALSE)
//	@OneToMany(mappedBy="proprietaire", cascade = CascadeType.ALL)
//	Collection<Commande> commandes;
	
	public Proprietaire(String prenom, String nom, String pseudo, String mdp, String adresseMail, String adresse, Collection<String> gouts) {
		super(prenom, nom, pseudo, mdp, adresseMail, adresse, gouts);
	}
	
	public void ajouterRestaurant(Restaurant resto) {
		this.restaurants.add(resto);
	}
	
	public void accepterCommande() {};

}
