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
public class Permissions {

    private int id;
    private boolean create;
    private boolean read;
    private boolean update;
    private boolean delete;
    private Module module;

    public Permissions(int id, boolean create, boolean read, boolean update, boolean delete, Module module) {
        this.id = id;
        this.create = create;
        this.read = read;
        this.update = update;
        this.delete = delete;
        this.module = module;
    }

    public Permissions(boolean create, boolean read, boolean update, boolean delete, Module module) {
        this.create = create;
        this.read = read;
        this.update = update;
        this.delete = delete;
        this.module = module;
    }

    public Permissions(boolean create, boolean read, boolean update, boolean delete) {
        this.create = create;
        this.read = read;
        this.update = update;
        this.delete = delete;
    }

    public Permissions() {
        this.create = false;
        this.read = false;
        this.update = false;
        this.delete = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCreate() {
        return create;
    }

    public void setCreate(boolean create) {
        this.create = create;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
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
        final Permissions other = (Permissions) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Permissions{" + "id=" + id + ", create=" + create + ", read=" + read + ", update=" + update + ", delete=" + delete + ", module=" + module + '}';
    }

}
