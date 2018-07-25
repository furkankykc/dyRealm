/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import DAO.UserDAO;
import Entity.Group;
import Entity.Permissions;
import Entity.User;
import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cypher
 */
public class SessionUtils {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public static String getUserName() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return session.getAttribute("email").toString();
    }

    public static int getUserId() {
        HttpSession session = getSession();
        if (session != null && (session.getAttribute("userid") != null)) {
            return (int) session.getAttribute("userid");

        } else {
            return 0;
        }
    }

    public static void setUserId(User user) {
        HttpSession session = getSession();
        if (user != null) {
            session.setAttribute("userid", user.getId());
        }
    }

    public static void setLoggedin() {
        HttpSession session = getSession();

        session.setAttribute("loggedin", "true");

    }

    public static boolean getLoggedin() {
        HttpSession session = getSession();
        if (session != null){
            if(session.getAttribute("loggedin") != null) 
            return true;

        } else {
            return false;
        }
        return false;
    }

    public static Permissions getPermission(int moduleid) {
        int userid = 0;
        HttpSession session = getSession();

        userid = (getUserId());
        UserDAO uDao = new UserDAO();
        System.out.println("Utils.SessionUtils.getPermission()"+uDao.hasAccess(userid, moduleid));
        return uDao.hasAccess(userid, moduleid);
    }

}
