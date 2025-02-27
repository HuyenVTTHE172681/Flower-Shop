<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>LovePik | Thông tin sản phẩm</title>

        <!--==================== CSS ====================-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/style/home.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/style/product_detail.css" />

        <link rel="icon" type="image/x-icon" href="<%=request.getContextPath()%>/img/icon_web.png">
    </head>
    <body>
        <!--==================== HEADER ====================-->
        <%@include file="common/header.jsp" %>

        <div class="container">
            <div class="justify-content-center bg-white">

                <div class="row border m-2 detail">
                    <div class="col-lg-4 left-side-product-box pb-3 product-banner">
                        <img src="<%=request.getContextPath()%>/${pro.banners}" alt="Product" class="p-3 bann" height="500">
                    </div>
                    <div class="col-lg-8">
                        <div class="right-side-pro-detail p-3 m-0">
                            <div class="row">
                                <div class="col-lg-12 product-name">
                                    <p class="m-0 p-0">${pro.name}</p>
                                </div>
                                <div class="col-lg-12 product-price">
                                    <p class="m-0 p-0 price-pro">
                                        <script>
                                            var formattedPrice = new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(${pro.price});
                                            document.write(formattedPrice);
                                        </script>
                                    </p>

                                </div>

                                <div class="col-lg-12 pt-2 product-type">
                                    <h5>Loại:</h5>
                                    <span>${nameCAT}</span>

                                </div>
                                <div class="col-lg-12 pt-2 product-desc">
                                    <h5>Mô tả:</h5>
                                    <span>${pro.detail}</span>
                                </div>
                                <div class="col-lg-12 call">
                                    <h5>Gọi ngay: </h5>
                                    <h5 class="call-now">1900</h5>
                                </div>   
                                <div class="col-lg-12 ship">
                                    <h5>Giao hàng nhanh trong khu vực Tỉnh Bình Định </h5>
                                </div>   

                                <div class="col-lg-12 mt-3">
                                    <div class="row">
                                        <div class="col-lg-6 pb-2 ">
                                            <c:if test="${sessionScope.user == null}">
                                                <a href="login" class="btn btn-success w-100" style="font-size: 20px">Thêm vào Giỏ Hàng</a>
                                            </c:if>

                                            <c:if test="${sessionScope.user != null}">
                                                <a href="addcart?id=${pro.id}&amount=1" class="btn btn-success w-100" style="font-size: 20px">Thêm vào Giỏ Hàng</a>
                                            </c:if>
                                        </div>

                                        <div class="col-lg-6 pb-2 ">
                                            <form action="pay" method="get">
                                                <input type="hidden" name="proId" value="${pro.id}">
                                                <button type="submit" class="btn btn-success w-100" style="font-size: 20px">Mua Ngay</button>
                                            </form>              
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="container position-relative text-center more">
                    <h1>Sản phẩm liên quan</h1>
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

                </div>
            </div>

        </div>
        <!--==================== FOOTER ====================-->
        <%@include file="common/footer.jsp" %>

         <!--==================== JS ====================-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    </body>
</html>