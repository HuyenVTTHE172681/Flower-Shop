

package com.flowerstore.controller;

import com.flowerstore.dao.CategoriesDAO;
import com.flowerstore.dao.UserDAO;
import com.flowerstore.model.Categories;
import com.flowerstore.model.User;
import com.flowerstore.service.Hash;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
public class ChangePassController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        List<Categories> listCAT = CategoriesDAO.getInstance().selectAll();

        request.setAttribute("listCAT", listCAT);
        request.getRequestDispatcher("/view/user_change_pass.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String oldPass = request.getParameter("oldPassword");
        String newPass = request.getParameter("newPassword");
        String conPass = request.getParameter("conPassword");
        String oldPassword = Hash.hashCode(oldPass);
        String newPassword = Hash.hashCode(newPass);
        String conPassword = Hash.hashCode(conPass);

        HttpSession session = request.getSession();
        User us = (User) session.getAttribute("user");
        if (oldPassword.equals(us.getPassword())) {
            if (newPass.length() < 6) {
                String errGotPass = "Mật khẩu mới phải nhập tối thiểu 6 ký tự!";
                request.setAttribute("errGotPass", errGotPass);

                this.doGet(request, response);
            } else {
                if (newPassword.equals(oldPassword)) {
                    String errOldPass = "Mật khẩu mới trùng với mật khẩu cũ!";
                    request.setAttribute("errOldPass", errOldPass);

                    this.doGet(request, response);
                } else {
                    if (conPassword.equals(newPassword)) {
                        UserDAO.getInstance().changePass(us.getId(), newPassword);

//                String success = "Đổi mật khẩu thành công.";
//                request.setAttribute("success", success);
//                request.getRequestDispatcher("/view/user_profile.jsp").forward(request, response);
                        response.sendRedirect("profile");

                    } else {
                        String errConfPass = "Xác nhận Mật khẩu mới không đúng!";
                        request.setAttribute("errConfPass", errConfPass);

                        this.doGet(request, response);
                    }
                }

            }
        } else {
            String errPass = "Mật khẩu không đúng!";
            request.setAttribute("errPass", errPass);

            this.doGet(request, response);
        }
    
    }


}
