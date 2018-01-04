<%@page import="pack.Notification"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    
 <%
		  	
		  	List<Notification> notifs = (List<Notification>) request.getAttribute("notifs");
		  	int nbNotifs = notifs.size();
%>   
    
<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bell"></i><span class="badge badge-important"><%=nbNotifs%></span>Notifications</a>
            <div class="dropdown-menu">
              
              <%
              	for(Notification notif : notifs) {
              %>
	              <a href="profil.html" class="dropdown-item">
	                <i class="fa fa-user-circle"></i> <%=notif.getText_notif() %>
	              </a>
              <%
              	}
              %>
            </div>