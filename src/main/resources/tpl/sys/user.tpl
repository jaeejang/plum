<#include "../include/page.tpl" />
<@page title="登陆" js=["plugins/jqGrid/i18n/grid.locale-cn.js",
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
					<h5>用户管理</h5>
				</div>
				<div class="ibox-content">
						<div class="row ">
								<!-- search area -->
								<form class="form-horizontal">
									<div class="form-group col-lg-5">
										<label class="col-lg-4 control-label">所属机构</label>
									    <div class="col-lg-8 m-b-xs"><select name="brchno" class="form-control chosen-select">
									   		<option></option>
											<#if branches??>
												<#list branches as branch>
														<option value="${branch.brchno}" class="select2-results__group">${branch.brchna}(${branch.brchno})</option>
														<#list branch.children as child>
															<option value="${child.brchno}" class="select2-results__option">${child.brchna}(${child.brchno})</option>
														</#list>
												</#list>
											</#if>
										</select></div>
	                                </div>
	                                <div class="form-group col-lg-5">
	                                    <div class="input-group">
	                                    	<input type="text"  name="keyword" placeholder="关键字" class="input-sm form-control"> 
	                                    	<span class="input-group-btn">
	                                        	<button type="button" class="btn btn-sm btn-primary" onclick="search()"> 搜索</button>
	                                        </span></div>
	                                </div>
								</form>
						</div>
					    <table id="jqGrid"></table>
					    <div id="jqGridPager"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$(".chosen-select").select2({
		placeholder: '请选择',
	    allowClear: true,
		templateResult: function (data) {    
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
			url: '${base}/user/list',
			mtype: 'GET',
			datatype: 'json',
            colModel: [
                { label: '工号', name: 'username', key: true },
                { label: '姓名', name: 'fullname',formatter:function(val,opts,row){
                	return '<a href="${base}/user/edit/'+ row['username'] +'">'+ val +  '</a>';
                }},
                { label: '所属机构', name: 'brchno'},
                { label: '机构名称', name: 'brchna' },
                { label:'锁定', name: 'lock',sortable:false, formatter:function(val){
						return '<input type=checkbox ' + (val ? 'checked':'') + ' disabled />';                	
                }}
            ],
            height:370,
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
                	location.href="${base}/user/add";
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
			'${base}/user/delete/' + ids,				
			function(ret){
				if(ret.type == "success"){
					search();
				}
				toastr[ret.type](ret.message,ret.name);
			}		
		);
}
function search(){
    $("#jqGrid").jqGrid('setGridParam',{  
        datatype:'json',  
        postData:{
        	'keyword':encodeURI($("input[name='keyword']").val()),
        	'brchno': encodeURI($("select[name='brchno']").val())
        }
    }).trigger("reloadGrid"); 
}
</script>
</@page>
