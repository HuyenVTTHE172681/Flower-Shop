
package com.flowerstore.controller;

import com.flowerstore.dao.CategoriesDAO;
import com.flowerstore.dao.ProductDAO;
import com.flowerstore.model.Categories;
import com.flowerstore.model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name="SearchController", urlPatterns={"/search"})
public class SearchController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String search = request.getParameter("txt");
        List<Product> listP = ProductDAO.getInstance().selectByName(search);
        if (listP.isEmpty()) {
            String err = "Không tìm thấy sản phẩm " + "'" + search + "'";
            request.setAttribute("notFound", err);

        } else {
            request.setAttribute("listP", listP);
        }

        List<Categories> listCAT = CategoriesDAO.getInstance().selectAll();

        request.setAttribute("listCAT", listCAT);

        request.setAttribute("txtS", search);
        request.getRequestDispatcher("/view/home.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    }


}
