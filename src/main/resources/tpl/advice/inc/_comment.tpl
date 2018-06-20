<#assign label = ["warning","danger","default","primary","success","info"] />
<#macro show_comment  delete=false>
		<!-- 意见反馈展示 -->
		<div class="ibox float-e-margins">
			<div class="ibox-title">
					<h5>
						建议反馈 <small>反馈内容</small>
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
								<div class="panel  panel-${label[comm?index%6]}" id="panel${comm.id}">
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
	                                    <div class="panel-footer">
	                                      <div class="clearfix">
	                                    	<div class="pull-right ">
	                                    	    <div>反馈时间：${comm.crttime?string["yyyy.MM.dd, HH:mm"]}</div>
	                                    	    <div>反馈部门：${comm.crtbrna!}</div> 
	                                    	</div>
		                                    <div class="pull-left">
	                                    		<#if comm.satisfy?? == false && _user.username == advice.crtusr>
		                                    	<span>
		                                    		<a class="btn btn-circle btn-primary" data-src="${comm.id!}"  data-value="true" href="javascript:void(0)" onclick="survey(this)"><i class="fa fa-check "></i></a>
		                                    		<i class="fa fa-smile-o fa-2x hidden"></i>
		                                    	</span>
		                                    	<span>
		                                    		<a class="btn btn-circle btn-danger"  data-src="${comm.id!}"   data-value="false" href="javascript:void(0)" onclick="survey(this)"><i class="fa fa-times "></i></a>
		                                    		<i class="fa fa-frown-o fa-2x hidden"></i>
		                                    	</span>
			                                    <#elseif comm.satisfy>
			                                    	<i class="fa fa-smile-o fa-2x "></i>
			                                    <#else>
			                                    	<i class="fa fa-frown-o fa-2x "></i>
			                                    </#if>
		                                    </div>
		                                  </div>
	                                    </div>
	                                </div>
	                            </div>
							</#list>
						</div>
				<#else>
					<div class="alert alert-warning" role="alert">没有反馈内容.</div>
				</#if>
			</div>
		</div>
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

		function survey(obj){
			jQuery.post(
				'${base}/adv/survey',
				{
					id : $(obj).attr("data-src"),
					satisfy: $(obj).attr("data-value")
				},
				function(data){
					if(data.type == "success"){
						//TODO
						$(obj).siblings().toggleClass('hidden');
						$(obj).parents(".pull-left").find('.btn-circle').toggleClass('hidden');
					}
				}		
			);
		}
		</script>
</#macro>