package pack;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	
	//private static final String PATH = "C:\\Users\\WhyRootOne\\Desktop\\app\\FoodOn\\WebContent\\images";
	 private static final String PATH   = "D:\\FoodOnV8\\FoodOn\\WebContent\\images\\" ;
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
		
		/* Rï¿½cupï¿½ration de la session depuis la requï¿½te */
        HttpSession session = request.getSession();
		
		
		String operation = request.getParameter("operation");
		PrintWriter out = response.getWriter();
		
		switch (operation) {

			case "profil" :
				System.out.println("servlet attaque profil !!!");
				int clientPID = (int) session.getAttribute("utilisateur");
				Client clientP = f.getUserByID(clientPID);
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
				int clientHCID = (int) session.getAttribute("utilisateur");
				Client clientHC = f.getUserByID(clientHCID);
				request.setAttribute("client", clientHC);
				request.setAttribute("listeRestaux", f.getRestaurantsProposes(clientHC));				
				request.getRequestDispatcher("homeClient.jsp").forward(request, response);
				break;				
			case "homeProprio" :
				System.out.println("Servlet attaque homeProprio !!!");
				int proprioID = (int) session.getAttribute("utilisateur");
				Proprietaire proprio = (Proprietaire) f.getUserByID(proprioID);
				request.setAttribute("proprio", proprio);
				int nbRestaux = f.getNbRestos(proprio);
				int nbPlats = f.getNbPlats(proprio);
				
				if(getServletContext().getAttribute("lesConnectes")==null){
					getServletContext().setAttribute("lesConnectes", Collections.synchronizedMap(new HashMap<String,HttpSession>()));
				}
				
				Map<String,HttpSession> lesConnectes=(Map<String,HttpSession>) getServletContext().getAttribute("lesConnectes");			
				if(lesConnectes.get(request.getParameter(Integer.toString(proprioID)))==null){
					lesConnectes.put(Integer.toString(proprioID), session);
				}	
				
				
				
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
				int userID = (int) session.getAttribute("utilisateur");
				Proprietaire user = (Proprietaire) f.getUserByID(userID);
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
				int userrID = (int) session.getAttribute("utilisateur");
				Proprietaire userr = (Proprietaire) f.getUserByID(userrID);
				List<Plat> listePlats = f.listerPlats(userr);
				if (!listePlats.isEmpty()) { 
					request.setAttribute("listePlats", listePlats);
				}
				request.setAttribute("prenom", userr.getPrenom());
				request.getRequestDispatcher("plats.jsp").forward(request, response);
				break;
			
			case "commander" : 
				System.out.println("Servlet attaque commander");
				int clientCID = (int) session.getAttribute("utilisateur");
				Client clientC =  f.getUserByID(clientCID);
				f.addCommander(clientCID);
				request.setAttribute("listeRestaux", f.getRestaurantsProposes(clientC));
				request.getRequestDispatcher("homeClient.jsp").forward(request, response);
				break;
			case "platsRestaurant" :
				System.out.println("Servlet attaque plats dun restaurant");
				int clientID = (int) session.getAttribute("utilisateur");
				Client client =  f.getUserByID(clientID);
				int restoId = Integer.parseInt(request.getParameter("restaurant"));
				List<Plat> listePlatsResto = f.getPlatsRestaurant(restoId);
				request.setAttribute("prenom", client.getPrenom());
				request.setAttribute("listePlatsResto", listePlatsResto);
				session.setAttribute("restoID", restoId);
				request.getRequestDispatcher("restaurantClient.jsp").forward(request, response);
				break;
				
			case "ajoutPanier" :
				System.out.println("Servlet attaque ajoutPanier");
				int clientAPID = (int) session.getAttribute("utilisateur");
				Client clientAP =  f.getUserByID(clientAPID);
				// session.setAttribute PANIER
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
				request.getRequestDispatcher("CommentairesPlat.jsp").forward(request, response);
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
		
		/* Rï¿½cupï¿½ration de la session depuis la requï¿½te */
        HttpSession session = request.getSession();
		
		switch (operation) {
			case "connexion":
				System.out.println("Servlet attaquee connexion !!");
		        
		        String email = request.getParameter("email");
		        String pass = request.getParameter("pass");
		        
		        Client utilisateur = null;

		        if ((utilisateur = ValidationUser.verifierUser(f, email, pass)) != null) {
		        	out.println("Bienvenue " + utilisateur.getPrenom());
		        	session.setAttribute("utilisateur", utilisateur.getId());
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
				int userID = (int) session.getAttribute("utilisateur");
				f.addRestaurant(userID , nomResto, descriptionResto, specialtiteResto, photoResto, adresseResto);
				Proprietaire user = (Proprietaire) f.getUserByID(userID);
				int nbRestaux = user.getRestaurants().size();
        		request.setAttribute("nbRestaux",Integer.toString(nbRestaux));
				request.setAttribute("proprio", user);
				List<Commande> listeCommandes = f.getCommandeProprio(user);
				request.setAttribute("listeCommandes", listeCommandes);
				List<Restaurant> listeRestos = user.getRestaurants();
				request.setAttribute("restos", listeRestos);
				int nbPlats = f.getNbPlats(user);
        		request.setAttribute("nbPlats",Integer.toString(nbPlats));
				request.getRequestDispatcher("homeProprietaire.jsp").forward(request, response);
				break;
			
			case "ajouterPlatRestaurant" :
			
				
				
				//dï¿½but  partie  qui concerne l'ajout de l'image 
				Part part = request.getPart("photo");
		        String fileName = extractFileName(part);
		        String savePaths = PATH + File.separator + fileName;
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
				
				int userrID = (int) session.getAttribute("utilisateur");
				f.addPlatResto(nomPlat,descriptionPlat,prixPlat,fileName,restoAssocieId,userrID);
				Proprietaire userr = (Proprietaire) f.getUserByID(userrID);
				int nbRestau = userr.getRestaurants().size();
        		request.setAttribute("nbRestaux",Integer.toString(nbRestau));
				request.setAttribute("proprio", userr);
				
				List<Commande> commandes = f.getCommandeProprio(userr);
				request.setAttribute("listeCommandes", commandes);
				List<Restaurant> listeRestaus = userr.getRestaurants();
				request.setAttribute("restos", listeRestaus);

				int nbPlatss = f.getNbPlats(userr);
        		request.setAttribute("nbPlats",Integer.toString(nbPlatss));
				request.getRequestDispatcher("homeProprietaire.jsp").forward(request, response);
				break;
				//ajout le 06-01-2018
			case "commenterPlat" :
				System.out.println("Servlet attaque commenter Plat !!!");
				int idPlat = (int) Integer.parseInt(request.getParameter("idplat"));
				String texte = (String) request.getParameter("text");
				System.out.println("le commentaire:"+texte);
				int clientID = (int) session.getAttribute("utilisateur");
				Client client = f.getUserByID(clientID);
				CommentairePlat commentairePlat  = new CommentairePlat();
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
				int clienID = (int) session.getAttribute("utilisateur");
				Client clien = f.getUserByID(clienID);
				
				CommentaireResto commentaireResto  = new CommentaireResto();
				commentaireResto.setTexte(text);
				f.commenterResto(clien.getId(), idResto, commentaireResto);
				System.out.println("Le client de Id = "+clien.getId()+"a commenter  un plat !!! ");
				
				Restaurant resto =f.get_Resto_Par_Id(idResto);
        		request.setAttribute("Resto",resto);
				request.getRequestDispatcher("CommentaireResto.jsp").forward(request, response);
				break;
				
				
			case "validerModifs" :
				System.out.println("servlet attaque modif !!!");
				int clientVMID = (int) session.getAttribute("utilisateur");
				Client clientVM = f.getUserByID(clientVMID);
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
				int clientMDPID = (int) session.getAttribute("utilisateur");
				Client clientMDP = f.getUserByID(clientMDPID);
				String oldmdp = request.getParameter("oldmdp");
				String newmdp = request.getParameter("newmdp");
				boolean verif = f.modifierMDP(clientMDP,oldmdp,newmdp);
				if (!verif) {
					out.println("ancien mot de passe incorrect");
				}
				//Client clientModifie = (Client) session.getAttribute("utilisateur");
				//request.setAttribute("clientP", clientModifie);
				request.getRequestDispatcher("profil.jsp").forward(request, response);
				break;
			

				
				

				//update 16-01-2018 
			case "modifierImage":
				
				
				
				//  partie  qui concerne l'ajout de l'image de l'utilisateur 
				Part part2 = request.getPart("photo");
		        String fileName2= extractFileName(part2);
		        String savePaths2 = PATH + File.separator + fileName2;
		        System.out.println(savePaths2);
		        part2.write(savePaths2 + File.separator);
		        System.out.println("photo du client inserted !!!");
				
				//fin partie concernat l'image
		        
		        int clienIDD = (int) session.getAttribute("utilisateur");
		        System.out.println("photo under Name :"+fileName2 );
				f.modifierImageClient(fileName2 , clienIDD);
				Client clientP = f.getUserByID(clienIDD);
				 System.out.println("servlet va te rederiger vers profil.jsp");
				request.setAttribute("clientP", clientP);
				
				request.getRequestDispatcher("profil.jsp").forward(request, response);
				
				
				break;
				
				//fin update 16-01-2018
				
			case "supprimerImage":
				System.out.println("suppression de votre image de notre base de donnéé" );
				int clienIDDD = (int) session.getAttribute("utilisateur");
				f.supprimerImageClient(clienIDDD);
				Client clientPP = f.getUserByID(clienIDDD);			
				request.setAttribute("clientP", clientPP);
				 System.out.println("servlet va te rederiger vers profil.jsp");
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
