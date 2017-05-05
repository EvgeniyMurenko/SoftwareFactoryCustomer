<%@ page import="java.util.ArrayList" %>
<%@ page import="com.SoftwareFactoryCustomer.constant.StatusEnum" %>
<%@ page import="com.SoftwareFactoryCustomer.model.*" %>
<%@ page import="com.SoftwareFactoryCustomer.constant.ProjectEnum" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Set" %>
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

<section class="container content mb20">

    <!-- Buttons -->
    <div class="row mb20">
        <%
            Case aCase = (Case) request.getAttribute("case");
            Long caseId = aCase.getId();
            String caseStatus = aCase.getStatus();

        ArrayList<Message> messages = (ArrayList<Message>) request.getAttribute("messagesSorted");
        ManagerInfo managerInfo = (ManagerInfo) request.getAttribute("managerInfo");
        SimpleDateFormat dateFormatShow = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        %>

        <div class="col-md-6"><a href="<c:url value="/cabinet/"/>" class="btn btn-primary btn-mobile">Back to CASE
            list</a></div>

        <%if (!caseStatus.equals(StatusEnum.CLOSE.toString())) {%>
        <form action="/cabinet/case/<% out.print(caseId); %>/close_case?${_csrf.parameterName}=${_csrf.token}"
              method="POST">
            <div class="col-md-6 text-right">
                <a href="/cabinet/case/<%out.print(caseId); %>/answer"
                   class="btn btn-primary btn-mobile">Write this CASE</a>


                <button type="button" class="btn btn-warning btn-mobile" data-toggle="modal" data-target="#myModal">
                    Close this CASE
                </button>
                <%--Modal window--%>
                <div id="myModal" class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button class="close" type="button" data-dismiss="modal">×</button>
                                <h4 class="modal-title">ATTENTION</h4>
                            </div>
                            <div class="modal-body">Are you sure to close?</div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-primary btn-mobile">YES</button>
                                <button type="button" class="btn btn-default" data-dismiss="modal">NO</button>
                            </div>
                        </div>
                    </div>
                </div>
                <%--END Modal window--%>
            </div>
        </form>
        <%}%>
    </div>
    <!-- #End Buttons -->

    <!-- Case table -->
    <table class="table table-striped">
        <tbody>
        <%
            String projectName = "";
            if (aCase.getProject().getProjectName().equals(ProjectEnum.projectNameNormal.getDbValue())) {
                projectName = ProjectEnum.projectNameNormal.getValue();
            } else if (aCase.getProject().getProjectName().equals(ProjectEnum.projectNameEstimate.getDbValue())) {
                projectName = ProjectEnum.projectNameEstimate.getValue();
            } else {
                projectName = aCase.getProject().getProjectName();
            }
        %>
        <tr class="unread checked">
            <td><a href="javascript:void(0);"><%out.print(aCase.getProjectTitle());%></a></td>
            <td class="text-center"><%out.print(projectName);%></td>
            <td class="text-center"><%out.print(aCase.getProject().getStatus());%></td>
            <td class="hidden-xs text-center"><%out.print(dateFormatShow.format(aCase.getCreationDate()));%></td>
            <td class="hidden-xs text-center">
                <time class="timeago" datetime="<%  out.print(messages.get(0).getMessageTime()); %>"></time>
            </td>
            <td class="hidden-xs text-center"><% out.print(messages.size()); %></td>
        </tr>
        </tbody>
    </table>
    <!-- #End Case table -->

    <!-- Case Messages -->
    <section class="messages">

        <%for (Message message : messages) {
            Long newLong = new Long(message.getUser().getId());
            if (aCase.getProject().getCustomerInfo().getId().equals(newLong)) {%>
                <div class="message-customer-informer">
                    <div class="mi-c-title"><% out.print(aCase.getProject().getCustomerInfo().getName()); %><a
                            href="javascript:void(0);"><% out.print("(ID " + message.getId() + ")"); %></a> <span
                            class="mi-c-time pull-right"><% out.print(dateFormatShow.format(message.getMessageTime())); %></span></div>
                    <div class="mi-c-message">
                        <% out.print(message.getMessageText()); %>
                        <%
                            Set<MessageLink> messageLinks = message.getMessageLinks();
                            if (!messageLinks.isEmpty()) {

                                for (MessageLink messageLink : messageLinks){
                                    out.print("<p><a href="+ messageLink.getFileLink() +" target='_blank'>"+messageLink.getFileName()+"</a>");
                                }
                            }
                        %>
                    </div>
                </div>
            <%} else {%>
                <div class="message-manager-informer">
                    <%String managerName;
                    if (managerInfo == null){
                            managerName = "SoFac Team";
                        }else {
                        managerName = managerInfo.getName();
                    }%>
                    <div class="mi-m-title"><% out.print(managerName); %> <a href="javascript:void(0);"><%
                        out.print("(ID " + message.getId() + ")"); %></a> <span class="mi-m-time pull-right"><%
                        out.print(dateFormatShow.format(message.getMessageTime())); %></span></div>
                    <div class="mi-m-message">
                        <% out.print(message.getMessageText()); %>
                        <% Set<MessageLink> messageLinks = message.getMessageLinks();
                            if (!messageLinks.isEmpty()) {

                                for (MessageLink messageLink : messageLinks){
                                    out.print("<p><a href="+ messageLink.getFileLink() +" target='_blank'>"+messageLink.getFileName()+"</a>");
                                }
                            }
                        %>
                    </div>
                </div>
            <%}%>
        <%}%>

    </section>
    <!-- #End Case Messages -->


</section>

<!-- Footer -->
<footer class="container footer mb20">
    <div class="mt20 text-center">Copyright &copy; 2017. All rights reserved.</div>
</footer>
<!-- #End Footer -->

<%@ include file="customerFooterJavaScript.jsp" %>

</body>
</html>