<#include "../include/page.tpl" />
<@page title="专题管理" js=["plugins/jqGrid/i18n/grid.locale-cn.js",
"plugins/jqGrid/jquery.jqGrid.min.js",
"plugins/jqGrid/jqGrid.global.js",
"plugins/select2/select2.min.js"]
css=["plugins/jqGrid/ui.jqgrid-bootstrap.css",
"plugins/jqGrid/ui.jqgrid-bootstrap-ui.css",
"plugins/select2/select2.min.css"]>

<div class="wrapper wrapper-content  animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox ">
				<div class="ibox-title">
					<h5>专题管理</h5>
				</div>
				<div class="ibox-content">
					    <table id="jqGrid"></table>
					    <div id="jqGridPager"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$('#jqGrid').jqGrid({
			url: '${base}/subject/list',
			mtype: 'GET',
			datatype: 'json',
            colModel: [
                { label: 'ID', name: 'id', key: true },
                { label: '主题', name: 'topic',formatter:function(val,opts,row){
                	return '<a href="${base}/subject/edit/'+ row['id'] +'">'+ val +  '</a>';
                }},
                { label:'是否可用', name: 'enable',sortable:false, formatter:function(val){
						return '<input type=checkbox ' + (val ? 'checked':'') + ' disabled />';                	
                }},
                { label: '顺序', name: 'order' },
            ],
            height:400,
            pager: "#jqGridPager"
	});
	
    $("#jqGrid").navGrid("#jqGridPager",
            { refresh: true, align: "left", view: false ,add:false,edit:false, del:false,search:false}
        ).navButtonAdd('#jqGridPager',
            {
                buttonicon: "glyphicon-plus",
                title: "新增",
                caption: "新增",
                position: "last",
                onClickButton: function(){
                	location.href="${base}/subject/add";
                }
            }).navButtonAdd('#jqGridPager',
            {
                buttonicon: "glyphicon-trash",
                title: "删除",
                caption: "删除",
                position: "last",
                onClickButton: function(){
    				var ids = $('#jqGrid').getGridParam('selrow');
    				if(!ids || ids.length == 0 ){
    					toastr["info"]("请选择一行记录","提示");
    					return ;
    				}
    				var row = $('#jqGrid').getRowData(ids);
	    			deleteRow(ids);
                }
            });
});

function deleteRow(ids){
	jQuery.get(
			'${base}/subject/delete/' + ids,				
			function(ret){
				if(ret.type == "success"){
				    $("#jqGrid").jqGrid().trigger("reloadGrid"); 
				}
				toastr[ret.type](ret.message,ret.name);
			}		
		);
}

</script>
</@page>
