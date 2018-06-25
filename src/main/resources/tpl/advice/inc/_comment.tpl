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
				<#if  commets?? && (commets?size > 0)>
						<div class="panel-group" id="accordion">
							<#list commets as comm>
								<#if advice.anony=false ||  advice.crtusr == _user.username ||  comm.crtusr == _user.username>
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
			                                    <div class="radio radio-info radio-inline">
		                                            <input type="radio" id="inlineRadio1_${comm.id}" value="1" data-src="${comm.id}" name="satisfy${comm.id}" 
		                                            <#if comm.satisfy?? && comm.satisfy==1>checked</#if>
		                                            <#if comm.satisfy?? ||  advice.crtusr != _user.username>disabled</#if>>
		                                            <label for="inlineRadio1"> 满意 </label>
		                                        </div>
		                                        <div class="radio radio-inline">
		                                            <input type="radio" id="inlineRadio2_${comm.id}" value="0" data-src="${comm.id}"  name="satisfy${comm.id}"
		                                            <#if comm.satisfy?? && comm.satisfy==0>checked</#if>
		                                            <#if comm.satisfy?? ||  advice.crtusr != _user.username>disabled</#if>>
		                                            <label for="inlineRadio2"> 一般 </label>
		                                        </div>
		                                        <div class="radio radio-warning radio-inline">
		                                            <input type="radio" id="inlineRadio3_${comm.id}" value="-1" data-src="${comm.id}"  name="satisfy${comm.id}"
		                                            <#if comm.satisfy?? && comm.satisfy==-1>checked</#if>
		                                            <#if comm.satisfy?? ||  advice.crtusr != _user.username>disabled</#if>>
		                                            <label for="inlineRadio3"> 不满意 </label>
		                                        </div>
		                                    </div>
		                                  </div>
	                                    </div>
	                                </div>
	                            </div>
	                          </#if>
							</#list>
						</div>
				<#else>
					<div class="alert alert-warning" role="alert">没有反馈内容.</div>
				</#if>
			</div>
		</div>
		<script type="text/javascript">
		$(document).ready(function(){
			$("#accordion .panel").on('close.bs.alert',function(){
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
			$(".radio-inline input:radio").on('click',function(){
				var name = $(this).attr("name");
				var value = $('input[name=' +name +']:checked').val();
				jQuery.post(
						'${base}/adv/survey',
						{
							id : $(this).attr("data-src"),
							satisfy: value
						},
						function(data){
							if(data.type == "success"){
								//TODO
								$('input[name=' +name +']').attr("disabled","disabled");
							}
						}		
					);
			})
		});

		</script>
</#macro>