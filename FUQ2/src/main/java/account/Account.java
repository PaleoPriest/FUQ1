/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account;

import DB_entities.Users;
import dao.UserDAO;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import login.UserCreationHelper;
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
    
    @Inject
    private UserCreationHelper creationHelper;
    
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
        user = userDAOImpl.getUserById(userSession.getId());
    }
    
    public String updateUserInfo(){
        if(!creationHelper.comparePasswords(user.getPassword(), passRepeat))
        {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nesutampa slaptazodziai", "Nesutampa slaptazodis"));
            return null;
        }
        
        userDAOImpl.updateUser(user);
        userSession.getUsi().setFirstName(user.getName());
        userSession.getUsi().setLastName(user.getSurname());
        return "paskyra?faces-redirect=true";
    }
}
