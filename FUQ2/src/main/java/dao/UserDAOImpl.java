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
    public int updateUser(Users user) {

        em.joinTransaction();
        Users newUser = em.find(Users.class, user.getId());

        if (user.getName() != null) {
            newUser.setName(user.getName());
        }

        if (user.getSurname() != null) {
            newUser.setSurname(user.getSurname());
        }

        if (user.getNickname() != null) {
            newUser.setName(user.getNickname());
        }

        if (user.getPassword() != null) {
            newUser.setPassword(user.getPassword());
        }

        if (user.getMail() != null) {
            newUser.setMail(user.getMail());
        }

        if (user.getPhone() != null) {
            newUser.setPhone(user.getPhone());
        }

        if (user.getStatus() != null) {
            newUser.setStatus(user.getStatus());
        }

        if (user.getPoints() != null) {
            newUser.setPoints(user.getPoints());
        }
        em.flush();

        return 0;
    }

    @Override
    public int removeUser(Users user) {
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
