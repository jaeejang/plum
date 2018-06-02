<#include "include/base.tpl" />
<@base base_title="登陆" base_body="gray-bg">
<div class="loginColumns animated fadeInDown">
        <div class="row ">

            <div class="col-md-6">
                <h2 class="font-bold">Welcome to 江南银行创新平台</h2>
                <p>
                    Perfectly designed and precisely prepared admin theme with over 50 pages with extra new web app views.
                </p>
            </div>
            <div class="col-md-6">
                <div class="ibox-content">
                    <form class="m-t" role="form" action="${base}/login" method="post">
                        <div class="form-group">
                            <input type="text" class="form-control" name="username" placeholder="Username" value="jaee">
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" name="password" placeholder="Password" value="minyan">
                        </div>
                        <button type="submit" class="btn btn-primary block full-width m-b">Login</button>
						<!--
						<a href="login_two_columns.html#">
                            <small>Forgot password?</small>
                        </a>
                        <p class="text-muted text-center">
                            <small>Do not have an account?</small>
                        </p>
                        
                        <a class="btn btn-sm btn-white btn-block" href="register.html">Create an account</a>
                        -->
                    </form>
                </div>
            </div>
        </div>
        <hr/>
        <div class="row">
            <div class="col-md-6">
                Copyright © Jiangnan Rural Commercial Bank
            </div>
            <div class="col-md-6 text-right">
               <small>© 2017-2020</small>
            </div>
        </div>
    </div>
</@base>