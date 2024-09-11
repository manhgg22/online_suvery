<%-- 
    Document   : newsurvey
    Created on : Jul 2, 2024, 4:10:08 PM
    Author     : Norttie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Online Survey</title>
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

    <div class="container survey">
        <h1>Tạo Khảo Sát</h1>
        <form id="surveyForm" action="newsurvey" method="POST" class="form-survey">
            <label for="surveyTitle">Tiêu đề khảo sát:</label>
            <input type="text" id="surveyTitle" name="surveyTitle" required>

            <div id="questionsContainer">
                <div class="question">
                    <label for="question1">Câu hỏi 1:</label>
                    <input type="text" id="question1" name="questions[]" required>

                    <label for="questionType1">Loại câu hỏi:</label>
                    <select id="questionType1" name="questionTypes[]" onchange="addOptions(this, 1)">
                        <option value="text">text</option>
                        <option value="radio">Radio</option>
                        <option value="checkbox">Checkbox</option>
                    </select>

                    <div id="optionsContainer1"></div>
                </div>
            </div>

            <button type="button" onclick="addQuestion()" class="survey-btn">Thêm câu hỏi</button>
            <button type="submit" class="survey-submit">Tạo khảo sát</button>
        </form>
    </div>
    <!-- footer [component]-->
    <jsp:include page="./jsptemplate/footer.jsp" />
    <!--end footer-->
    <!--end home content-->
</body>
</html>
