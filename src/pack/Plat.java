package pack;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Plat {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String photo;
	String nom;
	String description;
	String prix;
	
	
	@ManyToOne
	Restaurant resto;
	
	@OneToMany(mappedBy="plat",fetch=FetchType.EAGER)
	List<CommentairePlat> commentaires;
	public Plat() {};
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrix() {
		return prix;
	}
	public void setPrix(String prix) {
		this.prix = prix;
	}
	
		
	public Restaurant getResto() {
		return resto;
	}
	public void setResto(Restaurant resto) {
		this.resto = resto;
	}
	
	public Plat(String photo, String nom, String description, String prix) {
		super();
		this.photo = photo;
		this.nom = nom;
		this.description = description;
		this.prix = prix;
	}

	public List<CommentairePlat> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(List<CommentairePlat> commentaires) {
		this.commentaires = commentaires;
	}
	
	
	
	
}
