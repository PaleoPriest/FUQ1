package dao;

import DB_entities.Users;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.SynchronizationType;
import java.util.List;

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
    public int updateUser(Users user) {
        return 0;
    }

    @Override
    public int removeUser(Users user) {
        return 0;
    }

    @Override
    public Users getUserById(int id) {
        return null;
    }

    @Override
    public int getUserPoints(Users user) {
        return 0;
    }

    @Override
    public int updateUserPoints(Users user) {
        return 0;
    }
}
