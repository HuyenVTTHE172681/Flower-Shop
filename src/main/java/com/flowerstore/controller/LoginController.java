/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.flowerstore.controller;

import com.flowerstore.dao.UserDAO;
import com.flowerstore.model.User;
import com.flowerstore.model.option.UserStatus;
import com.flowerstore.service.Hash;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        RequestDispatcher rd = request.getRequestDispatcher("/view/user_login.jsp");
        rd.forward(request, response);
    } 

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = UserDAO.getInstance().selectByEmail(email);

        if (user != null && user.getPassword().equals(Hash.hashCode(password)) && user.getStatus() == UserStatus.ACTIVATE) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            Map<Integer, Integer> cart = new HashMap<>();
            session.setAttribute("cart", cart);

            response.sendRedirect("home");

            return;
        }

        String errorMsg = "Sai mật khẩu!";
        if (user != null && user.getStatus() == UserStatus.DEACTIVATE) {
            String errorEmail = "Tài khoản đã bị khóa!";
            request.setAttribute("errorEmail", errorEmail);

            this.doGet(request, response);
        }
        if (user == null) {
            String errorEmail = "Tài khoản không tồn tại!";
            email = null;

            request.setAttribute("errorEmail", errorEmail);

            this.doGet(request, response);
        }

        request.setAttribute("email", email);
        request.setAttribute("errorMsg", errorMsg);

        this.doGet(request, response);
    }


}
