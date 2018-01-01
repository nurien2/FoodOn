package pack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


@Singleton
public class Facade {

	
	@PersistenceContext
	EntityManager em;
	
	public void addClient(String prenom, String nom, String pseudo, String mdp, String adresseMail, String adresse, String gouts, Boolean isClient) {
		if (isClient) {
			Client client = new Client(nom, prenom, pseudo, mdp, adresseMail, adresse, gouts);
			em.persist(client);
		} else {
			Proprietaire proprietaire = new Proprietaire(nom, prenom, pseudo, mdp, adresseMail, adresse, gouts);
			em.persist(proprietaire);
		}
		
	}
	
	public void addRestaurant(Proprietaire proprio, String nom,String description,String specialite, File photo, String adresse) {
		Restaurant resto = new Restaurant(nom, specialite, photo, description, adresse);
		em.persist(resto);
		resto.setProprietaire(proprio);
		proprio.addResto(resto);
	}
	
	public void addPlatResto(String nom, String description, String prix, File photo, String nomResto,Proprietaire proprio) {

		int idResto = -1;
		for (Restaurant r : proprio.getRestaurants()) {
			if (r.getNom().equals(nomResto)) {
				idResto = r.getId();
			}
		}
		if (idResto != -1) {
			Plat plat = new Plat(photo,nom,description,prix);
			em.persist(plat);
			plat.setResto(em.find(Restaurant.class, idResto));
		} else {
			System.out.println("resto inconnu");
		}
		
	}
	
	
	public ArrayList<Client> getListeUsers() {
		TypedQuery<Client> req = em.createQuery("select client from pack.Client client", Client.class);
		List<Client> listeClients = req.getResultList();
		TypedQuery<Proprietaire> req2 = em.createQuery("select prop from pack.Proprietaire prop", Proprietaire.class);
		List<Proprietaire> listeProprietaires = req2.getResultList();
		
		ArrayList<Client> Users = new ArrayList<Client>(listeClients);
		Users.addAll(listeProprietaires);
		
		return Users;
	}
	
	
}
