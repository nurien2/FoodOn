package pack;

import java.util.ArrayList;

public class ValidationUser {

	public static Client verifierUser(Facade f, String email, String mdp) {
		

		ArrayList<Client> users = f.getListeUsers();
		
		for (Client user : users) {
			if (user.getAdresseMail().equals(email) && user.getMdp().equals(mdp)) {
				return user;
			}
		}
		return null;
	}
	
}
