package pack;

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
	
	public void addClient(String prenom, String nom, String mdp, String adresseMail, String adresse, List<String> gouts, Boolean isClient) {
		
		if (isClient) {
			Client client = new Client(nom, prenom, mdp, adresseMail, adresse, gouts);
			em.persist(client);
		} else {
			Proprietaire proprietaire = new Proprietaire(nom, prenom, mdp, adresseMail, adresse, gouts);
			em.persist(proprietaire);
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
