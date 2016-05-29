package login;

import DB_entities.Users;
import dao.UserDAO;
import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.SynchronizationType;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Paulina
 */

@Stateless
public class UserCreationHelper {

    public UserCreationHelper() {
    }
   
    @PersistenceContext(synchronization = SynchronizationType.UNSYNCHRONIZED)
    private EntityManager em;

    @Inject
    private UserDAO userDAOImpl;
    
    @Inject
    UserSession userSession;
    
    public void create(Users user) {
        em.persist(user);
    }
    
    public boolean comparePasswords(String pass1, String pass2)
    {
        return pass1.equals(pass2);
    }
    
    public String convertGender(String value)
    {
        if("1".equals(value))
            return "Vyras";
        if ("2".equals(value))
            return "Moteris";
        if ("".equals(value) || value==null)
            return null;
        else
            return "Kita";
    }
    
    public boolean isUserRegisteredYet(String email)
    {
        Users user = userDAOImpl.getUserByEmail(email);
        return user!=null;
    }
    
    public void setSessionInfo(int id, String firstName, String lastName, boolean isAdmin)
    {
        userSession.setAllSessionInfo(id, firstName, lastName, isAdmin);
    }
    
    public void loginUser(String firstName, String lastName, String email)
    {
        int tempId = userDAOImpl.getUserByEmail(email).getId();
        setSessionInfo(tempId, firstName, lastName, false);
    }
}
