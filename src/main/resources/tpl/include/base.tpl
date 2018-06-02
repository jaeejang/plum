<#compress>
<#macro base base_title="" base_body="" base_keywords="" base_js=[] base_css=[] notification=true>
<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="zh">

<head>
    <base id="base" href="${base}">
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>${base_title} | 江南银行创新平台 </title>
    <link rel="icon" href="/resources/images/favicon.ico" type="image/x-icon" />
    
    <link href="${base}/resources/styles/bootstrap.min.css" rel="stylesheet">
    <link href="${base}/resources/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${base}/resources/styles/animate.css" rel="stylesheet">
    <link href="${base}/resources/styles/style.css" rel="stylesheet">
<#list base_css as c>
	<link href="${base}/resources/styles/${c}" rel="stylesheet" >
</#list>

	<script type="text/javascript" src="${base}/resources/js/jquery-2.1.1.js"></script>
	<script type="text/javascript" src="${base}/resources/js/plugins/pjax/jquery.pjax.js"></script>
	
	<script type="text/javascript" src="${base}/resources/js/bootstrap.min.js"></script>
	<#if notification>
    <link rel="stylesheet" href="${base}/resources/styles/plugins/toastr/toastr.min.css">
	<script type="text/javascript" src="${base}/resources/js/plugins/toastr/toastr.min.js"></script>
	<script type="text/javascript">
		toastr.options = {
		  "closeButton": true,
		  "debug": true,
		  "progressBar": true,
		  "positionClass": "toast-top-right",
		  "showDuration": "300",
		  "hideDuration": "1000",
		  "timeOut": "5000",
		  "extendedTimeOut": "1000",
		  "showEasing": "swing",
		  "hideEasing": "linear",
		  "showMethod": "fadeIn",
		  "hideMethod": "fadeOut"
		}
	</script>
	</#if>
</head>
<body <#if (base_body)!="">class="${base_body}"</#if>>
	<#nested>
	<#list base_js as j>
	<script type="text/javascript" src="${base}/resources/js/${j}"></script>
	</#list>
	<#if error?exists>
	<script type="text/javascript">
		$(function(){
			toastr["error"]("错误","${error}");
		});
	</script>
	</#if>
</body>
</html>
</#macro>
</#compress>