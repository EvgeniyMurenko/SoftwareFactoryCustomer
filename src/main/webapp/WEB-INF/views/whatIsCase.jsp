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

    <title>What is the “CASE” ? :: Software Factory</title>

    <%@ include file="siteHeaderStyle.jsp" %>

</head>
<body>

<%@ include file="siteHeader.jsp" %>

<!-- Content -->
<section class="container content mb40">
    <div class="row pt40">
        <div class="col-md-8">

            <h3>What is the “CASE” ?</h3>
            <p>소프트웨어 팩토리는 원가 절감 및 고객과의 소통에서 가장 효율적인 방법으로 CASE 라는 개념을 통해서 고객과 소통 합니다.</p>
            <p><b>고객은 CASE를 통해서 견적, 작업내용, 수정사항 대응, 비상조치 등 모든 내용을 언제든지 CASE 화면을 열어서 질문하시면 됩니다.</b></p>
            <p>접수된 CASE는 실시간으로 자동 번역되어 관련부서로 전송되며 요청 내용에 따라 어떤 즉시답변 또는 답변 시간 약속 이메일이 전송되며 정해진 시간에 정확히 답변 드리도록 하고
                있습니다.</p>
            <p>또한 CASE는 고객이 만족할때 까지 지속적으로 대화가 진행되며 업무가 완료되면 고객이 CASE를 닫을 수 있는 권한을 가지고 있습니다.</p>
            <p>소프트웨어 팩토리는 이러한 개념으로 고객의 요청사항에 최대한 충실히 대응하는 온라인 소통 시스템 입니다.</p>
            <p><b>만약 온라인 소통을 원치 않으실 경우 소프트웨어팩토리의 지점을 통하여 대면 상담도 가능합니다.</b></p>

            <div class="row mt40 mb30 clearfix">
                <div class="col-md-12"><a data-fancybox="gallery" href="resources/indexPage/images/sfema2.jpg"><img
                        src="resources/indexPage/images/sfema2.jpg" alt="" class="img-responsive"/></a></div>
            </div>
        </div>
    </div>
</section>
<!-- #End Content -->

<%@ include file="siteFooter.jsp" %>

<%@ include file="siteAuthorizationModal.jsp" %>

<%@ include file="siteFooterJavaScript.jsp" %>

</body>
</html>
