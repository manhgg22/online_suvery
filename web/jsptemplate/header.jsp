<%-- 
    Document   : header
    Created on : Jul 2, 2024, 2:14:05 PM
    Author     : Norttie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header class="header">
    <div class="container">
        <!-- Navbar -->
        <nav class="navbar navbar-expand navbar-light  d-flex justify-content-between">
            <a class="navbar-brand" href="home"><img src="./assets/image/logo.png" class="logo"/></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="home">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <c:if test="${not empty sessionScope.acc}">
                        <li class="nav-item active">
                            <a class="nav-link" href="newsurvey">New survey  <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="surveymanage">Your survey  <span class="sr-only">(current)</span></a>
                        </li>
                    </c:if>


                </ul>

            </div>
            <div class="user col-lg-3">
                <div class="user_control dropdown">

                    <c:if test="${not empty sessionScope.acc.avatar}">
                        <a href="profile.jsp">
                            <img
                                src="./assets/image/${sessionScope.acc.avatar}"
                                alt="avatar"
                                class="avatar"

                                />
                        </a>
                    </c:if>



                </div>
                <div class="user__name">${sessionScope.acc.username}</div>
                <c:choose>
                    <c:when test="${not empty sessionScope.acc}">
                        <a class="logout-btn" href="login.jsp">Log out</a>
                    </c:when>
                    <c:otherwise>
                        <a class="logout-btn" href="login.jsp">Log in</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </nav>

    </div>

</header>
