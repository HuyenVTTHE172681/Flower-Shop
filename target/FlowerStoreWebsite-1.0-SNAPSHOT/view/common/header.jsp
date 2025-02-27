<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--==================== HEADER ====================-->

<nav class="navbar navbar-expand-lg navbar-light bg-light position-fixed top-0 start-0 w-100">
    <div class="container-header">
        <a href="home" class="navbar-brand d-lg-none">LovePik</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse p-2 flex-column" id="navbarContent">
            <div class="d-flex justify-content-center justify-content-lg-between flex-column flex-lg-row w-100 test">
                <a href="home" class="navbar-brand d-none d-lg-block">LovePik</a>

                <!-- Search -->
                <form action="search" method="get" class="d-flex search" style="padding-left: 200px;">
                    <input value="${txtS}" name="txt" type="search" class="form-control me-2 search-input" placeholder="Search" />
                    <button class="btn btn-outline-dark" type="submit">
                        <i class="fas fa-search"></i>
                    </button>
                </form>

                <ul class="navbar-nav">
                    <li class="nav-item d-flex align-items-center">
                        <!--==================== HEADER CHECK ROLE ====================-->
                        <ul class="navbar-nav">
                            <!-- ROLE USER -->
                            <c:if test="${sessionScope.user != null}">
                                <li class="nav-item d-flex align-items-center dropdown">
                                    <i class="fa-solid fa-user" style="margin-right: 10px;"></i>
                                    <a href="#" class="nav-link mx-2 nav-link dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false" style="color: black; padding: 0;">
                                        ${sessionScope.user.fullName}
                                    </a>
                                </li>

                                <ul class="dropdown-menu" style="margin-left: 20px;">
                                    <li>
                                        <a class="dropdown-item" href="profile" style="font-weight: bold;">Thong tin</a>
                                    </li>
                                    <li>
                                        <hr class="dropdown-divider">
                                    </li>
                                    <li>
                                        <a class="dropdown-item" href="history" style="font-weight: bold;">Lich su mua hang</a>
                                    </li>
                                    <li>
                                        <hr class="dropdown-divider">
                                    </li>
                                    <li>
                                        <a class="dropdown-item" href="logout" style="font-weight: bold;">Dang Xuat</a>
                                    </li>
                                </ul>
                            </c:if>

                            <c:if test="${sessionScope.user == null}">
                                <li class="nav-item d-flex align-items-center dropdown">
                                    <a href="register" class="nav-link mx-2 nav-link" style="color: black" aria-expanded="false">
                                        Dang Ky
                                    </a>   
                                    <a href="" class="nav-link mx-2 nav-link dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false" style="color: black;">
                                        Dang Nhap
                                    </a>                                    
                                    <ul class="dropdown-menu" style="margin-left: 100px;">
                                        <li>
                                            <a class="dropdown-item" href="login" style="font-weight: bold;">User</a>
                                        </li>
                                        <li>
                                            <hr class="dropdown-divider">
                                        </li>
                                        <li>
                                            <a class="dropdown-item" href="admin_login" style="font-weight: bold;">Admin</a>
                                        </li>
                                    </ul>
                                </li>
                            </c:if>
                        </ul>
                    </li>

                    <li class="nav-item d-flex align-items-center">
                        <c:if test="${sessionScope.user == null}">
                            <a href="login" class="nav-link mx-2">
                                <i class="fas fa-shopping-cart"></i>                          
                            </a>
                        </c:if>
                        <c:if test="${sessionScope.user != null}">
                            <a href="cart" class="nav-link mx-2">
                                <i class="fas fa-shopping-cart"></i>                          
                            </a>
                        </c:if> 
                    </li>
                </ul> 
            </div>
                            
             <div class="d-block w-100">
                <ul class="navbar-nav d-flex justify-content-center align-items-center pt-3 menu">
                    <li class="nav-item mx-2 ">
                        <a href="home" class="nav-link listmenu">
                            Trang Chu
                        </a>
                    </li>
                    <!-- List all of Categories -->
                    <c:forEach items="${listCAT}" var="o">
                        <li class="nav-item mx-2 ${tag == o.id ? "active":""} ">
                            <a href="categories?id=${o.id}" class="nav-link listmenu">
                                ${o.nameCAT}
                            </a>
                        </li>  
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</nav>