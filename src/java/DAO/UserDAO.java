/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Group;
import Entity.Permissions;
import Entity.User;
import Utils.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author furkankykc
 */
public class UserDAO {

    Connection connection;
    User user;
    ArrayList<User> userList;
    GroupDAO gDAO = new GroupDAO();

    public void create(User user) {
        try {
            connection = ConnectionManager.getConnection();
            String sql = "insert into user (name,email) values (?,?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            int kid = 0;
            if (rs.next()) {
                kid = (rs.getInt(1));
                System.out.println("DAO.GroupDAO.create()  kid = " + kid);
            }
            for (Group group : user.getGroups()) {
                sql = "insert into user_grup (grup_id,user_id) values(?,?)";
                ps = connection.prepareStatement(sql);
                ps.setInt(2, kid);
                ps.setInt(1, group.getId());
                ps.executeUpdate();

            }
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public ArrayList<User> read() {
        ArrayList<User> userList = new ArrayList();
        try {
            connection = ConnectionManager.getConnection();
            String sql = "select * from user";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userList.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), getGroup(rs.getInt("id"))));
            }
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return userList;
    }

    public ArrayList<Group> getGroup(int userid) {
        ArrayList<Group> groupList = new ArrayList();
        gDAO = new GroupDAO();
        try {
            connection = ConnectionManager.getConnection();
            String sql = "select grup_id from user_grup where user_id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                groupList.add(gDAO.read(rs.getInt("grup_id")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return groupList;
    }

    public User read(int id) {
        User user = null;
        try {
            connection = ConnectionManager.getConnection();
            String sql = "select * from user where id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), getGroup(id));
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public User read(String name) {
        User user = null;
        try {
            connection = ConnectionManager.getConnection();
            String sql = "select * from user where name=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            rs.next();
            user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), getGroup(rs.getInt("id")));
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public void update(User user) {
        try {
            connection = ConnectionManager.getConnection();
            String sql = "update user set name=? , email = ? where id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setInt(3, user.getId());
            ps.executeUpdate();
            sql = "delete from user_grup where user_id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ps.executeUpdate();
            for (Group group : user.getGroups()) {
                sql = "insert into user_grup(grup_id,user_id) values(?,?)";
                ps = connection.prepareStatement(sql);
                ps.setInt(1, group.getId());
                ps.setInt(2, user.getId());
                ps.executeUpdate();

            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    public void delete(int id) {
        try {
            connection = ConnectionManager.getConnection();
            String sql = "delete from user where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    public Permissions hasAccess(int user_id, int module_id) {
            Permissions perm = new Permissions();
        try {
            connection = ConnectionManager.getConnection();
            String sql = "SELECT *"
                    + "FROM group_perm "
                    + " INNER JOIN user_grup ON group_perm.group_id = user_grup.grup_id"
                    + " INNER JOIN permissions ON group_perm.perm_id = permissions.id and user_grup.user_id=? and permissions.module_id = ?; ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, user_id);
            ps.setInt(2, module_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getBoolean("create_perm")) {
                    perm.setCreate(true);
                }
                if (rs.getBoolean("read_perm")) {
                    perm.setRead(true);
                }
                if (rs.getBoolean("update_perm")) {
                    perm.setUpdate(true);
                }
                if (rs.getBoolean("delete_perm")) {
                    perm.setDelete(true);
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return perm;
    }
}
