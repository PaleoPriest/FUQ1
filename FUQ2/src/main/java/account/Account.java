/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account;

import DB_entities.Users;
import dao.UserDAO;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import login.UserSession;

/**
 *
 * @author Rugile
 */
@Named
@Stateless
public class Account {
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserDAO userDAOImpl;
    
    @Inject
    private UserSession userSession;
    
    private Users user;
    private String passRepeat;
    private String genderValue;
    public Users getUser() {
        return user;
    }
    public void setUser(Users user) {
        this.user = user;
    }
    public String getPassRepeat() {
        return passRepeat;
    }
    public void setPassRepeat(String passRepeat) {
        this.passRepeat = passRepeat;
    }
    public String getGenderValue() {
        return genderValue;
    }
    public void setGenderValue(String genderValue) {
        this.genderValue = genderValue;
    }
    
    
    public void init(){
        //todo 
        //find user by id and load to user
    }
    
    public void updateUserInfo(){
        //to do
        //add to db
        //update userSessionInfo if neccessary
    }
}
