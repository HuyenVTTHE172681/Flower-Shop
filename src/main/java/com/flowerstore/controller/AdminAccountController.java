/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.flowerstore.controller;

import com.flowerstore.dao.UserDAO;
import com.flowerstore.model.User;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
public class AdminAccountController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String error = request.getParameter("error");
        String status = request.getParameter("status");

        if (error != null) {
            request.setAttribute("error", error);
        }
        UserDAO userDAO = new UserDAO();
        List<User> users = userDAO.selectAll();
        if (status != null && !status.equals("0")) {
            request.setAttribute("status", status);
            String statusCode = status.equals("1") ? "activate" : "deactivate";
            users = users.stream().filter((t) -> t.getStatus().toString().equals(statusCode)).collect(Collectors.toList());
        }
        request.setAttribute("users", users);
        request.getSession().setAttribute("isActive", 1);
        request.getRequestDispatcher("/view/AccountAdmin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
    }

}
