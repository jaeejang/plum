<#macro nav>
		<nav class="navbar-default navbar-static-side" role="navigation">
			<div class="sidebar-collapse">
				<ul class="nav metismenu " id="side-menu">
					<li class="nav-header">
						<div class="profile-element">江南意见建议平台</div>
						<div class="logo-element">
							建议平台
						</div>
					</li>
					<#if menu?? && menu['children']??>
						<#list menu['children'] as item>
							<#if item.puissant>
							<li <#if item['active']>class="active"</#if>>
							<a href="${item['path']!}"><i class="fa fa-${item['icon']}"></i>
								<span class="nav-label">${item['funcname']}</span><span class="fa arrow"></span></a>
								<#if item['children']??>
									<ul class="nav nav-second-level collapse">
										<#list item['children'] as it>
											<#if it.puissant>
											<li <#if it['active']>class="active"</#if>>
												<a href="${base}${it['path']!}"><i class="fa fa-${it['icon']!}"></i>${it['funcname']!}</a>
											</li>
											</#if>
										</#list>
									</ul>
								</#if>
							</li>
							</#if>
						</#list>
					</#if>
				</ul>
			</div>
		</nav>
</#macro>