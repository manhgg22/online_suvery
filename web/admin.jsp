<%-- 
    Document   : admin
    Created on : Jun 4, 2024, 12:49:21 PM
    Author     : Norttie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
            />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Dashboard - Admin</title>
        <!--<link rel="stylesheet" href="./assets/css/bootstrap.min.css" />-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="./assets/css/admin.css" />
        <!-- Embed google font -->
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;1,100&display=swap"
            rel="stylesheet"
            />

        <link
            href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css"
            rel="stylesheet"
            />
        <script
            src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
            crossorigin="anonymous"
        ></script>



    </head>
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="index.html">Admin </a>
            <!-- Sidebar Toggle-->
            <button
                class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0"
                id="sidebarToggle"
                href="#!"
                >
                <i class="fas fa-bars"></i>
            </button>
            <!-- Navbar Search-->

            <!-- Navbar-->
            <ul class="navbar-nav ms-auto">
                <li class="nav-item dropdown">
                    <a
                        class="nav-link dropdown-toggle"
                        id="navbarDropdown"
                        href="#"
                        role="button"
                        data-bs-toggle="dropdown"
                        aria-expanded="false"
                        ><i class="fas fa-user fa-fw"></i
                        ></a>
                    <ul
                        class="dropdown-menu dropdown-menu-end"
                        aria-labelledby="navbarDropdown"
                        >

                        <li><a class="dropdown-item" href="Login">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav
                    class="sb-sidenav accordion sb-sidenav-dark"
                    id="sidenavAccordion"
                    >
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading">Core</div>
                            <a class="nav-link" href="index.html">
                                <div class="sb-nav-link-icon">
                                    <i class="fas fa-tachometer-alt"></i>
                                </div>
                                Dashboard
                            </a>

                        </div>
                        <div class="sb-sidenav-footer">
                            <div class="small">Logged in as:</div>
                            admin
                        </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container">
                        <h1 class="mt-4">Dashboard</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Dashboard</li>
                        </ol>

                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                DataTable User
                            </div>
                            <div class="card-body">
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Id</th>
                                            <th>Username</th>
                                            <th>Password</th>
                                            <th>Displayname</th>
                                            <th>Description</th>
                                            <th>Address</th>
                                            <th>Role</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>#</th>
                                            <th>Id</th>
                                            <th>Username</th>
                                            <th>Password</th>
                                            <th>Displayname</th>
                                            <th>Description</th>
                                            <th>Address</th>
                                            <th>Role</th>
                                            <th>Action</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                        <c:forEach items="${listU}" var="u" varStatus="i">
                                            <tr>
                                                <td>${i.index + 1}</td>
                                                <td>${u.user_id}</td>
                                                <td>${u.username}</td>
                                                <td>${u.password}</td>
                                                <td>${u.displayname}</td>
                                                <td>
                                                    <c:if test="${u.description != null}">
                                                        ${u.description}
                                                    </c:if>
                                                    <c:if test="${u.description == null}">
                                                        null
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${u.address != null}">
                                                        ${u.address}
                                                    </c:if>
                                                    <c:if test="${u.address == null}">
                                                        null
                                                    </c:if>
                                                </td>
                                                <td>${u.role}</td>
                                                <td>
                                                    <a href="admin?ac=upd&&accid=${u.user_id}" class="btn btn-primary" >Update</a>
                                                    <a href="admin?ac=del&&accid=${u.user_id}" class="btn btn-danger">Delete</a>
                                                </td>
                                            </tr>
                                        </c:forEach>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </main>
                <section class="container form-user" id="formuser">
                    <div class="row">
                        <form action="admin" class="col-12"method="post">

                            <h1 class="text-center">Update user</h1>
                            <div class="row">
                                <div data-mdb-input-init class="form-outline mb-4 col-12">
                                    <div class="col-6">

                                        <label class="form-label" for="form2Example1"
                                               >User id</label
                                        >
                                        <input
                                            type="text"
                                            id="userid"
                                            class="form-control"
                                            name="userid"
                                            value="${userid}"
                                            required
                                            />
                                    </div>
                                    <p class="text-danger col-6">${error}</p>

                                </div>
                                <!-- Username input -->
                                <div data-mdb-input-init class="form-outline mb-4 col-6">
                                    <label class="form-label" for="form2Example1"
                                           >User name</label
                                    >
                                    <input
                                        type="text"
                                        id="username"
                                        class="form-control"
                                        name="username"
                                        value="${username}"
                                        required
                                        />

                                </div>

                                <!-- Display name input -->
                                <div data-mdb-input-init class="form-outline mb-4 col-6">
                                    <label class="form-label" for="form2Example2"
                                           >Display name</label
                                    >
                                    <input
                                        type="text"
                                        id="displayname"
                                        class="form-control"
                                        name="displayname"
                                        value="${displayname}"
                                        required
                                        />

                                </div>
                            </div>

                            <div class="row">
                                <!-- password input -->
                                <div data-mdb-input-init class="form-outline mb-4 col-6">
                                    <label class="form-label" for="form2Example2"
                                           >password</label
                                    >
                                    <input
                                        type="password"
                                        id="password"
                                        class="form-control"
                                        name="password"
                                        value="${password}"
                                        required
                                        />

                                </div>

                                <!-- address input -->
                                <div data-mdb-input-init class="form-outline mb-4 col-6">
                                    <label class="form-label" for="form2Example2"
                                           >Address</label
                                    >
                                    <input
                                        type="text"
                                        id="address"
                                        class="form-control"
                                        value="${address}"
                                        name="address"
                                        />

                                </div>

                                <!-- description input -->
                                <div data-mdb-input-init class="form-outline mb-4">
                                    <label class="form-label" for="form2Example2"
                                           >Description</label
                                    >
                                    <input
                                        type="text"
                                        id="description"
                                        class="form-control"
                                        value="${desc}"
                                        name="desc"
                                        />

                                </div>
                            </div>


                            <!-- Submit button -->
                            <button
                                type="submit"
                                data-mdb-button-init
                                data-mdb-ripple-init
                                class="btn btn-block mb-4 text-white"
                                style="background-color: #58dcf1"
                                >
                                submit
                            </button>
                            <form/>



                            </section>
                            <footer class="py-4 bg-light mt-auto">
                                <div class="container-fluid px-4">
                                    <div
                                        class="d-flex align-items-center justify-content-between small"
                                        >
                                        <div class="text-muted">
                                            Copyright &copy; Your Website 2023
                                        </div>
                                        <div>
                                            <a href="#">Privacy Policy</a>
                                            &middot;
                                            <a href="#">Terms &amp; Conditions</a>
                                        </div>
                                    </div>
                                </div>
                            </footer>

                    </div>
            </div>


            <script src="./assets/js/sidebarjs.js"></script>
            <script src="./assets/js/datatables-simple-demo.js"></script>

            <script
                src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
                crossorigin="anonymous"
            ></script>

            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

    </body>
</html>


