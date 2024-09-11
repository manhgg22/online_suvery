<%-- 
    Document   : profile
    Created on : Jun 11, 2024, 8:20:46 PM
    Author     : Norttie
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Profile</title>
        <link rel="stylesheet" href="./assets/css/bootstrap.min.css" />
        <link rel="stylesheet" href="./assets/css/styles.css" />
        <style>
            .no-border {
                border: none;
                outline: none;
                box-shadow: none;
            }
            .opa-05{
                opacity: 0.5;
            }
        </style>
    </head>
    <body style="
          background-color: #e7e7e7;
          ">
        <c:if test="${sessionScope.acc.role !='student' && sessionScope.acc.role !='teacher'}">
            <c:redirect url="login"/>
        </c:if>
        <!-- header [component]-->
        <jsp:include page="./jsptemplate/header.jsp" />
        <!--end header-->
        <section >
            <div class="container py-5">
                <div class="row form-log">
                    <div class="col-lg-4">
                        <div class="card mb-4">
                            <div class="card-body text-center rounded-3">
                                <img src="./assets/image/${sessionScope.acc.avatar}" alt="avatar"
                                     class="rounded-circle img-fluid" style="width: 150px;">
                                <h4 class="my-3 pro-name">${sessionScope.acc.displayname}</h4>
                                <p class="text-muted mb-1">${sessionScope.acc.description}</p>
                                <p class="text-muted mb-4">${sessionScope.acc.address}</p>
                            </div>
                        </div>

                    </div>
                    <div class="col-lg-8">
                        <div class="card mb-4">
                            <div class="card-body rounded-3 ">
                                <form action="profile" method="POST" id="myForm" enctype="multipart/form-data">

                                    <div class="row align-items-center">
                                        <input type="text" name="id" value="${sessionScope.acc.user_id}" hidden />
                                    </div>

                                    <div class="row align-items-center">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Display Name</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <input type="text" class="mb-0 no-border text-muted " name="displayname" value="${sessionScope.acc.displayname}"/>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row align-items-center">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Description</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <input type="text" class="mb-0 no-border text-muted " name="desc" value="${sessionScope.acc.description}"/>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row align-items-center">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Address</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <input type="text" class="mb-0 no-border text-muted " name="address" value="${sessionScope.acc.address}"/>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row align-items-center">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Avatar</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <input type="file" class="mb-0 no-border text-muted "  name="file" accept="image/"/>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="d-flex justify-content-end">
                            <button type="submit" class="btn btn-primary col-1 ml-auto" id="submitBtn">Save</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- footer [component]-->
        <jsp:include page="./jsptemplate/footer.jsp" />
        <!--end footer-->
        <!--end home content-->
        <script>
            document.getElementById('submitBtn').addEventListener('click', function () {
                document.getElementById('myForm').submit();
            });
        </script>
    </body>
</html>
