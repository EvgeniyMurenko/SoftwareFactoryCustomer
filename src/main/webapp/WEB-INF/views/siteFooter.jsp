<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" %>

<!-- Footer -->
<footer class="container footer mb20">
    <div class="row">
        <div class="col-md-6 bottom-informer">
            Policy of SoFAC<br/>
            <a href="<c:url value='/policy'/>"><span style="font-size: 12pt;">SoFAC 고객 정책</span></a>
        </div>
        <div class="col-md-6 text-right" style="color: #999; font-size: 9pt;">
            220-87-45112<br/>
            서울시 강남구 역삼동 해성빌딩 7층<br/>
            대표자 : 박상만
        </div>
    </div>
    <div class="mt20 text-center" style="color: #999; font-size: 9pt;">Copyright &copy; 2016. All rights reserved</div>
</footer>
<!-- #End Footer -->
