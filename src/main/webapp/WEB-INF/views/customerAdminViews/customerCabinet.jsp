<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.SoftwareFactoryCustomer.model.Case" %>
<%@ page import="com.SoftwareFactoryCustomer.model.Message" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.SoftwareFactoryCustomer.constant.ProjectEnum" %>
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

<%@ include file="customerHeader.jsp" %>

<section class="container mb20">
    <div class="row">
        <div class="col-md-12">
            <!-- Breadcrumbs -->
            <ol class="breadcrumb">
                <li><a href="<c:url value="/cabinet/" />"><i class="fa fa-home"></i></a></li>
                <li class="active"><%out.print((String) request.getAttribute("currentProjectCasesName"));%></li>
            </ol>
            <!-- #End Breadcrumbs -->

            <!-- Warning Block -->
            <div class="row mb20">
                <div class="col-md-12">

                    <!-- Text warning -->
                    <p class="bg-warning p10">
                        CASE는 고객의 요청사항을 접수하고 처리가 완료될 때 까지 진행 상황을 주고 받는 곳입니다.<br>
                        <br>
                        고객의 요청사항은 언제든지 CASE를 오픈하여 접수하실 수 있습니다.<br>
                        그러나 한가지 프로젝트에서 같은 종류의 이슈는 한번만 만드시고 지속적으로 대화를 주고 받아야 합니다.<br>
                        <br>
                        한가지 프로젝트에서도 여러가지 이슈가 있을 경우에는 여러 개의 CASE를 만들어도 됩니다.
                    </p>
                    <!-- #End text warning -->

                </div>
            </div>
            <!-- #End Warning Block -->

            <%ArrayList<Case> cases = (ArrayList<Case>) request.getAttribute("cases");%>

            <div class="row mb20">
                <div class="col-md-6">

                    <!-- table pagination -->
                    <%if (cases.size() > 10) {%>
                    <div class="holder"></div>
                    <%}%>
                    <!-- #End table pagination -->

                </div>
                <div class="col-md-6 text-right"><a href="<c:url value="/cabinet/newCase"/>" class="btn btn-primary">새로운 CASE 만들기</a></div>
            </div>
            <!-- Projects list table -->
            <table class="table table-striped">

                <thead>
                <tr>
                    <th>Title</th>
                    <th class="text-center">Project</th>
                    <th class="text-center">Progress</th>
                    <th class="hidden-xs text-center">Date</th>
                    <th class="hidden-xs text-center">Update</th>
                    <th class="hidden-xs text-center">Appointment time</th>
                    <th class="hidden-xs text-center">Messages</th>
                </tr>
                </thead>
                <tbody id="itemContainer">
                <%
                    Iterator<Case> caseIterator = cases.iterator();
                    while (caseIterator.hasNext()) {
                        Case aCase = caseIterator.next();
                %>

                <!--<tr class="unread checked danger" // что бы сделать красным добавить в класс danger-->

                <tr class="unread checked"
                    onclick="javascript:window.location.href='/cabinet/case/<%    out.print(Long.toString(aCase.getId()));   %>'; return false;">
                    <td><a href="javascript:void(0);"><% out.print(aCase.getProjectTitle().toString()); %></a></td>
                    <%String projectName = aCase.getProject().getProjectName(); %>
                    <td class="text-center"><a href="javascript:void(0);"><%
                        if (projectName.equals(ProjectEnum.projectNameNormal.getDbValue())) {
                            out.print(ProjectEnum.projectNameNormal.getValue());
                        } else if (projectName.equals(ProjectEnum.projectNameEstimate.getDbValue())) {
                            out.print(ProjectEnum.projectNameEstimate.getValue());
                        } else out.print(projectName); %></a></td>
                    <td class="text-center"><% out.print(aCase.getStatus()); %></td>
                    <td class="hidden-xs text-center"><%
                        out.print(aCase.getCreationDate().toString().substring(0, 10)); %></td>

                    <% List<Message> messages = new ArrayList<>(aCase.getMessages());
                        Message msg = messages.get(0); %>

                    <td class="hidden-xs text-center">
                        <time class="timeago" datetime="<%  out.print(msg.getMessageTime()); %>"></time>
                    </td>
                    <td class="hidden-xs text-center">
                        <time class="timeago" datetime="<%  out.print(msg.getMessageTime()); %>"></time>
                    </td>
                    <td class="hidden-xs text-center"><% out.print(aCase.getMessages().size()); %></td>
                </tr>
                <%}%>
                </tbody>
            </table>
            <!-- #End Projects list table -->
        </div>
    </div>
</section>

<!-- Footer -->
<footer class="container footer mb20">
    <div class="mt20 text-center">Copyright &copy; 2017. All rights reserved.</div>
</footer>
<!-- #End Footer -->

<%@ include file="customerFooterJavaScript.jsp" %>

</body>
</html>
