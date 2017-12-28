package pack;

import java.io.File;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Plat {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	File photo;
	String nom;
	HashMap<Integer,Commentaire> commentaires;
	
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
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public HashMap<Integer, Commentaire> getCommentaires() {
		return commentaires;
	}
	public void setCommentaires(HashMap<Integer, Commentaire> commentaires) {
		this.commentaires = commentaires;
	}
	
	public void Commenter(Commentaire commentaire) {
		this.commentaires.put(commentaire.getIdClient(), commentaire);
	}
	
	
}
