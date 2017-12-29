package pack;

import java.io.File;
import java.util.HashMap;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Restaurant {

	@ManyToOne
	Proprietaire proprietaire;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
	File photo;
//	@ManyToMany
//	Collection<Plat> plats;
	HashMap<Integer,Commentaire> commentaires;
	
	
	public Proprietaire getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(Proprietaire proprietaire) {
		this.proprietaire = proprietaire;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public File getPhoto() {
		return photo;
	}
	public void setPhoto(File photo) {
		this.photo = photo;
	}
//	public Collection<Plat> getPlats() {
//		return plats;
//	}
//	public void setPlats(Collection<Plat> plats) {
//		this.plats = plats;
//	}
	public HashMap<Integer, Commentaire> getCommentaires() {
		return commentaires;
	}
	public void setCommentaires(HashMap<Integer, Commentaire> commentaires) {
		this.commentaires = commentaires;
	}
	
	public void commenter(Commentaire commentaire) {
		this.commentaires.put(commentaire.idClient, commentaire);
	}
	
//	public void ajouterPlat(Plat plat) {
//		if (!this.plats.contains(plat)) {
//			this.plats.add(plat);
//		}
//	}
//	
//	public void retirerPlat(Plat plat) {
//		this.plats.remove(plat);
//	}
	
}
