<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>LovePik | Tất cả sản phẩm</title>

        <!--==================== CSS ====================-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/style/home.css" />

        <link rel="icon" type="image/x-icon" href="<%=request.getContextPath()%>/img/icon_web.png">

    </head>
    <body>
        <!--==================== HEADER ====================-->
        <%@include file="common/header.jsp" %>

        <div class="container position-relative text-center">

            <div class="listp">
                <c:forEach items="${listP}" var="p">
                    <div class="col-sm-3">
                        <div class="d-flex justify-content-between align-items-center flex-column flex-lg-row my-5" id="new"> 
                            <div class="card m-2 card-pro">

                                <div class="product">
                                    <a href ="detail?pid=${p.id}" class="product-img">
                                        <img src="${p.banners}" alt="Product" class="card-img-top p-img"
                                             height="200" >
                                    </a>

                                    <c:if test="${sessionScope.user == null}">
                                        <a href="login" class="buy">
                                            <i class="fa-solid fa-cart-shopping"></i>
                                        </a>
                                    </c:if>

                                    <c:if test="${sessionScope.user != null}">
                                        <input type="hidden" name="amount" value="1">

                                        <a href="addcart?id=${p.id}&amount=1" class="buy">
                                            <i class="fa-solid fa-cart-shopping"></i>
                                        </a>
                                    </c:if>

                                </div>
                                <div class="card-body">
                                    <p class="card-text fw-bold namep">
                                        ${p.name}
                                    </p>
                                    <small class="text-secondary pricep">
                                        <script>
                                            var formattedPrice = new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(${p.price});
                                            document.write(formattedPrice);
                                        </script>
                                    </small>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div class="p-3 paging">  
                <c:forEach begin="1" end="${endP}" var="i">
                    <a class="page-item ${index == i ? "active": ""}" href="${pageContext.request.contextPath}/allproduct?index=${i}">${i}</a>
                </c:forEach>
            </div>
        </div>

        <!--==================== FOOTER ====================-->
        <%@include file="common/footer.jsp" %>

        <!--==================== JS ====================-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    </body>
</html>