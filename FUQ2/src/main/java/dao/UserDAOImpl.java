package dao;

import DB_entities.Users;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.SynchronizationType;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Created by Martynas on 5/28/2016.
 */

@Stateless
public class UserDAOImpl implements UserDAO {

    @PersistenceContext(type= PersistenceContextType.TRANSACTION,
            synchronization= SynchronizationType.UNSYNCHRONIZED)
    private EntityManager em;

    @Override
    public int insertUser(Users user) {
        return 0;
    }

    @Override
    public List<Users> getUserList() {
        try {
            List<Users> userList = em.createQuery("select e from Users e",
                    Users.class).getResultList();
            return userList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    
    @Override   
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Users updateUser(Users user) { 

        em.joinTransaction();
        user = em.merge(user);  
        em.flush();

        return user;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public int removeUser(Users user) {
        em.joinTransaction();
        em.remove(em.merge(user));
        em.flush();
        return 0;
    }

    @Override
    public Users getUserById(int id) {
        Query query = em.createQuery("SELECT u FROM Users u WHERE u.id = :id").setParameter("id", id);
        List<Users> results = query.getResultList();
        Users user = null;
        if(!results.isEmpty())
        {
            user = results.get(0);
        }
        return user;
    }

    @Override
    public int getUserPoints(Users user) {
        return 0;
    }

    @Override
    public int updateUserPoints(Users user) {
        return 0;
    }

    @Override
    public Users getUserByFBId(String facebookId) {
        Query query = em.createQuery("SELECT u FROM Users u WHERE u.fbid = :id").setParameter("id", facebookId);
        List<Users> results = query.getResultList();
        Users user = null;
        if(!results.isEmpty())
        {
            user = results.get(0);
        }
        return user;
    }

    @Override
    public Users getUserByEmail(String email) {
        TypedQuery<Users> query = em.createQuery("SELECT u FROM Users u where u.mail = :email", Users.class).setParameter("email", email);
        List<Users> results = query.getResultList();
        Users user = null;
        if(!results.isEmpty())
        {
            user = results.get(0);
        }
        return user;
    }
    
    
}
