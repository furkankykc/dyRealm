/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.ModuleDAO;
import Entity.Module;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author furkankykc
 */
@FacesConverter(value = "moduleConverter")
public class ModuleConverter implements Converter{

    private ModuleDAO moduleDao = new ModuleDAO();
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getModuleDao().read(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Module module = (Module) value;
        if(module!=null)
            return Integer.toString(module.getId());
        else
            return "0";
    }

    public ModuleDAO getModuleDao() {
        if(moduleDao!=null)
            moduleDao =new ModuleDAO();
        return moduleDao;
    }
    
    
}
