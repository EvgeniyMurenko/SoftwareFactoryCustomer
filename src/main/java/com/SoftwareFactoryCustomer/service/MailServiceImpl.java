package com.SoftwareFactoryCustomer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Service("mailService")
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender mailSender;

    String serverEmail = "sofac.team@gmail.com";

    @Override
    public void sendNaverMailAfterEstimate(String estimateId, String registrationLink, String recipientMail) {
        try {
            mailSender.send(new MimeMessagePreparator() {

                public void prepare(MimeMessage mimeMessage) throws Exception {
                    mimeMessage.setFrom(new InternetAddress("sofac.team@gmail.com", "SoFAC"));
                    mimeMessage.setRecipient(Message.RecipientType.TO,
                            new InternetAddress(recipientMail));
                    mimeMessage.setSubject("[SoFAC] 소프트웨어팩토리에 문의해 주셔서 감사드립니다. (" + estimateId + ")", "utf-8");
                    mimeMessage.setContent("<table\n" +
                            "       style=\"margin: 0px auto; padding: 0px; border: 0px currentColor; border-image: none; text-align: left;!important; line-height: inherit; font-size: inherit; border-collapse: collapse; border-spacing: 0; -premailer-cellpadding: 0; -premailer-cellspacing: 0;\"\n" +
                            "       cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                            "    <tbody>\n" +
                            "    <tr>\n" +
                            "        <td style=\"height: 16px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; -premailer-height: 16;\" height=\"16\"></td>\n" +
                            "    </tr>\n" +
                            "    <tr>\n" +
                            "        <td style=\"width: 745px; !important; -premailer-width: 745;\">\n" +
                            "            <table class=\"iPhone_font\"\n" +
                            "                   style=\"padding: 0px; border: 0px currentColor; border-image: none; line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: inherit;\">\n" +
                            "                <tbody>\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"width: 745px;\">\n" +
                            "\t\t\t\t\t\t\t<!-- Header -->\n" +
                            "\t\t\t\t\t\t\t<div class=\"clearfix\" style=\"padding: 10px 0 15px 0; margin-bottom: 5px; \">\n" +
                            "\t\t\t\t\t\t\t\t<div style=\"float: left;\">\n" +
                            "\t\t\t\t\t\t\t\t\t<a href=\"http://www.sofac.kr\" target=\"_blank\" style=\"text-decoration: none; font-size: 24pt; color: #333;\">소프트웨어<span\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\tstyle=\"font-weight: 600; color: #03a9f4;\">팩토리</span></a>\n" +
                            "\t\t\t\t\t\t\t\t\t<div style=\"font-size: 9pt; color: #999;\">SoFAC : <span style=\"font-style: italic;\">Software Factory</span>\n" +
                            "\t\t\t\t\t\t\t\t\t</div>\n" +
                            "\t\t\t\t\t\t\t\t</div>\n" +
                            "\n" +
                            "\t\t\t\t\t\t\t\t<div style=\"float: right; padding-top: 15px;\">\n" +
                            "\t\t\t\t\t\t\t\t\t<a href=\"http://www.sofac.kr/whatIsSofac\" target=\"_blank\">소프트웨어 팩토리 란 무엇입니까?</a>\n" +
                            "\t\t\t\t\t\t\t\t</div>\n" +
                            "\t\t\t\t\t\t\t</div>\n" +
                            "\t\t\t\t\t\t\t<!-- #End Header -->\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"text-align: left; color: rgb(51, 51, 51); line-height: 1px; padding-top: 0px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t<div class=\"clearfix\" style=\" margin: 10px 0; border-bottom: 1px solid #eee;\">\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"text-align: left; color: rgb(51, 51, 51); line-height: 18px; padding-top: 0px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t<b>접수번호 : "+estimateId+"</b>\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 10px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t먼저 소프트웨어팩토리에 문의해 주셔서 감사드립니다.\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 0px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t문의 하신 내용은 대부분 소프트웨어를 만들기 위한 아이디어나 정해진 작업 내용에 대한 가격 및 업무 방법일 것입니다.\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 10px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t따라서 고객님의 요청내용은 작업의 종류의 규모를 기준으로 기술적인 견적과 업무 방법 그리고 기초적인 분석 자료를 함께 보내드립니다.\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 10px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t대부분 업무시간 이내의 경우 3시간 이내에 답변을 드리며 질문이 필요할 경우 질문 메일을 드릴 수 도 있습니다.\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 10px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t내용 확인 후 지속적인 대화를 원하실 경우 소팩은 CASE라는 개념으로 고객과 소통하는 기능을 제공하고 있습니다.\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 0px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t따라서 예비 고객등록을 먼저 해 주시면 아이디를 발급해 드리며\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 0px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t그 아이디를 통하여 지속적인 대화가 가능합니다. (CASE ID 발급 요청 : <a href = \"http://"+registrationLink+"\" style=\"color: #03a9f4 !important; cursor: pointer !important; text-decoration: none !important;\">"+registrationLink+"</a>)\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 0px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t질문과 답변 형식으로 대화가 진행되며 대화는 견적, 작업방법등 모든 종류의 대화 진행이 가능합니다. \n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 0px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t소팩은 소프트웨어(앱, 웹, IOT 등) 개발 및 유지보수 서비스를 대행하는 기업 입니다.\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 10px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t전세계의 개발 인프라들을 FXM이라는 공장형 작업 관리시스템에 의해서 대량 작업 및\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 0px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t원가절감 퀄리티 높은 완성도를 유지하고 있습니다.\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 10px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t소팩은 소프트웨어 개발 대행 업계에서 유일하게 세계적 인프라를 갖추어\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 0px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t저 비용 고효율, 지속성 유지를 가능하게 하고 있습니다.\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 10px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t자세한 내용은 홈페이지를 참고 하시기 바랍니다.\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 0px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t<a href = \"http://www.sofac.kr\" style=\"color: #03a9f4 !important; cursor: pointer !important; text-decoration: none !important;\">www.sofac.kr</a>\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 10px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t감사합니다.\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 15px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t(이 메일은 발송 전용 메일 입니다.)\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 10px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\tNo need to reply to this email.\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"text-align: left; color: rgb(51, 51, 51); line-height: 18px; padding-top: 0px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t<!-- Footer -->\n" +
                            "\t\t\t\t\t\t\t<div class=\"clearfix\" style=\"margin: 20px 10px 10px 10px; padding-top: 15px; border-top: 1px solid #eee;\">\n" +
                            "\n" +
                            "\t\t\t\t\t\t\t\t<div style=\"color: #999; font-size: 9pt; float: left;\">\n" +
                            "\t\t\t\t\t\t\t\t\tPolicy of SoFAC<br/>\n" +
                            "\t\t\t\t\t\t\t\t\t<a href=\"http://www.sofac.kr/policy\"><span style=\"font-size: 9pt;\">SoFAC 고객 정책</span></a>\n" +
                            "\t\t\t\t\t\t\t\t</div>\n" +
                            "\t\t\t\t\t\t\t\t<div style=\"color: #999; font-size: 9pt; float: right; text-align: right;\">\n" +
                            "\t\t\t\t\t\t\t\t\t220-87-45112<br/>\n" +
                            "\t\t\t\t\t\t\t\t\t서울시 강남구 역삼동 해성빌딩 7층<br/>\n" +
                            "\t\t\t\t\t\t\t\t\t대표자 : 박상만\n" +
                            "\t\t\t\t\t\t\t\t</div>\n" +
                            "\n" +
                            "\t\t\t\t\t\t\t</div>\n" +
                            "\t\t\t\t\t\t\t<div style=\"color: #999; font-size: 9pt; text-align: center; padding: 10px;\">\n" +
                            "\t\t\t\t\t\t\t\tCopyright © 2016. All rights reserved\n" +
                            "\t\t\t\t\t\t\t</div>\n" +
                            "\t\t\t\t\t\t\t<!-- Footer -->\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\t\n" +
                            "                </tbody>\n" +
                            "            </table>\n" +
                            "        </td>\n" +
                            "    </tr>\n" +
                            "    <tr style=\"height: 17px; -premailer-height: 17;\" height=\"17\">\n" +
                            "        <td style=\"font-family: Geneva, Helvetica, Arial, sans-serif;\"></td>\n" +
                            "    </tr>\n" +
                            "    <tr>\n" +
                            "        <td style=\"background-position: top; padding: 15px 0px 12px; width: 685px; text-align: center; color: rgb(136, 136, 136); line-height: 14px; font-family: Geneva, Helvetica, Arial, sans-serif; font-size: 11px; background-repeat: no-repeat; -webkit-font-smoothing: antialiased; -webkit-text-size-adjust: 100%; font-smooth: always;\"\n" +
                            "            colspan=\"3\" background=\"http://sofac.kr/resources/indexPage/images/footer_gradient_web.png\"\n" +
                            "            align=\"center\">\n" +
                            "               \n" +
                            " \n" +
                            "        </td>\n" +
                            "    </tr>\n" +
                            "    <tr style=\"height: 50px; -premailer-height: 50;\" height=\"50\">\n" +
                            "        <td style=\"font-family: Geneva, Helvetica, Arial, sans-serif;\"></td>\n" +
                            "    </tr>\n" +
                            "    </tbody>\n" +
                            "</table>", "text/html; charset=utf-8");

                }
            });
            System.out.println("Message Send...Hurrey");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void sendNaverMailAfterRegistration(String password, String login, String recipientMail, String recipientName) {
        try {
            mailSender.send(new MimeMessagePreparator() {

                public void prepare(MimeMessage mimeMessage) throws Exception {
                    mimeMessage.setFrom(new InternetAddress("sofac.team@gmail.com", "SoFAC"));
                    mimeMessage.setRecipient(Message.RecipientType.TO,
                            new InternetAddress(recipientMail));
                    mimeMessage.setSubject("[SoFAC]소프트웨어팩토리에 가입 하신 것을 축하드립니다.", "utf-8");
                    mimeMessage.setContent("<table\n" +
                            "       style=\"margin: 0px auto; padding: 0px; border: 0px currentColor; border-image: none; text-align: left;!important; line-height: inherit; font-size: inherit; border-collapse: collapse; border-spacing: 0; -premailer-cellpadding: 0; -premailer-cellspacing: 0;\"\n" +
                            "       cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                            "    <tbody>\n" +
                            "    <tr>\n" +
                            "        <td style=\"height: 16px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; -premailer-height: 16;\" height=\"16\"></td>\n" +
                            "    </tr>\n" +
                            "    <tr>\n" +
                            "        <td style=\"width: 745px; !important; -premailer-width: 745;\">\n" +
                            "            <table class=\"iPhone_font\"\n" +
                            "                   style=\"padding: 0px; border: 0px currentColor; border-image: none; line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: inherit;\">\n" +
                            "                <tbody>\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"width: 745px;\">\n" +
                            "\t\t\t\t\t\t\t<!-- Header -->\n" +
                            "\t\t\t\t\t\t\t<div class=\"clearfix\" style=\"padding: 10px 0 15px 0; margin-bottom: 5px; \">\n" +
                            "\t\t\t\t\t\t\t\t<div style=\"float: left;\">\n" +
                            "\t\t\t\t\t\t\t\t\t<a href=\"http://www.sofac.kr\" target=\"_blank\" style=\"text-decoration: none; font-size: 24pt; color: #333;\">소프트웨어<span\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\tstyle=\"font-weight: 600; color: #03a9f4;\">팩토리</span></a>\n" +
                            "\t\t\t\t\t\t\t\t\t<div style=\"font-size: 9pt; color: #999;\">SoFAC : <span style=\"font-style: italic;\">Software Factory</span>\n" +
                            "\t\t\t\t\t\t\t\t\t</div>\n" +
                            "\t\t\t\t\t\t\t\t</div>\n" +
                            "\n" +
                            "\t\t\t\t\t\t\t\t<div style=\"float: right; padding-top: 15px;\">\n" +
                            "\t\t\t\t\t\t\t\t\t<a href=\"http://www.sofac.kr/whatIsSofac\" target=\"_blank\">소프트웨어 팩토리 란 무엇입니까?</a>\n" +
                            "\t\t\t\t\t\t\t\t</div>\n" +
                            "\t\t\t\t\t\t\t</div>\n" +
                            "\t\t\t\t\t\t\t<!-- #End Header -->\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"text-align: left; color: rgb(51, 51, 51); line-height: 1px; padding-top: 0px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t<div class=\"clearfix\" style=\" margin: 10px 0; border-bottom: 1px solid #eee;\">\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"text-align: left; color: rgb(51, 51, 51); line-height: 18px; padding-top: 0px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t안녕하세요 "+recipientName+" 고객님\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 0px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t먼저 소프트웨어팩토리에 회원이 되신 것을 감사드립니다.\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 20px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t정상적으로 가입 처리가 완료 되었습니다.\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 10px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t고객님의 아이디는 : "+login+" 입니다.\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 0px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t패스워드는 : "+password+" 입니다. \n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 30px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t최초 패스워드는 고객님의 패스워드 입니다.\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 0px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t로그인 후 수정하시기 바랍니다.\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 10px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t고객님은 소프트웨어팩토리 웹사이트(<a href = \"http://www.sofac.kr/\" style=\"color: #03a9f4 !important; cursor: pointer !important; text-decoration: none !important;\">www.sofac.kr</a>) 에서 위의 계정을 로그인 하신 후 다양한 형태의 대화를 나눌 수 있습니다.\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 0px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t견적, 작업내용정리, 작업과정참여, 테스트, 업그레이드요청 등 모든 종류의 대화는 CASE를 통해서 가능합니다.\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 40px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\tCASE는 정확한 약속을 드립니다.\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 0px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t언제까지 어떤 결과에 대한 답변을 드릴 것을 약속하는 시스템입니다.\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 0px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t그러므로 고객님은 편안한 느낌으로 지속적인 대화와 업무 진행 과정을 함께 하실 수 있습니다.\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 0px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\tCASE에 잘 적응하셔서 편안하고 안전한 소프트웨어 개발 지원 받으시기 바랍니다.\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 20px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t참고로\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 0px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t소프트웨어는 스마트폰이 지배하는 SNS 시대에 사업이나 각종 업무에 필수적인 요소 입니다.\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 0px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t제 4산업으로 일컫는 새로운 시대는 소프트웨어 세상을 지배하는 시대를 말합니다.\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 0px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t고객님이 무슨 일을 하던 소프트웨어 지원 없이는 비즈니스가 불가능한 것이 현대 사회의 특징입니다.\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 0px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t소프트웨어팩토리는 고객님이 필요로 하는 다양한 종류의 소프트웨어 개발 대행 및 유지보수 대행을 전문적으로 하는 다국적 기업입니다.\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 30px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t(이 메일은 발송 전용 메일 입니다.)\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"padding: 10px 0px 0px; text-align: left; color: rgb(51, 51, 51); line-height: 18px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\tNo need to reply to this email.\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\n" +
                            "\t\t\t\t\t<tr>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t\t<td style=\"text-align: left; color: rgb(51, 51, 51); line-height: 18px; padding-top: 0px; font-family: Lucida Grande, Helvetica, Arial, sans-serif; font-size: 14px; -webkit-font-smoothing: antialiased; font-smooth: always;\">\n" +
                            "\t\t\t\t\t\t\t<!-- Footer -->\n" +
                            "\t\t\t\t\t\t\t<div class=\"clearfix\" style=\"margin: 20px 10px 10px 10px; padding-top: 15px; border-top: 1px solid #eee;\">\n" +
                            "\n" +
                            "\t\t\t\t\t\t\t\t<div style=\"color: #999; font-size: 9pt; float: left;\">\n" +
                            "\t\t\t\t\t\t\t\t\tPolicy of SoFAC<br/>\n" +
                            "\t\t\t\t\t\t\t\t\t<a href=\"http://www.sofac.kr/policy\"><span style=\"font-size: 9pt;\">SoFAC 고객 정책</span></a>\n" +
                            "\t\t\t\t\t\t\t\t</div>\n" +
                            "\t\t\t\t\t\t\t\t<div style=\"color: #999; font-size: 9pt; float: right; text-align: right;\">\n" +
                            "\t\t\t\t\t\t\t\t\t220-87-45112<br/>\n" +
                            "\t\t\t\t\t\t\t\t\t서울시 강남구 역삼동 해성빌딩 7층<br/>\n" +
                            "\t\t\t\t\t\t\t\t\t대표자 : 박상만\n" +
                            "\t\t\t\t\t\t\t\t</div>\n" +
                            "\n" +
                            "\t\t\t\t\t\t\t</div>\n" +
                            "\t\t\t\t\t\t\t<div style=\"color: #999; font-size: 9pt; text-align: center; padding: 10px;\">\n" +
                            "\t\t\t\t\t\t\t\tCopyright © 2016. All rights reserved\n" +
                            "\t\t\t\t\t\t\t</div>\n" +
                            "\t\t\t\t\t\t\t<!-- Footer -->\n" +
                            "\t\t\t\t\t\t</td>\t\t\t\t\t\t \n" +
                            "\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\t\n" +
                            "                </tbody>\n" +
                            "            </table>\n" +
                            "        </td>\n" +
                            "    </tr>\n" +
                            "    <tr style=\"height: 17px; -premailer-height: 17;\" height=\"17\">\n" +
                            "        <td style=\"font-family: Geneva, Helvetica, Arial, sans-serif;\"></td>\n" +
                            "    </tr>\n" +
                            "    <tr>\n" +
                            "        <td style=\"background-position: top; padding: 15px 0px 12px; width: 685px; text-align: center; color: rgb(136, 136, 136); line-height: 14px; font-family: Geneva, Helvetica, Arial, sans-serif; font-size: 11px; background-repeat: no-repeat; -webkit-font-smoothing: antialiased; -webkit-text-size-adjust: 100%; font-smooth: always;\"\n" +
                            "            colspan=\"3\" background=\"http://sofac.kr/resources/indexPage/images/footer_gradient_web.png\"\n" +
                            "            align=\"center\">\n" +
                            "               \n" +
                            " \n" +
                            "        </td>\n" +
                            "    </tr>\n" +
                            "    <tr style=\"height: 50px; -premailer-height: 50;\" height=\"50\">\n" +
                            "        <td style=\"font-family: Geneva, Helvetica, Arial, sans-serif;\"></td>\n" +
                            "    </tr>\n" +
                            "    </tbody>\n" +
                            "</table>","text/html; charset=utf-8");

                }
            });
            System.out.println("Message Send...Hurrey");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }


    @Override
    public void sendBugExceptionToEmail(String message) {


        try {

            mailSender.send(new MimeMessagePreparator() {

                public void prepare(MimeMessage mimeMessage) throws Exception {

                    mimeMessage.setFrom(new InternetAddress(serverEmail, "SoFAC"));
                    mimeMessage.setRecipient(Message.RecipientType.TO,
                            new InternetAddress("sofac.bug.finder@gmail.com"));
                    mimeMessage.setSubject("CUSTOMER part", "utf-8");
                    mimeMessage.setText(message);

                }
            });

        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }
}