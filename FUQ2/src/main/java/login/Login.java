/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Rugile
 */


@Named
@Stateful
@SessionScoped

public class Login {
    
    @Inject
    PabegusiKlaseLoginHelper lh;
    
    private String  userName;
    private String  password;
    private Integer id; 
    private boolean admin;
    
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public boolean getAdmin() {
        return admin;
    }
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    
    public void login(){
        id = lh.isValidUser(userName, password);
        password=null;
        
        if(id!=null)
        {
            System.out.println(id);
        }
        else
        {
            System.out.println("Nera userio");
        }
    }
    
    public Boolean isLoggedIn()
    {
        if(id!=null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public Boolean isAdmin()
    {
        return admin;
    }
}
