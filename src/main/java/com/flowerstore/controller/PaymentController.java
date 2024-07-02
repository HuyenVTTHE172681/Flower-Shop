package com.flowerstore.controller;

import com.flowerstore.dao.ProductDAO;
import com.flowerstore.model.Product;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PaymentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String proId = request.getParameter("proId");

        Product p = ProductDAO.getInstance().getById(proId);
        int total = p.getPrice();
        request.setAttribute("total", total);
        request.setAttribute("pid", proId);

        request.getRequestDispatcher("/view/payment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
