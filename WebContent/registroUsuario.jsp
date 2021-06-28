<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Nuestra tienda virtual</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/registro.css">
</head>
<body>
	<div class="container-fluid">
		<div class="row no-gutter">
			<div class="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div>
			<div class="col-md-8 col-lg-6">
				<div class="login d-flex align-items-center py-5">
					<div class="container">
						<div class="row  justify-content-center pb-5">

							<div class="col-xs-12 col-sm-6 col-md-3">
								<a href="<%=request.getContextPath()%>/Login"
									class="btn btn-lg btn-primary btn-block text-uppercase font-weight-bold mb-2 text-light"
									type="submit">Login</a>
							</div>

							<div class="col-xs-12 col-sm-6 col-md-3 align-self-center">
								<a href="<%=request.getContextPath()%>/Tiendas"
									class="btn btn-lg btn-primary btn-block text-uppercase font-weight-bold mb-2 text-light"
									type="submit">Usuarios</a>
							</div>
						</div>
						<div class="row">
							<div class="col-md-9 col-lg-8 mx-auto">
								<h3 class="login-heading mb-4">Registro de Usuario</h3>
								<form
									action="<%=request.getContextPath()%>/Tiendas/Registro/Enviar"
									method="post">

									<input type="hidden" name="validacion" value="valido">
									<%
										if (request.getAttribute("mensajeError") != null) {
									%>
									<p style="color: red;"><%=request.getAttribute("mensajeError")%></p>
									<%
										} else if (request.getAttribute("mensajeExito") != null) {
									%>
									<p style="color: green;"><%=request.getAttribute("mensajeExito")%></p>
									<%
										}
									%>
									<div class="form-label-group">
										<input type="text" id="inputUsuario" class="form-control"
											placeholder="Usuario" name="usuario" required autofocus>
										<label for="inputUsuario">Usuario</label>
									</div>
									<div class="form-label-group">
										<input type="email" id="inputEmail" class="form-control"
											placeholder="Email address" name="email" required autofocus>
										<label for="inputEmail">Email</label>
									</div>

									<div class="form-label-group">
										<input type="password" id="inputPassword" class="form-control"
											placeholder="Password" name="pass" required> <label
											for="inputPassword">Password</label>
									</div>
									<div class="form-label-group">
										<select name="rol">
											<option value="1" >Administrador</option>	
											<option value="2">Usuario</option>		
										</select>
									</div>


									<button
										class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2"
										type="submit">Registrar</button>

								</form>


							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
		integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
		crossorigin="anonymous"></script>
</body>
</html>