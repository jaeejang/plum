<#include "../include/page.tpl" />
<@page title="角色编辑" css=['plugins/iCheck/custom.css']
js=['plugins/iCheck/icheck.min.js'] >
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
						<li class="active"><a href="#">基本信息</a></li>
						<li><a href="${base}/role/func/${roleid!}">功能权限</a></li>
					</ul>
					<div class="tab-content ">
						<div id="tab-1" class="tab-pane active">
							<div class="panel-body">
								<form method="POST" class="form-horizontal" action="${base}/role/edit">
									<#if role.roleid??>
										<div class="form-group">
											<label class="col-sm-2 control-label">ID</label>
											<div class="col-sm-3">
												<input type="text" name="roleid" class="form-control"
													value="${role.roleid!}" readonly="readonly">
											</div>
										</div>
										</#if>
										<div class="form-group">
											<label class="col-sm-2 control-label">名称</label>
											<div class="col-sm-3">
												<input type="text" name="rolename" class="form-control"
													value="${role.rolename!}">
											</div>
											<#if validation?? && validation['rolename']??>
											<label class="col-sm-2 control-label error">
												${validation['rolename']} </label>
											</#if>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label"></label>
											<div class="col-sm-3">
												<button type="submit" class="btn btn-info">保存</button>
												<a class="btn btn-info" href="${base}/role">返回</a>
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
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$('.i-checks').iCheck({
			checkboxClass : 'icheckbox_square-green',
			radioClass : 'iradio_square-green',
		});
	});
</script>
</@page>
