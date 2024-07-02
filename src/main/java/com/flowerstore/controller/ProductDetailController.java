
package com.flowerstore.controller;

import com.flowerstore.dao.CategoriesDAO;
import com.flowerstore.dao.ProductDAO;
import com.flowerstore.model.Categories;
import com.flowerstore.model.Product;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ProductDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        String id = request.getParameter("pid");
        String nameCAT =ProductDAO.getInstance().getNameCAT(id);
        Product p = ProductDAO.getInstance().getById(id);

        List<Categories> listCAT = CategoriesDAO.getInstance().selectAll();
        List<Product> list = ProductDAO.getInstance().randomById(id,4);

        request.setAttribute("pro", p);
        request.setAttribute("nameCAT", nameCAT);
        request.setAttribute("listCAT", listCAT);
        request.setAttribute("listP", list);
        RequestDispatcher rd = request.getRequestDispatcher("/view/product_detail.jsp");
        rd.forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    }

}
