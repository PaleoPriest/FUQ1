/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DB_entities.Remember;
import DB_entities.Users;

/**
 *
 * @author Rugile
 */
public interface RememberDAO {
    public boolean saveLogin(String uuid, Users user);
    public boolean removeLogin(Users user);
    public Remember findRememberByUser(Users user);
    public Remember findRemember(String id);
}
