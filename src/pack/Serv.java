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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Serv
 */
@WebServlet("/Serv")
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
			case "deconnexion" :
				session.invalidate();  
				out.print("Vous vous etes deconnecte"); 
				request.getRequestDispatcher("Connexion.html").forward(request, response);
				break;
			
			case "homeProprio" :
				System.out.println("Servlet attaque homeProprio !!!");
				Proprietaire proprio = (Proprietaire) session.getAttribute("utilisateur");
				request.setAttribute("prenom", proprio.getPrenom());
				int nbRestaux = f.getNbRestos(proprio);
				int nbPlats = f.getNbPlats(proprio);
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
				f.addCommander(clientC);
				request.getRequestDispatcher("homeClient.html").forward(request, response);
				break;
			case "platsRestaurant" :
				System.out.println("Servlet attaque plats dun restaurant");
				Client client = (Client) session.getAttribute("utilisateur");
				int restoId = Integer.parseInt(request.getParameter("restaurant"));
				List<Plat> listePlatsResto = f.getPlatsRestaurant(restoId);
				request.setAttribute("prenom", client.getPrenom());
				request.setAttribute("listePlatsResto", listePlatsResto);
				request.getRequestDispatcher("restaurant.jsp").forward(request, response);
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
		        	request.setAttribute("prenom", utilisateur.getPrenom());
		        	if (utilisateur instanceof Proprietaire) {
		        		int nbRestaux = f.getNbRestos((Proprietaire) utilisateur);
		        		int nbPlats = f.getNbPlats((Proprietaire) utilisateur);
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
				request.setAttribute("prenom", user.getPrenom());
				int nbPlats = f.getNbPlats((Proprietaire) user);
        		request.setAttribute("nbPlats",Integer.toString(nbPlats));
				request.getRequestDispatcher("homeProprietaire.jsp").forward(request, response);
				break;
			
			case "ajouterPlatRestaurant" :
				System.out.println("Servlet attaque ajoutPlatResto !!!");
				String nomPlat = request.getParameter("nom");
				String descriptionPlat = request.getParameter("description");
				String prixPlat = request.getParameter("prix");
				String restoAssocie = request.getParameter("restaurant");
				File photoPlat = (File) request.getAttribute("photo");
				Proprietaire userr = (Proprietaire) session.getAttribute("utilisateur");
				f.addPlatResto(nomPlat,descriptionPlat,prixPlat,photoPlat,restoAssocie,userr);
				int nbRestau = f.getNbRestos((Proprietaire) userr);
        		request.setAttribute("nbRestaux",Integer.toString(nbRestau));
				request.setAttribute("prenom", userr.getPrenom());
				int nbPlatss = f.getNbPlats((Proprietaire) userr);
        		request.setAttribute("nbPlats",Integer.toString(nbPlatss));
				request.getRequestDispatcher("homeProprietaire.jsp").forward(request, response);
				break;
				
			case "ajoutPanier" :
				System.out.println("Servlet attaque ajoutPanier");
				Client clientAP = (Client) session.getAttribute("utilisateur");
				Plat platAP = (Plat) request.getAttribute("plat");
				Restaurant restoAP = platAP.getResto();
				int quantite = Integer.parseInt("quantite");
				f.addPlatPanier(clientAP,restoAP,platAP,quantite);
				request.getRequestDispatcher("restaurant.html").forward(request, response);
				break;
			

		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
        
	}

}