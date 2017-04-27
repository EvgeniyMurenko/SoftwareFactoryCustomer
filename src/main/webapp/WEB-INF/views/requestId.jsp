<%@ page import="com.SoftwareFactoryCustomer.model.Estimate" %>
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

    <%@ include file="siteHeaderMeta.jsp" %>

    <title>소팩소개 :: Software Factory</title>

    <%@ include file="siteHeaderStyle.jsp" %>

</head>
<body>

<!-- Header -->
<header class="container header">
    <div class="row">
        <div class="col-lg-6 col-md-6 col-sm-6 logo">
            <a href="">소프트웨어<span>팩토리</span></a>
            <div class="small-logo">SoFAC : <i>Software Factory</i></div>
        </div>
    </div>
</header>
<!-- #End Header -->

<%
    Estimate estimate = (Estimate) request.getAttribute("CustomerEstimate");
    User user = (User) request.getAttribute("User");
%>

<section class="container content mb40 request-id">

    <div class="row pt40 pb40">
        <div class="col-md-8">
            <h3 class="mt0">SoFAC과 지속적인 대화를 위한 ID 발급을 원하십니까 ?</h3>

            <c:url var="generateCustomerIdUrl" value="/generateCustomerId?${_csrf.parameterName}=${_csrf.token}"/>
            <form action="${generateCustomerIdUrl}" method="post">
                <div class="row">
                    <div class="col-md-6">

                        <input type="hidden" name="userId" value="<%out.print(user.getId());%>">

                        <div class="form-group">
                            <input type="text" name="name" id="name" class="form-control"
                                   value="<%out.print(estimate.getName());%>" placeholder="이름" required/>
                        </div>

                        <div class="form-group">
                            <input type="email" name="email" id="email" class="form-control form-block"
                                   value="<%out.print(estimate.getEmail());%>" placeholder="이메일" required/>
                        </div>

                        <div class="form-group">
                            <input type="text" name="phone" id="phone" class="form-control bfh-phone"
                                   value="<%out.print(estimate.getPhone());%>" pattern="[\(]\d{3}[\)]\s\d{4}[\-]\d{4}$"
                                   placeholder="전화번호" data-format="(ddd) dddd-dddd" maxlength="100" required/>
                        </div>

                        <div class="form-group">
                            <input type="text" name="companyName" id="company-name" class="form-control" value=""
                                   placeholder="회사명" required/>
                        </div>

                        <div class="form-group">
                            <input type="text" name="companySite" id="company-site" class="form-control" value=""
                                   placeholder="회사 홈페이지" required/>
                        </div>

                        <button type="submit" class="btn btn-primary">CASE ID 발급을 요청합니다</button>
                    </div>
                </div>
                <div class="clearfix"></div>
            </form>

            <div class="pt40">
                <small>CASE ID는 4자의 숫자로 발급됩니다.<br/>
                    잘 기억해 주시기 바랍니다.<br/>
                    CASE 로그인에 필요한 패스워드는 휴대폰 번호 입니다.<br/>
                    (로그인 후 패스워드 변경 가능)
                </small>
            </div>

        </div>
    </div>

</section>

<%@ include file="siteFooter.jsp" %>

<%@ include file="siteFooterJavaScript.jsp" %>

</body>
</html>