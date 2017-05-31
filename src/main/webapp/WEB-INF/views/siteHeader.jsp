<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" %>



<!-- Header -->
<header class="container header">
    <div class="row">
        <div class="col-lg-6 col-md-6 col-sm-6 logo">
            <a href="/main">소프트웨어<span>팩토리</span></a>
            <div class="small-logo">SoFAC : <i>Software Factory</i></div>
        </div>
        <div class="col-lg-6 col-md-6 col-sm-6 text-right login">

        <% String userName = (String) request.getSession().getAttribute("UserName");
            if (userName == null){
        %>
            <b>CASE OPEN</b> : <a href="javascript:void(0);"
                                                                                           data-toggle="modal"
                                                                                           data-target="#authorizationModal">고객
                아이디로 로그인하세요</a>
        <%
        } else {
        %>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <span class="avatar-welcome"><%out.print((String) request.getSession().getAttribute("UserName"));%> <a href="/cabinet/"> 님 접속을 환영합니다.</a></span>
                    <a href="javascript:void(0);" class="dropdown-toggle avatar" data-toggle="dropdown"><i
                            class="fa fa-user"></i></a>
                    <ul class="dropdown-menu">
                        <li class="dropdown-menu-header text-center">설정</li>
                        <li><a href="/cabinet/"><i class="fa fa-user"></i> MY</a></li>
                        <li><a href="/cabinet/settings/"><i class="fa fa-cog"></i> Settings</a></li>
                        <li><a href="<c:url value="/logout" />"><i class="fa fa-lock"></i> 로그 아웃</a></li>
                    </ul>
                </li>
            </ul>
        <%}%>
        </div>
    </div>
</header>
<!-- #End Header -->
