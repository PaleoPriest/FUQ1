/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import DB_entities.Users;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Rugile
 */
@Named
@Stateless
public class LoginHelper {
    
    @PersistenceContext
    private EntityManager em;
    
    public Integer isValidUser(String name, String pass)
    {
        //Users userList = null;
        Query query = em.createQuery("SELECT u.nickname, u.password, u.id FROM Users u where u.nickname = :name").setParameter("name", name);
        
        String id2=null;
        List<Object[]> results = query.getResultList();
        for(Object[] elements: results){
        String nick = String.valueOf(String.valueOf(elements[0]));
        String pass2  = String.valueOf(elements[1]);
          id2 = String.valueOf(elements[2]);
        }
        //userList = (Users) query.getSingleResult();
        
        //System.out.println(results);
        //System.out.println(id2);
        /*if(userList!=null)
        {
            return userList.getId();
        }
        else{
            return null;
        }*/
        return null;
    }
}
