package pack;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Restaurant {

	@ManyToOne
	Proprietaire proprietaire;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
	List<File> photos;
	List<Plat> plats;
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
	public List<File> getPhotos() {
		return photos;
	}
	public void setPhotos(List<File> photos) {
		this.photos = photos;
	}
	public List<Plat> getPlats() {
		return plats;
	}
	public void setPlats(List<Plat> plats) {
		this.plats = plats;
	}
	public HashMap<Integer, Commentaire> getCommentaires() {
		return commentaires;
	}
	public void setCommentaires(HashMap<Integer, Commentaire> commentaires) {
		this.commentaires = commentaires;
	}
	
	public void commenter(Commentaire commentaire) {
		this.commentaires.put(commentaire.idClient, commentaire);
	}
	
	public void ajouterPlat(Plat plat) {
		if (!this.plats.contains(plat)) {
			this.plats.add(plat);
		}
	}
	
	public void retirerPlat(Plat plat) {
		this.plats.remove(plat);
	}
	
}
