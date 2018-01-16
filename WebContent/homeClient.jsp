<%@page import="java.util.List"%>
<%@page import="pack.Restaurant"%>
<%@page import="pack.Client"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="css/font-awesome.min.css">
  <link rel="stylesheet" href="css/bootstrap.css">
  <link rel="stylesheet" href="css/style.css">
  <title>Bienvenue à votre espace utilisateur</title>
  
  
   <script>
    var socketChat = null;
    var login = null;
  	
    
    function initChatSocket(userN) {
		socketChat = new WebSocket('ws://localhost:8080/test/chat/'+userN);	
		console.log("--------------: " + userN);
		login = userN;
		socketChat.onopen = function(evt) { onOpen(evt) };
		socketChat.onmessage = function(evt) { onMessage(evt) };
		socketChat.onerror = function(evt) { onError(evt) };
	}
  	
  	function closeChatSocket() {
  		if (socketChat !== null) {
  			socketChat.close();
  			socketChat = null;
  		}
  	}
  	
  	function sendMessage() {
  		socketChat.send(document.getElementById("toSend").value);
  	}
  	
  </script>
  
</head>
<%
	Client client = (Client) request.getAttribute("client");
%>
<body onload="initChatSocket('<%= client.getId()%>')" onbeforeunload="closeChatSocket()">
	<nav class="navbar navbar-toggleable-sm navbar-inverse bg-inverse p-0">
    <div class="container">
      <button class="navbar-toggler navbar-toggler-right" data-toggle="collapse" data-target="#navbarNav">
        <span class="navbar-toggler-icon"></span>
      </button>
      <a href="Serv?operation=homeClient" class="navbar-brand mr-5">FoodOn</a>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item px-2">
            <a href="Serv?Operation=homeClient" class="nav-link active">Espace Client</a>
          </li>
        </ul>

        <ul class="navbar-nav ml-auto">

          <li class="nav-item dropdown mr-3">
            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bell"></i><span class="badge badge-important">2</span>Notifications</a>
            <div class="dropdown-menu">
              <a href="Serv?operation=profil" class="dropdown-item">
                <i class="fa fa-user-circle"></i>  Profil
              </a>
              <a href="connexion.html" class="dropdown-item">
                <i class="fa fa-user-times"></i> Déconnexion
              </a>
            </div>
          </li>
			
			
		  <%
		  	String prenom = (String) request.getAttribute("prenom");
		  %>	
          <li class="nav-item dropdown mr-3">
            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> Bienvenue <%= prenom %></a>
            <div class="dropdown-menu">
              <a href="profil.html" class="dropdown-item">
                <i class="fa fa-user-circle"></i>  Profil
              </a>
              <a href="Serv?operation=deconnexion" class="dropdown-item">
                <i class="fa fa-user-times"></i> Déconnexion
              </a>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  
 <header id="main-header" class="py-2 bg-primary text-white">
    <div class="container">
      <div class="row">
        <div class="col-md-6">
          <h1><i class="fa fa-gear"></i>Espace Client</h1>
        </div>
      </div>
    </div>
 </header>


  <section id="actions" class="py-4 mb-4 bg-faded">
    <div class="container">
      <div class="row">
        <div class="col-md-3">
          <a href="#" class="btn btn-success btn-block" data-toggle="modal" data-target="#chercherRes"><i class="fa fa-plus"></i>Chercher des Restaurants</a>
        </div>
      </div>
    </div>
  </section>
  
  
  <input id="toSend" type="text" name="operation" value="SALUT">
  <a class="btn btn-success btn-block" onclick='sendMessage()'>Détails</a>
  
    <!-- POSTS -->
  <section id="posts">
    <div class="container">
      <div class="row">
        <div class="row center">
          
          <%
      			List<Restaurant> lrestos = (List<Restaurant>) request.getAttribute("listeRestaux");
          		int i = 0;
      			for (Restaurant resto : lrestos) {
      				i++;
      	  %>
      	  
          <div class="col-md-3 m-4">
            <div class="card" style="width:20rem">
                <img class="card-img-top" src="http://lorempixel.com/300/300/sports/" alt="Card image cap">
                <div class="card-block">
                    <h4 class="card-title"><%= resto.getNom() %></h4>
                    <p class="card-text"><%= resto.getDescription() %> </p>
                    <form role="form" method="get" action="Serv" id="getPlats<%=i%>">
              			<input type="hidden" name="operation" value="platsRestaurant">
              			<input type="hidden" name="restaurant" value="<%= resto.getId()%>">
              		</form>
                    <a class="btn btn-success btn-block" onclick='$("#getPlats<%=i%>").submit();'>Détails</a>
                </div>
            </div>
          </div>
          
          <%
      			}
      	  %>
          
        </div>
      </div>
    </div>
  </section>

  <footer id="main-footer" class="bg-inverse text-white mt-5 p-5">
    <div class="container">
      <div class="row">
        <div class="col">
          <p class="lead text-center">Copyright &copy; 2018 FoodOn</p>
        </div>
      </div>
    </div>
  </footer>


  <!-- ADD POST MODAL -->
  <div class="modal fade" id="chercherRes">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header bg-primary text-white">
          <h5 class="modal-title" id="ajouterRestaurantLabel">Chercher Restaurant</h5>
          <button class="close" data-dismiss="modal">
            <span>&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form>
            <div class="form-group">
              <label for="title" class="form-control-label">Nom</label>
              <input type="text" class="form-control">
            </div>
            <div class="form-group">
              <label for="category" class="form-control-label">Spécialité</label>
              <input type="text" class="form-control">
            </div>
            <div class="form-group">
              <label for="category" class="form-control-label">Ville</label>
              <input type="text" class="form-control">
            </div>

          </form>
        </div>
        <div class="modal-footer">
          <button class="btn btn-secondary" data-dismiss="modal">fermer</button>
            <button class="btn btn-primary" data-dismiss="modal">Chercher</button>
        </div>
      </div>
    </div>
  </div>

  <!-- ADD CATEGORY MODAL -->
  <div class="modal fade" id="chercherPlat">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header bg-success text-white">
          <h5 class="modal-title" id="ajouterPlatLabel">Chercher Plat</h5>
          <button class="close" data-dismiss="modal">
            <span>&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form>
            <div class="form-group">
              <label for="title" class="form-control-label">Nom</label>
              <input type="text" class="form-control">
            </div>
            <div class="form-group">
              <label for="category" class="form-control-label">Restaurant</label>
              <input type="text" class="form-control">
            </div>
            <div class="form-group">
              <label for="category" class="form-control-label">Spécialité</label>
              <input type="text" class="form-control">
            </div>
            <div class="form-group">
              <label for="category" class="form-control-label">Ville</label>
              <input type="text" class="form-control">
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button class="btn btn-secondary" data-dismiss="modal">Fermer</button>
            <button class="btn btn-success" data-dismiss="modal">chercher le plat</button>
        </div>
      </div>
    </div>
  </div>


  <script src="js/jquery.min.js"></script>
  <script src="js/tether.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="https://cdn.ckeditor.com/4.7.1/standard/ckeditor.js"></script>
  <script>
    CKEDITOR.replace('editor1');
  </script>
</body>
</html>
  
  
  