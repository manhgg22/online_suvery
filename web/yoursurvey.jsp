<%-- 
    Document   : yourservey
    Created on : Jul 2, 2024, 4:18:38 PM
    Author     : Norttie
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./assets/css/bootstrap.min.css" />
        <link rel="stylesheet" href="./assets/css/styles.css" />
        <script src="./assets/js/script.js"></script>
    </head>
    <body>
        <c:if test="${sessionScope.acc.role !='student' && sessionScope.acc.role !='teacher'}">
            <c:redirect url="login"/>
        </c:if>
        <!-- header [component]-->
        <jsp:include page="./jsptemplate/header.jsp" />
        <!--end header-->
        <div class=" container form-log" style="min-height: 500px;">
            <h1 class="content-title text-center mb-3">Your Survey</h1>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Survey code</th>
                        <th scope="col" class="rs-name">
                            Survey Title
                        </th>
                        <th scope="col">Number of questions</th>
                        <th scope="col">Number of answer</th>
                        <th scope="col">Create at</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listS}" varStatus="i" var="s">
                        <tr data-href="SurveyResults?surveyId=${s.survey_id}">
                            <th scope="row">${s.survey_id}</th>
                            <th scope="col" >${s.title}</th>
                            <th scope="col">${listQ.get(i.index)}</th>
                            <th scope="col">${listA.get(i.index)}</th>
                            <th scope="col">${s.create_at}</th>
                            <th scope="col">
                                <a href="SurveyResults?surveyId=${s.survey_id}" class="btn btn-primary">Detail</a>
                                <a href="surveymanage?ac=del&surveyId=${s.survey_id}" class="btn btn-danger">Delete</a>
                            </th>
                        </tr>

                    </c:forEach>

                </tbody>
            </table>
            <div class="d-flex justify-content-between">
                <form action="surveymanage" class="" method="GET">
                    <input type="text" name="search" class="search" value="${search}" autofocus/>
                    <input type="submit" class="btn btn-cos" value="search"/>

                </form>
                <ul class="pagination pagination-lg">
                    <c:forEach begin="1" end="${numPage}" var="i">
                        <li class="page-item"> <a class="page-link" href="?index=${i}">${i}</a></li>
                        </c:forEach>
                </ul>
            </div>

        </div>
         <!-- footer [component]-->
         <jsp:include page="./jsptemplate/footer.jsp" />
                    <!--end footer-->
    <!--end home content-->
    </body>
</html>
