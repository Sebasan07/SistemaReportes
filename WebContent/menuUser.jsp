
<!-- Sidebar -->
<nav class="sidebar">

	<!-- close sidebar menu -->
	<div class="dismiss">
		<i class="fas fa-arrow-left"></i>
	</div>


	<h3>
		<img src="<%=request.getContextPath()%>/assets/img/Huelleritos.png">
	</h3>


	<ul class="list-unstyled menu-elements">
		<li><a href="<%=request.getContextPath()%>/Usuario">Inicio</a>
		</li>
		<li><a href="<%=request.getContextPath()%>/Usuario/reportes">Reportes y tokens</a>
		</li>
		<li><a href="<%=request.getContextPath()%>/Logout">Cerrar
				sesion</a></li>
	</ul>



	<div class="dark-light-buttons">
		<a class="btn btn-primary btn-customized-4 btn-customized-dark"
			href="#" role="button">Dark</a> <a
			class="btn btn-primary btn-customized-4 btn-customized-light"
			href="#" role="button">Light</a>
	</div>

</nav>
