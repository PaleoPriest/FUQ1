/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import DB_entities.Users;
import dao.UserDAO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Rugile
 */
@Named
@Stateless
public class LoginHelper {
    
    @Inject
    private UserDAO userDAOImpl;
    
    @Inject
    RememberMe remember;
    
    public boolean isValidUser(String email, String pass, UserSessionInfo usi)
    {
        //Users userList = null;
        //Query query = em.createQuery("SELECT u.mail, u.password, u.id, u.isAdmin, u.name, u.surname FROM Users u where u.mail = :email").setParameter("email", email);
        
        String emailTemp="";
        String passTemp="";
        
        Users results = userDAOImpl.getUserByEmail(email);
        remember.doCookieStuff(results);
        
        if(results!=null)
        {
            emailTemp = results.getMail();
            passTemp  = results.getPassword();
            usi.id = results.getId();
            
            usi.firstName = results.getName();
            usi.lastName = results.getSurname();
            
            if(results.getIsAdmin()==null)
            {
                usi.isAdmin = false;
            }
            else
            {
                usi.isAdmin = results.getIsAdmin();
            }
        }

        if(usi.id!=null)
        {
            //System.out.println(name+emailTemp+pass+passTemp);
            return email.equals(emailTemp) && pass.equals(passTemp);
        }
        else{
            return false;
        } 
    }
}
