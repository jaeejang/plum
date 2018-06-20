<#include "../include/page.tpl" />
<@page title="我的建议" layout="full-height-layout" js=["plugins/jqGrid/i18n/grid.locale-cn.js",
"plugins/jqGrid/jquery.jqGrid.min.js",
"plugins/jqGrid/jqGrid.global.js", 
"plugins/select2/select2.min.js"]
css=[
"plugins/jqGrid/ui.jqgrid-bootstrap.css",
"plugins/jqGrid/ui.jqgrid-bootstrap-ui.css",
"plugins/select2/select2.min.css"]>
<#assign label = ["primary","success","info","warning","danger","default"] />
 <div class="fh-breadcrumb">
                <div class="fh-column">
                    <div class="full-height-scroll">
                    	<#if subjects??  && (subjects?size > 0)>
                        <ul class="list-group elements-list">
                        	<#list subjects as subject>
                            <li class="list-group-item <#if  subjectid?? && (subjectid = subject.id)> active </#if>" >
                                <a data-toggle="${subject.id!}">
                                   <span class="label label-${label[subject?counter%6]}">${subject?counter}</span>
                                   <strong>${subject.topic!}</strong>
                                    <div class="small m-t-xs">
                                        <p>
                                            ${subject.summary!}
                                        </p>
                                    </div>
                                </a>
                            </li>
                            </#list>
                        </ul>
                        <#else>
                        	<div class="alert alert-warning" role="alert">目前没有专题.</div>
                        </#if>
                    </div>
                </div>

                <div class="full-height">
                    <div class="full-height-scroll white-bg border-left" style="padding-top:20px" >
                        <div class="element-detail-box">
                            <div class="tab-content">
								<form name="search-form" class="form-horizontal" action="${base}/adv/edit" method="POST">
									<input type="text" name="subject" value="${subjectid!}" style="display:none" />
									<div class="row">
										<div class="form-group col-md-5">
											<label class="col-lg-4 control-label">状态</label>
										    <div class="col-lg-8 m-b-xs">
												<select name="status" class="form-control  chosen-select">
											   		<option></option>
												</select>
											</div>
		                                </div>
										<div class="form-group col-md-5">
											<label class="col-lg-4 control-label">提出部门</label>
										    <div class="col-lg-8 m-b-xs">
												<select name="brchno" class="form-control chosen-select">
												   		<option></option>
														<#if branches??>
															<#list branches as branch>
																	<option value="${branch.brchno}" class="select2-results__group">${branch.brchna}(${branch.brchno})</option>
																	<#list branch.children as child>
																		<option value="${child.brchno}" class="select2-results__option">${child.brchna}(${child.brchno})</option>
																	</#list>
															</#list>
														</#if>
													</select>
											</div>
		                                </div>
		                             </div>
		                             <div class="row">
		                                <div class="form-group  col-md-5">
	                                    	<label class="col-lg-4 control-label">关键字</label>
	                                    	<div class="col-lg-8 m-b-xs">
	                                    		<input type="text" name="keyword" placeholder="关键字" class="input-sm form-control">
	                                    	</div>
										</div>
										<div class="form-group  col-md-5 ">
											<label class="col-lg-4 control-label"></label>
											<div class="col-lg-8 m-b-xs">
		                                    	<span class="input-group-btn">
		                                        	<button type="button" class="btn btn-sm btn-primary" onclick="search()"> 搜索</button>
		                                        	<button type="button" class="btn btn-sm btn-warning" onclick="exportExcel()"> Excel</button>
		                                        	<button type="button" class="btn btn-sm" onclick="formReset()"> 重置</button>
		                                        </span>
	                                        </div>
										</div>
									</div>
								</from>
						    
						    <table id="jqGrid"></table>
						    <div id="jqGridPager"></div>
						  </div>
						</div>
                    </div>
                </div>
            </div>
<script type="text/javascript">
function jsonpCallback(data) {
	$.each(data, function(i, n) {
		if (n.type == "advice_status") {
			$('select[name="status"]').append(new Option(n.name, n.code, false, false));
		}
	})
	$('.chosen-select').select2({
		placeholder : '请选择',
		allowClear : true
	});
	$("select[name='brchno']").select2({
		placeholder : '请选择',
		allowClear : true,
		templateResult : function(data) {
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
	$('#jqGrid').jqGrid({
		url : '${base}/tpc/list/show',
		mtype : 'GET',
		datatype : 'json',
		postData : {subject:$("input[name='subject']").val()},
		colModel : [ {
			label : '标题',
			name : 'summary',
			width:200,
			sortable : false,
			formatter : function (val, options, row){
				return "<a href=${base}/adv/view/" + row['id'] + '>' + row['summary'] + '</a>';
			}
		}, {
			label : '提出人',
			name : 'crtusrna',
			formatter:function(val,options,row){
				if(row['anony']) 
					return '匿名'; 
				else
					return val;
			}
		}, {
			label : ' 提出部门',
			name : 'brchna'
		}, {
			label : ' 状态',
			name : 'status',
			width:55,
			formatter : function(value) {
				return decode(data, "advice_status", value);
			}
		}, {
			label : ' 日期',
			name : 'crttime',
			formatter:function(value){return new Date(value).Format('yyyy-MM-dd hh:mm:ss');}
		}, ],
		height : 432,
		pager : "#jqGridPager"
	});
	
	$(".list-group-item a").bind('click',function(){
		$('input[name=subject]').val($(this).attr('data-toggle'));
		$('.list-group-item').removeClass('active');
		$(this).parent().addClass('active');
		search();
	});
	
}
function deleteRow(ids){
	jQuery.get(
			'${base}/adv/delete/' + ids,				
			function(ret){
				if(ret.type == "success"){
					search();
				}
				toastr[ret.type](ret.message,ret.name);
			}		
		);
}
function formReset(){
	$('.list-group-item').removeClass('active');
	$('form[name=search-form]')[0].reset();
}

function search() {
	$("#jqGrid").jqGrid('setGridParam', {
		datatype : 'json',
		postData : {
			'keyword' : encodeURI($("input[name='keyword']").val()),
			'brchno' : encodeURI($("select[name='brchno']").val()),
			//'leaddep' : encodeURI($("select[name='leaddep']").val()),
			//'catalog' : encodeURI($("select[name='catalog']").val()),
			'status' : encodeURI($("select[name='status']").val()),
			'subject': encodeURI($("input[name='subject']").val())
		}
	}).trigger("reloadGrid");
}

function exportExcel() {
	   var params =  {
				'keyword' : encodeURI($("input[name='keyword']").val()),
				'brchno' : encodeURI($("select[name='brchno']").val()),
				'status' : encodeURI($("select[name='status']").val()),
				'subject': encodeURI($("input[name='subject']").val())
			};
        var form = $('<form method="POST" action="${base}/tpc/export/all">');
        $.each(params, function(k, v) {
            form.append($('<input type="hidden" name="' + k +
                    '" value="' + v + '">'));
        });
        $('body').append(form);
        form.submit(); //自动提交
}
</script>

</@page>