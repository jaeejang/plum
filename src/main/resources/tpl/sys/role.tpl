<#include "../include/page.tpl" />
<@page title="角色管理" js=["plugins/jqGrid/i18n/grid.locale-cn.js",
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
					<h5>角色管理</h5>
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
			url: '${base}/role/list',
			mtype: 'GET',
			datatype: 'json',
            colModel: [
                { label: 'ID', name: 'roleid', key: true },
                { label: '名称', name: 'rolename',formatter:function(val,opts,row){
                	return '<a href="${base}/role/edit/'+ row['roleid'] +'" >' + val + ' </a>';
                }}
            ],
            height:320,
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
                	location.href="${base}/role/add";
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
			'${base}/role/delete/' + ids,				
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
