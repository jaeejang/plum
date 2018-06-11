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
<#macro location >
<div class="row wrapper border-bottom white-bg page-heading">
	<div class="col-lg-10">
		<h3>${function!}</h3>
		<ol class="breadcrumb">
			<@bpTree node=menu! />
		</ol>
	</div>
</div>
</#macro>		
