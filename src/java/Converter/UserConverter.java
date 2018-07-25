/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.UserDAO;
import Entity.User;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author furkankykc
 */
@FacesConverter(value = "userConverter")
public class UserConverter implements Converter{

    private UserDAO userDao = new UserDAO();
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getUserDao().read(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        User user = (User) value;
        if(user!=null)
            return Integer.toString(user.getId());
        else
            return "0";
    }

    public UserDAO getUserDao() {
        if(userDao!=null)
            userDao =new UserDAO();
        return userDao;
    }
    
    
}
