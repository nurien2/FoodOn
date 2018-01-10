package pack;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Servlet implementation class Serv
 */
/*l'ajout de multipart config c'est pour l'ajout des images*/
@WebServlet("/Serv")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class Serv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@EJB
	Facade f;
	
    /**
     * Default constructor. 
     */
    public Serv() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DOOOOOOOOOOGEEEEEEEEET");
		response.setContentType("text/html;charset=UTF-8");
		
		/* R�cup�ration de la session depuis la requ�te */
        HttpSession session = request.getSession();
		
		
		String operation = request.getParameter("operation");
		PrintWriter out = response.getWriter();
		
		switch (operation) {

			case "profil" :
				System.out.println("servlet attaque profil !!!");
				Client clientP = (Client) session.getAttribute("utilisateur");
				request.setAttribute("clientP", clientP);
				request.getRequestDispatcher("profil.jsp").forward(request, response);
				break;
		
			case "deconnexion" :
				session.invalidate();  
				out.print("Vous vous etes deconnecte"); 
				request.getRequestDispatcher("Connexion.html").forward(request, response);
				break;
		
			case "homeClient" :
				System.out.println("servlet attaque homeClient !!!");
				Client clientHC = (Client) session.getAttribute("utilisateur");
				request.setAttribute("listeRestaux", f.getRestaurantsProposes(clientHC));				
				request.getRequestDispatcher("homeClient.jsp").forward(request, response);
				break;				
			case "homeProprio" :
				System.out.println("Servlet attaque homeProprio !!!");
				Proprietaire proprio = (Proprietaire) session.getAttribute("utilisateur");
				request.setAttribute("proprio", proprio);
				int nbRestaux = f.getNbRestos(proprio);
				int nbPlats = f.getNbPlats(proprio);
				//
				List<Commande> listeCommandes = f.getCommandeProprio(proprio);
				request.setAttribute("listeCommandes", listeCommandes);
				//
				List<Restaurant> listeRestos = proprio.getRestaurants();
				request.setAttribute("restos", listeRestos);
	        	request.setAttribute("nbPlats",Integer.toString(nbPlats));
	        	request.setAttribute("nbRestaux",Integer.toString(nbRestaux));
	        	
				request.getRequestDispatcher("homeProprietaire.jsp").forward(request, response);
				break;
				
			case "restaurants" :
				System.out.println("Servlet attaquee listerResto !!!");
				Proprietaire user = (Proprietaire) session.getAttribute("utilisateur");
				List<Restaurant> listeResto = user.getRestaurants();
				System.out.println(listeResto);
				if (!listeResto.isEmpty()) { 
						request.setAttribute("listeResto", listeResto);
				}
				request.setAttribute("prenom", user.getPrenom());
				request.getRequestDispatcher("restaurants.jsp").forward(request, response);
				break;
				
			case "plats":
				System.out.println("Servlet attaquee listerPlats !!!");
				Proprietaire userr = (Proprietaire) session.getAttribute("utilisateur");
				List<Plat> listePlats = f.listerPlats(userr);
				if (!listePlats.isEmpty()) { 
					request.setAttribute("listePlats", listePlats);
				}
				request.setAttribute("prenom", userr.getPrenom());
				request.getRequestDispatcher("plats.jsp").forward(request, response);
				break;
			
			case "commander" : 
				System.out.println("Servlet attaque commander");
				Client clientC = (Client) session.getAttribute("utilisateur");
				f.addCommander(clientC.getId());
				request.setAttribute("listeRestaux", f.getRestaurantsProposes(clientC));
				request.getRequestDispatcher("homeClient.jsp").forward(request, response);
				break;
			case "platsRestaurant" :
				System.out.println("Servlet attaque plats dun restaurant");
				Client client = (Client) session.getAttribute("utilisateur");
				int restoId = Integer.parseInt(request.getParameter("restaurant"));
				List<Plat> listePlatsResto = f.getPlatsRestaurant(restoId);
				request.setAttribute("prenom", client.getPrenom());
				request.setAttribute("listePlatsResto", listePlatsResto);
				session.setAttribute("restoID", restoId);
				request.getRequestDispatcher("restaurantClient.jsp").forward(request, response);
				break;
				
			case "ajoutPanier" :
				System.out.println("Servlet attaque ajoutPanier");
				Client clientAP = (Client) session.getAttribute("utilisateur");
				int platAPid = Integer.parseInt(request.getParameter("plat"));
				int quantite = Integer.parseInt(request.getParameter("quantite"));
				f.addPlatPanier(clientAP,platAPid,quantite);
				int rid = (int) session.getAttribute("restoID");
				List<Plat> listePlatsRestau = f.getPlatsRestaurant(rid);
				request.setAttribute("listePlatsResto", listePlatsRestau);
				request.setAttribute("prenom", clientAP.getPrenom());
				request.getRequestDispatcher("restaurantClient.jsp").forward(request, response);
				break;
				
				//ajout le 06-01-2018
			case "Resto_avec_commentaire" :
				System.out.println("Servlet attaque Resto_avec_commentaire");
			
				int idResto = Integer.parseInt(request.getParameter("idResto"));
				Restaurant resto =f.get_Resto_Par_Id(idResto);
				request.setAttribute("Resto",resto);
				request.getRequestDispatcher("CommentaireResto.jsp").forward(request, response);
				break;
				
				
				//ajout le 06-01-2018
			case "Plat_avec_commentaire" :
				System.out.println("Servlet attaque Plat_avec_commentaire");
			
				int idPlat = Integer.parseInt(request.getParameter("idPlat"));
				Plat plat =f.get_Plat_par_Id(idPlat);
				request.setAttribute("Plat",plat);
				System.out.println("pass");
				request.getRequestDispatcher("CommentairesPlat.jsp").forward(request, response);
				System.out.println("pass2");
				break;
				
				
				
				
				
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SEEEEEEEEEEEEEEEEEEEEEEEERV");
		response.setContentType("text/html;charset=UTF-8");
		
		String operation = request.getParameter("operation");
		PrintWriter out = response.getWriter();
		
		/* R�cup�ration de la session depuis la requ�te */
        HttpSession session = request.getSession();
		
		switch (operation) {
			case "connexion":
				System.out.println("Servlet attaquee connexion !!");
		        
		        String email = request.getParameter("email");
		        String pass = request.getParameter("pass");
		        
		        Client utilisateur = null;

		        if ((utilisateur = ValidationUser.verifierUser(f, email, pass)) != null) {
		        	out.println("Bienvenue " + utilisateur.getPrenom());
		        	session.setAttribute("utilisateur", utilisateur );
		        	request.setAttribute("proprio", utilisateur);
		        	if (utilisateur instanceof Proprietaire) {
		        		int nbRestaux = f.getNbRestos((Proprietaire) utilisateur);
		        		int nbPlats = f.getNbPlats((Proprietaire) utilisateur);
		        		List<Commande> listeCommandes = f.getCommandeProprio((Proprietaire) utilisateur);
						List<Restaurant> listeRestos = ((Proprietaire) utilisateur).getRestaurants();
						request.setAttribute("restos", listeRestos);
		        		request.setAttribute("listeCommandes", listeCommandes);
		        		request.setAttribute("nbPlats",Integer.toString(nbPlats));
		        		request.setAttribute("nbRestaux",Integer.toString(nbRestaux));
		        		request.getRequestDispatcher("homeProprietaire.jsp").forward(request, response);
		        	} else {
		        		List<Restaurant> restaurantsProposes = f.getRestaurantsProposes(utilisateur);
		        		request.setAttribute("listeRestaux",restaurantsProposes);
		        		request.getRequestDispatcher("homeClient.jsp").forward(request, response);
		        	}
		        } else {
		           out.println("Adresse ou mdp incorrects");
		           RequestDispatcher rs = request.getRequestDispatcher("Connexion.html");
		           rs.include(request, response);
		        }
		        break;
		        
			case "inscription":
				System.out.println("Servlet attaquee inscription !!");
				String nom = request.getParameter("nom");
				String prenom = request.getParameter("prenom");
				String mdp = request.getParameter("mdp");
				String adresse = request.getParameter("adresse");
				String adresseMail = request.getParameter("email");
				String pseudo = request.getParameter("pseudo");
				Boolean isClient = true;
				String gouts = "";
				if (request.getParameter("isClient").equals("false")) {
					isClient = false;
				}
				f.addClient(prenom, nom, pseudo, mdp, adresseMail, adresse, gouts, isClient);
				request.getRequestDispatcher("Connexion.html").forward(request, response);
				break;
				
			case "ajouterRestaurant" :
				System.out.println("Servlet attaque ajoutResto !!!");
				String nomResto = request.getParameter("nom");
				String adresseResto = request.getParameter("adresse");
				String specialtiteResto = request.getParameter("specialite");
				String descriptionResto = request.getParameter("editor1");
				File photoResto = (File) request.getAttribute("photo");
				Proprietaire user = (Proprietaire) session.getAttribute("utilisateur");
				f.addRestaurant(user , nomResto, descriptionResto, specialtiteResto, photoResto, adresseResto);
				int nbRestaux = f.getNbRestos((Proprietaire) user);
        		request.setAttribute("nbRestaux",Integer.toString(nbRestaux));
				request.setAttribute("proprio", user);
				List<Commande> listeCommandes = f.getCommandeProprio((Proprietaire) user);
				request.setAttribute("listeCommandes", listeCommandes);
				List<Restaurant> listeRestos = user.getRestaurants();
				request.setAttribute("restos", listeRestos);
				int nbPlats = f.getNbPlats((Proprietaire) user);
        		request.setAttribute("nbPlats",Integer.toString(nbPlats));
				request.getRequestDispatcher("homeProprietaire.jsp").forward(request, response);
				break;
			
			case "ajouterPlatRestaurant" :
			
				
				
				//d�but  partie  qui concerne l'ajout de l'image 
				Part part = request.getPart("photo");
		        String fileName = extractFileName(part);
		        String savePaths = "/home/nicolas/ImagesAppliWeb/" + File.separator + fileName;
		        System.out.println(savePaths);
		        part.write(savePaths + File.separator);
		        System.out.println("photo du plat inserted !!!");
				
				//fin partie concernat l'image
		
				
				System.out.println("Servlet attaque ajoutPlatResto !!!");
				String nomPlat = request.getParameter("nom");
				String descriptionPlat = request.getParameter("description");
				String prixPlat = request.getParameter("prix");
				int restoAssocieId = Integer.parseInt(request.getParameter("restaurant"));
				
				//String photoPlat = (String) request.getAttribute("photo");
				
				Proprietaire userr = (Proprietaire) session.getAttribute("utilisateur");
				f.addPlatResto(nomPlat,descriptionPlat,prixPlat,fileName,restoAssocieId,userr);
				int nbRestau = f.getNbRestos((Proprietaire) userr);
        		request.setAttribute("nbRestaux",Integer.toString(nbRestau));
				request.setAttribute("proprio", userr);
				
				List<Commande> commandes = f.getCommandeProprio(userr);
				request.setAttribute("listeCommandes", commandes);
				List<Restaurant> listeRestaus = userr.getRestaurants();
				request.setAttribute("restos", listeRestaus);

				int nbPlatss = f.getNbPlats((Proprietaire) userr);
        		request.setAttribute("nbPlats",Integer.toString(nbPlatss));
				request.getRequestDispatcher("homeProprietaire.jsp").forward(request, response);
				break;
				//ajout le 06-01-2018
			case "commenterPlat" :
				System.out.println("Servlet attaque commenter Plat !!!");
				int idPlat = (int) Integer.parseInt(request.getParameter("idplat"));
				String texte = (String) request.getParameter("text");
				System.out.println("le commentaire:"+texte);
				Client client = (Client) session.getAttribute("utilisateur");
				CommentairePlat commentairePlat  =new CommentairePlat();
				commentairePlat.setTexte(texte);
				f.commenterPlat(client.getId(), idPlat, commentairePlat);
				System.out.println("Le client de Id = "+client.getId()+"a commenter  un plat !!! ");
				
				Plat plat =f.get_Plat_par_Id(idPlat);
        		request.setAttribute("Plat",plat);
				request.getRequestDispatcher("CommentairesPlat.jsp").forward(request, response);
				break;
				//ajout le 06-01-2018
			case "commenterResto" :
				System.out.println("Servlet attaque commenter Resto !!!");
				int idResto = (int) Integer.parseInt(request.getParameter("idResto"));
				String text = (String) request.getAttribute("text");
				Client clien = (Client) session.getAttribute("utilisateur");
				CommentaireResto commentaireResto  =new CommentaireResto();
				commentaireResto.setTexte(text);
				f.commenterResto(clien.getId(), idResto, commentaireResto);
				System.out.println("Le client de Id = "+clien.getId()+"a commenter  un plat !!! ");
				
				Restaurant resto =f.get_Resto_Par_Id(idResto);
        		request.setAttribute("Resto",resto);
				request.getRequestDispatcher("CommentaireResto.jsp").forward(request, response);
				break;
				
				
			case "validerModifs" :
				System.out.println("servlet attaque modif !!!");
				Client clientVM = (Client) session.getAttribute("utilisateur");
				String nomVM = request.getParameter("nom");
				String prenomVM = request.getParameter("prenom");
				String adresseVM = request.getParameter("adresse");
				String adresseMailVM = request.getParameter("email");
				String pseudoVM = request.getParameter("pseudo");
				f.modifierProfil(clientVM,nomVM,prenomVM,adresseVM,adresseMailVM,pseudoVM);
				request.setAttribute("clientP", f.getClientparId(clientVM.getId()));
				request.getRequestDispatcher("profil.jsp").forward(request, response);
				break;
				
			case "modifMDP" :
				System.out.println("servlet attaque modifMDP !!!");
				Client clientMDP = (Client) session.getAttribute("utilisateur");
				String oldmdp = request.getParameter("oldmdp");
				String newmdp = request.getParameter("newmdp");
				boolean verif = f.modifierMDP(clientMDP,oldmdp,newmdp);
				if (!verif) {
					out.println("ancien mot de passe incorrect");
				}
				Client clientModifie = (Client) session.getAttribute("utilisateur");
				request.setAttribute("clientP", clientModifie);
				request.getRequestDispatcher("profil.jsp").forward(request, response);
				break;
			

		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
        
	}
	
	
	//fonction pour l'ajout des images 
	  private String extractFileName(Part part) {
	        String contentDisp = part.getHeader("content-disposition");
	        String[] items = contentDisp.split(";");
	        for (String s : items) {
	            if (s.trim().startsWith("filename")) {
	                return s.substring(s.indexOf("=") + 2, s.length() - 1);
	            }
	        }
	        return "";
	    }
	

}
