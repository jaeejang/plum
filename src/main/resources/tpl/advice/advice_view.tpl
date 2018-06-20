<#include "../include/page.tpl" />
<#include "inc/_comment.tpl" />
<#include "inc/_poll.tpl" />
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
								<div class="panel panel-primary">
									<div class="panel-body">
										${advice.content!}
									</div>
									<div class="panel-footer">
											<div class="clearfix"><div class="pull-left">发表人：${advice.crtusrna!}  </div><div class="pull-right"> 发表时间：${advice.crttime?string["yyyy.MM.dd, HH:mm"]} </div></div>
									</div>
								</div>
							</div>
                     </div>
				  <@poll />
                     <#if files?? && (files?size > 0)>
                     <div class="row">
						 <div class="col-sm-12 m-b-xs">
	                     	 <div class="panel panel-default">
	                     	  	<div class="panel-heading"> 附件 </div>
	                     		<table class="table ">
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
                     	 </div>
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
}
</script>
</@page>
