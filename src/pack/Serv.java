package pack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SEEEEEEEEEEEEEEEEEEEEEEEERV");
		response.setContentType("text/html;charset=UTF-8");
		
		String operation = request.getParameter("operation");
		PrintWriter out = response.getWriter();
		switch (operation) {
			case "connexion":
				System.out.println("Servlet attaquee connexion !!");
		        
		        String email = request.getParameter("email");
		        String pass = request.getParameter("pass");
		        
		        if(ValidationUser.verifierUser(f, email, pass)) {
		            RequestDispatcher rs = request.getRequestDispatcher("Bienvenue");
		            rs.forward(request, response);
		        } else {
		           out.println("Adresse ou mdp incorrects");
		           RequestDispatcher rs = request.getRequestDispatcher("connexion.html");
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
				request.getRequestDispatcher("connexion.html").forward(request, response);
				break;
		}
        
	}

}
