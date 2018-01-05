package pack;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Entity;
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
	File photo;
	String nom;
	String description;
	String prix;
	
	
	@ManyToOne
	Restaurant resto;
	
	@OneToMany(mappedBy="plat")
	List<CommentairePlat> commentaires;
	public Plat() {};
	
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
	
	public Plat(File photo, String nom, String description, String prix) {
		super();
		this.photo = photo;
		this.nom = nom;
		this.description = description;
		this.prix = prix;
	}
	
	
	
	
}
