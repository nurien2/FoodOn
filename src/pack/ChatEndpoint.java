package pack;



import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;



@ServerEndpoint("/chat/{userLogin}")
public class ChatEndpoint {
	//Contiendra la liste des sessions websocket. Donc des personnes connectées.
	static Map<String,Session> peers = Collections.synchronizedMap(new HashMap<String,Session>());
	//@PathParam("userLogin") dans les parametres permet de recuperer les parametres venant de la websock
	@OnOpen
	public void onOpen(Session peer, @PathParam("userLogin") String userLogin) {
		for (String s : peers.keySet()) {
			Session p = peers.get(s);
			try {
				p.getBasicRemote().sendText("Nouveau:"+userLogin);
				peer.getBasicRemote().sendText("Nouveau MESSAGE :"+s);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
		peers.put(userLogin,peer);
		System.out.println("Chat websocket open for user "+userLogin);
	}

	@OnClose
	public void onClose(Session peer, @PathParam("userLogin") String userLogin) {
		peers.remove(userLogin);
		for (String s : peers.keySet()) {
			Session p = peers.get(s);
			try {
				p.getBasicRemote().sendText("Au revoir:" +userLogin);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Chat websocket closed for user "+userLogin);
	}

	@OnMessage
	public void message(String message, Session peer, @PathParam("userLogin") String userLogin) throws IOException {
		for (String s : peers.keySet()) {
			Session p = peers.get(s);
			p.getBasicRemote().sendText(userLogin+" : " + message);
			System.out.println("Chat enpoint send message to user "+s+" "+message+" from "+userLogin);
		}
	}
}
