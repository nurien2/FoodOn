<!DOCTYPE html>
<%@page import="pack.CommentairePlat"%>
<%@page import="java.util.List"%>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="css/font-awesome.min.css">
  <link rel="stylesheet" href="css/bootstrap.css">
  <link rel="stylesheet" href="css/style.css">
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
  <title>Bienvenue à votre espace utilisateur</title>
  
</head>
<body>







  <nav class="navbar navbar-toggleable-sm navbar-inverse bg-inverse p-0">
    <div class="container">
      <button class="navbar-toggler navbar-toggler-right" data-toggle="collapse" data-target="#navbarNav">
        <span class="navbar-toggler-icon"></span>
      </button>
      <a href="homeProprietaire.html" class="navbar-brand mr-5">FoodOn</a>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item px-2">
            <a href="homeClient.html" class="nav-link active">Espace Client</a>
          </li>
        </ul>

        <ul class="navbar-nav ml-auto">

          <li class="nav-item dropdown mr-3">
            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bell"></i><span class="badge badge-important">2</span>Notifications</a>
            <div class="dropdown-menu">
              <a href="profil.html" class="dropdown-item">
                <i class="fa fa-user-circle"></i> blabla
              </a>
              <a href="connexion.html" class="dropdown-item">
                <i class="fa fa-user-times"></i> blabla
              </a>
            </div>
          </li>

          <li class="nav-item dropdown mr-3">
            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> Bienvenue Youssef</a>
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
          <h1><i class="fa fa-spoon"></i>Plat</h1>
        </div>
      </div>
    </div>
  </header>

<%
		  String nom = (String) request.getAttribute("nom");
          String des = (String) request.getAttribute("description");
          String prix = (String) request.getAttribute("prix");
          List<CommentairePlat>  commentaires =(List<CommentairePlat>)request.getAttribute("commentaires");
             

		  	
		  %>	
		  <br>
<div class="container" align="center">
  <img  right" class="img-fluid p-3" src="http://lorempixel.com/300/300/sports/" alt="Card image cap">
  <div>
<!--       <h4>Nom Plat</h4> -->
<!--       <h6>Nom restaurant</h6> -->
<!--       <p>bla bla bla haha bla blaxxx </p> -->
  </div>
</div>

<div  class="container text-center">
  <h3>Nom Plat</h3>
  
  <p><em>this plat is very very gooddzdazdazdadadazddfam zad zadaz dazd z</em></p>
 
  <p>Prix</p>
</div>

<div align="center">
<input   class="w3-circle w3-green w3-xlarge" type="button" value="+">
</div>
<br><br>
<div class="container" style="background-color: Beige">
<div class="media">
  <div class="media-left">
    <img src="http://lorempixel.com/400/200/people/" class="media-object" style="width:70px;height: 70px;">
  </div>
  <div class="media-body">
    <h4 class="media-heading">John Doe</h4>
    <p>Lorem ipsum...</p>
  </div>
</div>
</div>

<br><br>
<div class="container" style="background-color: Beige">
<div class="media" >
  <div class="media-left">
    <img src="http://lorempixel.com/400/200/people/" class="media-object" style="width:70px;height: 70px;">
  </div>
  <div class="media-body">
    <h4 class="media-heading">John Doe<small><i>   Posted on February 19, 2016</i></small></h4>
    <p>it is very very good plate</p>
  </div>
</div>
</div>

  









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
