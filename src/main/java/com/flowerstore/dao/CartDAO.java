
package com.flowerstore.dao;

import com.flowerstore.model.Cart;
import com.flowerstore.service.JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CartDAO implements DAO<Cart> {
    
    private static final CartDAO instance = new CartDAO();

    public static CartDAO getInstance() {
        return instance;
    }
    

    @Override
    public List<Cart> selectAll() {
        List<Cart> list = new ArrayList<>();
        try {
            Connection c = JDBC.getConnection();

            PreparedStatement st = c.prepareStatement("SELECT * FROM cart");

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Cart ca = new Cart();
                ca.setUserID(rs.getInt("user_id"));
                ca.setProductID(rs.getInt("product_id"));
                ca.setAmount(rs.getInt("amount"));

                list.add(ca);

            }

            c.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        System.out.println(list);
        return list;
    }

    @Override
    public List<Cart> selectById(String id) {
        List<Cart> list = new ArrayList<>();
        try {
            Connection c = JDBC.getConnection();

            PreparedStatement st = c.prepareStatement("SELECT * FROM cart WHERE user_id = ?");
            st.setString(1, id);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Cart ca = new Cart();
                ca.setUserID(rs.getInt("user_id"));
                ca.setProductID(rs.getInt("product_id"));
                ca.setAmount(rs.getInt("amount"));

                list.add(ca);

            }

            c.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        System.out.println(list);
        return list;
    }

    @Override
    public void insert(Cart ob) {
        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("INSERT INTO cart(user_id, product_id, amount) VALUES (?, ?, ?)");
            smt.setInt(1, ob.getUserID());
            smt.setInt(2, ob.getProductID());
            smt.setInt(3, ob.getAmount());

            smt.executeUpdate();

            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void update(Cart ob) {
        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("UPDATE cart SET product_id = ?, amount = ? WHERE user_id = ?");
            smt.setInt(1, ob.getProductID());
            smt.setInt(2, ob.getAmount());
            smt.setInt(3, ob.getUserID());

            smt.executeUpdate();

            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("DELETE FROM cart WHERE user_id = ?");
            smt.setString(1, id);

            smt.executeUpdate();

            JDBC.closeConnection(conn);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static void main(String[] args) {
        getInstance().delete("31");
    }
    
}
