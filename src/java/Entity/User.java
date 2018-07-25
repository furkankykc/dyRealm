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
public class User {
    
    private int id;
    private String name;
    private String email;
    private ArrayList<Group> groups;

    public User(int id, String name, String email,ArrayList<Group> groups) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.groups = groups;
    }

    public User(String name, String email,ArrayList<Group> groups) {
        this.name = name;
        this.email = email;
        this.groups = groups;
    }

    public User() {
        this.groups =  new ArrayList<Group>();
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", email=" + email + ", groups=" + groups + '}';
    }
    
    
}
