<!DOCTYPE html>
<%@page import="pack.Plat"%>
<%@page import="pack.Client"%>
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

<!--           <li class="nav-item dropdown mr-3"> -->
<!--             <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bell"></i><span class="badge badge-important">2</span>Notifications</a> -->
<!--             <div class="dropdown-menu"> -->
<!--               <a href="Serv?operation=profil" class="dropdown-item"> -->
<!--                 <i class="fa fa-user-circle"></i>  Profil -->
<!--               </a> -->
<!--               <a href="connexion.html" class="dropdown-item"> -->
<!--                 <i class="fa fa-user-times"></i> Déconnexion -->
<!--               </a> -->
<!--             </div> -->
<!--           </li> -->
  <% Client client = (Client) request.getAttribute("utilisateur");
	String nomClient =client.getPrenom();
   %>
          <li class="nav-item dropdown mr-3">
            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> Bienvenue <%= nomClient %></a>
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
          <h1><i class="fa fa-spoon"></i>Plat</h1>
        </div>
      </div>
    </div>
  </header>

<%

         Plat plat =(Plat)request.getAttribute("Plat");
// 		  String nom = (String) request.getAttribute("nom");
//           String des = (String) request.getAttribute("description");
//           String prix = (String) request.getAttribute("prix");
//           List<CommentairePlat>  commentaires =(List<CommentairePlat>)request.getAttribute("commentaires");
             

		  	
		  %>	
		  <br>
<div class="container" align="center">
  <img  right" class="img-fluid p-3" src="<%=plat.getPhoto()%>" alt="Card image cap">
  <div>
<!--       <h4>Nom Plat</h4> -->
<!--       <h6>Nom restaurant</h6> -->
<!--       <p>bla bla bla haha bla blaxxx </p> -->
  </div>
</div>

<div  class="container text-center">
  <h3>Nom Plat :<%= plat.getNom() %></h3>
  
  <p  ><em> <%= plat.getDescription() %></em></p>
 
  <p>Prix:<%= plat.getPrix() %> $</p>
</div>

<div align="center">
<input  data-toggle="modal" data-target="#ajouterRestaurant" class="w3-circle w3-green w3-xlarge" type="button" value="+">
</div>
<br><br>


<br><br>
<div class="container" style="background-color: Lavender">

</div>
<br><br>

<section id="restaurants">
    <div class="container">
      <div class="row center">
      
      
      		<%
      			List<CommentairePlat> commentairesPlat = plat.getCommentaires();
      			for (CommentairePlat commentaire : commentairesPlat) {
      		%>
      		<br><br>
	       <div class="container" >
  <div class="media" style="background-color: Lavender">
    <div class="media-left">
    <img src="<%=commentaire.getClient().getImage()%>" class="media-object" style="width:70px;height: 70px;">
     </div>
    <div class="media-body">
    <h4 class="media-heading">  <%= commentaire.getClient().getNom() %>    <small ><i> Posted on   <i style="color:Beige ;"> </i>                            <%= commentaire.getDate_commentaire() %>    </i></small></h4>
    <p><%= commentaire.getTexte() %></p>
    </div>
    </div>
    <br><br>
    </div>
	        <br><br>
	        <%
      			}
      		%>
      		
      </div>
       <br><br>
    </div>
  </section>


















  
 <!-- ADD POST MODAL -->
  <div class="modal fade" id="ajouterRestaurant">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header bg-primary text-white">
          <h5 class="modal-title" id="ajouterRestaurantLabel">Commenter Le Plat</h5>
          <button class="close" data-dismiss="modal">
            <span>&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form method="post" action="Serv?operation=commenterPlat&idplat=<%= plat.getId() %>" id="myForm">
            <div class="form-group">
              <label for="nom" class="form-control-label">commentaire</label>
              <input style ="height: 100px;" type="text" name="text" class="form-control">
            </div>
           
            
            
           <!--  <input type="hidden" name="operation" value="ajouterRestaurant"> --> 
          </form>
        </div>
        
        <div class="modal-footer">
          <button class="btn btn-secondary" data-dismiss="modal">fermer</button>
          <button type="submit" class="btn btn-primary" data-dismiss="modal" onclick='$("#myForm").submit();'>Commenter</button>
        </div>
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
  <script type="text/javascript" script-name="allura" src="http://use.edgefonts.net/allura.js"></script>
</body>
</html>
