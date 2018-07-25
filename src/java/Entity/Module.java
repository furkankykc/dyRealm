/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


/**
 *
 * @author furkankykc
 */

public class Module {
    
    private int id;
    private String title;
    public Module(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Module(String title) {
        this.title = title;
    }

    public Module() {
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Module other = (Module) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    public Permissions getAccess(){
       Permissions perm = Utils.SessionUtils.getPermission(this.getId());
        return perm;
                }
}
