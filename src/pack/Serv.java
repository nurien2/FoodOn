package pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		
		String operation = request.getParameter("operation");
		PrintWriter out = response.getWriter();
		switch (operation) {
			case "connexion":
				
		        
		        String email = request.getParameter("email");
		        String pass = request.getParameter("pass");
		        
		        if(ValidationUser.verifierUser(f, email, pass)) {
		            RequestDispatcher rs = request.getRequestDispatcher("Bienvenue");
		            rs.forward(request, response);
		        } else {
		           out.println("Adresse ou mdp incorrects");
		           RequestDispatcher rs = request.getRequestDispatcher("index.html");
		           rs.include(request, response);
		        }
		        break;
		        
			case "inscription":
				
				String nom = request.getParameter("nom");
				String prenom = request.getParameter("prenom");
				String mdp = request.getParameter("mdp");
				String adresse = request.getParameter("adresse");
				String adresseMail = request.getParameter("email");
				Boolean isClient = true;
				List<String> gouts = new ArrayList<String>();
				if (request.getParameter("isClient").equals("false")) {
					isClient = false;
				}
				f.addClient(prenom, nom, mdp, adresseMail, adresse, gouts, isClient);
				request.getRequestDispatcher("connexion.html").forward(request, response);
				break;
		}
        
	}

}
