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

    <title>SoFAC의 특징 :: Software Factory</title>

    <%@ include file="siteHeaderStyle.jsp" %>

</head>
<body>

<%@ include file="siteHeader.jsp" %>

<!-- Content -->
<section class="container content mb40">

    <div class="row pt40">

        <div class="col-md-8 other-font2">
            <h3>SoFAC은 스타트업과 함께 합니다</h3>

            <div class="row mb20">
                <div class="col-md-11"><a data-fancybox="gallery" href="resources/indexPage/images/image006.jpg"><img
                        src="resources/indexPage/images/image006.jpg" alt="" class="img-responsive"/></a></div>
            </div>

            <p>스타트업 기업의 대부분은 아이디어와 열정을 가지고 있습니다.</p>
            <p>스타트업 기업의 대부분은 <b>자금이 없습니다.</b><br/>자금만 있으면 아이디어를 성공 시킬 것이라 믿습니다.</p>
            <p>또 한가지...<br/><span style="font-size: 14pt; font-weight: bold;">스타트업이 잘 알지 못하는 실패 요인이 있습니다.<br/>
                그것은 소프트웨어지원 입니다.<br/>스타트업 기업의 대부분은 소프트웨어 개발 단계에서 창업 자금을 모두 소모하게 되며<br/>
                결과는 얻지 못하고 시간만 낭비 하게 됩니다.</span>
            </p>
            <p>소프트웨어는 열정만으로 해결 할 수 있는 문제가 아니기 때문입니다.</p>

            <div class="row mb20">
                <div class="col-md-12"><a data-fancybox="gallery" href="resources/indexPage/images/image005.jpg"><img
                        src="resources/indexPage/images/image005.jpg" alt="" class="img-responsive"/></a></div>
            </div>

            <p style="font-weight: bold; color: #03a9f4;">소프트웨어팩토리는 스타트업이 필요로 하는 소프트웨어를 지속성 있게 지원하고 함께 하는 프로젝트를 운영하고
                있습니다.</p>
            <p>비싼 개발비를 요구하지 않습니다.<br/>항상 스타업의 IT 지원팀 처럼 저희의 홈페이지에 로그인 하셔서 CASE를 통해서 원하는 것을 말하기만 하면 됩니다.<br/>지속적으로 여러분 곁에
                조용히 머무르고 있습니다.<br/>유지보수나 어려운 기술 문제에 대해서 아무 걱정하지 않으셔도 됩니다.<br/><span
                        style="font-weight: bold; color: red;">당신의 아이디어를 성공 시키는 일에만 몰두 하세요</span></p>
            <p style="font-weight: bold; color: green;">한가지 더 좋은 점이 있습니다...<br/>소프트웨어팩토리는 ㈜굿앤굿파이낸스의 자회사 입니다.<br/>굿앤굿파이낸스는
                투자자, 회계사, 변호사, 전문경영인들이 함께 하는 곳입니다.<br/>여러분들의 아이디어 성장에 따른 자금의 연결이나, 법적 문제의 해결, 회계 문제등<br/>성공에 따른 많은 부분을
                지원해 드릴 것입니다.</p>
            <p>소프트웨어팩토리와 함께 하시면 굿앤굿과 함께 하는 것입니다.</p>
            <p style="font-size: 14pt; font-weight: bold;">소프트웨어팩토리는 스타트업의 성공을 지원 합니다.</p>

            <div class="row mb20">
                <div class="col-md-11"><a data-fancybox="gallery" href="resources/indexPage/images/image007.jpg"><img
                        src="resources/indexPage/images/image007.jpg" alt="" class="img-responsive"/></a></div>
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