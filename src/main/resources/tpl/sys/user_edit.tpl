<#include "../include/page.tpl" />
<@page title="用户编辑" css=['plugins/iCheck/custom.css',
'plugins/chosen/chosen.css']
js=['plugins/iCheck/icheck.min.js',
'plugins/chosen/chosen.jquery.min.js'] >
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<div class="float-e-margins">
				<div class="ibox-title">
					<h5>
						用户编辑 <small>编辑用户并提交</small>
					</h5>
				</div>
			</div>
			<div class="ibox-content">
				<form method="POST" class="form-horizontal" action="${base}/user/edit">
					<div class="form-group">
						<label class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-3">
							<input type="text" name="username" class="form-control"
								value="${user.username!}" 
								<#if user.username! !=''> readonly="readonly"</#if>>
						</div>
						<#if validtion?? && validtion['username']??>
						<label class="col-sm-2 control-label error">
							${validation['username']}
						</label>
						</#if>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-3">
							<input type="text" name="fullname" class="form-control"
								value="${user.fullname!}">
						</div>
						<#if validation?? && validation['fullname']??>
						<label class="col-sm-2 control-label error">
							${validation['fullname']}
						</label>
						</#if>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">修改密码</label>
						<div class="col-sm-3">
							<input type="password" name="password" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">所属机构</label>
						<div class="col-sm-3">
							<select name="brchno" class="form-control chosen-select" value="${user.brchno!}">
								<#if branches??>
									<#list branches as branch>
										<optgroup label="${branch.brsmna!}">
											<#list branch.children as child>
												<option value="${child.brchno}">${child.brsmna}</option>
											</#list>
										</optgroup>
									</#list>
								</#if>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">是否锁定</label>
						<div class="col-sm-3">
							<div class="i-checks">
								<input type="checkbox" name="lock" value="1" <#if user.lock!>checked="true"</#if>/>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"></label>
						<div class="col-sm-3">
							<button type="submit" class="btn btn-info">保存</button>
							<a class="btn btn-info" href="${base}/user">返回</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$(".chosen-select").chosen({
			disable_search_threshold:5,
			search_contains : true
		});
		$('.i-checks').iCheck({
			checkboxClass : 'icheckbox_square-green',
			radioClass : 'iradio_square-green',
		});
	});
</script>
</@page>
