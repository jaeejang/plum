<#include "include/page.tpl" />
<@page title="首页" js=["plugins/dotdotdot/jquery.dotdotdot.min.js"] css=[]>

<div class="wrapper wrapper-content">
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox ">
				<div class="col-lg-12">
					<div class="ibox">
	                    <div class="ibox-title">
	                        <h5>欢迎进入江南意见建议平台</h5>
	                    </div>
	                    <div class="ibox-content">
	                    	<p style="text-indent:2em">
	                    		江南意见建议平是为实现意见建议和创新”金点子“网上进行输入、交办、分办、签收、签批、办理、答复、签发、抄送、督办、反馈和互动，提高意见建议办理工作效率，进一步提升部室工作的质量和效果，对促进新形势下的部室工作具有重要意义。
	                    	</p>
	                    	<p style="text-indent:2em">
		                    	以先进的网络技术为手段，建设一个意见建议平台，提供部室与各机构之间的信息交换，为办理、回复和督办提供服务，实现内容数据化、办理网络化、督办在线化、结果公开化，以提高管理的工作效率和质量，增强科学化管理水平。系统特点如下：<br>
							</p><p>
								1.瘦客户端方式,
								2.低成本应用,
								3.系统弹性大,
								4.网络通讯量小
							</p>
	                    </div>
					</div>
				</div>
				<div class="col-lg-12">
					<div class="ibox float-e-margins">
	                    <div class="ibox-title">
	                        <h5>专题</h5>
	                    </div>
	                    <div class="ibox-content">
	                    	<p class="subject-list">
                        	<#if subjects?? && (subjects?size > 0)>
                        	<#list subjects as subject>
                        		<#if (subject?counter > 10)>
                        		<#break>
                        		</#if>
		                        	<a href="${base}/tpc/${subject.id}" >
		                        	<button class="btn btn-outline btn-danger  dim "  data-container="body" data-toggle="popover" data-placement="bottom"
		                        	 data-content="${subject.summary!}">
			                                <i class="fa fa-envelope-o"></i> ${subject.topic!}
				                    </button>
				                    </a>
							</#list>
							<div class="ibox-footer">
								<div class="pull-right"><a href="${base}/tpc/show">&gt;&gt;更多</a></div>
							</div>
							<#else>
								<div class="alert alert-warning" role="alert">目前还没有专题.</div>
                            </#if>
                            </p>
	                    </div>
	                </div>
                </div>
			</div>
		</div>
	</div>
</div>
<style type="text/css">
	.subject-list button{
		width:160px
	}
	.subject-list a{
		display:inline-block;
		margin:0px 30px 112px 20px;
	}
	.popover-content{
		width:210px;
		height:100px;
	}
</style>
<script type="text/javascript">
	$(function(){
		$("[data-toggle='popover']").popover('show'); 
        $(".popover-content").dotdotdot({
            watch: 'window',
            wrap:'letter',
            ellipsis: ' ...'
        });

	});
</script>
</@page>
