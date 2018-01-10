package pack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Restaurant {



	@ManyToOne
	Proprietaire proprietaire;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	String nom;
	String specialite;
	File photo;
	String description;
	String adresse;
	
	@OneToMany(mappedBy="resto", fetch=FetchType.EAGER)
	
	List<Plat> plats;
	
	
	@OneToMany(mappedBy="Resto")
	List<CommentaireResto> commentaires;
	
	public Restaurant() {};

	
	public Restaurant(String nom, String specialite, File photo, String descrption, String adresse) {
		super();
		this.nom = nom;
		this.specialite = specialite;
		this.photo = photo;
		this.description = descrption;
		this.adresse = adresse;
		this.plats = new ArrayList<>();
	}



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
	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getSpecialite() {
		return specialite;
	}


	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	public List<Plat> getPlats() {
		return plats;
	}
	public void setPlats(List<Plat> plats) {
		this.plats = plats;
	}
	
	
	public void ajouterPlat(Plat plat) {
		if (!this.plats.contains(plat)) {
			this.plats.add(plat);
		}
	}
	
	public void retirerPlat(Plat plat) {
		this.plats.remove(plat);
	}


	public List<CommentaireResto> getCommentaires() {
		return commentaires;
	}


	public void setCommentaires(List<CommentaireResto> commentaires) {
		this.commentaires = commentaires;
	}
	
	
}
