<#include "../include/page.tpl" />
<@page title="我的建议" js=["plugins/jqGrid/i18n/grid.locale-cn.js",
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
					<h5>我的建议</h5>
				</div>
				<div class="ibox-content">
								<!-- search area -->
								<form name="search-form" class="form-horizontal" method="POST">
									<div class="row">
										<div class="form-group col-md-4">
											<label class="col-lg-4 control-label">专题</label>
										    <div class="col-lg-8 m-b-xs">
											<select  name="subject" class="form-control chosen-select" >
													<option></option>
													<#if subjects??>
														<#list subjects as subject>
															<option value="${subject.id!}" >${subject.topic!}</option>
														</#list>
													</#if>
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
		                                        	<button type="button" class="btn btn-sm btn-primary" onclick="search()" title="搜索"  > 搜索</button>
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
		$('#jqGrid').jqGrid({
			url : '${base}/tpc/list/my',
			mtype : 'GET',
			datatype : 'json',
			colModel : [ {
				label : '专题',
				name : 'topic',
				width:100
			},  {
				label : '标题',
				name : 'summary',
				width:200,
				sortable : false,
				formatter : function (val, options, row){
					return "<a href=${base}/tpc/view/" + row['id'] + '>' + row['summary'] + '</a>';
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
				label : ' 日期',
				name : 'crttime',
				formatter:function(value){return new Date(value).Format('yyyy-MM-dd hh:mm:ss');}
			}, ],
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
		}). navButtonAdd('#jqGridPager', {
			buttonicon : "glyphicon-trash",
			title : "删除",
			caption : "删除",
			position : "last",
			onClickButton : function() {
				//alert($('#jqGrid').getGridParam('selarrrow'));
				var ids = $('#jqGrid').getGridParam('selrow');
				if(!ids || ids.length == 0 ){
					toastr["info"]("请选择一行记录","提示");
					return ;
				}
				var row = $('#jqGrid').getRowData(ids);
				deleteRow(ids);
			}
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
		$('select').select2('val','All');
		$('form[name=search-form]')[0].reset();
	}
	function search() {
		$("#jqGrid").jqGrid('setGridParam', {
			datatype : 'json',
			postData : {
				'keyword' : encodeURI($("input[name='keyword']").val()),
				'subject' : encodeURI($("select[name='subject']").val()),
				'status' : encodeURI($("select[name='status']").val())
			}
		}).trigger("reloadGrid");
	}
</script>
</@page>
