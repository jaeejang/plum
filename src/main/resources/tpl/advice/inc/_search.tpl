<#macro show_search  >
								<!-- search area -->
								<form name="search-form" class="form-horizontal" action="${base}/adv/edit" method="POST">
									<div class="row">
										<div class="form-group col-md-4">
											<label class="col-lg-4 control-label">类型</label>
										    <div class="col-lg-8 m-b-xs">
												<select name="catalog" class="form-control  chosen-select">
											   		<option></option>
												</select>
											</div>
		                                </div>
										<div class="form-group col-md-4">
											<label class="col-lg-4 control-label">牵头部门</label>
										    <div class="col-lg-8 m-b-xs">
												<select name="leaddep" class="form-control chosen-select">
												   		<option></option>
														<#if adviceBranch??>
															<#list adviceBranch as branch>
																	<option value="${branch.adviceBrchno}" >${branch.adviceBrchna}(${branch.adviceBrchno})</option>
															</#list>
														</#if>
													</select>
											</div>
		                                </div>
										<div class="form-group col-md-4">
											<label class="col-lg-4 control-label">状态</label>
										    <div class="col-lg-8 m-b-xs">
												<select name="status" class="form-control  chosen-select">
											   		<option></option>
												</select>
											</div>
		                                </div>
		                             </div>
		                             <div class="row">
										<div class="form-group col-md-4">
											<label class="col-lg-4 control-label">提出部门</label>
										    <div class="col-lg-8 m-b-xs">
												<select name="brchno" class="form-control chosen-select">
												   		<option></option>
														<#if branches??>
															<#list branches as branch>
																	<option value="${branch.brchno}" class="select2-results__group">${branch.brchna}(${branch.brchno})</option>
																	<#list branch.children as child>
																		<option value="${child.brchno}" class="select2-results__option">${child.brchna}(${child.brchno})</option>
																	</#list>
															</#list>
														</#if>
													</select>
											</div>
		                                </div>
		                                <div class="form-group  col-md-4">
	                                    	<label class="col-lg-4 control-label">关键字</label>
	                                    	<div class="col-lg-8 m-b-xs">
	                                    		<input type="text" name="keyword" placeholder="关键字" class="input-sm form-control">
	                                    	</div>
										</div>
										<div class="form-group  col-md-4 ">
											<label class="col-lg-4 control-label"></label>
											<div class="col-lg-8 m-b-xs">
		                                    	<span class="input-group-btn">
		                                        	<button type="button" class="btn btn-sm btn-primary" onclick="search()"> 搜索</button>
		                                        	<button type="button" class="btn btn-sm btn-warning" onclick="exportExcel()"> 导出excel</button>
		                                        	<button type="button" class="btn btn-sm" onclick="reset()"> 重置</button>
		                                        </span>
	                                        </div>
										</div>
									</div>
								</from>
								<script type="text/javascript">								
								function deleteRow(ids){
									jQuery.get(
											'${base}/adv/delete/' + ids,				
											function(ret){
												if(ret.type == "success"){
													search();
												}
												toastr[ret.type](ret.message,ret.name);
											}		
										);
								}
								function search() {
									$("#jqGrid").jqGrid('setGridParam', {
										datatype : 'json',
										postData : {
											'keyword' : encodeURI($("input[name='keyword']").val()),
											'brchno' : encodeURI($("select[name='brchno']").val()),
											'leaddep' : encodeURI($("select[name='leaddep']").val()),
											'catalog' : encodeURI($("select[name='catalog']").val()),
											'status' : encodeURI($("select[name='status']").val())
										}
									}).trigger("reloadGrid");
								}
								function exportExcel() {
									   var params =  {
												'keyword' : encodeURI($("input[name='keyword']").val()),
												'brchno' : encodeURI($("select[name='brchno']").val()),
												'leaddep' : encodeURI($("select[name='leaddep']").val()),
												'catalog' : encodeURI($("select[name='catalog']").val()),
												'status' : encodeURI($("select[name='status']").val())
											};
							            var form = $('<form method="POST" action="${base}/adv/export/all">');
							            $.each(params, function(k, v) {
							                form.append($('<input type="hidden" name="' + k +
							                        '" value="' + v + '">'));
							            });
							            $('body').append(form);
							            form.submit(); //自动提交
								}
								</script>
</#macro>