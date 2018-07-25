/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.ArrayList;

/**
 *
 * @author furkankykc
 */
public class Group {

    private int id;
    private String groupName;
    private ArrayList<Permissions> permissions;
    public Group(int id, String groupName,ArrayList<Permissions>permissions) {
        this.id = id;
        this.groupName = groupName;
        this.permissions = permissions;
    }

    public Group(String groupName,ArrayList<Permissions>permissions) {
        this.groupName = groupName;
        this.permissions = permissions;
    }

    public Group() {
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ArrayList<Permissions> getPermissions() {
        return permissions;
    }

    public void setPermissions(ArrayList<Permissions> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "Group{" + "id=" + id + ", groupName=" + groupName + ", permissions="+permissions + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.id;
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
        final Group other = (Group) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }


}
