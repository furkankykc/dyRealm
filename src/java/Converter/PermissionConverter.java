/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.PermissionsDAO;
import Entity.Permissions;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author furkankykc
 */
@FacesConverter(value = "permConverter")
public class PermissionConverter implements Converter{

    private PermissionsDAO permissionsDao = new PermissionsDAO();
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getPermissionsDao().read(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Permissions permissions = (Permissions) value;
        if(permissions!=null)
            return Integer.toString(permissions.getId());
        else
            return "0";
    }

    public PermissionsDAO getPermissionsDao() {
        if(permissionsDao!=null)
            permissionsDao =new PermissionsDAO();
        return permissionsDao;
    }
    
    
}
