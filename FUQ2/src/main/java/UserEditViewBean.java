
import DB_entities.Users;
import dao.UserDAO;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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

/**
 * Created by Martynas on 5/28/2016.
 */


@Named
@Stateless

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

    public String doneAction(Users user) {

        user.setEditable(false);
        return null;
    }

    public void doTransaction(){

       em.flush();
    }



}


