<#include "../include/page.tpl" />
<@page title="角色管理" js=["plugins/dataTables/datatables.min.js",
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
						<div><a class="btn btn-primary" href="${base}/role/add">新建角色</a></div>
						<table id="table_list"
							class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>ID</th>
									<th>名称</th>
									<th>操作</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th>ID</th>
									<th>名称</th>
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
			url: '${base}/role/list',
			type: 'GET'
		},
        dom: '<"html5buttons"B>lTfgitp',
		buttons: ['excel','print'],
		columns: [{
			data: 'roleid'
		}, {
			data: 'rolename'
		}],
		columnDefs: [{
			"targets": 2,
			"width": 100,
			"render": function(a, b, c, d) {
				var html = "<div class=\"btn-group btn-group-xs\" role=\"group\">";
				html += "<a  class=\"btn btn-link btn-default\" href='${base}/role/edit/" + c.roleid + "'>修改</a>";
				html += "<a class=\"btn btn-link btn-danger btn\" href='${base}/role/delete/" + c.roleid + "'>删除</a>";
				html += "</div>";
				return html;
			}
		}]
	});
});
</script>
</@page>
