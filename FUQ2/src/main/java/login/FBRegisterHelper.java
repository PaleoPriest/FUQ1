/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import DB_entities.Users;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceException;
import javax.persistence.SynchronizationType;

/**
 *
 * @author Rugile
 */

@Named
@Stateless

public class FBRegisterHelper implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @PersistenceContext(type = PersistenceContextType.TRANSACTION, synchronization = SynchronizationType.SYNCHRONIZED)
    private EntityManager em;
    
    //@Inject
    //private User_valid user_valid;
    
    private Users user = new Users();

    public Users getUser() {
        return user;
    }
    public void setUser(Users user) {
        this.user = user;
    }
    
    public boolean createUser(String facebookId, String firstName, String lastName, String email, String gender) {
        user.setName(firstName);
        user.setSurname(lastName);
        user.setMail(email);
        user.setSex(gender);
        user.setFbid(facebookId);
        
        try
        {
            int tempId = 0;
            user.setId(tempId);     //nes kitaip persist skundziasi
            em.persist(user);
            //user_valid.create(user);
            //System.out.println("asdf");
            em.flush();
        }
        catch (OptimisticLockException ole) {
            // Kažkas kitas buvo greitesnis...
            //FacesContext.getCurrentInstance().addMessage(
            //        null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Vartotojas nesukurtas. Griskite atgal ir bandykite dar karta.", " "));
            return false;
        }
        catch(PersistenceException pe)
        {
            //FacesContext.getCurrentInstance().addMessage(
             //       null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Vartotojas nesukurtas. Griskite atgal ir bandykite dar karta.", " "));
            return false;
        }
        catch(Exception e)
        {
            return false; //just in case
        }
        return true;
    }
}
