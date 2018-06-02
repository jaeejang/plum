<#--递归目录-->
<#macro bpTree node>
	<#if node??>
		<#if node.active!false>
			<li><a href="<#if node.path??>${base}${node.path!}<#else>#</#if>">${node.funcname}</a></li>
		</#if>
		<#if node.children?? && node.children?size gt 0>
			<#list node.children as child>
				<@bpTree node=child />
			</#list>
		</#if>
	</#if>
</#macro>
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
						to 江南银行创新平台</span></li>
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
	<div class="row wrapper border-bottom white-bg page-heading">
		<div class="col-lg-10">
			<h3>${function!}</h3>
			<ol class="breadcrumb">
				<@bpTree node=menu />				
			</ol>
		</div>
	</div>
</#macro>