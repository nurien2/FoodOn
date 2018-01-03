package pack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

//import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Singleton
//@Singleton(name="Facade")
//@Remote(FacadeRemote.class)
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
		int indexOfRs = 0;
		int idResto = -1;
		for (Restaurant r : proprio.getRestaurants()) {
			if (r.getNom().equals(nomResto)) {
				idResto = r.getId();
				indexOfRs = proprio.getRestaurants().indexOf(r);
			}
		}
		if (idResto != -1) {
			Plat plat = new Plat(photo,nom,description,prix);
			em.persist(plat);
			plat.setResto(em.find(Restaurant.class, idResto));
			proprio.getRestaurants().get(indexOfRs).ajouterPlat(plat);
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
	
	public List<Restaurant> getListeRestaurants() {
		TypedQuery<Restaurant> req = em.createQuery("select restaurant from pack.Restaurant restaurant", Restaurant.class);
		List<Restaurant> listeRestaux = req.getResultList();
		return listeRestaux;
	}
	
	public int getNbRestos(Proprietaire proprio) {
		return proprio.getRestaurants().size();
	}

	public int getNbPlats(Proprietaire proprio) {
		int nbPlats = 0;
		for (Restaurant r : proprio.getRestaurants()) {
			nbPlats += r.getPlats().size();
		}
		return nbPlats;
	}

	public List<Plat> listerPlats(Proprietaire prop) {
		List<Plat> listePlat = new ArrayList<Plat>();
		for (Restaurant r : prop.getRestaurants()) {
			listePlat.addAll(r.getPlats());
		}
		return listePlat;
	}

	public void addPlatPanier(Client client, Restaurant resto, Plat plat, int quantite) {
		if (client.getCommandeEnCours() == null) {
			Commande commande = new Commande(client,resto,plat,quantite);
			em.persist(commande);
		} else {
			client.getCommandeEnCours().ajouterPlat(plat, quantite);
		}
		
	}

	public void addCommander(Client clientC) {
		if (clientC.getCommandeEnCours() != null) {
			Proprietaire prop = clientC.getCommandeEnCours().getRestaurant().getProprietaire();
			prop.addCommande(clientC.getCommandeEnCours());
		}
		
	}
	
	public List<Restaurant> getRestaurantsProposes(Client client) {
		List<Restaurant> listeRestaux = this.getListeRestaurants();
		for (Restaurant resto : listeRestaux) {
			// après on va voir quel critère
		}
		return listeRestaux;
	}
	
	public List<Plat> getPlatsRestaurant(int idResto) {
		Restaurant resto = em.find(Restaurant.class, idResto);
		return resto.getPlats();
	}
	
	
}
