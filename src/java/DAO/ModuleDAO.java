/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Module;
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
public class ModuleDAO {
    Connection  connection;
    Module module;
    ArrayList<Module> moduleList;
    public void create (Module module){
       try{
           connection = ConnectionManager.getConnection();
           String sql = "insert into module (title) values (?)";
           PreparedStatement ps = connection.prepareStatement(sql);
           ps.setString(1, module.getTitle());
           ps.executeUpdate();
           connection.close();
       }catch( SQLException ex) {
            System.out.println(ex.getMessage());            
       }
        
    }
    
    public ArrayList<Module> read (){
        ArrayList<Module> moduleList = new ArrayList();
            try {
                connection = ConnectionManager.getConnection();
                String sql = "select * from module";
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while ( rs.next() ) {
                        moduleList.add(new Module(rs.getInt("id"),rs.getString("title")));
                    }
                connection.close();
                } catch( SQLException ex) {
                        System.out.println(ex.getMessage());
                        }
        return moduleList;
    }
    
    public Module read (int id){
       Module module = null;
            try {
                    connection = ConnectionManager.getConnection();
                    String sql = "select * from module where id=?";
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    rs.next();
                    module = new Module(rs.getInt("id"), rs.getString("title"));
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
        return module;
    }
    public void update (Module module){
        try {
                connection = ConnectionManager.getConnection();
                String sql = "update module set title=? where id=?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, module.getTitle());
                ps.setInt(2, module.getId());
                ps.executeUpdate();
                connection.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
           
            }
    }
 
   
    public void delete (int id){
        try {
                connection = ConnectionManager.getConnection();
                String sql = "delete from module where id = ?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
                connection.close();
            } catch (SQLException e) {
                    System.out.println(e.getMessage());
           
            }
    }
    
}
    
