import DB_entities.Users;
import Errors.Check_errors;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
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
import jdk.nashorn.internal.objects.annotations.Getter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Paulina
 */
@Named
@ConversationScoped
@Stateful
public class User_code implements Serializable
{

    private static final String PAGE_INDEX          = "index?faces-redirect=true";
    //private static final String PAGE_CREATE_STUDENT = "createStudent?faces-redirect=true";
    //private static final String PAGE_CONFIRM        = "confirm?faces-redirect=true";

    
    
    @PersistenceContext(type = PersistenceContextType.EXTENDED, synchronization = SynchronizationType.UNSYNCHRONIZED)
    private EntityManager em;

    @Inject
    //@Getter KODEL NEVEIKIA??? META ERRORA :(
    private Conversation conversation;
    
    private Users user = new Users();

    public Users getUser() {
        return user;
    }
    public void setUser(Users user) {
        this.user = user;
    }
   
	String username;
	String password;
	
        //TOLIAU CONVERSATION... FUNKCIJOS ETC
        
	public String login(String userN, String pass)
	{
		//Get username & password from db
		if (username ==	userN && password == pass)
			;	//good user data, login user
		else
			return ("Wrong username or password has been entered.");
                return "";
	}
	
	public String addUser(String userN, String pass/*, String ...*/)
	{
		//insert all fields to db
		return (new Check_errors()).checkForErrors(true/* or false*/, "Success of smth");	//insert done successfully
		
		/*if ()	//insert done successfully
			return ("New user created successful");
		else
			return ("error msg");*/
	}
	
	public String changeAccName(String username /*... fileds to change*/)	//change name / last name / nickname
	{
		/*
			Get user from db
			insert new fields to db
		*/
		return (new Check_errors()).checkForErrors(true/* or false*/, "Success of smth");	//insert done successfully
	}
	
	public String changeAccImg(String username, Object img)
	{
		/*
			Get user from db
			insert new img to db
		*/
		return (new Check_errors()).checkForErrors(true /*or false*/, "Success of smth");	//insert done successfully
	}
	
	
	public String createUser() {
            if (conversation.isTransient()) {
                return PAGE_INDEX;
            }

            em.persist(user);
            //studentService.create(student);
            //student.getCourseList().add(course);
            //course.getStudentList().add(student);

            return PAGE_CONFIRM;
        }

        public String ok() {
            try {
                conversation.end();
                em.joinTransaction();
                em.flush();
                return PAGE_INDEX;
            } catch (OptimisticLockException ole) {
                // Kažkas kitas buvo greitesnis...
                return null;
            } catch (PersistenceException pe) {
                // Kitokios bėdos su DB
                FacesContext.getCurrentInstance().addMessage(
                        null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Oi ai ajajai.", "Finita la commedia")
                );
                return null;
            }
        }

        public String cancel() {
            if (!conversation.isTransient()) {
                conversation.end();
            }
            return PAGE_INDEX;
        }
	
}