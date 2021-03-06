<%@ page pageEncoding="UTF-8" %>
<%@page import="pack.Restaurant"%>
<%@page import="pack.Proprietaire, pack.Commande, java.util.List, java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>

  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="css/font-awesome.min.css">
  <link rel="stylesheet" href="css/bootstrap.css">
  <link rel="stylesheet" href="css/style.css">
  <title>Bienvenue sur votre espace utilisateur</title>
  
  <script type="text/javascript">
  
  function detailsCommande(){
	  $get(
		'Serv?operation=detailsCommande',
		{
			idCommande:formCommande["id"]
		},
		function(data,status){
			
		}	  	  
	  );
		  
	  return false;	  
  }
  
  </script>
  
  
</head>
<%
	Proprietaire prop = (Proprietaire) request.getAttribute("proprio");
%>
<body>

	<nav class="navbar navbar-toggleable-sm navbar-inverse bg-inverse p-0">
    <div class="container">
      <button class="navbar-toggler navbar-toggler-right" data-toggle="collapse" data-target="#navbarNav">
        <span class="navbar-toggler-icon"></span>
      </button>
      <a href="Serv?Operation=homeProprio" class="navbar-brand mr-5">FoodOn</a>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item px-2">
            <a href="Serv?Operation=homeProprio" class="nav-link active">Espace Propriétaire</a>
          </li>
          <li class="nav-item px-2">
            <a href="Serv?operation=restaurants" class="nav-link">Restaurants</a>
          </li>
          <li class="nav-item px-2">
            <a href="Serv?operation=plats" class="nav-link">Plats</a>
          </li>
        </ul>

        <ul class="navbar-nav ml-auto">

			
			
		  <%
		  	Proprietaire proprio = (Proprietaire) request.getAttribute("proprio");
		  	int nbRestaux = Integer.parseInt((String) request.getAttribute("nbRestaux"));
		  	int nbPlats = Integer.parseInt((String) request.getAttribute("nbPlats"));
		  %>	
          <li class="nav-item dropdown mr-3">
            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> Bienvenue <%=proprio.getPrenom() %></a>
            <div class="dropdown-menu">
              <a href="Serv?operation=profil" class="dropdown-item">
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
          <h1><i class="fa fa-gear"></i> Espace Propriétaire</h1>
        </div>
      </div>
    </div>
  </header>

  <!-- ACTIONS -->
  <section id="actions" class="py-4 mb-4 bg-faded">
    <div class="container">
      <div class="row">
        <div class="col-md-3">
          <a href="#" class="btn btn-primary btn-block" data-toggle="modal" data-target="#ajouterRestaurant"><i class="fa fa-plus"></i> Ajouter Restaurant</a>
        </div>
        <div class="col-md-3">
          <a href="#" class="btn btn-success btn-block" data-toggle="modal" data-target="#ajouterPlat"><i class="fa fa-plus"></i> Ajouter Plat</a>
        </div>
      </div>
    </div>
  </section>
  
  <section id="posts">
    <div class="container">
      <div class="row">
        <div class="col-md-9">
          <div class="card">
            <div class="card-header">
            	<div id="MSG"></div>
                <h4>Commandes en cours</h4>
              </div>
              <table class="table table-striped">
                <thead class="thead-inverse">
                  <tr>
                    <th>#</th>
                    <th>Restaurant</th>
                    <th>Pseudo client</th>
                    <th>Date</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
            	<%
      			List<Commande> lcommandes = (List<Commande>) request.getAttribute("listeCommandes");
          		
                int i = 0;
      			for (Commande com : lcommandes) {
      				i++; 				
      			
      			  %>
      			 
      			  	<tr>
      			  	<form role="form" method="get" name="formDetailsCommande<%= com.getId() %>" action="Serv" onsubmit="return detailsCommande(this)">
                    <input type="hidden" name="iDcommande" value="<%= com.getId() %>"> 
                    <input type="hidden" name="operation" value="detailsCommande">
                    <td name="id" scope="row"><%= com.getId() %></td>
                    <td><%= com.getRestaurant().getNom() %></td>
                    <td><%= com.getClient().getPseudo() %> </td>
                    <td><%= com.getDateCommande() %></td>
                    <td><a onclick='$("getCommande<%=i %>").submit();' class="btn btn-secondary"><i class="fa fa-angle-double-right"></i> Détails</a></td>
                  	 </form>
                  	</tr>
                  <% } %>
                </tbody>
              </table>
            </div>             


        </div>
        <div class="col-md-3">
          <div class="card text-center card-primary text-white mb-3">
            <div class="card-block">
              <h3>Restaurants</h3>
              <h1 class="display-4"><i class="fa fa-cutlery"></i> <%= nbRestaux %></h1>
              <form role="form" method="get" action="Serv" id="getRestaux">
              	<input type="hidden" name="operation" value="restaurants">
              </form>
              <a class="btn btn-sm btn-outline-secondary text-white" onclick='$("#getRestaux").submit();'>Voir</a>
            </div>
          </div>

          <div class="card text-center card-success text-white mb-3">
            <div class="card-block">
              <h3>Plats</h3>
              <h1 class="display-4"><i class="fa fa-spoon"></i> <%=nbPlats %></h1>
              <form role="form" method="get" action="Serv" id="getPlats">
              	<input type="hidden" name="operation" value="plats">
              </form>
              <a  class="btn btn-sm btn-outline-secondary text-white" onclick='$("#getPlats").submit();'>Voir</a>
            </div>
          </div>

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
  <div class="modal fade" id="ajouterRestaurant">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header bg-primary text-white">
          <h5 class="modal-title" id="ajouterRestaurantLabel">Ajouter Restaurant</h5>
          <button class="close" data-dismiss="modal">
            <span>&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form method="post" action="Serv" id="myForm" enctype="multipart/form-data">
            <div class="form-group">
              <label for="nom" class="form-control-label">Nom</label>
              <input type="text" name="nom" class="form-control">
            </div>
            <div class="form-group">
              <label for="adresse" class="form-control-label">Adresse</label>
              <input type="text" name="adresse" class="form-control">
            </div>
            <div class="form-group">
              <label for="specialite" class="form-control-label">Spécialité</label>
              <input type="text" name="specialite" class="form-control">
            </div>
            <div class="form-group bg-faded p-3">
              <label for="file">Image</label>
              <input type="file" name="photo" class="form-control-file" id="file">
              <small id="fileHelp" class="form-text text-muted">
                taille max 2MB
              </small>
            </div>
            <div class="form-group">
              <label for="body">Description</label>
              <textarea name="description" class="form-control"></textarea>
            </div>
            <input type="hidden" name="operation" value="ajouterRestaurant">
          </form>
        </div>
        
        <div class="modal-footer">
          <button class="btn btn-secondary" data-dismiss="modal">fermer</button>
          <button type="submit" class="btn btn-primary" data-dismiss="modal" onclick='$("#myForm").submit();'>Ajouter le restaurant</button>
        </div>
      </div>
    </div>
  </div>

  <!-- ADD CATEGORY MODAL -->
  <div class="modal fade" id="ajouterPlat">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header bg-success text-white">
          <h5 class="modal-title" id="ajouterPlatLabel">Ajouter Plat</h5>
          <button class="close" data-dismiss="modal">
            <span>&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form method="post" action="Serv" id="myForm1" enctype="multipart/form-data">
            <div class="form-group">
              <label for="title" class="form-control-label">Nom</label>
              <input type="text" name="nom" class="form-control">
            </div>
            <div class="form-group">
              <label for="prix" class="form-control-label">Prix</label>
              <input type="text" name="prix" class="form-control">
            </div>
            <div class="form-group">
              <label for="restaurant" class="form-control-label">Restaurant</label>
              <select class="form-control" name="restaurant">
              <%
              	List<Restaurant> restos = (List<Restaurant>) request.getAttribute("restos");
              	for (Restaurant resto : restos) {
              %>
              <option value="<%=resto.getId() %>"><%=resto.getNom()%></option>
              <%
              	}
              %>
              </select>
            </div>
            <div class="form-group bg-faded p-3">
              <label for="file">Image</label>
              <input type="file" name="photo" class="form-control-file" id="file">
              <small id="fileHelp" class="form-text text-muted">
                taille max 2MB
              </small>
            </div>
            <div class="form-group">
              <label for="body">Description</label>
              <textarea name="description" class="form-control"></textarea>
            </div>
            <input type="hidden" name="operation" value="ajouterPlatRestaurant">
          </form>
        </div>
        <div class="modal-footer">
          <button class="btn btn-secondary" data-dismiss="modal">fermer</button>
          <button type="submit" class="btn btn-primary" data-dismiss="modal" onclick='$("#myForm1").submit();'>Ajouter le plat</button>
        </div>
        </div>
      </div>
    </div>
  

  <script>
  
  </script>

  <script src="js/jquery.min.js"></script>
  <script src="js/tether.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="https://cdn.ckeditor.com/4.7.1/standard/ckeditor.js"></script>
  <script>
    CKEDITOR.replace('editor1');
  </script>
</body>
</html>
  