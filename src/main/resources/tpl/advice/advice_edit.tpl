<#include "../include/page.tpl" />
<#include "../include/dict.tpl" />
<@page title="创新建议" css=['plugins/summernote/summernote.css',
'plugins/chosen/chosen.css']
js=['plugins/summernote/summernote.min.js',
'plugins/summernote/summernote-zh-CN.min.js',
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
				<form method="POST" class="form-horizontal">
					<input type="hidden" name="id" value="${advice.id!}" /> 
					<input type="hidden" name="status" value="${advice.status!}" />
					<div class="form-group">
						<label class="col-sm-2 control-label">季度</label>
						<div class="col-sm-3">
							<select type="text" name="crtdt" class="form-control chosen-select"
							value="${advice.crtdt!}">
								<#if seasons??>
										<#list seasons as season>
											<option value="${season!}">${season!}</option>
										</#list>
									</#if>
							</select>							
						</div>
						<#if validation?? && validation['crtdt']??>
						<label class="col-sm-2 control-label error">
							${validation['crtdt']}
						</label>
						</#if>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">建议牵头部门</label>
						<div class="col-sm-3">
							<select type="text" name="leaddep" class="form-control chosen-select"
								value="${advice.leaddep!}">
									<#if adviceBranch??>
										<#list adviceBranch as d>
											<option value="${d.adviceBrchno!}">${d.adviceBrchna!}</option>
										</#list>
									</#if>
							</select>
						</div>
						<#if validation?? && validation['leaddep']??>
						<label class="col-sm-2 control-label error">
							${validation['leaddep']}
						</label>
						</#if>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">创新类别</label>
						<div class="col-sm-3">
							<select type="text" name="catalog" class="form-control chosen-select"
								value="${advice.catalog!}">
								<@select_dict type="advice" />
							</select>
						</div>
						<#if validation?? && validation['catalog']??>
						<label class="col-sm-2 control-label error">
							${validation['catalog']}
						</label>
						</#if>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">概述</label>
						<div class="col-sm-5">
							<textarea type="text" name="summary" class="form-control"
								value="${advice.summary!}"></textarea>
						</div>
						<#if validation?? && validation['summary']??>
						<label class="col-sm-2 control-label error">
							${validation['summary']}
						</label>
						</#if>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">内容</label>
						<div class="col-sm-10">
							<textarea name="content" id="summernote">${advice.content!}</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"></label>
						<div class="col-sm-3">
							<button type="submit" class="btn btn-info">保存</button>
							<#if advice.id??>
							<a class="btn btn-info" href="${base}/adv">返回</a>
							</#if>
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
		$('#summernote').summernote({
			lang: 'zh-CN',
			toolbar: [
			   // [groupName, [list of button]]
			   ['style', ['bold', 'italic', 'underline', 'clear']],
			   ['font', ['strikethrough', 'superscript', 'subscript']],
			   ['fontsize', ['fontsize']],
			   ['color', ['color']],
			   ['para', ['ul', 'ol', 'paragraph']],
			   ['height', ['height']]
			]
		});
	});
</script>
</@page>
