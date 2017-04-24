<%@ page import="com.SoftwareFactoryCustomer.model.Project" %>
<%@ page import="java.util.*" %>
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

    <%
        List<Project> projectSet = (List<Project>) request.getAttribute("projects");
    %>

    <div class="col-md-12">
        <!-- Warning Block -->
        <div class="row mb20">
            <div class="col-md-12">

                <!-- Text warning -->
                <p class="bg-warning p10">
                    우선 기존에 같은 이슈에 대해서 CASE를 오픈한 내용이 있는지 확인하시기 바랍니다. <br/>
                    하나의 이슈에 대해서는 하나의 CASE만 오픈 하셔야 합니다.<br/>
                    (이슈는 구체적으로 기록해 주세요 : ex 견적진행, 개발작업스토리, 앱작동불능, 유지보수요청 등)<br/><br/>

                    <a href="<c:url value="/cabinet/" />">Check current CASE lise... </a>

                </p>
                <!-- #End text warning -->

            </div>
        </div>
        <!-- #End Warning Block -->

        <!-- Estimation -->
        <c:url var="createCase" value="/cabinet/createCase?${_csrf.parameterName}=${_csrf.token}"/>
        <form action="${createCase}" enctype="multipart/form-data" method="POST">
            <div class="form-group">
                <label for="project">Project</label>
                <select name="projectName" class="form-control" id="project">
                    <%Iterator<Project> itr = projectSet.iterator();
                        while (itr.hasNext()) {
                            Project project = itr.next();%>
                            <option value="<%out.print(project.getProjectName());%>">
                                <%
                                    if (project.getProjectName().equals(ProjectEnum.projectNameNormal.getDbValue())) {
                                        out.print(ProjectEnum.projectNameNormal.getValue());
                                    } else if (project.getProjectName().equals(ProjectEnum.projectNameEstimate.getDbValue())) {
                                        out.print(ProjectEnum.projectNameEstimate.getValue());
                                    } else {
                                        out.print(project.getProjectName());
                                    }
                                %>
                            </option>
                        <%}%>
                </select>
            </div>

            <div class="form-group">
                <label for="title">Issue title</label>
                <input type="text" class="form-control" name="caseName" id="title" placeholder="Issue title" required>
            </div>

            <div class="form-group">
                <label for="lang">Language</label>
                <select class="form-control" name="language" id="lang">
                    <option value="KO">한국어 / Korean</option>
                    <option value="AR">العربية / Arabic</option>
                    <option value="BE">Беларускі / Belarusian</option>
                    <option value="BG">Български / Bulgarian</option>
                    <option value="CS">Čeština / Czech</option>
                    <option value="DA">Dansk / Danish</option>
                    <option value="DE">Deutsch / German</option>
                    <option value="EL">Ελληνικά / Greek</option>
                    <option value="EN">English / English</option>
                    <option value="ES">Español / Spanish</option>
                    <option value="ET">Eesti / Estonian</option>
                    <option value="FI">Suomi / Finnish</option>
                    <option value="FR">Français / French</option>
                    <option value="GA">Gaeilge / Irish</option>
                    <option value="HI">हिंदी / Hindi</option>
                    <option value="HR">Hrvatski / Croatian</option>
                    <option value="HU">Magyar / Hungarian</option>
                    <option value="IN">Bahasa indonesia / Indonesian</option>
                    <option value="IS">Íslenska / Icelandic</option>
                    <option value="IT">Italiano / Italian</option>
                    <option value="IW">עברית / Hebrew</option>
                    <option value="JA">日本語 / Japanese</option>
                    <option value="LT">Lietuvių / Lithuanian</option>
                    <option value="LV">Latviešu / Latvian</option>
                    <option value="MK">Македонски / Macedonian</option>
                    <option value="MS">Bahasa melayu / Malay</option>
                    <option value="MT">Malti / Maltese</option>
                    <option value="NL">Nederlands / Dutch</option>
                    <option value="NO">Norsk / Norwegian</option>
                    <option value="PL">Polski / Polish</option>
                    <option value="PT">Português / Portuguese</option>
                    <option value="RO">Română / Romanian</option>
                    <option value="RU">Русский / Russian</option>
                    <option value="SK">Slovenčina / Slovak</option>
                    <option value="SL">Slovenščina / Slovenian</option>
                    <option value="SQ">Shqipe / Albanian</option>
                    <option value="SR">Српски / Serbian</option>
                    <option value="SV">Svenska / Swedish</option>
                    <option value="TH">ไทย / Thai</option>
                    <option value="TR">Türkçe / Turkish</option>
                    <option value="UK">Українська / Ukrainian</option>
                    <option value="VI">Tiếng việt / Vietnamese</option>
                    <option value="ZH">中文 / Chinese</option>
                    <option value="OL">Other language</option>
                </select>
            </div>

            <div class="form-group">
                <label for="editor">Message</label>
                <textarea class="form-control" name="message" rows="7" id="editor" placeholder="Message"
                          required></textarea>
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
                        <input id="caseInput" name="fileCase[]" type="file" multiple class="file-loading">
                    </div>
                </div>
                <div class="col-md-6 col-sm-12 col-xs-12">
                    <div class="form-group text-right mt25">
                        <button type="submit" class="btn btn-primary btn-mobile">Send case</button>
                    </div>
                </div>
            </div>
        </form>
        <!-- #End Estimation -->

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