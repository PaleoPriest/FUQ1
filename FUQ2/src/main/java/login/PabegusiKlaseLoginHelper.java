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
    public boolean isValidUser(String name, String pass, UserSessionInfo usi)
    {
        //Users userList = null;
        Query query = em.createQuery("SELECT u.nickname, u.password, u.id, u.isAdmin, u.name, u.surname FROM Users u where u.nickname = :name").setParameter("name", name);
        
        List<Object[]> results = query.getResultList();
        
        String nicknameTemp = String.valueOf(String.valueOf(results.get(0)[0]));
        String passTemp  = String.valueOf(results.get(0)[1]);
        usi.id = Integer.valueOf(results.get(0)[2].toString());
        usi.isAdmin = Boolean.valueOf(results.get(0)[3].toString());
        usi.firstName =String.valueOf(results.get(0)[4]);
        usi.lastName =String.valueOf(results.get(0)[5]);

        if(usi.id!=null)
        {
            if(name.equals(nicknameTemp) && pass.equals(passTemp))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else{
            return false;
        } 
    }
}
