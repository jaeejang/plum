<#macro page_bar>
<div id="page-wrapper" class="gray-bg">
	<div class="row border-bottom">
		<nav class="navbar navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<a class="navbar-minimalize minimalize-styl-2 btn btn-primary"><i class="fa fa-bars"></i> </a>
			</div>
			<ul class="nav navbar-top-links navbar-right">
				<li><span class="m-r-sm text-muted welcome-message">Welcome
						${_user.fullname} （${_user.brchna}） </span></li>
				<#--li><a class=" count-info"> <i
						class="fa fa-envelope"></i> <span class="label label-warning">16</span>
				</a></li>
				<li><a class="count-info"> <i
						class="fa fa-bell"></i> <span class="label label-primary">8</span>
				</a></li-->
				<li><a href="${base}/logout"> <i class="fa fa-sign-out"></i>
					退出
				</a></li>
			</ul>
		</nav>
	</div>
</#macro>