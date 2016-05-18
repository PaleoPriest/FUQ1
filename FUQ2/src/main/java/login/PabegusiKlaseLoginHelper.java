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
public class PabegusiKlaseLoginHelper {
    
    @PersistenceContext
    private EntityManager em;
    public Integer isValidUser(String name, String pass)
    {
        //Users userList = null;
        Query query = em.createQuery("SELECT u.nickname, u.password, u.id FROM Users u where u.nickname = :name").setParameter("name", name);
        
        List<Object[]> results = query.getResultList();
        
        String nicknameTemp = String.valueOf(String.valueOf(results.get(0)[0]));
        String passTemp  = String.valueOf(results.get(0)[1]);
        String idTemp = String.valueOf(results.get(0)[2]);

        Integer idTemp2 = Integer.valueOf(idTemp);
        if(idTemp2!=null)
        {
            if(name.equals(nicknameTemp) && pass.equals(passTemp))
            {
                return idTemp2;
            }
            else
            {
                return null;
            }
        }
        else{
            return null;
        } 
    }
}
