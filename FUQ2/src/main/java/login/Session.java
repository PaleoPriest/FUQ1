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

public class Session {
    
    @Inject
    PabegusiKlaseLoginHelper lh;
    
    private String  userName;
    private String  password;
    private UserSessionInfo usi;
    
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
    public UserSessionInfo getUsi() {
        return usi;
    }
    public void setUsi(UserSessionInfo usi) {
        this.usi = usi;
    }
    
    
    public void login(){
        password=null;
        
        if(lh.isValidUser(userName, password, usi)!=false)
        {
            System.out.println(usi.id);
        }
        else
        {
            System.out.println("Nera userio");
        }
    }
    
    public Boolean isLoggedIn()
    {
        return usi.id!=null;    //return true if not equals
    }
    
    public Boolean isAdmin()
    {
        return usi.isAdmin;
    }
    
    public Integer getId()
    {
        return usi.id;
    }
}
