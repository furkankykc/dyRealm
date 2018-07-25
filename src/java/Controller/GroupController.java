/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.ArrayList;
import java.util.List;
import DAO.GroupDAO;
import Entity.Group;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

        
        
/**
 *
 * @author furkankykc
 */

@ManagedBean(name="groupController")
@SessionScoped
public class GroupController implements Serializable{
    
    private List<Group> groupList;
    private GroupDAO groupDao;
    private Group group;

    public GroupController() {
        this.groupList = new ArrayList<Group>();
        this.groupDao = new GroupDAO();
    }

    public List<Group> getaList() {
        this.groupList = getaDao().read();
        return groupList;
    }

    public GroupDAO getaDao() {
        if (this.groupDao == null)
            this.groupDao = new GroupDAO();
        return groupDao;
    }

    public Group getGroup() {
        if(this.group == null)
            this.group = new Group();
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
    
    public String create(){
        this.getaDao().create(this.group);
        clearForm();
        return "group";
    }
    
    public String delete(){
        this.getaDao().delete(this.group.getId());
        clearForm();
        return "group";
    }
    
    public String updateForm(Group group){
        this.group = group;
        
        return "group";
    }
    public void clearForm(){
        this.group = new Group();
        
    }
    public String update(){
        this.groupDao.update(this.group);
        this.clearForm();
        return "group";
    }
    
    public void deleteConfirm(Group group){
        this.group = group;
        this.delete();
        
    }
 
}
