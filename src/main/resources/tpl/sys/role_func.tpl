<#include "../include/page.tpl" />
<@page title="角色编辑" css=['plugins/jsTree/style.min.css']
js=['plugins/jsTree/jstree.min.js'] >
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<div class="float-e-margins">
				<div class="ibox-title">
					<h5>
						角色编辑 <small>编辑角色并提交</small>
					</h5>
				</div>
			</div>
			<div class="ibox-content">
				<div class="tabs-container">
					<ul class="nav nav-tabs">
						<li><a href="${base}/role/edit/${roleid!}">基本信息</a></li>
						<li class="active"><a data-toggle="tab" href="#">功能权限</a></li>
					</ul>
					<div class="tab-content ">
						<div id="tab-2" class="tab-pane active">
							<form name="role_func" method="post" action="${base}/role/func/${roleid!}">
								<input type="hidden" name="roleid" value="${roleid!}" />
								<input type="hidden" name="funcs" value="${funcs}" />
								<div class="panel-body">
									<div class="form-group">
										<div id="functree">
											<ul>
												<#macro bpTree node>
												<li id="tree_${node.funid}" class="jstree-open">${node.funcname}
													<#if node.children?? && node.children?size gt 0>
													<ul>
														<#list node.children as child> <@bpTree node=child /> </#list>
													</ul>
													</#if>
												</li>
												</#macro>
												<@bpTree node=menu />
											</ul>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label"></label>
										<div class="col-sm-3">
											<button type="submit" class="btn btn-info">保存</button>
											<a class="btn btn-info" href="${base}/role">返回</a>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$('#functree').on('ready.jstree',function(){
				//$('#functree').check_node($("input[name='funcs']").val().split(','));
				var arr = $("input[name='funcs']").val().split(',');
				for(var i=0;i<arr.length;i++){
					$('#functree').jstree('check_node','tree_'+arr[i]);
				}
			})
			.jstree({
				'core' : {
					'check_callback' : true,
					'multiple' : true
				},
				'plugins' : [ 'types', 'checkbox' ],
				'types' : {
					'default' : {
						'icon' : 'fa fa-folder'
					}
				}
			});
		
		$("form[name='role_func']").one('submit',function(){
			var ids = $('#functree').jstree().get_bottom_checked();
			var arrs = [];
			for(var i=0;i<ids.length;i++){
				arrs.push(ids[i].substring(5));
			}
			$("input[name='funcs']").val(arrs.join(','));
			return true;
		});
	});
</script>
</@page>
