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

                        <li><a class="dropdown-item" href="#!">Logout</a></li>
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
                            <div class="sb-sidenav-menu-heading">Interface</div>
                            <a
                                class="nav-link collapsed"
                                href="#"
                                data-bs-toggle="collapse"
                                data-bs-target="#collapseLayouts"
                                aria-expanded="false"
                                aria-controls="collapseLayouts"
                                >
                                <div class="sb-nav-link-icon">
                                    <i class="fas fa-columns"></i>
                                </div>
                                Layouts
                                <div class="sb-sidenav-collapse-arrow">
                                    <i class="fas fa-angle-down"></i>
                                </div>
                            </a>
                            <div
                                class="collapse"
                                id="collapseLayouts"
                                aria-labelledby="headingOne"
                                data-bs-parent="#sidenavAccordion"
                                >
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a
                                        class="nav-link"
                                        href="layout-static.html"
                                        >Static Navigation</a
                                    >
                                    <a
                                        class="nav-link"
                                        href="layout-sidenav-light.html"
                                        >Light Sidenav</a
                                    >
                                </nav>
                            </div>
                            <a
                                class="nav-link collapsed"
                                href="#"
                                data-bs-toggle="collapse"
                                data-bs-target="#collapsePages"
                                aria-expanded="false"
                                aria-controls="collapsePages"
                                >
                                <div class="sb-nav-link-icon">
                                    <i class="fas fa-book-open"></i>
                                </div>
                                Pages
                                <div class="sb-sidenav-collapse-arrow">
                                    <i class="fas fa-angle-down"></i>
                                </div>
                            </a>
                            <div
                                class="collapse"
                                id="collapsePages"
                                aria-labelledby="headingTwo"
                                data-bs-parent="#sidenavAccordion"
                                >
                                <nav
                                    class="sb-sidenav-menu-nested nav accordion"
                                    id="sidenavAccordionPages"
                                    >
                                    <a
                                        class="nav-link collapsed"
                                        href="#"
                                        data-bs-toggle="collapse"
                                        data-bs-target="#pagesCollapseAuth"
                                        aria-expanded="false"
                                        aria-controls="pagesCollapseAuth"
                                        >
                                        Authentication
                                        <div class="sb-sidenav-collapse-arrow">
                                            <i class="fas fa-angle-down"></i>
                                        </div>
                                    </a>
                                    <div
                                        class="collapse"
                                        id="pagesCollapseAuth"
                                        aria-labelledby="headingOne"
                                        data-bs-parent="#sidenavAccordionPages"
                                        >
                                        <nav class="sb-sidenav-menu-nested nav">
                                            <a
                                                class="nav-link"
                                                href="login.html"
                                                >Login</a
                                            >
                                            <a
                                                class="nav-link"
                                                href="register.html"
                                                >Register</a
                                            >
                                            <a
                                                class="nav-link"
                                                href="password.html"
                                                >Forgot Password</a
                                            >
                                        </nav>
                                    </div>
                                    <a
                                        class="nav-link collapsed"
                                        href="#"
                                        data-bs-toggle="collapse"
                                        data-bs-target="#pagesCollapseError"
                                        aria-expanded="false"
                                        aria-controls="pagesCollapseError"
                                        >
                                        Error
                                        <div class="sb-sidenav-collapse-arrow">
                                            <i class="fas fa-angle-down"></i>
                                        </div>
                                    </a>
                                    <div
                                        class="collapse"
                                        id="pagesCollapseError"
                                        aria-labelledby="headingOne"
                                        data-bs-parent="#sidenavAccordionPages"
                                        >
                                        <nav class="sb-sidenav-menu-nested nav">
                                            <a class="nav-link" href="401.html"
                                               >401 Page</a
                                            >
                                            <a class="nav-link" href="404.html"
                                               >404 Page</a
                                            >
                                            <a class="nav-link" href="500.html"
                                               >500 Page</a
                                            >
                                        </nav>
                                    </div>
                                </nav>
                            </div>
                            <div class="sb-sidenav-menu-heading">Addons</div>
                            <a class="nav-link" href="charts.html">
                                <div class="sb-nav-link-icon">
                                    <i class="fas fa-chart-area"></i>
                                </div>
                                Charts
                            </a>
                            <a class="nav-link" href="tables.html">
                                <div class="sb-nav-link-icon">
                                    <i class="fas fa-table"></i>
                                </div>
                                Tables
                            </a>
                        </div>
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
                        
                    </div>
                </main>
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
