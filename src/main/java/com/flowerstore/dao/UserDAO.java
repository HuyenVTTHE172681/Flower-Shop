/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.flowerstore.dao;

import com.flowerstore.model.User;
import com.flowerstore.model.option.UserGender;
import com.flowerstore.model.option.UserStatus;
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
public class UserDAO implements DAO<User>{
    
     private static final UserDAO instance = new UserDAO();

    public static UserDAO getInstance() {
        return instance;
    }
    
    @Override
    public List<User> selectAll() {
        List<User> list = new ArrayList<>();
        try {
            Connection c = JDBC.getConnection();

            PreparedStatement st = c.prepareStatement("SELECT * FROM user");

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFullName(rs.getString("full_name"));
                user.setYearOfBirth(rs.getInt("year_of_birth"));
                user.setGender(UserGender.create(rs.getString("gender")));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setAddress(rs.getString("address"));
                user.setPhone_number(rs.getString("phone_number"));
                user.setStatus(UserStatus.create(rs.getString("status")));
                user.setCreatedAt(Convert.convert(rs.getTimestamp("create_at")));
                user.setDeletedAt(Convert.convert(rs.getTimestamp("delete_at")));

                list.add(user);

            }

            c.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        System.out.println(list);
        return list;
    }

    public User selectByEmail(String email) {
        try {
            Connection c = JDBC.getConnection();

            PreparedStatement st = c.prepareStatement("SELECT * FROM user WHERE email = ?");
            st.setString(1, email);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFullName(rs.getString("full_name"));
                user.setYearOfBirth(rs.getInt("year_of_birth"));
                user.setGender(UserGender.create(rs.getString("gender")));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setAddress(rs.getString("address"));
                user.setPhone_number(rs.getString("phone_number"));
                user.setStatus(UserStatus.create(rs.getString("status")));
                user.setCreatedAt(Convert.convert(rs.getTimestamp("create_at")));
                user.setDeletedAt(Convert.convert(rs.getTimestamp("delete_at")));

                return user;

            }

            c.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return null;
    }
    
    @Override
    public List<User> selectById(String id) {
        List<User> list = new ArrayList<>();
        try {
            Connection c = JDBC.getConnection();

            PreparedStatement st = c.prepareStatement("SELECT * FROM user WHERE id = ?");
            st.setString(1, id);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFullName(rs.getString("full_name"));
                user.setYearOfBirth(rs.getInt("year_of_birth"));
                user.setGender(UserGender.create(rs.getString("gender")));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setAddress(rs.getString("address"));
                user.setPhone_number(rs.getString("phone_number"));
                user.setStatus(UserStatus.create(rs.getString("status")));
                user.setCreatedAt(Convert.convert(rs.getTimestamp("create_at")));
                user.setDeletedAt(Convert.convert(rs.getTimestamp("delete_at")));

                list.add(user);

            }

            c.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        System.out.println(list);
        return list;
    }

    @Override
    public void insert(User ob) {
        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("INSERT INTO `user` (`full_name`, `year_of_birth`, `gender`, `email`, `password`, `address`, `phone_number`, `status`, `create_at`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
            smt.setString(1, ob.getFullName());
            smt.setInt(2, ob.getYearOfBirth());
            smt.setString(3, ob.getGender().toString());
            smt.setString(4, ob.getEmail());
            smt.setString(5, ob.getPassword());
            smt.setString(6, ob.getAddress());
            smt.setString(7, ob.getPhone_number());
            smt.setString(8, ob.getStatus().toString());
            smt.setString(9, Convert.convert(LocalDateTime.now()));

            smt.executeUpdate();

            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void update(User ob) {
        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("UPDATE user SET full_name = ?, year_of_birth = ?, gender = ?, email = ?, password = ?, address = ?, phone_number = ?, status = ? WHERE id = ?");
            smt.setString(1, ob.getFullName());
            smt.setInt(2, ob.getYearOfBirth());
            smt.setString(3, ob.getGender().toString());
            smt.setString(4, ob.getEmail());
            smt.setString(5, ob.getPassword());
            smt.setString(6, ob.getAddress());
            smt.setString(7, ob.getPhone_number());
            smt.setString(8, ob.getStatus().toString());
            smt.setInt(9, ob.getId());

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

            PreparedStatement smt = conn.prepareStatement("DELETE FROM user WHERE id = ?");
            smt.setString(1, id);

            smt.executeUpdate();

            JDBC.closeConnection(conn);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void changePass(int id, String newPassword) {
        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("UPDATE `user` SET `password` = ? WHERE `user`.`id` = ?");
            smt.setString(1, newPassword);
            smt.setInt(2, id);

            smt.executeUpdate();

            JDBC.closeConnection(conn);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
//        getInstance().selectById("4");

    }
}
