<#include "../include/page.tpl" />
<@page title="功能管理" js=["plugins/jqGrid/i18n/grid.locale-cn.js",
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
					<h5>功能管理</h5>
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
			url: '${base}/func/list',
			mtype: 'GET',
			datatype: 'json',
            colModel: [
                { label: 'ID', name: 'funid', key: true },
                { label: '名称', name: 'funcname',formatter:function(val,opts,row){
                	return '<a href="${base}/func/edit/'+ row['funid'] +'">'+ val +  '</a>';
                }},
                { label: '图标', name: 'icon'},
                { label: '上级菜单', name: 'superid' },
                { label: '顺序', name: 'order' },
                { label: '路径', name: 'path' }
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
                	location.href="${base}/func/add";
                }
            }).navButtonAdd('#jqGridPager',
            {
                buttonicon: "glyphicon-trash",
                title: "删除",
                caption: "删除",
                position: "last",
                onClickButton: function(){
                	
                }
            });
});

function search(){
    $("#jqGrid").jqGrid('setGridParam',{  
        datatype:'json'
    }).trigger("reloadGrid"); 
}
</script>
</@page>
