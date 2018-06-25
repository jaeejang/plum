<#include "../include/page.tpl" />
<#include "inc/_search.tpl" />
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
					<@show_search brchno=false export=false />
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
			url : '${base}/adv/list/my',
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

	function search() {
		$("#jqGrid").jqGrid('setGridParam', {
			datatype : 'json',
			postData : {
				'keyword' : encodeURI($("input[name='keyword']").val()),
				'leaddep' : encodeURI($("select[name='leaddep']").val()),
				'catalog' : encodeURI($("select[name='catalog']").val()),
				'status' : encodeURI($("select[name='status']").val())
			}
		}).trigger("reloadGrid");
	}
	
</script>
</@page>
