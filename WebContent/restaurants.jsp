<%@page import="java.util.List"%>
<%@page import="pack.Proprietaire"%>
<%@page import="pack.Restaurant"%>
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
  <title>Restaurants</title>
</head>
<body>
  <nav class="navbar navbar-toggleable-sm navbar-inverse bg-inverse p-0">
    <div class="container">
      <button class="navbar-toggler navbar-toggler-right" data-toggle="collapse" data-target="#navbarNav">
        <span class="navbar-toggler-icon"></span>
      </button>
      <a href="Serv?operation=homeProprio" class="navbar-brand mr-5">FoodOn</a>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item px-2">
            <a href="Serv?operation=homeProprio" class="nav-link active">Espace Propriétaire</a>
          </li>
          <li class="nav-item px-2">
            <a href="Serv?operation=restaurants" class="nav-link">Restaurants</a>
          </li>
          <li class="nav-item px-2">
            <a href="Serv?operation=plats" class="nav-link">Plats</a>
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
          <h1><i class="fa fa-cutlery"></i> Restaurants</h1>
        </div>
      </div>
    </div>
  </header>


  <section id="actions" class="py-4 mb-4 bg-faded">
    <div class="container">
      <div class="row">
        <div class="col-md-6 offset-md-6">
          <div class="input-group">
            <input type="text" class="form-control" placeholder="Chercher des restaurants...">
            <span class="input-group-btn">
              <button class="btn btn-primary">Chercher</button>
            </span>
          </div>
        </div>
      </div>
    </div>
  </section>
  
  <section id="restaurants">
    <div class="container">
      <div class="row center">
      		
      		<%
      			List<Restaurant> lruser = (List<Restaurant>) request.getAttribute("listeResto");
      			for (Restaurant resto : lruser) {
      		%>
      				<div class="col-md-3 m-4">
	      	          <div class="card" style="width:20rem">
	      	              <img class="card-img-top" src="http://lorempixel.com/300/300/sports/" alt="Card image cap">
	      	              <div class="card-block">
	      	                  <h4 class="card-title"><%= resto.getNom() %></h4>
	      	                  <p class="card-text"><%= resto.getDescription() %> </p>
	      	                   <a href="#" class="btn btn-success btn-block">Détails</a>
	      	              </div>
	      	          </div>
      	        	</div>	
      		
      		<%
      			}
      		%>
      		
      </div>
    </div>
    
  </section>

  <footer id="main-footer" class="bg-inverse text-white mt-5 p-5">
    <div class="container">
      <div class="row">
        <div class="col">
          <p class="lead text-center">Copyright &copy; 2017 FoodOn</p>
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
  
  
  
        
