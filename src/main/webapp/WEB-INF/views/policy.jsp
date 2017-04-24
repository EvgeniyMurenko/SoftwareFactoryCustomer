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

    <title>실시간 데이터 교환 엔진 (ExD) :: Software Factory</title>

    <%@ include file="siteHeaderStyle.jsp" %>

</head>
<body>

<%@ include file="siteHeader.jsp" %>

<!-- Content -->
<div class="container content mt30 mb30">
    <div class="row pt40">
        <div class="col-md-8">

            <h3 style="font-size: 13pt;">소프트웨어팩토리 연혁</h3>

            <table class="document-table2 other-font2">
                <thead>
                <tr>
                    <th width="80">항목</th>
                    <th>내 용</th>
                    <th width="200">참 고</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>개발완료란?</td>
                    <td>앱과 같이 설계서를 받아서 작업하는 작업이 아닌 경우 소프트웨어는 100% 완벽하게 검수 완료할 수 없습니다...<br/>
                        모든 상황에서 완벽히 동작되는 소프트웨어는 단 기간에 만들어질 수 없다는 것을 의미 합니다. 따라서 계약된 기간내에 95% 이상 완성된 상태를 완료 상태로
                        규정합니다.<br/>
                        나머지 부분은 상황에 따라 보증 기간을 아래와 같이 적용하고 있으며 이 기간 동안에 발견하지 못한 버그나 SOFAC에서 보증해야 될 부분이 발생되면 보증 수리해
                        드립니다.<br/>
                        (단, 보증 기간내에 신규하드웨어 또는 신규O/S 또는 고객의 상황 변경에 따른 내용을 보증 내용으로 볼 수 없습니다.)
                    </td>
                    <td>검수 완료 후<br/>
                        모든 소스는 고객의 서버에 설치되며 소스코드 전체도 고객에게 즉시 이전 됩니다.
                    </td>
                </tr>
                <tr>
                    <td>유지보수정책</td>
                    <td>개발 이후 납품이 완료되어야 하는 작업의 경우 2개월의 보증기간을 적용하고 있으며 이 기간 동안에는 설계와 다른 동작이 발생될 경우 무상 보증 수리 해 해드립니다.<br/>
                        (개발 후 납품과 함께 계약이 종료되는 경우 무상 보증기간 이후에 발생하는 버그 및 수정사항은 별도의 견적 과정 부터 진행됩니다.)
                    </td>
                    <td>지속형 유지보수 계약자는 이 항목에 해당되지 않습니다.<br/><br/>
                        소팩 서버이용시 별도 비용 청구 됩니다.
                    </td>
                </tr>
                <tr>
                    <td>유지보수<br/>
                        의뢰 받을 시
                    </td>
                    <td>SoFAC에게 유지보수를 의뢰하는 경우 1개월은 무상 대응하며 그 이후부터는 아래 정책에 따른 계약에 의해서 성실하게 유지보수해 드립니다...<br/><br/>
                        -1년 이상 계약시 20% 할인 적용 (1년간 유지하겠다는 계약임)<br/>
                        -2년 이상 유지보수 지속시 매년10%씩 할인 적용 (3년간)
                    </td>
                    <td>소팩 서버이용시 별도 비용 청구 됩니다.</td>
                </tr>
                <tr>
                    <td>계정정책</td>
                    <td>구글, 안드로이드, 서버등의 계정은 유지보수를 SOFAC에 일임한 계약일 경우 SoFAC의 계정을 통해서 관리 운영 됩니다.<br/>
                        (개발만 의뢰한 경우는 고객의 계정을 사용합니다)
                    </td>
                    <td>고객 계정 사용시 계정의 관리 주체도 고객이므로 대응 주체도 고객책임하에 있습니다</td>
                </tr>
                </tbody>
            </table>

            <h3 style="font-size: 13pt;">저작권 및 권리 규정</h3>

            <table class="document-table2 other-font2">

                <thead>
                <tr>
                    <th width="80">항 목</th>
                    <th>내 용</th>
                    <th width="200">참고사항</th>
                </tr>
                </thead>

                <tbody>
                <tr>
                    <td>소프트웨어 소유권</td>
                    <td>SoFac에서 개발한 소프트웨어의 모든 권리는 고객에게 있습니다.<br/>
                        따라서 개발 완료 이후 고객이 요구하는 시점에 소스 및 개발 관련 모든 정보를 넘겨 드립니다.
                    </td>
                    <td>단, SOFAC 자체 기술의 경우 보호된 상태로 전달되며 사용만 고객은 사용만 할 수 있으며 수정 및 재사용을 하실 수 는 없습니다.</td>
                </tr>
                <tr>
                    <td>관리의무 및<br/>
                        소유권
                    </td>
                    <td>개발 이후 유지보수를 SoAFC에서 할 경우 모든 관리의 책임은 SoFAC이 담당하며 소스 및 백업자료 및 히스토리 자료는 모두 소팩에서 보관 합니다.</td>
                    <td>고객의 요청에 의하여 유지보수가 종료될 경우 고객이 원하는 위치로 서버를 이전해 드리며 그 동안 보관된 모든 소스 및 자료도 함께 전달해 드립니다.</td>
                </tr>
                <tr>
                    <td>비밀유지<br/>
                        보장
                    </td>
                    <td>SoFac 과 협의가 시작되는 시점부터 업무 종료 시점 까지 모든 내용은 절대 비밀 유지를 기본으로 합니다. 따라서 고객의 어떤 아이디어나 기술적인 정보를 SoFAC이
                        고객으로 부터 취득하여도 다른 고객들에게 전달되거나 공유하지 않습니다.
                    </td>
                    <td></td>
                </tr>

                </tbody>
            </table>
        </div>
    </div>
</div>
<!-- #End Content -->

<%@ include file="siteFooter.jsp" %>

<%@ include file="siteAuthorizationModal.jsp" %>

<%@ include file="siteFooterJavaScript.jsp" %>

</body>
</html>