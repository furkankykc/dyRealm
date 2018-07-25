/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Group;
import Entity.Permissions;
import Utils.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author furkankykc
 */
public class GroupDAO {

    Connection connection;
    Group group;
    ArrayList<Group> groupList;
    PermissionsDAO pDao;

    public void create(Group group) {
        try {
            connection = ConnectionManager.getConnection();
            String sql = "insert into grup (groupName) values (?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, group.getGroupName());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            int kid = 0;
            if (rs.next()) {
                kid = (rs.getInt(1));
                System.out.println("DAO.GroupDAO.create()  kid = " + kid);
            }
            for (Permissions perms : group.getPermissions()) {
                sql = "insert into group_perm (group_id,perm_id) values(?,?)";
                ps = connection.prepareStatement(sql);
                ps.setInt(1, kid);
                ps.setInt(2, perms.getId());
                System.err.println("perms = " + perms.getId());
                ps.executeUpdate();

            }
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public ArrayList<Permissions> getPerms(int groupid) {
        ArrayList<Permissions> permList = new ArrayList();
        pDao= new PermissionsDAO();
        try {
            connection = ConnectionManager.getConnection();
            String sql = "select perm_id from group_perm where group_id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, groupid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                permList.add(pDao.read(rs.getInt("perm_id")));
            }
        }  catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return permList;
    }

    public ArrayList<Group> read() {
        ArrayList<Group> groupList = new ArrayList();
        try {
            connection = ConnectionManager.getConnection();
            String sql = "select * from grup";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                groupList.add(new Group(rs.getInt("id"), rs.getString("groupName"), getPerms(rs.getInt("id"))));
            }
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return groupList;
    }

    public Group read(int id) {
        Group group = null;
        try {
            connection = ConnectionManager.getConnection();
            String sql = "select * from grup where id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            group = new Group(rs.getInt("id"), rs.getString("groupName"), getPerms(id));
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return group;
    }

    public void update(Group group) {
        try {
            //update ederken group_permi et 
            connection = ConnectionManager.getConnection();
            String sql = "update grup set groupName=? where id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, group.getGroupName());
            ps.setInt(2, group.getId());
            ps.executeUpdate();
            sql = "delete from group_perm where group_id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, group.getId());
            ps.executeUpdate();
            for (Permissions perms : group.getPermissions()) {
                sql = "insert into group_perm(group_id,perm_id) values(?,?)";
                ps = connection.prepareStatement(sql);
                ps.setInt(1, group.getId());
                ps.setInt(2, perms.getId());
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
            String sql = "delete from grup where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

}
