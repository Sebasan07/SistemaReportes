<%@page import="co.edu.ufps.sistema.entities.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

    <head>

		<!-- Required meta tags -->
        <meta charset="ISO-8859-1">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        
        <title>Lista de Solicitudes</title>

        <!-- CSS -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500&display=swap">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/jquery.mCustomScrollbar.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/animate.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/style.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/media-queries.css">

        <!-- Favicon and touch icons -->
  <link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/img/logocontacto.png">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<%=request.getContextPath()%>/assets/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<%=request.getContextPath()%>/assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<%=request.getContextPath()%>/assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="<%=request.getContextPath()%>/assets/ico/apple-touch-icon-57-precomposed.png">

    </head>

    <body>
    	<div>
			<!--Navbar-->
			<nav class="navbar navbar-dark  bg-dark">
			  <a class="form-inline my-2 my-lg-0 ml-auto">
			  	Usuario
			  </a>
			</nav>
			<!--/.Navbar-->
    	</div>    	

		<!-- Wrapper -->
    	<div class="wrapper">

			<!-- Sidebar -->
			<jsp:include page="menuAdmin.jsp" />
			<!-- End sidebar -->
			
			<!-- Dark overlay -->
    		<div class="overlay"></div>

			<!-- Content -->
			<div class="content mt-5">
					
					<!-- open sidebar menu -->
					<a class="btn btn-primary btn-customized open-menu" href="#" role="button">
	                    <i class="fas fa-align-left"></i> <span>Menu</span>
	                </a>
				<div class="container">
					<div class="col-md-4 m-2">
						<h1>Lista</h1>
						<a href="agregar" type="button" class="btn btn-dark">Agregar</a>
												
					</div>
					<div class="col-md-5 m-2 input-group">
						
						<label >Buscar:</label>
						<input type="text" align="right" class="form-control pull-right" style="width:30%" id="search" placeholder="Buscar:">

					</div>
				<%Connectiontoken token = (Connectiontoken)request.getAttribute("tokens");
				
				%>
					<div class="col-xs-12 col-md-3">
 
</div>
					<div class="row justify-content-center justify-content-md-start">
					<table class="table table-bordered" id="mytable">
						<thead class="table-dark">
							<tr role="row">
								<th scope="col">Nombre</th>
								<th scope="col">Descripci?n</th>
								<th scope="col">Fecha</th>
								<th scope="col">File</th>

								<th scope="col" colspan="2">Acciones</th>
							</tr>
						</thead>
						<tbody >
					
					
							 <tr>
								<th scope="row"><%=token.getToken() %></th>
								<td><%=token.getDb() %></td>
								<td><%=token.getHost()%></td>
								<td><%=token.getState()%></td>
								<td><button type="button" onclick="location.href='reportes'" class="btn btn-dark">Ver Reportes</button></td>
								
							</tr> 

						</tbody>
					</table>
				</div>
				</div>
			        	
			        <!-- Footer -->
			        <footer class="footer-container">
			
				        <div class="container">
				        	<div class="row">
			
			                    <div class="col">
			                    	&copy; 2021 - Todos los derechos reservados
			                    </div>
			
			                </div>
				        </div>
			
			        </footer>
		        
	        </div>
	        <!-- End content -->
        
        </div>
        <!-- End wrapper -->

        <!-- Javascript -->
       
		<script src="<%=request.getContextPath()%>/assets/js/jquery-3.3.1.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/jquery-migrate-3.0.0.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="<%=request.getContextPath()%>/assets/js/jquery.backstretch.min.js"></script>
        <script src="<%=request.getContextPath()%>/assets/js/wow.min.js"></script>
        <script src="<%=request.getContextPath()%>/assets/js/jquery.waypoints.min.js"></script>
        <script src="<%=request.getContextPath()%>/assets/js/jquery.mCustomScrollbar.concat.min.js"></script>
        <script src="<%=request.getContextPath()%>/assets/js/scripts.js"></script>
       <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
      
	<script>
 // Write on keyup event of keyword input element
 $(document).ready(function(){
 $("#search").keyup(function(){
 _this = this;
 // Show only matching TR, hide rest of them
 $.each($("#mytable tbody tr"), function() {
 if($(this).text().toLowerCase().indexOf($(_this).val().toLowerCase()) === -1)
 $(this).hide();
 else
 $(this).show();
 });
 });
});
</script>
    </body>

</html>