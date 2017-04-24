<%@ page import="com.SoftwareFactoryCustomer.model.Notice" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.io.File" %>
<%@ page import="com.SoftwareFactoryCustomer.constant.MainPathEnum" %>
<%@ page import="com.SoftwareFactoryCustomer.constant.GlobalEnum" %>
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

    <title>공지 사항 :: Software Factory</title>

    <%@ include file="siteHeaderStyle.jsp" %>

</head>
<body>

<%@ include file="siteHeader.jsp" %>

<% List<Notice> noticeList = (List<Notice>) request.getAttribute("noticeList");%>
<%SimpleDateFormat dateFormatShow = new SimpleDateFormat("yyyy-MM-dd");%>

<!-- Content -->
<section class="container content mb40">
    <div class="row pt40">
        <div class="col-md-8">

            <div>

                <h3 class="other-font2">공지 사항</h3>

                <%
                    int count = 1;
                    for (Notice notice : noticeList) {
                        if (notice.getActiv()) {
                %>

                <!-- Notice box -->
                <div class="notice-box other-font2" style="color: #000;">
                    <a href="javascript:void(0);" id="<%out.print("notice_"+count);%>" class="clearfix">
                        <span class="pull-left"><%out.print(notice.getTitle());%></span>
                        <span class="pull-right"><%out.print(dateFormatShow.format(notice.getDataCreate()));%>
                                <i class="fa fa-chevron-down pl20"></i>
                            </span>
                    </a>
                </div>
                <div class="notice-box-information other-font2" id="<%out.print("box_notice_"+count);%>" style="display: none; color: #000;">
                    <%out.print(notice.getNoticeText());%>
                    <%
                        if (notice.getFilePath() != null) {
                            File imageDirectory = new File(MainPathEnum.mainPath + "/" + notice.getFilePath());
                            File[] imageFiles = imageDirectory.listFiles();
                            if (imageFiles != null && imageFiles.length>0){
                                for (int i = 0; i < imageFiles.length; i++) {
                                    if(!imageFiles[i].isDirectory()){
                                        String fileName = imageFiles[i].getName();
                                %>
                                            <img src="<%out.print(GlobalEnum.webRoot+"/show-image/notice/"+notice.getId()+"/"+fileName);%>"
                                                 alt="<%out.print(fileName);%>" class="img-responsive mb10">
                            <%      }
                                }
                            }

                            File videoDirectory = new File(MainPathEnum.mainPath+"/"+notice.getFilePath()+"/video");
                            File[] videoFiles= videoDirectory.listFiles();
                            if( videoFiles!= null && videoFiles.length > 0){
                                for (int i=0; i<videoFiles.length; i++){
                                    String urlVideo = GlobalEnum.webRoot+"/show-video/"+notice.getId()+"/"+videoFiles[i].getName(); %>

                                        <div class="form-group form-img-thumbnail">
                                            <video width="400" height="300" controls >
                                                <source src="<%out.print(urlVideo);%>" >
                                            </video>
                                        </div>
                            <%  }
                            }
                        }
                        %>

                </div>
                <!-- #End Notice box -->
                <% count++;
                }
                }%>

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