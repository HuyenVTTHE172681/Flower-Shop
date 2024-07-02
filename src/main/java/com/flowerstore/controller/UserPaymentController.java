
package com.flowerstore.controller;

import com.flowerstore.dao.CategoriesDAO;
import com.flowerstore.dao.DataOrderDAO;
import com.flowerstore.dao.OrderDAO;
import com.flowerstore.dao.ProductDAO;
import com.flowerstore.model.Categories;
import com.flowerstore.model.DataOrder;
import com.flowerstore.model.Order;
import com.flowerstore.model.Product;
import com.flowerstore.model.User;
import com.flowerstore.model.option.OrderStatus;
import com.flowerstore.model.option.Payment;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UserPaymentController", urlPatterns = {"/payment"})
public class UserPaymentController extends HttpServlet {
   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        List<Categories> listCAT = CategoriesDAO.getInstance().selectAll();

        request.setAttribute("listCAT", listCAT);
        RequestDispatcher rd = request.getRequestDispatcher("/view/user_payment.jsp");
        rd.forward(request, response);
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        HttpSession session = request.getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");

        for (Integer key : cart.keySet()) {
            Integer value = cart.get(key);

            Product product = ProductDAO.getInstance().getById(key);
            int total = product.getPrice() * value;

            Order order = new Order();
            order.setUserID(user.getId());
            order.setTotalPrice(total);
            order.setPayment(Payment.COD);
            order.setStatus(OrderStatus.PROCESSING);

            OrderDAO.getInstance().insert(order);

            Order o = OrderDAO.getInstance().getIdAdd();
            DataOrder dataO = new DataOrder();
            dataO.setOderID(o.getId());
            dataO.setProductID(product.getId());
            dataO.setAmount(value);

            DataOrderDAO.getInstance().insert(dataO);

        }
        
        session.setAttribute("cart", Collections.emptyList());

        String done = "Đặt hàng thành công.";       
        session.setAttribute("done", done);
        response.sendRedirect("cart");
    }


}
