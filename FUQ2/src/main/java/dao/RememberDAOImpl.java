/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DB_entities.Remember;
import DB_entities.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.SynchronizationType;

/**
 *
 * @author Rugile
 */

@Stateless
public class RememberDAOImpl implements RememberDAO {

     @PersistenceContext(type= PersistenceContextType.TRANSACTION,
            synchronization= SynchronizationType.UNSYNCHRONIZED)
    private EntityManager em;
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean saveLogin(String uuid, Users user) {
        Remember remember = new Remember();
        remember.setId(uuid);
        remember.setLoggedinuser(user);
        
        try
        {
            em.joinTransaction();
            em.persist(remember);
            em.flush();
            return true;
        }
        catch(PersistenceException pe)
        {
            return false;
        }
        
    }

    @Override
    public boolean removeLogin(Users user) {
        Remember remember = findRememberByUser(user);
        if(remember==null)
        {
            return false;
        }
        try
        {
            em.joinTransaction();
            em.remove(em.merge(remember));
            user.setRememberList(null);
            user = em.merge(user);
            em.flush();
        }
        catch(PersistenceException pe)
        {
             return false;
        }
        return true;        //add try/catch
    }

    @Override
    public Remember findRememberByUser(Users user) {
        Query query = em.createQuery("SELECT r FROM Remember r WHERE r.loggedinuser = :user").setParameter("user", user);
        List<Remember> logins = query.getResultList();
        Remember login = null;
        if(!logins.isEmpty())
        {
            login = logins.get(0);
        }
        return login;
    }
    @Override
    public Remember findRemember(String id) {
        Query query = em.createQuery("SELECT r FROM Remember r WHERE r.id = :id").setParameter("id", id);
        List<Remember> logins = query.getResultList();
        Remember login = null;
        if(!logins.isEmpty())
        {
            login = logins.get(0);
        }
        return login;
    }
    
    
}
