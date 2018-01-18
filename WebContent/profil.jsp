<%@page import="pack.Proprietaire"%>
<%@page import="pack.Client"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="css/font-awesome.min.css">
  <link rel="stylesheet" href="css/bootstrap.css">
  <link rel="stylesheet" href="css/style.css">
  <title>Bienvenue à votre profil utilisateur</title>
</head>
<body>
  <nav class="navbar navbar-toggleable-sm navbar-inverse bg-inverse p-0">
    <div class="container">
      <button class="navbar-toggler navbar-toggler-right" data-toggle="collapse" data-target="#navbarNav">
        <span class="navbar-toggler-icon"></span>
      </button>
      <%
      Client client = (Client) request.getAttribute("clientP");
      if (client instanceof Proprietaire) {
      %>
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
      <% } else  {%>
      <a href="Serv?operation=homeClient" class="navbar-brand mr-5">FoodOn</a>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item px-2">
            <a href="Serv?operation=homeClient" class="nav-link active">Espace Client</a>
          </li>
        </ul>
        <% } %>

        <ul class="navbar-nav ml-auto">
          <li class="nav-item dropdown mr-3">
            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> Bienvenue <%=client.getPrenom() %></a>
            <div class="dropdown-menu">
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
          <h1><i class="fa fa-user"></i> Profil</h1>
        </div>
      </div>
    </div>
  </header>


  <section id="actions" class="py-4 mb-4 bg-faded">
    <div class="container">
      <div class="row">
        <div class="col-md-3 mr-auto">
          <%if (client instanceof Proprietaire) { %>
          <a href="Serv?operation=homeProprio" class="btn btn-secondary btn-block"><i class="fa fa-arrow-left"></i> Retour à l'espace utilisateur</a>
          <%} else { %>
          <a href="Serv?operation=homeClient" class="btn btn-secondary btn-block"><i class="fa fa-arrow-left"></i> Retour à l'espace utilisateur</a>
          <% } %>
        </div>

		<div class="col-md-3">
          <a class="btn btn-primary btn-block" onclick='$("#modif").submit();'> <i class="fa fa-lock"></i> valider les modifications</a>
        </div>
        <div class="col-md-3">
          <a href="index.html" class="btn btn-success btn-block" data-toggle="modal" data-target="#mdpModal"><i class="fa fa-lock"></i> changer le mot de passe</a>
        </div>
        <div class="col-md-3">
          <a href="index.html" class="btn btn-danger btn-block"><i class="fa fa-remove"></i> Supprimer le compte</a>
        </div>
      </div>
    </div>
  </section>


  <section id="edit-post">
    <div class="container">
      <div class="row">
        <div class="col-md-9">
          <div class="card">
            <div class="card-header">
              <h4>Modifier le profil</h4>
            </div>
            <div class="card-block">
              <form type="form" action="Serv" method="post" id="modif">
                <div class="form-group">
                  <label for="name" class="form-control-label">Nom</label>
                  <input type="text" name="nom" class="form-control" value="<%=client.getNom()%>">
                </div>
                <div class="form-group">
                  <label for="name" class="form-control-label">Prénom</label>
                  <input type="text" name="prenom" class="form-control" value="<%=client.getPrenom()%>">
                </div>
                <div class="form-group">
                  <label for="name" class="form-control-label">Pseudo</label>
                  <input type="text" name="pseudo" class="form-control" value="<%=client.getPseudo()%>">
                </div>
                <div class="form-group">
                  <label for="email" class="form-control-label">Email</label>
                  <input type="text" name="email" class="form-control" value="<%=client.getAdresseMail()%>">
                </div>
				<div class="form-group">
                  <label for="adresse" class="form-control-label">Adresse</label>
                  <input type="text" name="adresse" class="form-control" value="<%=client.getAdresse()%>">
                </div>
                <div class="form-group">
                  <label for="body">Bio</label>
                  <textarea name="editor1" class="form-control">bla bla bla!</textarea>
                </div>
                <input type="hidden" name="operation" value="validerModifs">
              </form>
            </div>
          </div>
        </div>
        <div class="col-md-3">
          <h3>Ton Avatar</h3>
          <img  src="images/<%=client.getImage() %>" alt="" class="d-block img-fluid mb-3">
          <button class="btn btn-primary btn-block" data-toggle="modal" data-target="#myModal1">modifier l'Image</button>
          <button class="btn btn-danger btn-block" data-toggle="modal" data-target="#myModal2">supprimer l'Image</button>
        
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

  <!-- PASSWORD MODAL -->
  <div class="modal fade" id="mdpModal">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header bg-warning text-white">
          <h5 class="modal-title" id="passwordModalLabel">Modifier le mdp</h5>
          <button class="close" data-dismiss="modal">
            <span>&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form type="form" action="Serv" method="post" id="myForm">
            <div class="form-group">
              <label for="password" class="form-control-label">Ancien Mot de passe</label>
              <input type="password" name="oldmdp" class="form-control">
            </div>
            <div class="form-group">
              <label for="password2" class="form-control-label">Nouveau mot de passe</label>
              <input type="password" name="newmdp" class="form-control">
            </div>
          	<input type="hidden" name="operation" value="modifMDP">
          </form>
        </div>
        
        <div class="modal-footer">
          <button class="btn btn-secondary" data-dismiss="modal">Fermer</button>
          <button type="submit" class="btn btn-warning" data-dismiss="modal" onclick='$("#myForm").submit();'>Modifier le mot de passe</button>
        </div>
      </div>
    </div>
  </div>






<div class="modal fade" id="myModal1" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
       <div class="modal-content">
        <div class="modal-header bg-warning text-white">
          <h5 class="modal-title" id="passwordModalLabel">Modifier ton image </h5>
          <button class="close" data-dismiss="modal">
            <span>&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form type="form" action="Serv" method="post" id="myForm1" enctype="multipart/form-data">
           
           <input type="file" name="photo" class="form-control-file" id="file">
              <small id="fileHelp" class="form-text text-muted">
                taille max 2MB
              </small>
              <small>veuillez valider les modifications!! </small>
          	<input type="hidden" name="operation" value="modifierImage">
          </form>
        </div>
        
        <div class="modal-footer">
          <button class="btn btn-secondary" data-dismiss="modal">annuler</button>
          <button type="submit" class="btn btn-warning" data-dismiss="modal" onclick='$("#myForm1").submit();'>ajouter l'image </button>
        </div>
      </div>
      
    </div>
  </div>
  




<div class="modal fade" id="myModal2" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
       <div class="modal-content">
        <div class="modal-header bg-warning text-white">
          <h5 class="modal-title" id="passwordModalLabel">Supprimer ton image </h5>
          <button class="close" data-dismiss="modal">
            <span>&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form type="form" action="Serv" method="post" id="myForm12" >
           
           <h4>vous allez  supprimer votre photo de profil!!</h4>
          	<input type="hidden" name="operation" value="supprimerImage">
          </form>
        </div>
        
        <div class="modal-footer">
          <button class="btn btn-secondary" data-dismiss="modal">annuler</button>
          <button type="submit" class="btn btn-warning" data-dismiss="modal" onclick='$("#myForm12").submit();'>Supprimer l'image </button>
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
