package login;

import login.UserCreationHelper;
import DB_entities.Users;

import java.util.Map;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceException;
import javax.persistence.SynchronizationType;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Paulina
 */
@Named
@ConversationScoped
@Stateful
public class UserCreation implements Serializable
{
    private static final String PAGE_INDEX          = "index?faces-redirect=true";
    private static final String PAGE_CONFIRM        = "confirm?faces-redirect=true";
    private static final long serialVersionUID = 1L;

    @PersistenceContext(type = PersistenceContextType.EXTENDED, synchronization = SynchronizationType.UNSYNCHRONIZED)
    private EntityManager em;

    @Inject
    private UserCreationHelper creationHelper;
    
    @Inject
    private Conversation conversation;
    
    public Conversation getConversation() {
        return conversation;
    }
    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }
    
    private Users user = new Users();
    public Users getUser() {
        return user;
    }
    public void setUser(Users user) {
        this.user = user;
    }

    String passRepeat;
    public String getPassRepeat() {
        return passRepeat;
    }
    public void setPassRepeat(String passRepeat) {
        this.passRepeat = passRepeat;
    }
    String genderValue;
    public String getGenderValue() {
        return genderValue;
    }
    public void setGenderValue(String genderValue) {
        this.genderValue = genderValue;
    }

    public void dateChanged(AjaxBehaviorEvent event)
    {
        //System.out.println("asdf");
        //System.out.println(user.getBirthday());
        //HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        
        //Object tempDate = event.//request.getParameter("userForm:calendarForRegistration");
        //System.out.println(tempDate);
    }
    
    /*public void dateChanged(ValueChangeEvent event)
    {
        System.out.println("asdf2");
        //HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        
        //Object tempDate = event.//request.getParameter("userForm:calendarForRegistration");
        //System.out.println(tempDate);
    }*/
    
    public String createUser() {
        //System.out.println(user.getBirthday());
        
        //DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        Date date = null;
        
        
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String myDate = params.get("calendarDate");
        //System.out.println(tempDate);
        /*try {
            date = format.parse(tempDate);
        } catch (ParseException ex) {
            Logger.getLogger(UserCreation.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(date);
        //user.setBirthday();
        */
        if(!creationHelper.comparePasswords(user.getPassword(), passRepeat))
        {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nesutampa slaptazodziai", "Nesutampa slaptazodis"));
            return null;
        }
        
        if(creationHelper.isUserRegisteredYet(user.getMail()))
        {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Vartotojas tokiu elektroniniu paštu jau egzistuoja", ""));
            return null;
        }
        
        if (!conversation.isTransient()) {
        conversation.end();
        return PAGE_INDEX;
        }

        conversation.begin();

        if (conversation.isTransient()) {
            return PAGE_INDEX;
        }

        user.setSex(creationHelper.convertGenderFromNumbers(genderValue));
        user.setStatus("Kandidatas");
        creationHelper.create(user);
        return PAGE_CONFIRM;
        
    }

    public String ok() {
        try {
            conversation.end();
            em.joinTransaction();
            em.flush();
            
            creationHelper.loginUser(user.getName(), user.getSurname(), user.getMail());
            
            return PAGE_INDEX;
        } catch (OptimisticLockException ole) {
            // Kažkas kitas buvo greitesnis...
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Vartotojas nesukurtas. Griskite atgal ir bandykite dar karta.", " "));
            return null;
        } catch (PersistenceException pe) {
            // Kitokios bėdos su DB
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Vartotojas nesukurtas. Griskite atgal ir bandykite dar karta.", " ")
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