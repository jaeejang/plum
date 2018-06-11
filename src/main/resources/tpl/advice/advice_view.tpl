<#include "../include/page.tpl" />
<#include "comment.tpl" />
<@page title="创新建议" css=['plugins/summernote/summernote.css',
"plugins/iCheck/custom.css",
'plugins/select2/select2.min.css']
js=['plugins/select2/select2.min.js',
'plugins/iCheck/icheck.min.js',
'plugins/summernote/summernote.min.js',
'plugins/summernote/summernote-zh-CN.js'] >
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
				<div class="col-lg-6">
			<div class="float-e-margins">
				<div class="ibox-title">
					<h5>
						创新建议 <small>修改</small>
					</h5>
				</div>
			</div>
			<div class="ibox-content">
				<form method="POST" action="${base}/adv/edit">
					<input type="hidden" name="id" value="${advice.id!}" /> 
					<div class="row">
						<div  class="col-sm-6 ">
							<label class=" control-label">创新类别</label>
							<select type="text" name="catalog" class="form-control chosen-select"
								value="${advice.catalog!}" required>
								<option></option>
							</select>
						</div>
						<div class="col-sm-3 ">
							<div class="i-checks">
								<label class="control-label"> 匿名</label>
								<div><input type="checkbox" name="anony"  value=“1”  disabled  <#if advice.anony?? && advice.anony> checked </#if> />
								</div>
							</div> 
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 ">
							<label class=" control-label">建议牵头部门</label>
							<select type="text" name="leaddep" class="form-control chosen-select" >
									<option></option>
									<#if adviceBranch??>
										<#list adviceBranch as d>
											<option value="${d.adviceBrchno!}" <#if advice.leaddep?? && advice.leaddep == d.adviceBrchno>selected </#if>>${d.adviceBrchna!}</option>
										</#list>
									</#if>
							</select>
						</div>
						<div  class="col-sm-3">
							<label class=“control-label”>状态</label>
							<select type="text" name="status" class="form-control chosen-select"
								value="${advice.status!}" required>
								<option></option>
							</select>
						</div>
					</div>
					<div  class="row">
							<div class="col-sm-10 m-b-xs">
								<label class="control-label">标题</label>
								<input type="text" name="summary" class="form-control" value="${advice.summary!}"   maxlength="100"/>
							</div>
					</div>
					<div class="row">
							<div class="col-sm-12 m-b-xs">
								<label class="control-label">内容</label>
								<div>
									<textarea name="content" class="summernote" >${advice.content!}</textarea>
								</div>
							</div>
                     </div>
                     <div class="row">
                     	<div class="col-sm-offset-4 col-sm-8">
                     		发表人：${advice.crtusrna!}   发表时间：${advice.crttime?string["yyyy.MM.dd, HH:mm"]} 
                     	</div>
                     </div>
					<div class="row form-group">
						<label class="col-sm-2 control-label"></label>
						<div class="col-sm-10">
							<div class="btn-group" role="group" aria-label="...">
								<a class="btn btn-info" href="${base}/adv/my">返回</a>
							</div>
						</div>
					</div>
				  </form>
                     <#if files?? && (files?size > 0)>
                     <div class="row">
                     		<table class="table table-bordered ">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>附件名</th>
                                    <th>附件大小</th>
                                    <th>附件地址</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list files as file>
                                	<tr>
                                		<td>${file?counter}</td>
                                		<td>${file.filename}</td>
                                		<td>${file.filesize}</td>
                                		<td><a href="${base}/file/get/${file.id}">下载</a></td>
                                	</tr>
                                </#list>
                                </tbody>
                     		</table>
                     </div>
                     </#if>
			</div>
		</div>
		<div class="col-lg-6">
			<@show_comment delete=false />
		</div>
	</div>
</div>
<script type="text/javascript">
function jsonpCallback(data) {
		$.each(data, function(i, n) {
			if (n.type == "advice_catalog") {
				$('select[name="catalog"]').append(new Option(n.name, n.code, false, false));
			}
			if (n.type == "advice_status") {
				$('select[name="status"]').append(new Option(n.name, n.code, false, false));
			}
		});
		$('select[name="catalog"]').val($('select[name="catalog"]').attr("value"));
		$('select[name="status"]').val($('select[name="status"]').attr("value"));

		$('.i-checks').iCheck({checkboxClass: 'icheckbox_square-green'});
		$('.chosen-select').select2({
			placeholder : '请选择',
			disabled : true
		});
		$('.summernote').summernote({
			lang: 'zh-CN',
			height: 150,
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
		$('.summernote').summernote('disable');
}
</script>
</@page>
