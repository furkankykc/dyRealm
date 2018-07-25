/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.GroupDAO;
import Entity.Group;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author furkankykc
 */
@FacesConverter(value = "groupConverter")
public class GroupConverter implements Converter{

    private GroupDAO groupDao = new GroupDAO();
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getGroupDao().read(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Group group = (Group) value;
        if(group!=null)
            return Integer.toString(group.getId());
        else
            return "0";
    }

    public GroupDAO getGroupDao() {
        if(groupDao!=null)
            groupDao =new GroupDAO();
        return groupDao;
    }
    
    
}
