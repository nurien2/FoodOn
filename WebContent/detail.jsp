<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="pack.Restaurant, pack.Plat"%>
<%@page import="pack.Proprietaire, pack.Commande, java.util.List, java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="css/font-awesome.min.css">
  <link rel="stylesheet" href="css/bootstrap.css">
  <link rel="stylesheet" href="css/style.css">
  <title>Bienvenue sur votre espace utilisateur</title>
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

          <li class="nav-item dropdown mr-3">
            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bell"></i><span class="badge badge-important">2</span>Notifications</a>
            <div class="dropdown-menu">
              <a href="Serv?operation=profil" class="dropdown-item">
                <i class="fa fa-user-circle"></i>  notif
              </a>
              <a href="Serv?operation=deconnexion" class="dropdown-item">
                <i class="fa fa-user-times"></i> notif
              </a>
            </div>
          </li>
			
	<% Proprietaire proprio = (Proprietaire) request.getAttribute("proprio"); %>			
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
          <h1>Détails Commande</h1>
        </div>
      </div>
    </div>
  </header>


  <section id="actions" class="py-4 mb-4 bg-faded">
    <div class="container">
      <div class="row">
        <div class="col-md-3 mr-auto">
          <a href="Serv?Operation=homeProprio" class="btn btn-secondary btn-block"><i class="fa fa-arrow-left"></i> Retour à l'espace</a>
        </div>

        <div class="col-md-3">
          <a href="Serv?Operation=homeProprio" class="btn btn-success btn-block"><i class="fa fa-check"></i> Confirmer la Commande</a>
        </div>
        <div class="col-md-3">
          <a href="Serv?Operation=homeProprio" class="btn btn-danger btn-block"><i class="fa fa-remove"></i> Refuser la Commande</a>
        </div>
      </div>
    </div>
  </section>
<%
	Commande commande = (Commande) request.getAttribute("commande");
%>

  <section id="users">
    <div class="container">
      <div class="row">
        <div class="col">
          <div class="card">
            <table class="table table-striped">
              <thead class="thead-inverse">
                <tr>
                  <th>#</th>
                  <th>Nom commandeur</th>
                  <th>Email commandeur</th>
                  <th>Restaurant</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td scope="row">1</td>
                  <td><%=commande.getClient().getPseudo()%></td>
                  <td><%=commande.getClient().getAdresseMail() %></td>
                  <td><%=commande.getRestaurant().getNom() %></td>
                </tr>

              </tbody>
            </table>


          </div>
        </div>
      </div>
    </div>
  </section>
  
  <section id="hashmap">
    <div class="container">
      <div class="row">
        <div class="col">
          <div class="card">
            <table class="table table-striped">
              <thead class="thead-inverse">
                <tr>
                  <th>#</th>
                  <th>Plat</th>
                  <th>Quantité</th>
                </tr>
              </thead>
              <tbody>
<%
HashMap<Plat,Integer> contenu = (HashMap<Plat,Integer>) request.getAttribute("contenu");
for (Plat p : contenu.keySet()) {
	String nom = p.getNom();
	int q = contenu.get(p);
	%>                

                <tr>
                  <td scope="row">1</td>
                  <td><%=nom%></td>
                  <td><%=q %></td>
                </tr>
<%} %>
              </tbody>
            </table>


          </div>
        </div>
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