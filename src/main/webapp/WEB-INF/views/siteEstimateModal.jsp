<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" %>

<!-- Estimate modal window -->
<div id="estimationModal" class="modal fade">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">

            <!-- Estimate modal title -->
            <form id="estimationForm" action="/estimate?${_csrf.parameterName}=${_csrf.token}" method="POST"
                  class="form-horizontal" enctype="multipart/form-data">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4><i class="fa fa-address-card-o" aria-hidden="true"></i> 문의 해 주세요...</h4>
                    <b>견적요청 및 문의사항을 남겨 주시면 3시간 이내에 답변을 메일로 보내드립니다.</b>
                    <div class="mt10 es-form-title">
                        <p><i>이 페이지는 단 한번만의 답변을 받으실 수 있습니다.<br/>견적 또는 어떠한 질문도 하실 수 있으며 한번의 질문에 한번의 답변만 받으실 수 있습니다.</i>
                        </p>
                        <p><i>따라서 협상, 업무협의등 지속성이 필요하실 경우 답변으로 받으신 이메일에 기재 되어 있는 방법대로 요청하시면 고객 ID를 발급하여 드립니다.</i></p>
                        <p><i>고객 아이디를 발급 받으신 분은 어떤 문제에 대해서도 CASE로그인을 통하여 언제든지 대화를 이어 가실 수 있습니다.</i></p>
                    </div>
                </div>

                <!-- Estimate modal -->
                <div class="modal-body">

                    <div class="row">
                        <!-- fields -->
                        <div class="col-md-4">
                            <div class="form-group">
                                <div class="col-lg-12 text-left">
                                    <input type="text" name="name" class="form-control form-block" placeholder="이름"
                                           required/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-lg-12 text-left">
                                    <input type="email" name="email" class="form-control form-block" placeholder="이메일"
                                           required/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-lg-12 text-left">
                                    <input type="text" name="phone" id="phone" class="form-control bfh-phone" value=""
                                           pattern="[\(]\d{3}[\)]\s\d{4}[\-]\d{4}$" placeholder="전화번호"
                                           data-format="(ddd) dddd-dddd" maxlength="100" required/>
                                </div>
                            </div>

                            <div class="form-group m0" style="margin: 0 !important;">
                                <div class="checkbox">
                                    <input id="request" name="price_request" class="styled checkEstimation"
                                           type="checkbox">
                                    <label for="request">견적문의</label>
                                </div>

                                <div class="checkbox">
                                    <input id="question" name="question_request" class="styled checkEstimation"
                                           type="checkbox">
                                    <label for="question">일반문의</label>
                                </div>
                                <div id="alertCheckboxMessage"></div>
                            </div>
                        </div>
                        <!-- message -->
                        <div class="col-md-8">
                            <div class="form-group">
                                <div class="col-lg-12 text-left">
                                    <textarea class="form-control form-block" name="message" rows="9"
                                              placeholder="문의사항을 적어 주세요" required></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group mt20">
                        <div class="col-lg-12 text-left">
                            <input id="input-repl-2" name="fileEstimate[]" type="file" multiple class="file-loading">
                        </div>
                    </div>
                </div>

                <!-- Estimate modal footer -->
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary btn-mobile">보내기</button>
                    <button type="button" class="btn btn-default btn-mobile" data-dismiss="modal">닫기</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- #End Estimate modal window -->