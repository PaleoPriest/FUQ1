/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account;

import DB_entities.Users;
import dao.UserDAO;
import java.util.Date;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import login.UserCreationHelper;
import login.UserSession;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Rugile
 */
@Named
@Stateless
//@Stateful
//@RequestScoped
public class Account {
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserDAO userDAOImpl;
    
    @Inject
    private UserSession userSession;
    
    @Inject
    private UserCreationHelper creationHelper;
    
    private Users user;
    private String passRepeat;
    private String genderValue;
    private String tempPass;
    
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
        user = userDAOImpl.getUserById(userSession.getId());
        genderValue = convertGenderToNumbers(user.getSex());
        tempPass = user.getPassword();
    }
    
    public String updateUserInfo(){
        if(!creationHelper.comparePasswords(user.getPassword(), passRepeat))
        {
            userSession.setIsPasswordFail(true);
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nesutampa slaptazodziai", ""));
            return null;
        }
        if(user.getPassword()==null || passRepeat==null || user.getPassword().equals("") || passRepeat.equals(""))
        {
            user.setPassword(tempPass);
        }
        
        System.out.println(user.getBirthday());
        userSession.setIsPasswordFail(false);
        user = userDAOImpl.updateUser(user);
        userSession.getUsi().setFirstName(user.getName());
        userSession.getUsi().setLastName(user.getSurname());
        return "paskyra?faces-redirect=true";
    }
    
    public void deleteAccount()
    {
        userSession.setIsBeingDeleted(true);
        FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ar tikrai norite istrinti savo paskyra?", ""));
        
    }
    
    public String removeAccount()
    {
        //System.out.println("asdf");
        userDAOImpl.removeUser(user);
        userSession.setIsBeingDeleted(false);
        return userSession.logoutForDelete();
    }
    
    public String cancelDelete()
    {
        userSession.setIsBeingDeleted(false);
        return "paskyra?faces-redirect=true";
    }
    
    public String convertGenderToNumbers(String value)
    {
        if("Vyras".equals(value))
            return "1";
        if ("Moteris".equals(value))
            return "2";
        else
            return "3";
    }
    
     public void dateChanged(SelectEvent event)
    {
        Date date = (Date) event.getObject();
        user.setBirthday(date);
    }
}
