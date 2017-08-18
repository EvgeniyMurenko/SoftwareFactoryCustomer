<%@ page import="java.util.ArrayList" %>
<%@ page import="com.SoftwareFactoryCustomer.model.*" %>
<%@ page import="com.SoftwareFactoryCustomer.constant.ProjectEnum" %>
<%@ page import="java.text.SimpleDateFormat" %>
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

<% Long caseId = (Long) request.getAttribute("caseId"); %>
<% Case aCase = (Case) request.getAttribute("case"); %>
<% ArrayList<Message> messages = new ArrayList<>(aCase.getMessages()); %>
<%SimpleDateFormat dateFormatShow = new SimpleDateFormat("yyyy-MM-dd HH:mm");%>

<%@ include file="customerHeader.jsp" %>

<section class="container content mb20">

    <!-- Buttons -->
    <div class="row mb20">
        <div class="col-md-12"><a href="<c:url value="/cabinet/"/>" class="btn btn-primary btn-mobile">Back to CASE
            list</a></div>
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
            <td class="text-center"><a href="javascript:void(0);"><%out.print(projectName);%></a></td>
            <td class="text-center"><%out.print(aCase.getStatus());%></td>
            <td class="hidden-xs text-center"><%out.print(dateFormatShow.format(aCase.getProject().getCreateDate()));%></td>
            <td class="hidden-xs text-center">
                <time class="timeago" datetime="<%  out.print(messages.get(0).getMessageTime()); %>"></time>
            </td>
            <td class="hidden-xs text-center"><% out.print(messages.size()); %></td>
        </tr>
        </tbody>
    </table>
    <!-- #End Case table -->

    <form action="/cabinet/case/<% out.print(Long.toString(caseId)); %>/print_message"
          method="POST" enctype="multipart/form-data">
        <div class="form-group">
            <textarea class="form-control" name="message" rows="7" id="editor" placeholder="Message"></textarea>
        </div>

        <div class="form-group">
            <div class="checkbox abc-checkbox">
                <input type="checkbox" id="emergency" name="emergency">
                <label for="emergency">Emergency</label>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 col-sm-12 col-xs-12">
                <div class="form-group">
                    <label class="control-label">Select File</label>
                    <input id="caseInput" name="file[]" type="file" multiple class="file-loading">
                </div>
            </div>
            <div class="col-md-6 col-sm-12 col-xs-12">
                <div class="form-group text-right mt25">
                    <a href="/cabinet/case/<%    out.print(Long.toString(aCase.getId()));  %>"
                       class="btn btn-default btn-mobile">Back</a>
                    <button type="submit" class="btn btn-primary btn-mobile btn-submit">Send</button>
                </div>
            </div>
        </div>
    </form>


</section>

<!-- Footer -->
<footer class="container footer mb20">
    <div class="mt20 text-center">Copyright &copy; 2017. All rights reserved.</div>
</footer>
<!-- #End Footer -->

<%@ include file="customerFooterJavaScript.jsp" %>

</body>
</html>