<#include "../include/page.tpl" />
<#include "../include/dict.tpl" />
<@page title="创新建议" css=['plugins/summernote/summernote.css',
'plugins/datepicker/bootstrap-datepicker3.min.css',
'plugins/chosen/chosen.css']
js=['plugins/summernote/summernote.min.js',
'plugins/summernote/summernote-zh-CN.min.js',
'plugins/datepicker/bootstrap-datepicker.min.js',
'plugins/datepicker/bootstrap-datepicker.zh-CN.min.js',
'plugins/chosen/chosen.jquery.min.js'] >
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<div class="float-e-margins">
				<div class="ibox-title">
					<h5>
						创新建议 <small>编辑创新建议并提交</small>
					</h5>
				</div>
			</div>
			<div class="ibox-content">
				<form method="POST" class="form-horizontal" action="#">
					<input type="hidden" name="id" value="${advice.id!}" /> 
					<input type="hidden" name="status" value="${advice.status!}" />
					<div class="form-group">
						<label class="col-sm-2 control-label">季度</label>
						<div class="col-sm-3">
							<div class="input-group date">
								<input type="text" class="form-control" name="crtdt" value="${advice.crtdt!}" readonly>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">建议牵头部门</label>
						<div class="col-sm-3">
							<select type="text" name="leaddep" class="form-control chosen-select"
								value="${advice.leaddep!}" disabled>
									<#if adviceBranch??>
										<#list adviceBranch as d>
											<option value="${d.adviceBrchno!}">${d.adviceBrchna!}</option>
										</#list>
									</#if>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">创新类别</label>
						<div class="col-sm-3">
							<select type="text" name="catalog" class="form-control chosen-select"
								value="${advice.catalog!}" disabled>
								<@select_dict type="advice" />
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">概述</label>
						<div class="col-sm-5">
							<textarea name="summary" class="form-control" readonly>${advice.summary!}</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">内容</label>
						<div class="col-sm-8">
							<div class="panel panel-default">
								<div class="panel-body">
									${advice.content!}
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"></label>
						<div class="col-sm-3">
							<a class="btn btn-info" href="${base}/adv/my">返回</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$(".chosen-select").chosen({disable_search_threshold:10});
	});
</script>
</@page>
