import DB_entities.*;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.SynchronizationType;


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
public class Summerhouse_edit {
    
    private String PAGE_INDEX = "vasarnamiai?faces-redirect=true";
    
    @PersistenceContext(type = PersistenceContextType.EXTENDED, synchronization = SynchronizationType.UNSYNCHRONIZED)
    private EntityManager em;
    
    private List<Summerhouse> summerhouseList = new ArrayList<Summerhouse>();
    
    private Summerhouse newSummerhouse;
    
    @PostConstruct
    public void init()
    {
        setNewSummerhouse(new Summerhouse());
        summerhouseList.add(new Summerhouse(1, "aaa"));
        summerhouseList.add(new Summerhouse(2, "bbb"));
        summerhouseList.add(new Summerhouse(3, "ccc"));
    }
    
    public void createSummerhouse()
    {
        newSummerhouse.setRoom(new Rooms());
        summerhouseList.add(newSummerhouse);
        
        //em.getTransaction().begin();
        //em.persist(newSummerhouse);
        //em.getTransaction().commit();
        
        //return PAGE_INDEX;
    }
    public void editSummerhouse()
    {
        
    }
    public void deleteSummerhouse()
    {
        
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
