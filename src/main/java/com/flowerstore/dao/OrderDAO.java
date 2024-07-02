/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.flowerstore.dao;

import com.flowerstore.model.Order;
import com.flowerstore.model.option.OrderStatus;
import com.flowerstore.model.option.Payment;
import com.flowerstore.service.Convert;
import com.flowerstore.service.JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class OrderDAO implements DAO<Order> {

    private static final OrderDAO instance = new OrderDAO();

    public static OrderDAO getInstance() {
        return instance;
    }

    @Override
    public List<Order> selectAll() {
        List<Order> list = new ArrayList<>();

        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("SELECT * FROM `order`");

            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                Order od = new Order();
                od.setId(rs.getInt("id"));
                od.setUserID(rs.getInt("user_id"));
                od.setTotalPrice(rs.getInt("total_price"));
                od.setPayment(Payment.create(rs.getString("payment")));
                od.setStatus(OrderStatus.create(rs.getString("status")));
                od.setCreateAt(Convert.convert(rs.getTimestamp("create_at")));

                list.add(od);
            }

            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println(list);

        return list;
    }

    public List<Order> selectProcess() {
        List<Order> list = new ArrayList<>();

        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("SELECT * FROM `order`WHERE status='processing'");

            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                Order od = new Order();
                od.setId(rs.getInt("id"));
                od.setUserID(rs.getInt("user_id"));
                od.setTotalPrice(rs.getInt("total_price"));
                od.setPayment(Payment.create(rs.getString("payment")));
                od.setStatus(OrderStatus.create(rs.getString("status")));
                od.setCreateAt(Convert.convert(rs.getTimestamp("create_at")));

                list.add(od);
            }

            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println(list);

        return list;
    }

    public List<Order> selectShip() {
        List<Order> list = new ArrayList<>();

        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("SELECT * FROM `order`WHERE status='shipping'");

            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                Order od = new Order();
                od.setId(rs.getInt("id"));
                od.setUserID(rs.getInt("user_id"));
                od.setTotalPrice(rs.getInt("total_price"));
                od.setPayment(Payment.create(rs.getString("payment")));
                od.setStatus(OrderStatus.create(rs.getString("status")));
                od.setCreateAt(Convert.convert(rs.getTimestamp("create_at")));

                list.add(od);
            }

            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println(list);

        return list;
    }

    public List<Order> selectDone() {
        List<Order> list = new ArrayList<>();

        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("SELECT * FROM `order`WHERE status='done'");

            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                Order od = new Order();
                od.setId(rs.getInt("id"));
                od.setUserID(rs.getInt("user_id"));
                od.setTotalPrice(rs.getInt("total_price"));
                od.setPayment(Payment.create(rs.getString("payment")));
                od.setStatus(OrderStatus.create(rs.getString("status")));
                od.setCreateAt(Convert.convert(rs.getTimestamp("create_at")));

                list.add(od);
            }

            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println(list);

        return list;
    }

    public List<Order> selectCancel() {
        List<Order> list = new ArrayList<>();

        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("SELECT * FROM `order`WHERE status='canceled'");

            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                Order od = new Order();
                od.setId(rs.getInt("id"));
                od.setUserID(rs.getInt("user_id"));
                od.setTotalPrice(rs.getInt("total_price"));
                od.setPayment(Payment.create(rs.getString("payment")));
                od.setStatus(OrderStatus.create(rs.getString("status")));
                od.setCreateAt(Convert.convert(rs.getTimestamp("create_at")));

                list.add(od);
            }

            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println(list);

        return list;
    }

    @Override
    public List<Order> selectById(String id) {
        List<Order> list = new ArrayList<>();

        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("SELECT * FROM `order` WHERE id = ?");
            smt.setString(1, id);

            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                Order od = new Order();
                od.setId(rs.getInt("id"));
                od.setUserID(rs.getInt("user_id"));
                od.setTotalPrice(rs.getInt("total_price"));
                od.setPayment(Payment.create(rs.getString("payment")));
                od.setStatus(OrderStatus.create(rs.getString("status")));
                od.setCreateAt(Convert.convert(rs.getTimestamp("create_at")));

                list.add(od);
            }

            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println(list);

        return list;
    }

    @Override
    public void insert(Order ob) {
        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("INSERT INTO `order` (`user_id`, `total_price`, `payment`, `status`, `create_at`) VALUES (?, ?, ?, ?, ?)");
            smt.setInt(1, ob.getUserID());
            smt.setInt(2, ob.getTotalPrice());
            smt.setString(3, ob.getPayment().toString());
            smt.setString(4, ob.getStatus().toString());
            smt.setString(5, Convert.convert(LocalDateTime.now()));

            smt.executeUpdate();

            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void update(Order ob) {
        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("UPDATE `order` SET status = ? WHERE id = ?");
            smt.setString(1, ob.getStatus().toString());
            smt.setInt(2, ob.getId());

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

            PreparedStatement smt = conn.prepareStatement("DELETE FROM `order` WHERE id = ?");
            smt.setString(1, id);

            smt.executeUpdate();

            JDBC.closeConnection(conn);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public Order getIdAdd() {
        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("SELECT * FROM `order` ORDER BY id DESC LIMIT 1;");

            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                Order od = new Order();
                od.setId(rs.getInt("id"));
                od.setUserID(rs.getInt("user_id"));
                od.setTotalPrice(rs.getInt("total_price"));
                od.setPayment(Payment.create(rs.getString("payment")));
                od.setStatus(OrderStatus.create(rs.getString("status")));
                od.setCreateAt(Convert.convert(rs.getTimestamp("create_at")));

                return od;
            }

            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().getIdAdd());
    }

}
