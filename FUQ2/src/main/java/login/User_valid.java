package login;

import DB_entities.Users;
import java.util.Date;
import javax.ejb.Stateless;
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
public class User_valid {

    public User_valid() {
    }
   
    @PersistenceContext(synchronization = SynchronizationType.UNSYNCHRONIZED)
    private EntityManager em;

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
}
