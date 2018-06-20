<#macro show_comment  delete=false>
		<!-- 意见反馈展示 -->
		<div class="ibox float-e-margins">
			<div class="ibox-title">
					<h5>
						创新建议 <small>反馈</small>
					</h5>
					<div class="ibox-tools">
                      <a class="collapse-link">
                          <i class="fa fa-chevron-up"></i>
                      </a>
                  </div>
			</div>
			<div class="ibox-content">
				<#if (advice.anony=false ||  advice.crtusr == _user.username) && commets?? && (commets?size > 0)>
						<div class="panel-group" id="accordion">
							<#list commets as comm>
								<div class="panel  panel-info" id="panel${comm.id}">
	                                 <div class="panel-heading">
	                                         <a data-toggle="collapse" data-parent="#accordion" href="#collapse${comm.id}">${comm.title[0..*30]}</a>
	                                         <#if delete>
						                     <button type="button" class="close"  data-target="#panel${comm.id}"  data-dismiss="alert" title="删除">
											   <span aria-hidden="true">&times;</span><span class="sr-only">删除</span>
											</button>
											</#if>
	                                 </div>
									<div id="collapse${comm.id}" class="panel-collapse  collapse <#if  comm?index == 0>in</#if>">
	                                    <div class="panel-body">
	                                       ${comm.content!}
	                                    </div>
	                                    <div class="panel-footer">${comm.crttime?string["yyyy.MM.dd, HH:mm"]}</div>
	                                </div>
	                            </div>
							</#list>
						</div>
				</#if>
			</div>
		</div>
		<#if delete>
		<script type="text/javascript">
		$(document).ready(function(){
			$(".panel-info").on('close.bs.alert',function(){
				var panel = $(this);
				$.ajax({
					url:'${base}/adv/delComment/' + panel.attr("id").substr(5),
					datatype:'json'
				}).done(function (ret) {
					if(ret.type == "success"){
						panel.remove();
					}
					toastr[ret.type](ret.message,ret.name);
	       		 });
				return false;
			});
		});
		</script>
		</#if>
</#macro>