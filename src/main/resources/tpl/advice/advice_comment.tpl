<#include "../include/page.tpl" />
<#include "inc/_comment.tpl" />
<#include "inc/_poll.tpl" />
<@page title="建议反馈" css=['plugins/summernote/summernote.css',
"plugins/iCheck/custom.css",
'plugins/select2/select2.min.css',
"plugins/switchery/switchery.css",
"awesome-bootstrap-checkbox.css"]
js=['plugins/select2/select2.min.js',
'plugins/iCheck/icheck.min.js',
"plugins/switchery/switchery.js",
'plugins/summernote/summernote.min.js',
'plugins/summernote/summernote-zh-CN.js'] >
<div class="wrapper wrapper-content ">
	<div class="row">
		<div class="col-lg-6">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>
						建议反馈 <small>查看</small>
					</h5>
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
						<div class="col-sm-3 ">
							<div class="i-checks">
								<label class="control-label"> 公开</label>
								<div><input type="checkbox" name="pub"  value=“1”  disabled  <#if advice.pub?? && advice.pub> checked </#if> />
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
								<input type="text" name="summary" class="form-control" value="${advice.summary!}"   maxlength="100" readonly />
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
											<div class="clearfix"><div class="pull-left">发表人：
											<#if advice.anony?? && advice.anony>
											匿名
											<#else>
											${advice.crtusrna!} 
											</#if> </div><div class="pull-right"> 发表时间：${advice.crttime?string["yyyy.MM.dd, HH:mm"]} </div></div>
									</div>
								</div>
							</div>
	                    </div>
			    	<@poll />
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
		</div>
		<div class="col-lg-6">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>
						建议反馈 <small>反馈</small>
					</h5>
					<div class="ibox-tools">
                      <a class="collapse-link">
                          <i class="fa fa-chevron-up"></i>
                      </a>
                  </div>
                </div>
                <div class="ibox-content">
					<form action="${base}/adv/comment" method='POST' class="form-horizontal"> 
						<input type="hidden" name="adviceId"  value="${advice.id!}" />
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group">
									<input type="text" name="title"  placeholder="标题" class="input-sm form-control"  maxlength=50 required>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group">
									<textarea name="content" class="summernote"></textarea>
								 </div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-10">
								<input type="checkbox" class="i-checks" name="isPublic" value="true"  checked />  是否公开
							</div>
							<div class="col-md-2">
								<button type="submit" class="btn btn-info">反馈</button>
							</div>
						</div>
					</form>
				</div>
			</div>			
			<@show_comment delete=true />
		</div>
	</div>
  </div>
<script type="text/javascript">
function jsonpCallback(data) {
		var elem = document.querySelector('.js-switch');
	    var switchery = new Switchery(elem, { color: '#1AB394' });
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
			height: 100,
			toolbar: [
			   // [groupName, [list of button]]
			   ['style', ['bold', 'italic', 'underline', 'clear']],
			   ['fontsize', ['fontsize','color']],
			   ['para', ['ul', 'ol', 'paragraph']]
			]
		});

}
</script>
</@page>
