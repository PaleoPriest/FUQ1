/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import DB_entities.Users;
import dao.RememberDAO;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rugile
 */

@Named
@Stateless
public class RememberMe {
    
    @Inject
    RememberDAO rememberDAOImpl;
    
    @Inject
    UserSession userSession;
    
    
    public void doCookieStuff(Users user)
    {
        //HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        if (userSession.isRemember())
        {
        String uuid = UUID.randomUUID().toString();
        rememberDAOImpl.saveLogin(uuid, user);
        addCookie(response, "remember", uuid, 2592000); //thats 30 days in seconds
        } else {
            rememberDAOImpl.removeLogin(user);
            removeCookie(response, "remember");
        }
    }
    
    public static String getCookieValue(HttpServletRequest request, String name) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
    }
    return null;
}

public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
    Cookie cookie = new Cookie(name, value);
    cookie.setPath("/");
    cookie.setMaxAge(maxAge);
    response.addCookie(cookie);
}

public static void removeCookie(HttpServletResponse response, String name) {
    addCookie(response, name, null, 0);
}
    
}
