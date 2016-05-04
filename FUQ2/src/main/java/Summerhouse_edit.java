import DB_entities.*;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
//import javax.faces.bean.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.SynchronizationType;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shalifar
 */
@Named
@SessionScoped
@Stateful
public class Summerhouse_edit implements Serializable {
    
    private static final String PAGE_INDEX = "vasarnamiai?faces-redirect=true";
    private static final String PAGE_EDIT = "vasarnamiai_edit?faces-redirect=true";
    
    @PersistenceContext(type = PersistenceContextType.EXTENDED, synchronization = SynchronizationType.UNSYNCHRONIZED)
    private EntityManager em;
    
    private List<Summerhouse> summerhouseList = new ArrayList<Summerhouse>();
    
    private Summerhouse newSummerhouse;
    
    private Summerhouse editedSummerhouse;
    
    @PostConstruct
    public void init()
    {
        setNewSummerhouse(new Summerhouse());
        summerhouseList.add(new Summerhouse(1, "aaa", "ad", 2));
        summerhouseList.add(new Summerhouse(2, "bbb", "bd", 3));
        summerhouseList.add(new Summerhouse(3, "ccc", "cd", 4));
    }
    
    public String toEditPage()
    {
        return PAGE_EDIT; 
    }
    
    public String saveSummerhouse()
    {
        /*if(editedSummerhouse == null)
        {
            summerhouseList.add(newSummerhouse);
        }
        else
        {
            summerhouseList.set(summerhouseList.indexOf(editedSummerhouse), newSummerhouse);
            editedSummerhouse = null;
        }*/
        
        summerhouseList.add(newSummerhouse);
        
        //DB INSERT code
        
        return PAGE_INDEX;
    }
    public String editSummerhouse(Summerhouse summerhouseToEdit)
    {
        //editedSummerhouse = summerhouseToEdit;
        return PAGE_EDIT;
    }
    public void deleteSummerhouse(Summerhouse summerhouseToDelete)
    {
        summerhouseList.remove(summerhouseToDelete);
        
        //DB DELETE code
    }

    /**
     * @return the summerhouse_list
     */
    public List<Summerhouse> getSummerhouseList() {
        return summerhouseList;
    }

    /**
     * @param summerhouseList the summerhouse_list to set
     */
    public void setSummerhouseList(List<Summerhouse> summerhouseList) {
        this.summerhouseList = summerhouseList;
    }

    /**
     * @return the newSummerhouse
     */
    public Summerhouse getNewSummerhouse() {
        return newSummerhouse;
    }

    /**
     * @param newSummerhouse the newSummerhouse to set
     */
    public void setNewSummerhouse(Summerhouse newSummerhouse) {
        this.newSummerhouse = newSummerhouse;
    }
}