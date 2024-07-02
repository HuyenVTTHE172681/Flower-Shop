/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.flowerstore.controller;

import com.flowerstore.dao.AdminDAO;
import com.flowerstore.model.Admin;
import com.flowerstore.model.option.AdminRole;
import com.flowerstore.service.Hash;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
public class AdminRegisterController extends HttpServlet {
   
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        RequestDispatcher rd = request.getRequestDispatcher("/view/admin_register.jsp");
        rd.forward(request, response);
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String userName = request.getParameter("username");
        String password = Hash.hashCode(request.getParameter("password"));
        String rePassword = Hash.hashCode(request.getParameter("re-password"));

        if (AdminDAO.getInstance().selectByUserName(userName) != null) {
            String errorMsg = "Tài khoản đã tồn lại!";
            request.setAttribute("errorUN", errorMsg);

            this.doGet(request, response);

        } else {
            if (password.equals(rePassword)) {

                Admin ad = new Admin();
                ad.setUserName(userName);
                ad.setPassword(password);
                ad.setRole(AdminRole.ADMIN);

                AdminDAO.getInstance().insert(ad);

                response.sendRedirect("admin");

            } else {

                String errorMsg = "Mật khẩu không khớp! Vui lòng kiểm tra lại.";
                request.setAttribute("errorPass", errorMsg);

                request.setAttribute("username", userName);

                this.doGet(request, response);
            }
        }
    }

    

}
