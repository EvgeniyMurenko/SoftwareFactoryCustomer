<%@ page import="com.SoftwareFactoryCustomer.model.Estimate" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
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
        <div class="col-lg-6 col-md-6 col-sm-6 text-right login">

            <% String userName = (String) request.getSession().getAttribute("UserName");
                if (userName == null) {
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
                    <span class="avatar-welcome"><%out.print((String) request.getSession().getAttribute("UserName"));%> <a
                            href="/cabinet/"> 님 접속을 환영합니다.</a></span>
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
</header>
<!-- #End Header -->

<!-- Cover section -->
<div class="container-fluid cover">
    <div class="container">
        <div class="row">
            <div class="col-md-6">

                <!-- Estimation -->
                <section class="estimation mt40 mb20 clearfix">
                    <div class="es-title">처음 오신 고객은 견적, 작업등 모든 문의를 하실 수 있습니다</div>
                    <div class="es-btn"><a href="javascript:void(0);" class="btn btn-primary btn-mobile"
                                           data-toggle="modal" data-target="#estimationModal"><i
                            class="fa fa-paper-plane-o"></i>문의하기</a></div>
                </section>
                <!-- #End Estimation -->

                <!-- Estimation list case -->
                <div class="scrollable" id="estimatetest">
                    <section class="estimation-list">
                        <%
                            ArrayList<Estimate> estimates = (ArrayList<Estimate>) request.getAttribute("estimates");
                            Iterator<Estimate> estimateIterator = estimates.iterator();
                            while (estimateIterator.hasNext()) {
                                Estimate estimate = estimateIterator.next();
                        %>
                        <% if (estimate != null) { %>

                        <div class="clearfix estimate">
                            <span class=<%
                                if (estimate.isRespond()) out.print("check-on");
                                else out.print("check-off");
                            %>></span>
                            <a href="javascript:void(0);"><span class="cb-title"><%
                                out.print(estimate.getName() + " : "); %></span></a><span><%
                            out.print(estimate.getEstimateGeneratedId());%></span> : <%
                            if (estimate.isPriceRequest()) out.print("견적문의 ");
                            if (estimate.isQuestionRequest()) out.print(" 일반문의");
                        %><span class="cb-time"><%
                            out.print(estimate.getDateRequest().toString().substring(0, 19)); %></span>
                        </div>

                        <%}%>
                        <%}%>
                    </section>
                </div>
                <!-- #End Estimation list case -->

            </div>
            <div class="col-md-6">

                <!-- Information -->
                <section class="information">
                    <div>
                        <span class="if-title">1000명의 엔지니어와 함께 하는</span>
                        <span class="if-title2">온라인 소프트웨어 개발 서비스</span>
                    </div>
                    <div class="mt20 mb20">
                        SoFAC은 전문적인 소프트웨어 개발 지원이 가능한 규모를 갖춘 소프트웨어 개발, 및 유지 운영을 대행하는 전문 개발 기업 입니다.<br>
                        SoFAC의 모든 작업은 CASE 라는 개념을 통하여 소통과 작업이 이루어지며 지속적이고 정확한 서비스를 제공 받으실 수 있습니다. <br>
                    </div>
                    <div class="text-right">
                        <a href="<c:url value='/whatIsSofac'/>" class="btn btn-primary btn-mobile"><i
                                class="fa fa-link"></i>SoFAC 알아보기</a>
                    </div>
                </section>
                <!-- #End Information -->

                <!-- Video button -->
                <div class="text-center mb20"><a href="javascript:void(0);" class="btn btn-info btn-mobile"><i
                        class="fa fa-video-camera"></i>SoFAC Video</a></div>
                <!-- #End Video button -->

            </div>
        </div>
    </div>
</div>
<div class="clearfix"></div>
<!-- #End Cover section -->

<!-- Advantage -->
<div class="container mt40 mb30 advantage">
    <div class="row">
        <div class="col-md-4 mb20">
            <div class="ad-icon"><i class="fa fa-clock-o"></i></div>
            <div>
                <h3 class="text-center">CASE 란 무엇인가요 ?</h3>
                <div class="text-justify">스마트 시대가 되면 대부분의 개발은 국제 표준에 대한 개념을 지속적으로 적용하여야 합니다 <b>CASE개념은 24시간 지속적인 고객 지원이
                    가능한 고객과의 소통 방법이며 정확한 시간 약속과 지원에 대한 결과를 확인할 수 있는 체계 입니다.</b></div>
                <div class="mt10 text-justify"><a href="<c:url value='/whatIsCase'/>"><i>자세히 알아보기...</i></a></div>
            </div>
        </div>
        <div class="col-md-4 mb20">
            <div class="ad-icon"><i class="fa fa-envira"></i></div>
            <div>
                <h3 class="text-center">가격 및 유지보수 정책</h3>
                <div class="text-justify">현대 사회의 모든 사업은 소프트웨어 지원이 필수적입니다. 따라서 기존의 개발비용 개념과 <b>달리 다양한 방법으로 비용을 절감하거나 고객이
                    원하는 형태로 비용 지불 방법을 정할 수 있는 유연한 가격 정책과 기술료를 받지 않는 정책을 운영합니다.</b></div>
                <div class="mt10 text-justify"><a href="<c:url value='/pricing'/>"><i>자세히 알아보기...</i></a></div>
            </div>
        </div>
        <div class="col-md-4 mb20">
            <div class="ad-icon"><i class="fa fa-laptop"></i></div>
            <div>
                <h3 class="text-center">FXM기법과 다국적 작업</h3>
                <div class="text-justify">SoFAC은 기존의 소규모 개발 회사와 달리 저비용을 실현하면서 개발 및 지속적인 관리가 가능하게 하기 위하여 <b>다국적 개발 및 관리
                    시스템인 FXM플랫폼을 통하여 공장형 개발 기법을 실현하였습니다.</b></div>
                <div class="mt10 text-justify"><a href="<c:url value='/guide'/>"><i>자세히 알아보기...</i></a></div>
            </div>
        </div>
    </div>
</div>
<div class="clearfix"></div>
<!-- #End Advantage -->

<!-- Tag line -->
<div class="container-fluid tag-line mt20 mb20">
    <div class="container">
        <img src="resources/indexPage/images/old-logo.png" alt="" class="img-responsive"/>
        <div class="last-blog clearfix">
            <div class="bl-quote"><i class="fa fa-quote-right"></i></div>
            <div class="bl-article-title">10여년 전부터 소프트웨어 지원은 자금 지원과 함께 이루어져야 한다는 생각을 가진<br>㈜굿앤굿재무법인이 미국적 표준화와 다국적 개발 능력을
                확보한 전문 개발 대행 기업 입니다.
            </div>
        </div>
        <div class="bl-body">소프트웨어팩토리는 수준높은 개발과 저비용을 실현한 최초의 소프트웨어 서비스 기업입니다. <a href="<c:url value='/aboutUs'/>"><i>자세히
            알아보기...</i></a></div>
    </div>
</div>
<div class="clearfix"></div>
<!-- #End Tag line -->

<!-- Portfolio -->
<section class="container mt40 mb10">
    <div class="row">
        <div class="col-md-12">
            <div id="portfolio" class="carousel slide">

                <ol class="carousel-indicators">
                    <li data-target="#portfolio" data-slide-to="0" class="active"></li>
                    <li data-target="#portfolio" data-slide-to="1"></li>
                    <li data-target="#portfolio" data-slide-to="2"></li>
                    <li data-target="#portfolio" data-slide-to="3"></li>
                </ol>

                <!-- Carousel items -->
                <div class="carousel-inner">

                    <div class="item active">
                        <div class="row">
                            <div class="col-md-3"><a data-fancybox="gallery"
                                                     href="resources/indexPage/images/portfolio1-big.jpg"
                                                     class="thumbnail"><img
                                    src="resources/indexPage/images/portfolio1-small.jpg" alt=""></a></div>
                            <div class="col-md-3"><a data-fancybox="gallery"
                                                     href="resources/indexPage/images/portfolio2-big.jpg"
                                                     class="thumbnail"><img
                                    src="resources/indexPage/images/portfolio2-small.jpg" alt=""></a></div>
                            <div class="col-md-3"><a data-fancybox="gallery"
                                                     href="resources/indexPage/images/portfolio3-big.jpg"
                                                     class="thumbnail"><img
                                    src="resources/indexPage/images/portfolio3-small.jpg" alt=""></a></div>
                            <div class="col-md-3"><a data-fancybox="gallery"
                                                     href="resources/indexPage/images/portfolio4-big.jpg"
                                                     class="thumbnail"><img
                                    src="resources/indexPage/images/portfolio4-small.jpg" alt=""></a></div>
                        </div><!--.row-->
                    </div><!--.item-->

                    <div class="item">
                        <div class="row">
                            <div class="col-md-3"><a data-fancybox="gallery"
                                                     href="resources/indexPage/images/portfolio5-big.jpg"
                                                     class="thumbnail"><img
                                    src="resources/indexPage/images/portfolio5-small.jpg" alt=""></a></div>
                            <div class="col-md-3"><a data-fancybox="gallery"
                                                     href="resources/indexPage/images/portfolio6-big.jpg"
                                                     class="thumbnail"><img
                                    src="resources/indexPage/images/portfolio6-small.jpg" alt=""></a></div>
                            <div class="col-md-3"><a data-fancybox="gallery"
                                                     href="resources/indexPage/images/portfolio7-big.jpg"
                                                     class="thumbnail"><img
                                    src="resources/indexPage/images/portfolio7-small.jpg" alt=""></a></div>
                            <div class="col-md-3"><a data-fancybox="gallery"
                                                     href="resources/indexPage/images/portfolio8-big.jpg"
                                                     class="thumbnail"><img
                                    src="resources/indexPage/images/portfolio8-small.jpg" alt=""></a></div>
                        </div><!--.row-->
                    </div><!--.item-->

                    <div class="item">
                        <div class="row">
                            <div class="col-md-3"><a data-fancybox="gallery"
                                                     href="resources/indexPage/images/portfolio9-big.jpg"
                                                     class="thumbnail"><img
                                    src="resources/indexPage/images/portfolio9-small.jpg" alt=""></a></div>
                            <div class="col-md-3"><a data-fancybox="gallery"
                                                     href="resources/indexPage/images/portfolio10-big.jpg"
                                                     class="thumbnail"><img
                                    src="resources/indexPage/images/portfolio10-small.jpg" alt=""></a></div>
                            <div class="col-md-3"><a data-fancybox="gallery"
                                                     href="resources/indexPage/images/portfolio11-big.jpg"
                                                     class="thumbnail"><img
                                    src="resources/indexPage/images/portfolio11-small.jpg" alt=""></a></div>
                            <div class="col-md-3"><a data-fancybox="gallery"
                                                     href="resources/indexPage/images/portfolio12-big.jpg"
                                                     class="thumbnail"><img
                                    src="resources/indexPage/images/portfolio12-small.jpg" alt=""></a></div>
                        </div><!--.row-->
                    </div><!--.item-->

                    <div class="item">
                        <div class="row">
                            <div class="col-md-3"><a data-fancybox="gallery"
                                                     href="resources/indexPage/images/portfolio13-big.jpg"
                                                     class="thumbnail"><img
                                    src="resources/indexPage/images/portfolio13-small.jpg" alt=""></a></div>
                            <div class="col-md-3"><a data-fancybox="gallery"
                                                     href="resources/indexPage/images/portfolio14-big.jpg"
                                                     class="thumbnail"><img
                                    src="resources/indexPage/images/portfolio14-small.jpg" alt=""></a></div>
                            <div class="col-md-3"><a data-fancybox="gallery"
                                                     href="resources/indexPage/images/portfolio15-big.jpg"
                                                     class="thumbnail"><img
                                    src="resources/indexPage/images/portfolio15-small.jpg" alt=""></a></div>
                            <div class="col-md-3"><a data-fancybox="gallery"
                                                     href="resources/indexPage/images/portfolio16-big.jpg"
                                                     class="thumbnail"><img
                                    src="resources/indexPage/images/portfolio16-small.jpg" alt=""></a></div>
                        </div><!--.row-->
                    </div><!--.item-->

                </div><!--.carousel-inner-->
                <a data-slide="prev" href="#portfolio" class="left carousel-control">‹</a>
                <a data-slide="next" href="#portfolio" class="right carousel-control">›</a>
            </div><!--.Carousel-->

        </div>
    </div>
</section>
<div class="clearfix"></div>
<!-- #End Portfolio -->

<!-- Features -->
<div class="container features mt30 mb30">
    <div class="text-center mb20">
        <h3>소프트웨어 팩토리 자세히 알아 보기</h3>
        <p>소프트웨어 팩토리는 모든 종류의 소프트웨어를 개발 할 수 있는 다국적 인프라를 구축하고 있습니다.</p>
    </div>

    <!-- Features list -->
    <div class="row">
        <div class="col-md-6">
            <article class="feature clearfix">
                <div class="ft-icon"><i class="fa fa-external-link"></i></div>
                <div class="ft-content">
                    <div class="ft-title">보유기술 및 정책</div>
                    <div class="ft-body">소프트웨어팩토리는 공장형 소프트웨어 개발 대행에 필요한 많은 기술을 보유하고 있으며 그 중에 핵심적인 본사의 기술적 베이스를 소개드립니다.
                        <a href="<c:url value='/technology'/>"><i>자세히 알아보기...</i></a></div>
                </div>
            </article>
        </div>
        <div class="col-md-6">
            <article class="feature clearfix">
                <div class="ft-icon"><i class="fa fa-star"></i></div>
                <div class="ft-content">
                    <div class="ft-title">고객 지원 인프라 소개</div>
                    <div class="ft-body">SoFAC은 좋은 품질의 소프트웨어의 개발과 지속적인 유지보수를 위하여 다양한 인프라를 갖추고 있습니다. <a
                            href="<c:url value='/fxm'/>"><i>자세히 알아보기...</i></a></div>
                </div>
            </article>
        </div>
    </div>

    <div class="row">
        <div class="col-md-6">
            <article class="feature clearfix">
                <div class="ft-icon"><i class="fa fa-file-o"></i></div>
                <div class="ft-content">
                    <div class="ft-title">회사연혁 및 인증자료</div>
                    <div class="ft-body">기술과 금융이 조합된 10년의 노하우로 미래를 준비하여 100년이 지속되는 기업이 되겠습니다. <a
                            href="<c:url value='/documents'/>"><i>자세히 알아보기...</i></a></div>
                </div>
            </article>
        </div>
        <div class="col-md-6">
            <article class="feature clearfix">
                <div class="ft-icon"><i class="fa fa-terminal"></i></div>
                <div class="ft-content">
                    <div class="ft-title">스타트업과 함께 하는 SoFAC</div>
                    <div class="ft-body">스타트업 기업들은 대부분 IT 지원을 필요로 합니다. 그리고 IT가 실패의 원인이 되기도 합니다. 비싼 개발비, 유지보수 안되는 개발로
                        좌절하지 마세요... SoFAC과 함게 하세요 <a href="<c:url value='/startup'/>"><i>자세히 알아보기...</i></a></div>
                </div>
            </article>
        </div>
    </div>

    <div class="row">
        <div class="col-md-6">
            <article class="feature clearfix">
                <div class="ft-icon"><i class="fa fa-mobile"></i></div>
                <div class="ft-content">
                    <div class="ft-title">지점 및 협력업체</div>
                    <div class="ft-body">SoFAC은 온라인을 기본으로 한 공장형 소프트웨어 개발 회사 이지만 오프라인 거래는 지점을 통해서 같은 효과를 누릴 수 있습니다. <a
                            href="<c:url value='/branch'/>"><i>자세히 알아보기...</i></a></div>
                </div>
            </article>
        </div>
        <div class="col-md-6">
            <article class="feature clearfix">
                <div class="ft-icon" style="color: orange;"><i class="fa fa-flask"></i></div>
                <div class="ft-content">
                    <div class="ft-title">알려드립니다</div>
                    <div class="ft-body">공지사항<br/>소프트팩토리에서 고객 여러분들께 알려드립니다.<br> <a href="<c:url value='/notices'/>"><i>자세히
                        알아보기...</i></a></div>
                </div>
            </article>
        </div>
    </div>
    <!-- #End Features list -->

</div>
<div class="clearfix"></div>
<!-- #End Features -->

<%@ include file="siteFooter.jsp" %>

<%@ include file="siteAuthorizationModal.jsp" %>

<%@ include file="siteEstimateModal.jsp" %>

<%@ include file="siteFooterJavaScript.jsp" %>

<% Boolean isEstimateSuccess = (Boolean) request.getAttribute("isEstimateSuccess"); %>
<% if (isEstimateSuccess != null && isEstimateSuccess) { %>
<script>
    jQuery(document).ready(function ($) {
        swal(
            '정상적으로 접수 되었습니다!',
            '감사합니다',
            'success'
        );
        history.pushState(null, null, '/main');
    });
</script>
<% } %>
<% Boolean isGenerateSuccess = (Boolean) request.getAttribute("isGenerateCustomerIdSuccess"); %>
<% if (isGenerateSuccess != null && isGenerateSuccess) { %>
<script>
    jQuery(document).ready(function ($) {
        swal(
            '축하드립니다.',
            '고객님의 CASE ID가 등록되었습니다.입력하신 E-MAIL로 ID가 발송되었습니다.E-MAIL 확인 후 접속해 주시기 바랍니다.' +
            '감사합니다.',
            'success'
        );
        history.pushState(null, null, '/main');
    });
</script>
<% } %>

</body>
</html>