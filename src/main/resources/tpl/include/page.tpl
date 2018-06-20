<#include "base.tpl" />
<#include "navigation.tpl" />
<#include "page_header.tpl" />
<#include "location.tpl" />
<#include "page_footer.tpl" />
<#macro page title="" layout="" keywords="" js=[] css=[]>
	<@basePage base_title=title
	base_body=layout
	base_keywords=""
	base_js=[
		"plugins/metisMenu/jquery.metisMenu.js",
		"plugins/slimscroll/jquery.slimscroll.min.js",
		"inspinia.js",
		"plugins/pace/pace.min.js"] + js
	base_css=[]+css
	notification=true>
		<div id="wrapper" >
			<@nav />
			<@page_bar />
			<@location />
			<#nested>
			<@page_footer />
		</div>
	</@basePage>
</#macro>