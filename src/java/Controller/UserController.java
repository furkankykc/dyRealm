/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.ArrayList;
import java.util.List;
import DAO.UserDAO;
import Entity.User;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

        
        
/**
 *
 * @author furkankykc
 */

@ManagedBean(name="userController")
@SessionScoped
public class UserController implements Serializable{
    
    private List<User> userList;
    private UserDAO userDao;
    private User user;

    public UserController() {
        this.userList = new ArrayList<User>();
        this.userDao = new UserDAO();
    }

    public List<User> getaList() {
        this.userList = getaDao().read();
        return userList;
    }

    public UserDAO getaDao() {
        if (this.userDao == null)
            this.userDao = new UserDAO();
        return userDao;
    }

    public User getUser() {
        if(this.user == null)
            this.user = new User();
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public String create(){
        this.getaDao().create(this.user);
        clearForm();
        return "user";
    }
    
    public String delete(){
        this.getaDao().delete(this.user.getId());
        clearForm();
        return "user";
    }
    
    public String updateForm(User user){
        this.user = user;
        
        return "user";
    }
    public void clearForm(){
        this.user = new User();
        
    }
    public String update(){
        this.userDao.update(this.user);
        this.clearForm();
        return "user";
    }
    
    public void deleteConfirm(User user){
        this.user = user;
        this.delete();
        
    }
    public User getCurrentUser(){
        return userDao.read(Utils.SessionUtils.getUserId());
    }
    
    public String login(){
        
        if(userDao.read(user.getName())!= null){
            Utils.SessionUtils.setUserId(userDao.read(user.getName()));
            Utils.SessionUtils.setLoggedin();}
        else
            return "login";
        return "index";
    }
    public String register(){
        userDao.create(user);
        return "login";
    }
 
}
