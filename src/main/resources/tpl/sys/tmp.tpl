<#include "../include/page.tpl" />
<@page title="建议回复" js=["plugins/dataTables/datatables.min.js",
"plugins/dataTables/datatables.global.js",
"plugins/dataTables/dataTables.select.min.js"]
css=["plugins/dataTables/datatables.min.css",
"plugins/dataTables/dataTables.bootstrap.min.css",
"plugins/dataTables/select.dataTables.min.css"]>

<div class="wrapper wrapper-content  animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox ">
				<div class="ibox-content">
					<div class="table-responsive">
						<table id="table_list"
							class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>季度</th>
									<th>管理机构</th>
									<th>支行</th>
									<th>姓名</th>
									<th>创新类别</th>
									<th>建议牵头部门</th>
									<th>创新概述</th>
									<th>查看</th>
									<th>点赞</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
function vote(obj){
	var id = $(obj).attr('data-src');
	//$(obj).replaceWith(id);
	jQuery.post(
		'${base}/adv/vote',
		{
			adviceid : id
		},
		function(data){
			if(data.ret == 0){
				toastr["success"]("投票成功","感谢您珍贵的一票！");
				$(obj).replaceWith(data.count);
			}
		}		
	);
}
$(document).ready(function() {
	$.ajax({
		url:'${base}/jsonp/dict',
		type: "get",  
        async: false,  
        dataType: "jsonp",
        jsonp: "callbackparam",
        jsonpCallback: "jsonpCallback"
	});
});

function jsonpCallback(data){
	$('#table_list').DataTable({
		serverSide: true,
        searchDelay:500,
		ajax: {
			url: '/adv/list/all',
			type: 'GET'
		},
        dom: '<"html5buttons"B>lTfgitp',
        aLengthMenu: [[50, 100, 300, -1], ["50", "100", "300", "所有"]],
		buttons: [{
			extend:'excel',
			text:'Excel'
		},{
			extend:'print',
			text:'打印'
		}],
		columns: [{
			data: 'crtdt'
		},{
			data: 'upbrchna'
		}, {
			data: 'brchna'
		}, {
			data: 'crtusrna'
		}, {
			data: 'catalog',
			render:function(value){
				return decode(data,"advice",value);
			}
		},{
			data: 'leaddepna'
		},{
			data: 'summary'
		}],
		columnDefs: [{
			"targets": 7,
			"width": 15,
			"render": function(a, b, c, d) {
				var html = "<a class=\"btn btn-default btn-xs\" href='${base}/show/view/"+ c.id+"'>";
				html += "<span class=\"fa fa-external-link\"></span>";
				html += "</a>";
				return html;
			}
		},{
			"targets": 8,
			"width": 15,
			"render": function(a, b, c, d) {
				var html;
				if(c.voter){
					html = c.polls;
				}
				else{

					html = "<button name='vote' class=\"btn btn-default btn-xs\" data-src="+ c.id +" onclick=\"vote(this)\">";
					html += "<span class=\"glyphicon glyphicon-thumbs-up\"></span>";
					html += "</button>";
				}
				return html;
			}
		}]
	});
}
</script>
</@page>
