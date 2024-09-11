<%-- 
    Document   : surveyDetail
    Created on : Jul 5, 2024, 10:12:35 PM
    Author     : Norttie
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Survey Results</title>
        <link rel="stylesheet" href="./assets/css/bootstrap.min.css" />
        <link rel="stylesheet" href="./assets/css/styles.css" />
        <script src="./assets/js/script.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    </head>
    <body>
        <c:if test="${sessionScope.acc.role !='student' && sessionScope.acc.role !='teacher'}">
            <c:redirect url="login"/>
        </c:if>
        <!-- header [component]-->
        <jsp:include page="./jsptemplate/header.jsp" />
        <!--end header-->
        <div class="container mt-5 survey">
            <a  class="btn btn-light" onclick="goBack()">Back</a>
            <h1 class="mb-3">Survey title:${survey.title}</h1>
            <hr>
            <p>create at: ${survey.create_at}</p>
            <h2 class="mb-3">Survey Results:</h2>
            <c:forEach var="entry" items="${resultsMap.entrySet()}">
                <div>
                    <h2>Question${i.index+1}: ${entry.value.questionText}</h2>
                    <c:choose>
                        <c:when test="${entry.value.questionType == 'text'}">
                            <div>
                                <c:forEach var="answer" items="${entry.value.textAnswers}">
                                    <p  class="ml-5">${answer}</p>
                                </c:forEach>
                            </div>
                        </c:when>
                        <c:when test="${entry.value.questionType == 'radio' || entry.value.questionType == 'checkbox'}">
                            <div class="input-wrap">
                                <c:forEach var="option" items="${entry.value.optionCounts.entrySet()}">
                                    <input type="${entry.value.questionType}" class="input-icon" disabled>${option.key}: ${option.value}<br>
                                </c:forEach>
                            </div>
                        </c:when>
                    </c:choose>
                </div>
            </c:forEach>
        </div>
        <!-- footer [component]-->
  <jsp:include page="./jsptemplate/footer.jsp" />
                    <!--end footer-->
    <!--end home content-->
        <script>
            function goBack() {
                window.history.back();
            }
        </script>
    </body>
</html>

