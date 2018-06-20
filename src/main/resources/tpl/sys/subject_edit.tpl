<#include "../include/page.tpl" />
<@page title="专题管理" css=['plugins/iCheck/custom.css',
'plugins/select2/select2.min.css']
js=['plugins/iCheck/icheck.min.js',
'plugins/select2/select2.min.js'] >
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<div class="float-e-margins">
				<div class="ibox-title">
					<h5>
						专题管理 <small>编辑专题并提交</small>
					</h5>
				</div>
			</div>
			<div class="ibox-content">
				<form method="POST" class="form-horizontal " action="${base}/subject/edit">
					<input type="hidden" name="id" value="${subject.id!}" />
				<div class="row">
						<div class="form-group col-md-4">
							<label class="col-sm-3 control-label">主题</label>
							<div class="col-sm-7">
								<input type="text" name="topic" class="form-control"
									value="${subject.topic!}"  required >
							</div>
						</div>
						<div class="form-group col-md-4">
							<label class="col-sm-3 control-label">是否启用</label>
							<div class="col-sm-7">
								<div class="i-checks">
									<input type="checkbox" name="enable" value="1" 
										<#if subject.enable?? && subject.enable == true >checked="true"</#if>/>
								</div>
							</div>
						</div>
					</div>
					<div class="row" >
						<div class="form-group col-md-4">
							<label class="col-sm-3 control-label">主题概述</label>
							<div class="col-sm-7">
								<textarea  name="summary"  cols="80" rows="5"  required maxLength="125">${subject.summary!}</textarea>
							</div>
						</div>
					</div>
					<div class="row" >
						<div class="form-group col-md-4">
							<label class="col-sm-3 control-label">优先级</label>
							<div class="col-sm-7">
								<input  name="primary"  class="form-control"
									value="${subject.primary!}"   />
							</div>
						</div>
						<div class="form-group col-md-4">
							<label class="col-sm-3 control-label">顺序</label>
							<div class="col-sm-7">
								<input type="text"  name="order"  class="form-control"
									value="${subject.order!}"  />
							</div>
						</div>
					</div>
					<div class="row">
					<div class="form-group">
						<label class="col-sm-2 control-label"></label>
						<div class="col-sm-3">
							<button type="submit" class="btn btn-info">保存</button>
							<a class="btn btn-info" href="${base}/subject">返回</a>
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
