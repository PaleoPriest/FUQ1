package dao;

import DB_entities.Users;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Martynas on 5/28/2016.
 */
public interface UserDAO extends Serializable {

    public int insertUser(Users user);
    public List<Users> getUserList();
    public int updateUser(Users user);
    public int removeUser(Users user);
    public Users getUserById(int id);
    public int getUserPoints(Users user);
    public int updateUserPoints(Users user);
    public Users getUserByFBId(String facebookId);
    public Users getUserByEmail(String email);
}
