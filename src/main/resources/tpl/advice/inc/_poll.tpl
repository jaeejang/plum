<#assign base=request.contextPath />
<#macro poll>
                     <div class="row">
                     	<div class="pull-right">
                     		<a class="btn btn-info btn-circle" href="javascript:vote(1)"><i class="fa fa-thumbs-o-up"></i><span class="badge pull-right" id="polls">${advice.polls!}</span></a>
                     		<a class="btn btn-info btn-circle" href="javascript:vote(0)"><i class="fa fa-thumbs-o-down"></i><span class="badge  pull-right" id="polldown">${advice.polldown!}</span></a>
                     	</div>
                     	<hr/>
                     </div>
                     <script type="text/javascript">
                     	function vote(type){
							jQuery.post(
								'${base}/adv/poll',
								{
									id : $("input[name=id]").val(),
									type : type
								},
								function(data){
									if(data.type == "success"){
										$("#polls").text(data.polls);
										$("#polldown").text(data.polldown);
									}
									else{
										toastr[data.type](data.message);
									}
								}
							);
						}
                     </script>
</#macro>