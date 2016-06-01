
import DB_entities.Users;
import dao.UserDAO;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.persistence.OptimisticLockException;

/**
 * Created by Martynas on 5/28/2016.
 */


@Named
@ManagedBean
@ViewScoped


public class UserEditViewBean implements Serializable {

    @PersistenceContext
    private EntityManager em;


    @Inject
    private UserDAO userDAOImpl;

    private int newPoints;
    private List<Users> users;

    public List<Users> getUsers() {

        return users;
    }

    @PostConstruct
    public void init(){

        em.isOpen();

        users = userDAOImpl.getUserList();
    }

    public String editAction(Users user) {

        user.setEditable(true);
        return null;
    }

    public String save(Users user) throws OptimisticLockException{

        user.setEditable(false);
        try{
            user = userDAOImpl.updateUser(user);
        }
        catch(OptimisticLockException ole)
        {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Kazkas jau pakeite si vartotoja. Atsidarykite langa is naujo ir bandykite dar karta", ""));
        }
        catch(Exception e)
        {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Kazkas jau pakeite si vartotoja. Atsidarykite langa is naujo ir bandykite dar karta", ""));
        }
        return null;
    }


}


