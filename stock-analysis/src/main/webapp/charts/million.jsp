<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@include file="/commons/taglibs.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <title>Million Points Demo</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width">
    <script type="text/javascript" src="${base}/js/HumbleSoftware/demos/yepnope.js"></script>
    <link rel="stylesheet" href="${base}/js/HumbleSoftware/demos/demos.css" type="text/css" />
    <link rel="stylesheet" href="${base}/js/HumbleSoftware/css/templates/timeseries.css" type="text/css" />
    <link rel="stylesheet" href="${base}/js/HumbleSoftware/css/flotr.css" type="text/css" />
</head>
<body>
  <div id="content">
    <h3>Million Points Demo:</h3>
    <div class="controls">
        <span><a href="">Basic</a></span>
        <span><a href="#crazy" onclick="window.location.hash = 'crazy'; window.location.reload();">Crazy</a></span>
        <span><a href="#minmax" onclick="window.location.hash = 'minmax'; window.location.reload();">MinMax</a></span>
    </div>
    <div class="million" id="demo" class="million"></div>
   
  </div>

  <script type="text/javascript" src="${base}/charts/million.js"></script>
  <script type="text/javascript" src="${base}/charts/includes.js"></script>

</body>
</html>
