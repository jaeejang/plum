<#include "base.tpl" />
<#include "navigation.tpl" />
<#include "page_header.tpl" />
<#include "page_footer.tpl" />
<#macro page_base>
	<div id="wrapper">
		<@nav />
		<@page_bar />
		<#nested>
		<@page_footer />
	</div>
</#macro>
<#macro page title="" body="" keywords="" js=[] css=[]>
	<@base base_title=title
	base_body=""
	base_keywords=""
	base_js=["inspinia.js",
		"plugins/metisMenu/jquery.metisMenu.js",
		"plugins/pace/pace.min.js",
		"plugins/slimscroll/jquery.slimscroll.min.js"] + js
	base_css=[]+css
	notification=true>
		<@page_base>
			<#nested>
		</@page_base>
	</@base>
</#macro>