<%-- 
    Document   : login
    Created on : Jun 4, 2024, 12:49:31 PM
    Author     : Norttie
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Document</title>
        <link rel="stylesheet" href="./assets/css/bootstrap.min.css" />
        <link rel="stylesheet" href="./assets/css/styles.css" />
        <!-- Embed google font -->
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;1,100&display=swap"
            rel="stylesheet"
            />
    </head>
    <body style="
          background-image:url('./assets/image/5040007.jpg');
          background-repeat: no-repeat;
          background-size: cover;
          background-position: center;
          width: 100vw;
          margin: 0;
          ">
        <section class="w-100 p-4 d-flex justify-content-center mt-5">
            <form class="col-4 form-log" action="Login" method="post">

                <h1 class="text-center">Login</h1>
                <p class="text-danger">${mess}</p>
                <!-- Username input -->
                <div data-mdb-input-init class="form-outline mb-4">
                    <label class="form-label" for="form2Example2"
                           >Username</label
                    >
                    <input
                        type="text"
                        id="username"
                        class="form-control"
                        name="username"
                        required
                        />

                </div>

                <!-- Password input -->
                <div data-mdb-input-init class="form-outline mb-4">
                    <label class="form-label" for="form2Example2"
                           >Password</label
                    >
                    <input
                        type="password"
                        id="password"
                        class="form-control"
                        name="password"
                        required
                        />

                </div>



                <div >
                    <!-- Simple link -->
                    Not have account <a href="signup.jsp" style="color: #007bff"> sign up</a>
                </div>


                <!-- Submit button -->
                <button
                    type="submit"
                    data-mdb-button-init
                    data-mdb-ripple-init
                    class="btn btn-block mb-4 text-white"
                    style="background-color: #000"
                    >
                    Log in
                </button>
            </form>
        </section>
        <!-- Login -->

        <!-- component -->
        <script
            src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"
        ></script>
        <script src="./assets/js/script.js"></script>
    </body>
</html>


