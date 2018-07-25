/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.ArrayList;
import java.util.List;
import DAO.PermissionsDAO;
import Entity.Permissions;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

        
        
/**
 *
 * @author furkankykc
 */

@ManagedBean(name="permissionsController")
@SessionScoped
public class PermissionsController implements Serializable{
    
    private List<Permissions> permissionsList;
    private PermissionsDAO permissionsDao;
    private Permissions permissions;

    public PermissionsController() {
        this.permissionsList = new ArrayList<Permissions>();
        this.permissionsDao = new PermissionsDAO();
    }

    public List<Permissions> getaList() {
        this.permissionsList = getaDao().read();
        return permissionsList;
    }

    public PermissionsDAO getaDao() {
        if (this.permissionsDao == null)
            this.permissionsDao = new PermissionsDAO();
        return permissionsDao;
    }

    public Permissions getPermissions() {
        if(this.permissions == null)
            this.permissions = new Permissions();
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }
    
    public String create(){
        this.getaDao().create(this.permissions);
        clearForm();
        return "permissions";
    }
    
    public String delete(){
        this.getaDao().delete(this.permissions.getId());
        clearForm();
        return "permissions";
    }
    
    public String updateForm(Permissions permissions){
        this.permissions = permissions;
        
        return "permissions";
    }
    public void clearForm(){
        this.permissions = new Permissions();
        
    }
    public String update(){
        this.permissionsDao.update(this.permissions);
        this.clearForm();
        return "permissions";
    }
    
    public void deleteConfirm(Permissions permissions){
        this.permissions = permissions;
        this.delete();
        
    }
 
}
