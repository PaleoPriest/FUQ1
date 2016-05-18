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
    LoginHelper lh;
    
    private String  userName;
    private String  password;
    private Integer id; 
    
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

    public void login(){
        id = lh.isValidUser(userName, password);
        //System.out.println(id);
    }
}