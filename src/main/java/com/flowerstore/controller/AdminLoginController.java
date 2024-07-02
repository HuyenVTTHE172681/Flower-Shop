/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.flowerstore.controller;

import com.flowerstore.dao.AdminDAO;
import com.flowerstore.model.Admin;
import com.flowerstore.service.Hash;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
public class AdminLoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        RequestDispatcher rd = request.getRequestDispatcher("/view/admin_login.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        Admin ad = AdminDAO.getInstance().selectByUserName(username);
        if (ad != null && ad.getPassword().equals(Hash.hashCode(password))) {
            HttpSession session = request.getSession();
            session.setAttribute("user", ad);
            response.sendRedirect("admin");
            return;
        }

        if (ad == null) {
            String errorUSN = "Tài khoản không tồn tại!";
            ad = null;

            request.setAttribute("errorUSN", errorUSN);
            this.doGet(request, response);
        }

        String errorMsg = "Sai mật khẩu!";
        request.setAttribute("username", username);
        request.setAttribute("errorMsg", errorMsg);

        this.doGet(request, response);
    }

}
