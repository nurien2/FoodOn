<%@page import="java.util.List"%>
<%@page import="pack.Plat"%>
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
</head>
<body>

	<nav class="navbar navbar-toggleable-sm navbar-inverse bg-inverse p-0">
    <div class="container">
      <button class="navbar-toggler navbar-toggler-right" data-toggle="collapse" data-target="#navbarNav">
        <span class="navbar-toggler-icon"></span>
      </button>
      <a href="Serv?operation=homeClient" class="navbar-brand mr-5">FoodOn</a>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item px-2">
            <a href="Serv?operation=homeClient" class="nav-link active">Espace Client</a>
          </li>
        </ul>

        <ul class="navbar-nav ml-auto">

          <li class="nav-item dropdown mr-3">
            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bell"></i><span class="badge badge-important">2</span>Notifications</a>
            <div class="dropdown-menu">
              <a href="profil.html" class="dropdown-item">
                <i class="fa fa-user-circle"></i>  blab la
              </a>
              <a href="connexion.html" class="dropdown-item">
                <i class="fa fa-user-times"></i> blabla
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
                <i class="fa fa-user-times"></i> D�connexion
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
          <h1><i class="fa fa-cutlery"></i>Restaurant</h1>
        </div>
      </div>
    </div>
  </header>

  <!-- ACTIONS -->
  <section id="actions" class="py-4 mb-4 bg-faded">
    <div class="container">
      <div class="row">
        <div class="col-md-6">
          <a href="Serv?operation=commander" class="btn btn-success btn-block"><i class="fa fa-plus"></i>Finaliser la commande</a>
        </div>
      </div>
    </div>
  </section>

  <!-- POSTS -->
  <section id="posts">
    <div class="container">
      <div class="row">
        <div class="row center">
        
       	  <%
      			List<Plat> plats = (List<Plat>) request.getAttribute("listePlatsResto");
      			int i = 0;	
       	  		for (Plat plat : plats) {
       	  			i++;
      	  %>
        
	          <div class="col-md-3 m-4">
	            <div class="card" style="width:20rem">
	                <img class="card-img-top" src="http://lorempixel.com/300/300/sports/" alt="Card image cap">
	                <div class="card-block">
	                    <h4 class="card-title"><%= plat.getNom()%></h4>
	                    <h6>..</h6>
	                    <p class="card-text">..</p>
	                    <form role="form" method="get" action="Serv" id="getPlat<%=i%>">
	                    	  	<input type="text" name="quantite" id="quantite" placeholder="quantite">
	                    		<input type="hidden" name="operation" value="ajoutPanier">
	                    		<input type="hidden" name="plat" value= "<%= plat.getId()%>">
	                     		<a class="btn btn-success btn-block" onclick='$("#getPlat<%=i%>").submit();'>Ajouter au panier : <span><%= plat.getPrix() %>> €</span></a>
	                     </form>
	                     <a href="plat.html" class="btn btn-success btn-block">Détails</a>	                    
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



  <script src="js/jquery.min.js"></script>
  <script src="js/tether.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="https://cdn.ckeditor.com/4.7.1/standard/ckeditor.js"></script>
  <script>
    CKEDITOR.replace('editor1');
  </script>
</body>
</html>
  