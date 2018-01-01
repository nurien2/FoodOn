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
	
	public void addRestaurant(int idProprio, String nom,String description,String specialite, File photo, String adresse) {
		Restaurant resto = new Restaurant(nom, specialite, photo, description, adresse);
		em.persist(resto);
		Proprietaire proprio = em.find(Proprietaire.class, idProprio);
		resto.setProprietaire(proprio);
		System.out.println(proprio.getRestaurants());
		System.out.println(resto);
		// ajout a la liste des restos du proprio : ça devrait se faire tout seul non?
		proprio.addResto(resto);
		System.out.println(proprio.getRestaurants());
	}
	
	public void addPlatResto(String nom, String description, String prix, File photo, int idResto) {
		System.out.println("coucou");
		Plat plat = new Plat(photo,nom,description,prix);
		em.persist(plat);
		plat.setResto(em.find(Restaurant.class, idResto));
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
