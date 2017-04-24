<%@ page import="com.SoftwareFactoryCustomer.model.CustomerInfo" %>
<%@ page import="com.SoftwareFactoryCustomer.model.User" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html lang="kr">
<head>

    <%@ include file="customerHeaderMeta.jsp" %>

    <title>소팩소개 :: Software Factory</title>

    <%@ include file="customerHeaderStyle.jsp" %>

</head>
<body>

<%CustomerInfo customerInfo = (CustomerInfo) request.getAttribute("customerInfo");%>
<%User user = (User) request.getAttribute("user");%>

<%@ include file="customerHeader.jsp" %>

<section class="container mb20">
    <h3 class="mb20">프로필 정보 변경</h3>


    <%--information form--%>
    <form id="infoSettings" action="/cabinet/infoSettings?${_csrf.parameterName}=${_csrf.token}" method="POST">
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="name">이름</label>
                    <input type="text" name="name" id="name" value="<%  out.print(customerInfo.getName());  %>"
                           class="form-control" placeholder="이름" required/>
                </div>
                <div class="form-group">
                    <label for="email">이메일</label>
                    <input type="email" name="email" id="email" value="<%  out.print(customerInfo.getEmail());  %>"
                           class="form-control form-block" placeholder="이메일" required/>
                </div>
                <div class="form-group">
                    <label for="phone">전화번호</label>
                    <input type="text" name="phone" id="phone" class="form-control bfh-phone"
                           value="<%  out.print(customerInfo.getPhone());  %>" pattern="(]\d{3}[\)]\s\d{4}[\-]\d{4}$"
                           placeholder="전화번호" data-format="(ddd) dddd-dddd" maxlength="100" required/>
                </div>

                <div class="form-group">
                    <label for="companyName">회사명</label>
                    <input type="text" name="companyName" id="companyName" class="form-control"
                           value="<%  out.print(customerInfo.getCompany());  %>" placeholder="회사명" required/>
                </div>

                <div class="form-group">
                    <label for="companySite">회사 홈페이지</label>
                    <input type="text" name="companySite" id="companySite" class="form-control"
                           value="<%  out.print(customerInfo.getWebsite());  %>" placeholder="회사 홈페이지"/>
                </div>

                <div class="form-group text-right">
                    <button type="submit" name="saveSettings" class="btn btn-primary">Save changes</button>
                </div>

                <!-- Delimiter -->
                <div class="delimiter"></div>
                <!-- #End Delimiter -->
            </div>
        </div>
    </form>
    <%-- end information form--%>

    <%--password form--%>
    <form id="passwordSettings" action="/cabinet/passwordSettings?${_csrf.parameterName}=${_csrf.token}" method="POST">
        <div class="row">
            <div class="col-md-6">

                <div class="form-group">
                    <label for="newPassword">새 비밀번호</label>
                    <input type="password" name="newPassword" id="newPassword" class="form-control" placeholder="새 비밀번호"
                           required/>
                </div>

                <div class="form-group">
                    <label for="confirmNewPassword">새 암호를 확인합니다</label>
                    <input type="password" name="confirmNewPassword" id="confirmNewPassword" class="form-control"
                           placeholder="새 암호를 확인합니다" required/>
                </div>

                <div class="form-group text-right">
                    <button type="submit" name="savePassword" class="btn btn-primary">Save password</button>
                </div>
            </div>
        </div>
    </form>
    <%-- end password form--%>
</section>

<!-- Footer -->
<footer class="container footer mb20">
    <div class="mt20 text-center">Copyright &copy; 2017. All rights reserved.</div>
</footer>
<!-- #End Footer -->

<%@ include file="customerFooterJavaScript.jsp" %>

</body>
</html>