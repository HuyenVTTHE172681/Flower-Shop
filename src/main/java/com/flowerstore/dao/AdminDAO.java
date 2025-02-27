
package com.flowerstore.dao;

import com.flowerstore.model.Admin;
import com.flowerstore.model.option.AdminRole;
import com.flowerstore.service.JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AdminDAO implements DAO<Admin> {

    private static final AdminDAO instance = new AdminDAO();

    public static AdminDAO getInstance() {
        return instance;
    }

    @Override
    public List<Admin> selectAll() {
        List<Admin> list = new ArrayList<>();
        try {
            Connection c = JDBC.getConnection();

            PreparedStatement st = c.prepareStatement("SELECT * FROM admin");

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Admin ad = new Admin();
                ad.setId(rs.getInt("id"));
                ad.setUserName(rs.getString("username"));
                ad.setPassword(rs.getString("password"));
                ad.setRole(AdminRole.create(rs.getString("role")));

                list.add(ad);

            }

            c.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        System.out.println(list);
        return list;
    }

    @Override
    public List<Admin> selectById(String id) {
        List<Admin> list = new ArrayList<>();
        try {
            Connection c = JDBC.getConnection();

            PreparedStatement st = c.prepareStatement("SELECT * FROM admin WHERE id = ?");
            st.setString(1, id);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Admin ad = new Admin();
                ad.setId(rs.getInt("id"));
                ad.setUserName(rs.getString("username"));
                ad.setPassword(rs.getString("password"));
                ad.setRole(AdminRole.create(rs.getString("role")));

                list.add(ad);

            }

            c.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        System.out.println(list);
        return list;
    }

    public Admin selectByUserName(String username) {

        try {
            Connection c = JDBC.getConnection();

            PreparedStatement st = c.prepareStatement("SELECT * FROM admin WHERE username = ?");
            st.setString(1, username);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Admin ad = new Admin();
                ad.setId(rs.getInt("id"));
                ad.setUserName(rs.getString("username"));
                ad.setPassword(rs.getString("password"));
                ad.setRole(AdminRole.create(rs.getString("role")));

                return ad;

            }

            c.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return null;
    }

    @Override
    public void insert(Admin ob) {
        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("INSERT INTO admin(username, password, role) VALUES (?, ?, ?)");
            smt.setString(1, ob.getUserName());
            smt.setString(2, ob.getPassword());
            smt.setString(3, ob.getRole().toString());

            smt.executeUpdate();

            JDBC.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void update(Admin ob) {
        try {
            Connection conn = JDBC.getConnection();

            PreparedStatement smt = conn.prepareStatement("UPDATE admin SET username = ?, password = ?, role = ? WHERE id = ?");
            smt.setString(1, ob.getUserName());
            smt.setString(2, ob.getPassword());
            smt.setString(3, ob.getRole().toString());
            smt.setInt(4, ob.getId());

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

            PreparedStatement smt = conn.prepareStatement("DELETE FROM admin WHERE id = ?");
            smt.setString(1, id);

            smt.executeUpdate();

            JDBC.closeConnection(conn);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public Admin login(String username, String password) {
        try {
            Connection c = JDBC.getConnection();

            PreparedStatement st = c.prepareStatement("select * from admin where username = ? and password = ?");
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Admin ad = new Admin();
                ad.setId(rs.getInt("id"));
                ad.setUserName(rs.getString("username"));
                ad.setPassword(rs.getString("password"));
                ad.setRole(AdminRole.create(rs.getString("role")));
                return ad;
            }

            c.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().selectByUserName("superadmin"));
    }
}
