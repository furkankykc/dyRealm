/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Permissions;
import Utils.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author furkankykc
 */
public class PermissionsDAO {
    Connection  connection;
    Permissions permissions;
    ArrayList<Permissions> permissionsList;
    ModuleDAO mdao = new ModuleDAO();
    public void create (Permissions permissions){
       try{
           connection = ConnectionManager.getConnection();
           String sql = "insert into permissions (create_perm,read_perm,update_perm,delete_perm,module_id) values (?,?,?,?,?)";
           PreparedStatement ps = connection.prepareStatement(sql);
           ps.setBoolean(1, permissions.isCreate());
           ps.setBoolean(2, permissions.isRead());
           ps.setBoolean(3, permissions.isUpdate());
           ps.setBoolean(4, permissions.isDelete());
           ps.setInt(5, permissions.getModule().getId());
           ps.executeUpdate();
           connection.close();
       }catch( SQLException ex) {
            System.out.println(ex.getMessage());
       }
        
    }
    
    public ArrayList<Permissions> read (){
        ArrayList<Permissions> permissionsList = new ArrayList();
            try {
                connection = ConnectionManager.getConnection();
                String sql = "select * from permissions";
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while ( rs.next() ) {
                        permissionsList.add(
                                new Permissions(rs.getInt("id")
                                ,rs.getBoolean("create_perm")
                                ,rs.getBoolean("read_perm")
                                ,rs.getBoolean("update_perm")
                                ,rs.getBoolean("delete_perm")
                                ,mdao.read(rs.getInt("module_id"))
                                ));
                    }
                connection.close();
                } catch( SQLException ex) {
                        System.out.println(ex.getMessage());
                        }
        return permissionsList;
    }
    
    public Permissions read (int id){
       Permissions permissions = null;
            try {
                    connection = ConnectionManager.getConnection();
                    String sql = "select * from permissions where id=?";
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    rs.next();
                    permissions =new Permissions(rs.getInt("id")
                                ,rs.getBoolean("create_perm")
                                ,rs.getBoolean("read_perm")
                                ,rs.getBoolean("update_perm")
                                ,rs.getBoolean("delete_perm")
                                ,mdao.read(rs.getInt("module_id"))
                                );

                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
        return permissions;
    }
    public void update (Permissions permissions){
        try {
                connection = ConnectionManager.getConnection();
                String sql = "update permissions set create_perm=? , read_perm=? , update_perm=? , delete_perm=? , module_id = ? where id=?";
                PreparedStatement ps = connection.prepareStatement(sql);
                
                ps.setBoolean(1, permissions.isCreate());
                ps.setBoolean(2, permissions.isRead());
                ps.setBoolean(3, permissions.isUpdate());
                ps.setBoolean(4, permissions.isDelete());
                ps.setInt(5, permissions.getModule().getId());
                ps.setInt(6, permissions.getId());
                ps.executeUpdate();
                connection.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
           
            }
    }
 
   
    public void delete (int id){
        try {
                connection = ConnectionManager.getConnection();
                String sql = "delete from permissions where id = ?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
                connection.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
           
            }
    }
    
}
    
