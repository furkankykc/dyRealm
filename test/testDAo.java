
import DAO.GroupDAO;
import DAO.ModuleDAO;
import DAO.PermissionsDAO;
import DAO.UserDAO;
import Entity.Group;
import Entity.Permissions;
import Entity.User;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author furkankykc
 */
public class testDAo {
    public static void main(String args[]){
        GroupDAO gDao = new GroupDAO();
        PermissionsDAO mDao = new PermissionsDAO();
        UserDAO uDao = new UserDAO();
        ArrayList<Permissions> permissions = new ArrayList<Permissions>();

        //gDao.update(new Group(1,"yeni_test", mDao.read()));
        //System.out.println("testDAo.main()"+gDao.read());
        //System.err.println(""+mDao.read(1).toString());
        uDao.create(new User("furkan", "kasda", gDao.read()));
        //System.out.println("testDAo.main()"+uDao.read());
    }
}
