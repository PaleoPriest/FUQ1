/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Rugile
 */


@Named
@Stateful
@SessionScoped

public class UserSession {
    
    @Inject
    LoginHelper lh;
    
    private String  email;
    private String  password;
    private UserSessionInfo usi = new UserSessionInfo();
    private boolean isFailure = false;
    private boolean isBeingDeleted = false;
    private boolean isPasswordFail = false;
    private boolean remember;

    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
       public boolean getIsFailure() {
        return isFailure;
    }
    public void setIsFailure(boolean isFailure) {
        this.isFailure = isFailure;
    }
    public boolean getIsBeingDeleted() {
        return isBeingDeleted;
    }
    public void setIsBeingDeleted(boolean isBeingDeleted) {
        this.isBeingDeleted = isBeingDeleted;
    }
    public boolean getIsPasswordFail() {
        return isPasswordFail;
    }
    public void setIsPasswordFail(boolean isPasswordFail) {
        this.isPasswordFail = isPasswordFail;
    }
    public boolean isRemember() {
        return remember;
    }
    public void setRemember(boolean remember) {
        this.remember = remember;
    }
    
    public String login(){
        if(lh.isValidUser(email, password, usi)!=false)
        {
            isFailure = false;
            password=null;
            
            //System.out.println(usi.firstName + usi.lastName);
            return returnHome();
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Neteisingas slaptazodis arba elektroninis pastas", ""));
            isFailure = true;
            //System.out.println("Nera userio");
        }
        password=null;
        return null;
    }
    
    public String returnHome()
    {
        return "index?faces-redirect=true";
    }
    
    public void resetShowMessage()
    {
        isFailure = false;
    }
    
    public String logoutForNav(){
        //System.out.println(usi.firstName);
        logoutGeneral();
        return "../index?faces-redirect=true";
    }
    
    public String logoutForDelete(){
        //System.out.println(usi.firstName);
        logoutGeneral();
        return "index?faces-redirect=true";
    }
    
    private void logoutGeneral(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
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
    
    public void setAllSessionInfo(int id, String firstName, String lastName, boolean isAdmin)
    {
        usi.id = id;
        usi.firstName = firstName;
        usi.lastName = lastName;
        usi.isAdmin = isAdmin;
    }
}
