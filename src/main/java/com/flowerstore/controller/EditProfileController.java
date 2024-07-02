package com.flowerstore.controller;

import com.flowerstore.dao.CategoriesDAO;
import com.flowerstore.dao.UserDAO;
import com.flowerstore.model.Categories;
import com.flowerstore.model.User;
import com.flowerstore.model.option.UserGender;
import com.flowerstore.service.Hash;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditProfileController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        List<Categories> listCAT = CategoriesDAO.getInstance().selectAll();

        request.setAttribute("listCAT", listCAT);
        request.getRequestDispatcher("/view/user_edit.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String newName = request.getParameter("fullName");
        int newYearOfBirth = Integer.parseInt(request.getParameter("yearOfBirth"));
        String newGender = request.getParameter("gender");
        String newPhoneNumber = request.getParameter("phoneNumber");
        String newAddress = request.getParameter("address");
        String conPass = request.getParameter("password");
        String conPassword = Hash.hashCode(conPass);

        HttpSession session = request.getSession();
        User us = (User) session.getAttribute("user");
// Kiểm tra năm sinh
        if (newYearOfBirth < 1900 || newYearOfBirth > 2024) {
            // Năm sinh không hợp lệ
            String errorMsg = "Năm sinh không hợp lệ!";
            request.setAttribute("errorNewYear", errorMsg);

            request.setAttribute("sessionScope.user.newName", newName);
            request.setAttribute("sessionScope.user.newYearOfBirth", newYearOfBirth);
            request.setAttribute("sessionScope.user.gender", newGender);
            request.setAttribute("sessionScope.user.phone_number", newPhoneNumber);
            request.setAttribute("sessionScope.user.address", newAddress);
            this.doGet(request, response);
        } else {
            // Năm sinh hợp lệ
// Kiểm tra số điện thoại
            if (newPhoneNumber.length() != 10) {
                // Số điện thoại không hợp lệ
                String errorMsg = "Số điện thoại phải có 10 kí tự số!";
                request.setAttribute("errorNewPhone", errorMsg);

                request.setAttribute("sessionScope.user.newName", newName);
                request.setAttribute("sessionScope.user.newYearOfBirth", newYearOfBirth);
                request.setAttribute("sessionScope.user.gender", newGender);
                request.setAttribute("sessionScope.user.phone_number", newPhoneNumber);
                request.setAttribute("sessionScope.user.address", newAddress);
                this.doGet(request, response);
            } else {
                // Số điện thoại hợp lệ
                // Kiểm tra mật khẩu
                if (conPass.length() < 6) {
                    // Mật khẩu không hợp lệ
                    String errorMsg = "Mật khẩu phải có ít nhất 6 ký tự!";
                    request.setAttribute("errorMissingNewPass", errorMsg);

                    request.setAttribute("sessionScope.user.newName", newName);
                    request.setAttribute("sessionScope.user.newYearOfBirth", newYearOfBirth);
                    request.setAttribute("sessionScope.user.gender", newGender);
                    request.setAttribute("sessionScope.user.phone_number", newPhoneNumber);
                    request.setAttribute("sessionScope.user.address", newAddress);

                    this.doGet(request, response);
                } else {
                    // Mật khẩu hợp lệ // Kiểm tra mật khẩu nhập lại
                    if (!conPassword.equals(us.getPassword())) {
                        String errPass = "Xác nhận mật khẩu không đúng! Vui lòng kiểm tra lại.";
                        request.setAttribute("errPass", errPass);

                        request.setAttribute("sessionScope.user.newName", newName);
                        request.setAttribute("sessionScope.user.newYearOfBirth", newYearOfBirth);
                        request.setAttribute("sessionScope.user.gender", newGender);
                        request.setAttribute("sessionScope.user.phone_number", newPhoneNumber);
                        request.setAttribute("sessionScope.user.address", newAddress);

                        this.doGet(request, response);

                    } else {
                        us.setFullName(newName);
                        us.setYearOfBirth(newYearOfBirth);
                        us.setGender(UserGender.create(newGender));
                        us.setPhone_number(newPhoneNumber);
                        us.setAddress(newAddress);

                        UserDAO.getInstance().update(us);

//                        String success = "Chỉnh sửa thông tin thành công.";
//                        session.setAttribute("success", success);
                        String flag = (String) session.getAttribute("flag");//dương thêm

                        if (flag != null) {//dương thêm
                            // Điều hướng đến payment.jsp

                            String pid = (String) request.getParameter("pid");//dương thêm
//                String quantity = request.getParameter("quantity");//dương thêm

                            response.sendRedirect(request.getContextPath() + "/pay?proId=" + pid);//dương thêm
                        } else {//dương thêm
                            response.sendRedirect("profile");
                        }//dương thêm

                        // Xóa giá trị của biến từ session bên payment sau khi sử dụng
                        session.removeAttribute("flag");//dương thêm
                    }
                }

            }
        }

    }

}
