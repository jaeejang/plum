<#include "../include/page.tpl" />
<@page title="用户编辑" css=['plugins/iCheck/custom.css',
'plugins/select2/select2.min.css']
js=['plugins/iCheck/icheck.min.js',
'plugins/select2/select2.min.js'] >
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
				<form method="POST" class="form-horizontal " action="${base}/user/edit">
				<div class="row">
						<div class="form-group col-md-4">
							<label class="col-sm-3 control-label">用户名</label>
							<div class="col-sm-7">
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
						<div class="form-group col-md-4">
							<label class="col-sm-3 control-label">姓名</label>
							<div class="col-sm-7">
								<input type="text" name="fullname" class="form-control"
									value="${user.fullname!}">
							</div>
							<#if validation?? && validation['fullname']??>
							<label class="col-sm-2 control-label error">
								${validation['fullname']}
							</label>
							</#if>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-4">
							<label class="col-sm-3 control-label">修改密码</label>
							<div class="col-sm-7">
								<input type="password" name="password" class="form-control">
							</div>
						</div>
						<div class="form-group col-md-4">
							<label class="col-sm-3 control-label">确认密码</label>
							<div class="col-sm-7">
								<input type="password" name="confirm-password" class="form-control">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-4">
							<label class="col-sm-3 control-label">所属机构</label>
							<div class="col-sm-7">
								<select name="brchno" class="form-control chosen-select" >
									<#if branches??>
										<#list branches as branch>
												<option value="${branch.brchno}" class="select2-results__group"  <#if user.brchno?? &&  branch.brchno == user.brchno>selected</#if>>${branch.brchna}(${branch.brchno})</option>
												<#list branch.children as child>
													<option value="${child.brchno}" class="select2-results__option" <#if user.brchno?? && child.brchno == user.brchno>selected</#if>>${child.brchna}(${child.brchno})</option>
												</#list>
										</#list>
									</#if>
								</select>
							</div>
						</div>
						<div class="form-group col-md-4">
							<label class="col-sm-3 control-label">是否锁定</label>
							<div class="col-sm-7">
								<div class="i-checks">
									<input type="checkbox" name="lock" value="1" <#if user.lock!>checked="true"</#if>/>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-sm-1 control-label">角色</label>
							<div class="col-sm-5">
									<select name="roles" class="form-control chosen-select" multiple="multiple">
								   		<option></option>
								   		<#if roles??>
								   			<#list roles as role>
								   				<option value="${role.roleid}"
								   				<#if user_roles??>
								   				<#list user_roles as ur>
								   					<#if ur.roleid = role.roleid>
								   						selected
								   					</#if>
								   				</#list>
								   				</#if>>${role.rolename}</option>
								   			</#list>
								   		</#if>
									</select>
							</div>
						</div>
					</div>
					<div class="row">
					<div class="form-group">
						<label class="col-sm-2 control-label"></label>
						<div class="col-sm-3">
							<button type="submit" class="btn btn-info">保存</button>
							<a class="btn btn-info" href="${base}/user">返回</a>
						</div>
					</div></div>
				</form>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$("select[name=brchno]").select2({
			placeholder: '请选择',
			allowClear : true,
			templateResult: function (data) {    
			    // We only really care if there is an element to pull classes from
			    if (!data.element) {
			      return data.text;
			    }
			    var $element = $(data.element);
			    var $wrapper = $('<span></span>');
			    $wrapper.addClass($element[0].className);
			    $wrapper.text(data.text);
			    return $wrapper;
			  }
		});
		$('select[name=roles]').select2({
			placeholder: '请选择',
			allowClear : true
		});
		$(".i-checks").iCheck({
			checkboxClass : 'icheckbox_square-green',
			radioClass : 'iradio_square-green',
		});
	});
</script>
</@page>
