<#include "../include/page.tpl" />
<@page title="菜单管理" js=["plugins/dataTables/datatables.min.js",
"plugins/dataTables/datatables.global.js",
"plugins/dataTables/dataTables.select.min.js"]
css=["plugins/dataTables/datatables.min.css",
"plugins/dataTables/dataTables.bootstrap.min.css",
"plugins/dataTables/select.dataTables.min.css"]>

<div class="wrapper wrapper-content  animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox ">
				<div class="ibox-content">
					<div class="table-responsive">
						<div><a class="btn btn-primary" href="/func/add">新建菜单</a></div>
						<table id="table_list"
							class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>ID</th>
									<th>名称</th>
									<th>图标</th>
									<th>上级菜单</th>
									<th>顺序</th>
									<th>路径</th>
									<th>操作</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th>ID</th>
									<th>名称</th>
									<th>图标</th>
									<th>上级菜单</th>
									<th>顺序</th>
									<th>路径</th>
									<th>操作</th>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$('#table_list').DataTable({
		serverSide: false,
		ajax: {
			url: '${base}/func/list',
			type: 'GET'
		},
        dom: '<"html5buttons"B>lTfgitp',
		buttons: ['excel','print'],
		columns: [{
			data: 'funid'
		}, {
			data: 'funcname'
		}, {
			data: 'icon'
		}, {
			data: 'superid'
		}, {
			data: 'order'
		}, {
			data: 'path'
		}],
		columnDefs: [{
			"targets": 6,
			"width": 100,
			"render": function(a, b, c, d) {
				var html = "<div class=\"btn-group btn-group-xs\" role=\"group\">";
				html += "<a  class=\"btn btn-link btn-default\" href='${base}/func/edit/" + c.funid + "'>修改</a>";
				html += "<a class=\"btn btn-link btn-danger btn\" href='${base}/func/delete/" + c.funid + "'>删除</a>";
				html += "</div>";
				return html;
			}
		}]
	});
});
</script>
</@page>
