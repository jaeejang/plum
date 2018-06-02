<#include "../include/page.tpl" />
<@page title="登陆" js=["plugins/dataTables/datatables.min.js",
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
						<div><a class="btn btn-primary" href="${base}/user/add">新建用户</a></div>
						<table id="table_list"
							class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>登录用户名</th>
									<th>名称</th>
									<th>所属机构号</th>
									<th>机构名称</th>
									<th>锁定</th>
									<th>操作</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th>登录用户名</th>
									<th>名称</th>
									<th>所属机构号</th>
									<th>机构名称</th>
									<th>锁定</th>
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
		serverSide: true,
		ajax: {
			url: '${base}/user/list',
			type: 'GET'
		},
        dom: '<"html5buttons"B>lTfgitp',
        aLengthMenu: [50, 100, 300],
		buttons: ['excel','print'],
		columns: [{
			data: 'username'
		}, {
			data: 'fullname'
		}, {
			data: 'brchno'
		}, {
			data: 'brchna'
		}, {
			data: 'lock'
		}, {
			data: 'username'
		}],
		columnDefs: [{
			"targets": 5,
			"width": 100,
			"render": function(a, b, c, d) {
				var html = "<div class=\"btn-group btn-group-xs\" role=\"group\">";
				html += "<a  class=\"btn btn-link btn-default\" href='${base}/user/edit/" + c.username + "'>修改</a>";
				html += "<a class=\"btn btn-link btn-danger btn\" href='${base}/user/delete/" + c.username + "'>删除</a>";
				html += "</div>";
				return html;
			}
		}]
	});
});
</script>
</@page>
