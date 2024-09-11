<%-- 
    Document   : answer
    Created on : Jul 3, 2024, 10:11:08 PM
    Author     : Norttie
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Answer Survey</title>
        <link rel="stylesheet" href="./assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="./assets/css/styles.css">
        <script src="./assets/js/script.js"></script>
    </head>
    <body>
        <c:if test="${sessionScope.acc.role !='student' && sessionScope.acc.role !='teacher'}">
            <c:redirect url="login"/>
        </c:if>
        <!--header-->
        <jsp:include page="./jsptemplate/header.jsp" />
        <!--end header-->

        <c:if test="${check}">
            <div class="container survey">
                <h1 class="survey-title"><strong>${survey.title}</strong></h1>
                <hr>
                <p class="text-center">Thank for answer survey!</p>
            </div>

        </c:if>
        <c:if test="${!check}">
            <div class="container survey">
                <h1 class="survey-title"><strong>${survey.title}</strong></h1>
                <hr>
                <form action="Answersurvey" method="post">
                    <input type="hidden" name="surveyId" value="${surveyId}">
                    <c:forEach var="question" items="${questions}" varStatus="i">

                        <p><strong>${i.index+1}. ${question.questionText}</strong></p>
                        <c:choose>
                            <c:when test="${question.questionType == 'text'}">
                                <input type="text" name="answers_${question.questionId}">
                            </c:when>
                            <c:when test="${question.questionType == 'radio'}">
                                <c:forEach var="option" items="${question.options}">
                                    <div class="input-wrap">
                                        <input type="radio" class="input-icon" name="answers_${question.questionId}" value="${option}">${option}<br>
                                        <div/>
                                    </c:forEach>
                                </c:when>
                                <c:when test="${question.questionType == 'checkbox'}">
                                    <c:forEach var="option" items="${question.options}">
                                        <div class="input-wrap">
                                            <input type="checkbox" class="input-icon" name="answers_${question.questionId}" value="${option}" >${option}<br>
                                            <div/>
                                        </c:forEach>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                            <br>
                            <button type="submit" class="survey-submit">Gủi khảo sát</button>
                            </form>
                        </div>
                    </c:if>

                    <!-- footer [component]-->
                    <jsp:include page="./jsptemplate/footer.jsp" />
                    <!--end footer-->
                    <!--end home content-->
                    </body>
                    </html>
