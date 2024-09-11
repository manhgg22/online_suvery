<%-- 
    Document   : index.jsp
    Created on : Jul 2, 2024, 2:58:07 PM
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
        <script src="../assets/js/scripts.js"></script>
    </head>
    <body style="
          background-image: url('./assets/image/blog-banner.jpg');
          background-repeat: no-repeat;
          background-size: cover;
          background-position: center;
          height: 100vh;
          width: 100vw;
          margin: 0;
          ">
    <c:if test="${sessionScope.acc.role !='student' && sessionScope.acc.role !='teacher'}">
        <c:redirect url="login"/>
    </c:if>
    <!--header-->
    <jsp:include page="./jsptemplate/header.jsp" />
    <!--end header-->

    <!--home content-->
    <section class="container">
        <div class=" row">
            <div class="home-info col-6">
                <h1 class="home-info__header ">Total ${snum} surveys available </h1>
                <p>about ${unum} persons using this website</p>
                <form class="form-inline" action="search" method="get">
                    <input class="form-control  home-search" type="search" placeholder="Enter survey code" name="surveyId" aria-label="Search">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </form>
                <p class="text-danger">${result}</p>
            </div>

        </div>
    </section>
            

  <!-- footer [component]-->
  <jsp:include page="./jsptemplate/footer.jsp" />
                    <!--end footer-->
    <!--end home content-->
</body>
</html>
