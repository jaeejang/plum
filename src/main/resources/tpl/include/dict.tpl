<#macro select_dict type="">
	<#if dicts??>
		<#list dicts as d>
			<#if d.type == type>
				<option value="${d.code}">${d.name}</option>
			</#if>
		</#list>
	</#if>
</#macro>