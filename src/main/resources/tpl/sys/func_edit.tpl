<#include "../include/page.tpl" />
<@page title="菜单编辑" css=['plugins/iCheck/custom.css']
js=['plugins/iCheck/icheck.min.js'] >
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<div class="float-e-margins">
				<div class="ibox-title">
					<h5>
						菜单编辑 <small>编辑菜单并提交</small>
					</h5>
				</div>
			</div>
			<div class="ibox-content">
				<form method="POST" class="form-horizontal" action="${base}/func/edit">
					<#if func.funid??>
					<div class="form-group">
						<label class="col-sm-2 control-label">ID</label>
						<div class="col-sm-3">
							<input type="text" name="funid" class="form-control"
								value="${func.funid!}"  readonly="readonly">
						</div>
					</div>
					</#if>
					<div class="form-group">
						<label class="col-sm-2 control-label">名称</label>
						<div class="col-sm-3">
							<input type="text" name="funcname" class="form-control"
								value="${func.funcname!}">
						</div>
						<#if validation?? && validation['funcname']??>
						<label class="col-sm-2 control-label error">
							${validation['funcname']}
						</label>
						</#if>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">上级菜单ID</label>
						<div class="col-sm-3">
							<input type="text" name="superid" class="form-control"
								value="${func.superid!}">
						</div>
						<#if validation?? && validation['superid']??>
						<label class="col-sm-2 control-label error">
							${validation['superid']}
						</label>
						</#if>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">顺序</label>
						<div class="col-sm-3">
							<input type="text" name="order" class="form-control"
								value="${func.order}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">路径</label>
						<div class="col-sm-3">
							<input type="text" name="path" class="form-control"
								value="${func.path!}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">图标</label>
						<div class="col-sm-3">
							<input type="text" name="icon" class="form-control"
								value="${func.icon!}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"></label>
						<div class="col-sm-3">
							<button type="submit" class="btn btn-info">保存</button>
							<a class="btn btn-info" href="${base}/func">返回</a>
						</div>
					</div>
				</form>
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
