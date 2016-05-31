/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import DB_entities.Users;
import dao.RememberDAO;
import dao.UserDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rugile
 */
public class LoginFilter implements Filter{

    @Inject
    UserSession userSession;
    @Inject
    RememberMe rememberMe;
    @Inject
    RememberDAO rememberDAOImpl;
    @Inject
    UserDAO userDAOImpl;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        //User user = request.getSession().getAttribute("user");

        if (userSession.isLoggedIn() == false) {
            String uuid = rememberMe.getCookieValue(req, "remember");

            Users user = null;
            if (uuid != null) {
                user = rememberDAOImpl.findRemember(uuid).getLoggedinuser();
            }
            
            
            if (user == null) {
                try {
                    response.sendRedirect("login");
                } catch (IOException ex) {
                    Logger.getLogger(LoginFilter.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                login(user);
            }
        }
    }

    public void login(Users user)
    {
        userSession.setAllSessionInfo(user.getId(), user.getName(), user.getSurname(), true);

        if(user.getIsAdmin()==null)
        {
            userSession.getUsi().isAdmin = false;
        }
        else
        {
            userSession.getUsi().isAdmin = user.getIsAdmin();
        }
        
    }

    

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
    }

    @Override
    public void destroy()
    {
    }
    
}
