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
    private boolean showMessage = false;
    
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
    public boolean getShowMessage() {
        return showMessage;
    }
    public void setShowMessage(boolean showMessage) {
        this.showMessage = showMessage;
    }

    
    
    public void login(){
        if(lh.isValidUser(email, password, usi)!=false)
        {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Prisijungta", ""));
            isFailure = false;
            //System.out.println(usi.id);
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Neteisingas slaptazodis arba elektroninis pastas", ""));
            isFailure = true;
            //System.out.println("Nera userio");
        }
        password=null;
        showMessage = !isFailure;   //edit logic so we stop seeing message!!!!!!!!!!!
    }
    
    public String returnHome()
    {
        return "index?faces-redirect=true";
    }
    
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
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
