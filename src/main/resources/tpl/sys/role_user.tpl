<#include "../include/page.tpl" />
<@page title="角色编辑" js=["plugins/dataTables/datatables.min.js",
	"plugins/dataTables/datatables.global.js",
	"plugins/dataTables/dataTables.select.min.js"]
css=["plugins/dataTables/datatables.min.css",
	"plugins/dataTables/dataTables.bootstrap.min.css",
	"plugins/dataTables/select.dataTables.min.css"]>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<div class="float-e-margins">
				<div class="ibox-title">
					<h5>
						角色编辑 <small>编辑角色并提交</small>
					</h5>
				</div>
			</div>
			<div class="ibox-content">
				<div class="tabs-container">
					<ul class="nav nav-tabs">
						<li><a href="${base}/role/edit/${roleid!}">基本信息</a></li>
						<li><a href="${base}/role/func/${roleid!}">功能权限</a></li>
						<li class="active"><a href="#">用户</a></li>
					</ul>
					<div class="tab-content ">
						<div id="tab-1" class="tab-pane active">
							<div class="panel-body">
								<div class="table-responsive">
									<div>
										<button class="btn btn-primary" data-toggle="modal"
											data-target="#selectModal">添加用户</button>
										<div class="modal inmodal fade" id="selectModal" tabindex="-1"
											role="dialog" aria-hidden="true">
											<div class="modal-dialog modal-lg">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal">
															<span aria-hidden="true">&times;</span><span
																class="sr-only">Close</span>
														</button>
														<h4 class="modal-title">添加用户</h4>
														<small class="font-bold">为用户添加角色</small>
														
													</div>
													<div class="modal-body">
														<table id="selector"
															class="table table-striped table-bordered table-hover">
															<thead>
																<tr>
																	<th>用户名</th>
																	<th>名称</th>
																	<th>所属机构</th>
																	<th>机构名称</th>
																</tr>
															</thead>
														</table>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-white"
															data-dismiss="modal">Close</button>
														<button type="button" class="btn btn-primary">Save
															changes</button>
													</div>
												</div>
											</div>
										</div>
										<button class="btn btn-primary">删除用户</button>
									</div>
									<table id="table_list"
										class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th></th>
												<th>用户名</th>
												<th>名称</th>
												<th>所属机构</th>
												<th>机构名称</th>
											</tr>
										</thead>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$('#selectModal').on('hide.bs.modal',function() {
			$('#selector').DataTable({
				serverSide: true,
				ajax: {
					url: '${base}/user/list',
					type: 'GET'
				},
		        dom: '<"html5buttons"B>lTfgitp',
				columns: [  {  
	                className: "td-checkbox",  
	                orderable : false,  
	                bSortable : false,  
	                data : "username",  
	                render : function(data, type, row, meta) {  
	                    var content = '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">';  
	                    content += '    <input type="checkbox" class="group-checkable" value="' + data + '" />';  
	                    content += '    <span></span>';  
	                    content += '</label>';  
	                    return content;  
	                }  
	            },
		        {
					data: 'username'
				}, {
					data: 'fullname'
				}, {
					data: 'brchno'
				}, {
					data: 'brchna'
				}]
			});
	    });
		
		$('#table_list').DataTable({
			serverSide : true,
			ajax : {
				url : '${base}/role/user/list/${roleid}',
				type : 'GET'
			},
			dom : '<"html5buttons"B>lTfgitp',
			buttons : [ 'excel', 'print' ],
			columns : [ {  
                className: "td-checkbox",  
                orderable : false,  
                bSortable : false,  
                data : "username", 
                width: 20,
                render : function(data, type, row, meta) {  
                    var content = '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">';  
                    content += '    <input type="checkbox" class="group-checkable" value="' + data + '" />';  
                    content += '    <span></span>';  
                    content += '</label>';  
                    return content;  
                }  
            }, {
				data: 'username'
			}, {
				data: 'fullname'
			}, {
				data: 'brchno'
			}, {
				data: 'brchna'
			} ]
		});
	});
</script>
</@page>
