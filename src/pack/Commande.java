package pack;

import java.util.Date;
import java.util.HashMap;

public class Commande {

	int id_client;
	int id_resto;
	HashMap<Plat,Integer> plats;
	Date DateCommande;
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	public int getId_resto() {
		return id_resto;
	}
	public void setId_resto(int id_resto) {
		this.id_resto = id_resto;
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
