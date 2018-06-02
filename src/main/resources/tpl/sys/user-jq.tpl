<#include "../include/page.tpl" />
<@page title="登陆" js=["plugins/jqGrid/i18n/grid.locale-cn.js",
"plugins/jqGrid/jquery.jqGrid.min.js",
"plugins/jqGrid/jqGrid.global.js"]
css=["plugins/jqGrid/ui.jqgrid-bootstrap.css"]>

<div class="wrapper wrapper-content  animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox ">
				<div class="ibox-title">
					<h5>用户管理</h5>
				</div>
				<div class="ibox-content">
					<div class="table-responsive">
					    <table id="jqGrid"></table>
					    <div id="jqGridPager"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {

	$('#jqGrid').jqGrid({
			url: '${base}/user/list-jq',
			type: 'GET',
			datatype: 'jsonp',
            colModel: [
                { label: '工号', name: 'username', key: true, width: 75 },
                { label: '姓名', name: 'fullname', width: 150 },
                { label: '所属机构', name: 'brchno', width: 150 },
                { label: '机构名称', name: 'brchna', width: 150 },
                { label:'锁定', name: 'lock', width: 150 },
                { label:'操作', name: 'username'}
            ],
            height: 250,
            rowNum: 20,
            pager: "#jqGridPager"
	});
});
</script>
</@page>
