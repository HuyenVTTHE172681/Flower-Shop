package com.flowerstore.controller;

import com.flowerstore.dao.CategoriesDAO;
import com.flowerstore.dao.DataOrderDAO;
import com.flowerstore.model.Categories;
import com.flowerstore.model.HistoryBuy;
import com.flowerstore.model.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserHistoryController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        HttpSession session = request.getSession();
        User us = (User) session.getAttribute("user");
        int amountSold = DataOrderDAO.getInstance().quantitySold(us.getId());
        int amountCan = DataOrderDAO.getInstance().amountCanceled(us.getId());

        int index = 1;
        try {
            String indexP = request.getParameter("index");
            index = Integer.parseInt(indexP);
        } catch (NumberFormatException ex) {
            index = 1;
        } catch (Exception ex) {
            index = 1;
        }

        int limit = 4;
        int offset = index;

        List<HistoryBuy> listHB = DataOrderDAO.getInstance().pagingHistoryBuy(us.getId(), limit, offset);

        int countH = DataOrderDAO.getInstance().countHistoryBuy(us.getId());
        int endP = countH / limit;
        if (countH % limit != 0) {
            endP++;
        }

        List<Categories> listCAT = CategoriesDAO.getInstance().selectAll();

        request.setAttribute("listCAT", listCAT);
        request.setAttribute("amountS", amountSold);
        request.setAttribute("amountC", amountCan);
        request.setAttribute("listHB", listHB);
        request.setAttribute("index", index);
        request.setAttribute("endP", endP);
        request.getRequestDispatcher("/view/user_history.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
