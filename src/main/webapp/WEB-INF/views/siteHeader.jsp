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
            <div class="orange-button"><a href=""><img src="/resources/indexPage/images/home-button.png" alt=""/></a>
            </div>
            <a href="/">소프트웨어<span>팩토리</span></a>
            <div class="small-logo">SoFAC : <i>Software Factory</i></div>

        </div>
        <div class="col-lg-6 col-md-6 col-sm-6 text-right login"><b>CASE OPEN</b> : <a href="javascript:void(0);"
                                                                                       data-toggle="modal"
                                                                                       data-target="#authorizationModal">고객
            아이디로 로그인하세요</a></div>
    </div>
</header>
<!-- #End Header -->
