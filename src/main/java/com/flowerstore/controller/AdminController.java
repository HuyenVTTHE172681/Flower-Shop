/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.flowerstore.controller;

import com.flowerstore.dao.OrderDAO;
import com.flowerstore.dao.ProductDAO;
import com.flowerstore.dao.UserDAO;
import com.flowerstore.model.Order;
import com.flowerstore.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
public class AdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        OrderDAO orderDAO = new OrderDAO();
        List<Order> orders = orderDAO.selectAll();

        List<Order> process = orderDAO.selectProcess();
        List<Order> ship = orderDAO.selectShip();
        List<Order> done = orderDAO.selectDone();
        List<Order> cancel = orderDAO.selectCancel();

        UserDAO userDAO = new UserDAO();
        List<User> users = userDAO.selectAll();
        ProductDAO productDAO = new ProductDAO();
        
        int productNum = productDAO.selectAll().size();
        
        request.setAttribute("numPro", productNum);
        request.setAttribute("users", users);
        request.setAttribute("orders", orders);
        request.setAttribute("process", process);
        request.setAttribute("ship", ship);
        request.setAttribute("done", done);
        request.setAttribute("cancel", cancel);
        request.getSession().setAttribute("isActive", 0);

        RequestDispatcher rd = request.getRequestDispatcher("/view/admin.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
    }

}
