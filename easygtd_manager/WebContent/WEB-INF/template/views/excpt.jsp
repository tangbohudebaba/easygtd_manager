﻿<%@ page contentType="text/html; charset=UTF-8"%><%@include file="inc/top.inc.jsp"%>
<% 
response.setHeader("Cache-Control","no-store"); 
response.setHeader("Pragrma","no-cache"); 
response.setDateHeader("Expires",0);
response.sendError(406);
%>