<#include "include/page.tpl" />
<@page title="首页" js=[
"plugins/flot/jquery.flot.js",
"plugins/flot/jquery.flot.tooltip.min.js",
"plugins/flot/jquery.flot.resize.js",
"plugins/flot/jquery.flot.pie.js"
"plugins/flot/jquery.flot.time.js"] css=[]>

<div class="wrapper wrapper-content">
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox ">
				<div class="col-lg-6">
					<div class="ibox">
	                    <div class="ibox-title">
	                        <h5>欢迎进入江南意见建议平台</h5>
	                    </div>
	                    <div class="ibox-content">
	                    	<p>
	                    		Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.
                                Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.
	                    	</p>
	                    </div>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="ibox">
	                    <div class="ibox-title">
	                        <h5>创新图表</h5>
	                    </div>
	                    <div class="ibox-content">
	                        <div class="flot-chart" id="flot-bar-chart">
	                        </div>
	                    </div>
	                </div>
                </div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">


	$(function(){
		/*
		var barOptions = {
			series : {
				bars : {
					show : true,
					barWidth : 0.6,
					fill : true,
					fillColor : {
						colors : [ {
							opacity : 0.8
						}, {
							opacity : 0.8
						} ]
					}
				}
			},
			xaxis : {
				tickDecimals : 0
			},
			colors : [ "#1ab394" ],
			grid : {
				color : "#999999",
				hoverable : true,
				clickable : true,
				tickColor : "#D4D4D4",
				borderWidth : 0
			},
			legend : {
				show : false
			},
			tooltip : true,
			tooltipOpts : {
				content : "x: %x, y: %y"
			}
		};
		var barData = {
			label : "bar",
			data : [ [ 1, 34 ], [ 2, 25 ], [ 3, 19 ], [ 4, 34 ],
					[ 5, 32 ], [ 6, 44 ] ]
		};
		$.plot($("#flot-bar-chart"), [ barData ], barOptions);
		*/
	});
</script>
</@page>
