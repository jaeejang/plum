<#include "../include/page.tpl" />
<@page title="提出/修改建议" css=['plugins/summernote/summernote.css',
'plugins/select2/select2.min.css',
"plugins/iCheck/custom.css",
"plugins/fileupload/jquery.fileupload.css",
"plugins/fileupload/jquery.fileupload-ui.css",
"plugins/switchery/switchery.css"]
js=[
'plugins/summernote/summernote.min.js',
'plugins/summernote/summernote-zh-CN.js',
'plugins/select2/select2.min.js',
'plugins/iCheck/icheck.min.js',
"plugins/switchery/switchery.js"
'plugins/validate/jquery.validate.js',
"plugins/validate/messages_zh.min.js",
"plugins/jQueryUI/jquery-ui.min.js",
"plugins/fileupload/tmpl.min.js",
"plugins/fileupload/jquery.iframe-transport.js",
"plugins/fileupload/jquery.fileupload.js",
"plugins/fileupload/jquery.fileupload-process.js",
"plugins/fileupload/jquery.fileupload-validate.js",
"plugins/fileupload/jquery.fileupload-ui.js"] >
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<div class="float-e-margins">
				<div class="ibox-title">
					<h5>
						意见建议 <small>编辑建议并提交</small>
					</h5>
				</div>
			</div>
			<div class="ibox-content">
			    <div class="row">
					<div class="tabs-container col-sm-offset-1 col-sm-10">
                        <ul class="nav nav-tabs">
                            <li class="active"><a data-toggle="tab" href="#tab-1">内容</a></li>
                            <li class=""><a data-toggle="tab" href="#tab-2">附件上传</a></li>
                        </ul>
                        <div class="tab-content">
                            <div id="tab-1" class="tab-pane active">
                            <div class="panel-body">
                            <form id="form1" method="POST" class="form-horizontal" action="${base}/adv/edit">
								<input type="hidden" name="id" value="${advice.id!}" />  
								<input type="hidden" name="status" value="${advice.status!}" />
								<input type="hidden" name="files" value=""  />
								<div class="row">
									<div class="form-group col-md-6" >
										<label class="col-sm-4 control-label">是否专题</label>
										<div class="col-sm-7"><input type="checkbox" class="js-switch" name="isSub" <#if advice.subject ??>  checked </#if> /></div>
									</div>
									<div class="form-group col-md-6 <#if advice.subject?? == false>hidden</#if>" "  id="subDiv">
										<label class="col-sm-4 control-label">选择专题</label>
										<div class="col-sm-7">
											<select  name="subject" class="form-control chosen-select"
												value="${advice.subject!}" required>
													<option></option>
													<#if subjects??>
														<#list subjects as subject>
															<option value="${subject.id!}" <#if advice.subject?? && advice.subject == subject.id>selected </#if>>${subject.topic!}</option>
														</#list>
													</#if>
											</select>
										</div>
									</div>
								</div>
								<div class="row <#if advice.subject??>hidden</#if>" id="commDiv">
									<div class="form-group col-md-6" >
										<label class="col-sm-4 control-label">建议类别</label>
										<div class="col-sm-7">
											<select type="text" name="catalog" class="form-control chosen-select"
												value="${advice.catalog!}" >
											 	<option></option>
											</select>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="col-sm-4 control-label">建议牵头部门</label>
										<div class="col-sm-7">
											<select type="text" name="leaddep" class="form-control chosen-select" >
													<option></option>
													<#if adviceBranch??>
														<#list adviceBranch as d>
															<option value="${d.adviceBrchno!}" <#if advice.leaddep?? && advice.leaddep == d.adviceBrchno>selected </#if>>${d.adviceBrchna!}</option>
														</#list>
													</#if>
											</select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group">
										<label class="col-sm-2 control-label">标题</label>
										<div class="col-sm-6 m-b-xs">
											<input type="text" name="summary" class="form-control"
												value="${advice.summary!}" required  maxlength="100"/>
										</div>
										<div class="col-sm-3 ">
											<div class="i-checks">
												<label><input type="checkbox" name="anony"  <#if advice.anony?? && advice.anony> checked </#if> /> 匿名</label>
											</div> 
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group">
											<label class="col-sm-2 control-label">内容</label>
											<div class="col-sm-9 m-b-xs">
												<textarea name="content" id="summernote" >${advice.content!}</textarea>
											</div>
										</div>
	                                </div>
								<div class="row">
	                                <div class="form-group">
										<label class="col-sm-2 control-label"></label>
										<div class="col-sm-10 m-b-xs">
										<div class="btn-group" role="group" aria-label="...">
											<button type="button" class="btn btn-info" id="save1">保存</button>
											<button type="button" class="btn btn-success" id="save2">保存并提交</button>
										</div>
											<#if advice.id??>
											<a class="btn btn-info" href="${base}/adv/my">返回</a>
											</#if>
										</div>
									 </div>
                           	       </div>
                           	    </form>
                            </div>
                        </div>
                          <div id="tab-2" class="tab-pane">
                              <div class="panel-body">
								<blockquote>
								    <p>控件上传支持图片(JPG, JPEG,PNG,BMP,GIF), Office文档(DOC,DOCX,XLS,XLSX),PDF 等格式；
								    <br />每个文件上传大小限制在10M内；
								    <br />最多上传3个附件</p>
								</blockquote>
                              	 <form id="fileupload" action="${base}/file/upload" method="POST" enctype="multipart/form-data">
							        <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
							        <input type="hidden" name="sourceid" value="${advice.id!}" />
							        <div class="row fileupload-buttonbar">
							            <div class="col-lg-10">
							                <!-- The fileinput-button span is used to style the file input field as button -->
							                <span class="btn btn-success fileinput-button">
							                    <i class="glyphicon glyphicon-plus"></i>
							                    <span>添加...</span>
							                    <input type="file" name="files[]" multiple>
							                </span>
							                <button type="submit" class="btn btn-primary start">
							                    <i class="glyphicon glyphicon-upload"></i>
							                    <span>开始上传</span>
							                </button>
							                <button type="reset" class="btn btn-warning cancel">
							                    <i class="glyphicon glyphicon-ban-circle"></i>
							                    <span>取消</span>
							                </button>
							                <button type="button" class="btn btn-danger delete">
							                    <i class="glyphicon glyphicon-trash"></i>
							                    <span>删除</span>
							                </button>
							                <input type="checkbox" class="toggle">
							                <!-- The global file processing state -->
							                <span class="fileupload-process"></span>
							            </div>
							            <!-- The global progress state -->
							            <div class="col-lg-5 fileupload-progress fade">
							                <!-- The global progress bar -->
							                <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
							                    <div class="progress-bar progress-bar-success" style="width:0%;"></div>
							                </div>
							                <!-- The extended global progress state -->
							                <div class="progress-extended">&nbsp;</div>
							            </div>
							        </div>
							        <!-- The table listing the files available for upload/download -->
							        <table role="presentation" class="table table-striped"><tbody class="files"></tbody></table>
                              	 </form>
                              </div>
                          </div>
                    </div>
                 </div>
			</div>
		</div>
	</div>
	</div>
</div>

<!-- The template to display files available for upload -->
<script id="template-upload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-upload fade">
        <td>
            <span class="preview"></span>
        </td>
        <td>
            <p class="name">{%=file.name%}</p>
            <strong class="error text-danger"></strong>
        </td>
        <td>
            <p class="size">Processing...</p>
            <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>
        </td>
        <td>
            {% if (!i && !o.options.autoUpload) { %}
                <button class="btn btn-primary start" disabled>
                    <i class="glyphicon glyphicon-upload"></i>
                    <span>开始</span>
                </button>
            {% } %}
            {% if (!i) { %}
                <button class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>取消</span>
                </button>
            {% } %}
        </td>
    </tr>
{% } %}
</script>

<!-- The template to display files available for download -->
<script id="template-download" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-download fade">
        <td>
            <span class="preview">
                {% if (file.thumbnailUrl) { %}
                    <a href="${base}/file/get/{%=file.id%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img src="{%=file.thumbnailUrl%}"></a>
                {% } %}
            </span>
        </td>
        <td>
            <p class="name">
                    <a href="${base}/file/get/{%=file.id%}" title="{%=file.name%}" download="{%=file.name%}" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.name%}</a>
            </p>
            {% if (file.error) { %}
                <div><span class="label label-danger">错误</span> {%=file.error%}</div>
            {% } %}
        </td>
        <td>
            <span class="size">{%=o.formatFileSize(file.size)%}</span>
        </td>
        <td>
                <button class="btn btn-danger delete" data-type="DELETE" data-url="${base}/file/get/{%=file.id%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                    <i class="glyphicon glyphicon-trash"></i>
                    <span>删除</span>
                </button>
                <input type="checkbox" name="delete" value="1" class="toggle">
        </td>
    </tr>
{% } %}
</script>
<script type="text/javascript">
function jsonpCallback(data) {
		var elem = document.querySelector('.js-switch');
	    var switchery = new Switchery(elem, { color: '#1AB394' });
	    $('#form1').validate({});
	    elem.onchange = function() {
	    	if(elem.checked){
	    		$('#commDiv').toggleClass('hidden');
	    		$('#subDiv').toggleClass('hidden');
	    		$("select[name=subject]").rules("add",{required:true});
	    		$("select[name=leaddep]").rules("remove");
	    		$("select[name=catalog]").rules("remove");
	    	}else{
	    		$('#subDiv').toggleClass('hidden');
	    		$('#commDiv').toggleClass('hidden');
		    		$("select[name=subject]").rules("remove");
		    		$("select[name=leaddep]").rules("add",{required:true});
		    		$("select[name=catalog]").rules("add",{required:true});
	    	}
	    };

		$("#save1").bind("click",function(){
			if($("#form1").valid())
				$("#form1").submit();
		});

		$("#save2").bind("click",function(){
			$("input[name=status]").val(1);
			if($("#form1").valid())
				$("#form1").submit();
		})
		$.each(data, function(i, n) {
			if (n.type == "advice_catalog") {
				$('select[name="catalog"]').append(new Option(n.name, n.code, false, false));
			}
		});
		$('select[name="catalog"]').val($('select[name="catalog"]').attr("value"));
		
		$('.chosen-select').select2({
			placeholder : '请选择',
			allowClear : true,
			width: "100%"
		});
		$('.i-checks').iCheck({checkboxClass: 'icheckbox_square-green'});
		$('#summernote').summernote({
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
	    $('#fileupload').fileupload({
	    	acceptFileTypes: /(\.|\/)(gif|jpe?g|png|docx?|xlsx?|pdf)$/i,
	    	maxFileSize: 10 * 1024 * 1024,
	    	maxNumberOfFiles: 3,
	    	messages: {
	    	    maxFileSize: '文件大小受限',
	    	    acceptFileTypes: '不允许的文件类型',
	    	    maxNumberOfFiles:'只允许上传3个文件'
	    	},
	    	processfail: function (e, data) {
	    	    var currentFile = data.files[data.index];
	    	    if (data.files.error && currentFile.error) {
	    	        console.log(currentFile.error);
	    	    }
	    	 },
	    	 processdone:function(e,data){
	    		 var currentFile = data.files[data.index];
	    		 if(currentFile.id){
	    			 var ids = $("input[name=files]").val().split(',');
	    			 ids.push(currentFile.id);
	    			 $("input[name=files]").val($ids.join(','));
	    		 }
	    	 }
	    });
        // Load existing files:
       	if($("input[name=id]").val()){
	        $('#fileupload').addClass('fileupload-processing');
	        $.ajax({
	            // Uncomment the following to send cross-domain cookies:
	            //xhrFields: {withCredentials: true},
	            url: '${base}/file/files/' + $("input[name=id]").val(),
	            dataType: 'json',
	            context: $('#fileupload')[0]
	        }).always(function () {
	            $(this).removeClass('fileupload-processing');
	        }).done(function (result) {
	            $(this).fileupload('option', 'done')
	                .call(this, $.Event('done'), {result: result});
	        });
       	}
}
</script>
</@page>
