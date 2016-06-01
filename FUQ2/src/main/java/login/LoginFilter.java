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
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rugile
 */

@WebFilter("*.html")    //this turns it on/off
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
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse)response;
        //User user = request.getSession().getAttribute("user");

        String path = ((HttpServletRequest) request).getRequestURI();
        System.out.println(path);
        if (path.contains("prisijungti")  || path.contains("index") || path.contains("registracija") || path.contains("kontaktine_info") || path.contains("facebook") || path.contains("css") || path.contains("fronts") || path.contains("img") || path.contains("js") || path.contains("source_fancybox") || path.contains("Templates") || path.contains("resource") || path.contains("confirm")) {
            //System.out.println("asdf");
            chain.doFilter(request, response); // Just continue chain.
        } else {
            if (userSession.isLoggedIn() == false) {
                String uuid = rememberMe.getCookieValue(req, "remember"); //probably need uniqe name

                Users user = null;
                if (uuid != null) {
                    user = rememberDAOImpl.findRemember(uuid).getLoggedinuser();
                }


                if (user == null) {
                    try {
                        res.sendRedirect("prisijungti.html");  //add exception fol login so user can see this
                    } catch (IOException ex) {
                        Logger.getLogger(LoginFilter.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    login(user);
                    chain.doFilter(request, response);
                }
            }
            else
            {
                chain.doFilter(request, response);
            }
        }
        
    }

    @Override
    public void destroy()
    {
    }
    
}
