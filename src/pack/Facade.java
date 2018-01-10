package pack;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
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
			Client client = new Client(prenom, nom, pseudo, mdp, adresseMail, adresse, gouts);
			em.persist(client);
		} else {
			Proprietaire proprietaire = new Proprietaire(prenom, nom, pseudo, mdp, adresseMail, adresse, gouts);
			em.persist(proprietaire);
		}
		
	}
	
	public void addRestaurant(Proprietaire proprio, String nom,String description,String specialite, File photo, String adresse) {
		Restaurant resto = new Restaurant(nom, specialite, photo, description, adresse);
		em.persist(resto);
		resto.setProprietaire(proprio);
		proprio.addResto(resto);
	}
	
	public void addPlatResto(String nom, String description, String prix, String photo, String nomResto,Proprietaire proprio) {
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

	public void addPlatPanier(Client client,int platId, int quantite) {
		Plat plat = em.find(Plat.class, platId);
		if (client.getCommandeEnCours() == null) {
			Commande commande = new Commande(client,plat.getResto(),plat,quantite);
			em.persist(commande);
			commande.setClient(client);
			commande.setRestaurant(plat.getResto());
			commande.setPropio(plat.getResto().getProprietaire());
		} else {
			client.getCommandeEnCours().ajouterPlat(plat, quantite);
		}
		
	}

	public void addCommander(int clientId) {
		Client client = em.find(Client.class, clientId);
		if (client.getCommandeEnCours() != null) {
			Proprietaire prop = client.getCommandeEnCours().getRestaurant().getProprietaire();
			prop.addCommande(client.getCommandeEnCours());
		}
		
	}
	
	public List<Restaurant> getRestaurantsProposes(Client client) {
		List<Restaurant> listeRestaux = this.getListeRestaurants();
		for (Restaurant resto : listeRestaux) {
			// apr�s on va voir quel crit�re
		}
		return listeRestaux;
	}
	
	public List<Plat> getPlatsRestaurant(int idResto) {
		Restaurant resto = em.find(Restaurant.class, idResto);
		return resto.getPlats();
	}
	
	
	
	public void commenterPlat(int idClient,int idPlat,CommentairePlat c){
		Client client=em.find(Client.class, idClient);
		Plat plat=em.find(Plat.class, idPlat);
	    c.setClient(client);
	    c.setPlat(plat);
	    c.setDate_commentaire(new Date());
	    em.persist(c);	        
	}
	
	public void commenterResto(int idClient,int idResto,CommentaireResto c){
		Client client=em.find(Client.class, idClient);
		Restaurant resto=em.find(Restaurant.class, idResto);
	    c.setClient(client);
	    c.setResto(resto);
	    c.setDate_commentaire(new Date());
	    em.persist(c);	     
	    	    
	}
	public List<CommentairePlat> get_Commentaires_Plat(int idPlat) {
		Plat p=em.find(Plat.class, idPlat);
		
		return p.getCommentaires();
		
		
	}
	public List<CommentaireResto> get_Commentaires_Resto(int idResto) {
		Restaurant r=em.find(Restaurant.class, idResto);
		
		return r.getCommentaires();
		
		
	}
	
	//ajout 06-01-2018
		public Plat get_Plat_par_Id(int idPlat){
			Plat  p=em.find(Plat.class, idPlat);
			return p;
		}
		//ajout 06-01-2018
		public Restaurant get_Resto_Par_Id(int idResto){
			Restaurant  r=em.find(Restaurant.class, idResto);
			return r;
		}
	
	
		public void modifierProfil(Client client, String nom, String prenom, String adresse, String adresseMail, String pseudo) {
			Client cli = em.find(Client.class,client.getId());
			cli.setAdresse(adresse);
			cli.setAdresseMail(adresseMail);
			cli.setNom(nom);
			cli.setPrenom(prenom);
			cli.setPseudo(pseudo);
			em.merge(cli);
		}
		public Client getClientparId (int idClient) {
			return em.find(Client.class, idClient);
		}

		public boolean modifierMDP(Client client, String oldmdp, String newmdp) {
			Client cli = em.find(Client.class, client.getId());
			if (cli.getMdp().equals(oldmdp)) {
				cli.setMdp(newmdp);
				em.merge(cli);
				return true;
			} else {
				return false;
			}
		}
	
	
	
	
	
	
}
