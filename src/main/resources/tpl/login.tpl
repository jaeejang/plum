<#include "include/base.tpl" />
<@basePage base_title="登陆" base_body="gray-bg">
<div class="row"  style="background:#fff; padding:20px 0 20px " >
		<div class=" col-lg-offset-1 col-lg-10">
			<span><img src="${base}/resources/images/login.jpg"  style="border-right:1px solid #000;display:inline" /></span>
			<span style="font:20px bold;margin-left:30px">江南意见建议平台</span>
		</div>
</div>
<div class="row" style="height:0">
	<div  style="z-index:10;position:relative;width:100%;height:0">
		<div class="loginColumns animated fadeInDown">
	        <div class="row ">
	            <div class="col-md-5 col-md-offset-7">
	                <div class="ibox-content">
	                    <form class="m-t" role="form" action="${base}/login" method="post">
	                        <div class="form-group">
	                            <input type="text" class="form-control" name="username" placeholder="用户名" value="jaee">
	                        </div>
	                        <div class="form-group">
	                            <input type="password" class="form-control" name="password" placeholder="密码" value="000000">
	                        </div>
	                        <button type="submit" class="btn btn-primary block full-width m-b">登录</button>
	                    </form>
	                </div>
	            </div>
	        </div>
	    </div>
    </div>
</div>
<div class="landing-page  pace-done">
	<div id="inSlider" class="carousel carousel-fade" data-ride="carousel">
	    <ol class="carousel-indicators">
	        <li data-target="#inSlider" data-slide-to="0" class="active"></li>
	        <li data-target="#inSlider" data-slide-to="1"></li>
	        <li data-target="#inSlider" data-slide-to="2"></li>
	        <li data-target="#inSlider" data-slide-to="3"></li>
	    </ol>
	    <div class="carousel-inner" role="listbox">
	        <div class="item active">
	            <div class="header-back one"></div>
	        </div>
	        <div class="item">
	            <div class="header-back two"></div>
	        </div>
	        <div class="item">
	            <div class="header-back three"></div>
	        </div>
	        <div class="item">
	            <div class="header-back four"></div>
	        </div>
	    </div>
	    <a class="left carousel-control" href="landing.html#inSlider" role="button" data-slide="prev">
	        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
	        <span class="sr-only">Previous</span>
	    </a>
	    <a class="right carousel-control" href="landing.html#inSlider" role="button" data-slide="next">
	        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
	        <span class="sr-only">Next</span>
	    </a>
	 </div>
</div>
<div class="row">
			<hr />
            <div class="col-md-5 col-md-offset-1">
                Copyright Jiangnan Rural Commerical Bank 
            </div>
            <div class="col-md-5 text-right">
               <small>© 2018-2020</small>
            </div>
        </div>


</@basePage>