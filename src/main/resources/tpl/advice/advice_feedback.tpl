<#include "../include/page.tpl" />
<@page title="建议反馈" js=[
"plugins/jqGrid/i18n/grid.locale-cn.js",
"plugins/jqGrid/jquery.jqGrid.min.js",
"plugins/jqGrid/jqGrid.global.js", "plugins/select2/select2.min.js"]
css=["plugins/jqGrid/ui.jqgrid-bootstrap.css",
"plugins/jqGrid/ui.jqgrid-bootstrap-ui.css",
"plugins/select2/select2.min.css"]>


<div class="wrapper wrapper-content  animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox ">
				<div class="ibox-title">
					<h5>建议反馈</h5>
				</div>
				<div class="ibox-content">
								<!-- search area -->
								<form name="search-form" class="form-horizontal"  method="POST">
									<input type="hidden" name="leaddep" value="${adviceBranch!}" />
									<div class="row">
										<div class="form-group col-md-4">
											<label class="col-lg-4 control-label">类型</label>
										    <div class="col-lg-8 m-b-xs">
												<select name="catalog" class="form-control  chosen-select">
											   		<option></option>
												</select>
											</div>
		                                </div>
										<div class="form-group col-md-4">
											<label class="col-lg-4 control-label">状态</label>
										    <div class="col-lg-8 m-b-xs">
												<select name="status" class="form-control  chosen-select">
											   		<option></option>
												</select>
											</div>
		                                </div>
		                             </div>
		                             <div class="row">
										<div class="form-group col-md-4">
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
		                                <div class="form-group  col-md-4">
	                                    	<label class="col-lg-4 control-label">关键字</label>
	                                    	<div class="col-lg-8 m-b-xs">
	                                    		<input type="text" name="keyword" placeholder="关键字" class="input-sm form-control">
	                                    	</div>
										</div>
										<div class="form-group  col-md-4 ">
											<label class="col-lg-4 control-label"></label>
											<div class="col-lg-8 m-b-xs">
		                                    	<span class="input-group-btn">
		                                        	<button type="button" class="btn btn-sm btn-primary" onclick="search()"> 搜索</button>
		                                        	<button type="button" class="btn btn-sm btn-warning" onclick="exportExcel()"> 导出excel</button>
		                                        </span>
	                                        </div>
										</div>
									</div>
								</from>
								<script type="text/javascript">								
								
								</script>
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
			if (n.type == "advice_catalog") {
				$('select[name="catalog"]').append(new Option(n.name, n.code, false, false));
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
			url : '${base}/adv/list/all',
			postData:{leaddep:$("input[name=leaddep]").val()},
			mtype : 'GET',
			datatype : 'json',
			colModel : [ {
				label : '类别',
				name : 'catalog',
				width:65,
				formatter : function(value) {
					return decode(data, "advice_catalog", value);
				}
			}, {
				label : '建议牵头部门',
				name : 'leaddepna',
				width:100
			}, {
				label : '标题',
				name : 'summary',
				width:200,
				sortable : false,
				formatter : function (val, options, row){
					return "<a href=${base}/adv/comment/" + row['id'] + '>' + row['summary'] + '</a>';
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
				name : 'brchna',
				formatter:function(val,options,row){
					if(row['anony']) 
						return '匿名'; 
					else
						return val;
				}
			}, {
				label : ' 状态',
				name : 'status',
				width:55,
				formatter : function(value) {
					return decode(data, "advice_status", value);
				}
			}, {
				label : ' 提出时间',
				name : 'crttime',
				formatter:function(value){return new Date(value).Format('yyyy-MM-dd hh:mm:ss');}
			}, {
				label : ' 处理时间',
				name : 'cmttime',
				formatter:function(value){
					if(value)
						return new Date(value).Format('yyyy-MM-dd hh:mm:ss');
					else
						return "";
				}
			}],
			height : 300,
			pager : "#jqGridPager"
		});

		$("#jqGrid").navGrid("#jqGridPager", {
			refresh : true,
			align : "left",
			view : false,
			add : false,
			edit : false,
			del : false,
			search : false
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
	function search() {
		$("#jqGrid").jqGrid('setGridParam', {
			datatype : 'json',
			postData : {
				'keyword' : encodeURI($("input[name='keyword']").val()),
				'brchno' : encodeURI($("select[name='brchno']").val()),
				'leaddep' : encodeURI($("input[name='leaddep']").val()),
				'catalog' : encodeURI($("select[name='catalog']").val()),
				'status' : encodeURI($("select[name='status']").val())
			}
		}).trigger("reloadGrid");
	}
	function exportExcel() {
		   var params =  {
					'keyword' : encodeURI($("input[name='keyword']").val()),
					'brchno' : encodeURI($("select[name='brchno']").val()),
					'leaddep' : encodeURI($("input[name='leaddep']").val()),
					'catalog' : encodeURI($("select[name='catalog']").val()),
					'status' : encodeURI($("select[name='status']").val())
				};
            var form = $('<form method="POST" action="${base}/adv/export/all">');
            $.each(params, function(k, v) {
                form.append($('<input type="hidden" name="' + k +
                        '" value="' + v + '">'));
            });
            $('body').append(form);
            form.submit(); //自动提交
	}
</script>
</@page>