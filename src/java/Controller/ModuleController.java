/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.ArrayList;
import java.util.List;
import DAO.ModuleDAO;
import Entity.Module;
import Entity.Permissions;
import java.io.Serializable;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author furkankykc
 */
@ManagedBean(name = "moduleController")
@SessionScoped
public class ModuleController implements Serializable {

    private List<Module> moduleList;
    private ModuleDAO moduleDao;
    private Module module;
    private Module moduleAccess;

    public ModuleController() {
        this.moduleList = new ArrayList<Module>();
        this.moduleDao = new ModuleDAO();
    }

    public List<Module> getaList() {
        this.moduleList = getaDao().read();
        return moduleList;
    }

    public ModuleDAO getaDao() {
        if (this.moduleDao == null) {
            this.moduleDao = new ModuleDAO();
        }
        return moduleDao;
    }

    public Module getModule() {
        if (this.module == null) {
            this.module = new Module();
        }
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public String create() {
        if (Utils.SessionUtils.getPermission(module.getId()).isCreate()||true) {
            this.getaDao().create(this.module);
        }
        clearForm();
        return "module";
    }

    public String delete() {
        if (Utils.SessionUtils.getPermission(module.getId()).isDelete()) {
            this.getaDao().delete(this.module.getId());
        }
        clearForm();
        return "module";
    }

    public String updateForm(Module module) {
        this.module = module;

        return "module";
    }

    public void clearForm() {
        this.module = new Module();

    }

    public String update() {
        if (Utils.SessionUtils.getPermission(module.getId()).isUpdate()){
            this.moduleDao.update(this.module);
        }
        this.clearForm();
        return "module";
    }

    public void deleteConfirm(Module module){
        this.module = module;
        this.delete();
        
    }
    public String updateAccess(Module moduleAccess) {
        this.moduleAccess = moduleAccess;

        return "module";
    }

    public Permissions hasAccess() throws java.io.IOException {
        return Utils.SessionUtils.getPermission(moduleAccess.getId());
    }

}
