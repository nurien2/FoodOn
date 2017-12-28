package pack;

import java.util.ArrayList;

public class ValidationUser {

	public static boolean verifierUser(Facade f, String email, String mdp) {
		
		
		ArrayList<Client> users = f.getListeUsers();
		
		for (Client user : users) {
			if (user.getAdresseMail().equals(email)) {
				if (user.getMdp().equals(mdp)) {
					return true;
				}
			}
		}
		
		
		return false;
		
	}
	
}
