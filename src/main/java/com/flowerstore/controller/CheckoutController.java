/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.flowerstore.controller;

import com.flowerstore.dao.CheckoutDAO;
import com.flowerstore.dao.ProductDAO;
import com.flowerstore.model.Product;
import com.flowerstore.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
public class CheckoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        CheckoutDAO d = new CheckoutDAO();
        HttpSession session = request.getSession();
        String pid = request.getParameter("pid");
        Product p = ProductDAO.getInstance().getById(pid);
        User user = (User) session.getAttribute("user");
        d.addOder(user, p);

        
        session.setAttribute("successMessage", "Bạn đã đặt hàng thành công. Cảm ơn và chúc một ngày tốt lành!");

        response.sendRedirect("home");
    } 

   

}
