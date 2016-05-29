import DB_entities.*;
import dao.*;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;


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
    
    private static final String PAGE_INDEX = "vasarnamiai_actions?faces-redirect=true";
    private static final String PAGE_EDIT = "vasarnamiai_edit?faces-redirect=true";
    
    @PersistenceContext(type = PersistenceContextType.EXTENDED, synchronization = SynchronizationType.UNSYNCHRONIZED)
    private EntityManager em;
    
    @Inject
    private SummerhouseDAO summerhouseDAOImpl;
    
    private List<Summerhouse> summerhouseList = new ArrayList<Summerhouse>();
    
    private Summerhouse newSummerhouse;
    
    private List<Summerhouse> filteredSummerhouseList = new ArrayList<>();
    
    private String searchName;
    
    private int searchMinRooms;
    
    private int newReservation;
    
    private int editID;
    
    private GregorianCalendar startDate;
    
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd");
    
    private String stringDate;
    
    private int dayOfTheWeek;
    
    public Summerhouse_edit()
    {
        //summerhouseList = summerhouseDAOImpl.getSummerhouseList();
    }
    
    @PostConstruct
    public void init()
    {
        startDate = (GregorianCalendar)GregorianCalendar.getInstance();
        System.out.println(startDate.getTime());
        dayOfTheWeek = startDate.get(Calendar.DAY_OF_WEEK);
        System.out.println(dayOfTheWeek);
        
        dateFormat.setCalendar(startDate);
        stringDate = dateFormat.format(startDate.getTime());
        
        if(dayOfTheWeek > 1)
        {
            startDate.add(GregorianCalendar.DATE, 9-dayOfTheWeek);
        }
        else
        {
            startDate.add(GregorianCalendar.DATE, 1);
        }
        
        System.out.println(startDate.getTime());
        
        setEditID(0);
        setNewSummerhouse(new Summerhouse());
        summerhouseList.add(new Summerhouse(1, "aaa", "ad", 2, 0));
        summerhouseList.add(new Summerhouse(2, "bbb", "bd", 3, 0));
        summerhouseList.add(new Summerhouse(3, "ccc", "cd", 4, 0));
        listCopy(summerhouseList, filteredSummerhouseList);
    }
    
    public String toEditPage()
    {
        setNewSummerhouse(new Summerhouse());
        return PAGE_EDIT; 
    }
    
    public String saveSummerhouse()
    {
        if(editID == 0)
        {
            newSummerhouse.setId(summerhouseList.get(summerhouseList.size()-1).getId()+1);
            summerhouseList.add(newSummerhouse);
            
            //summerhouseDAOImpl.insertSummerhouse(newSummerhouse);
        }
        else
        {
            int i = 0;
            for(Summerhouse sh : summerhouseList)
            {
                if(sh.getId() == editID)
                {
                    newSummerhouse.setId(editID);
                    summerhouseList.set(i, newSummerhouse);
                    
                    //summerhouseDAOImpl.updateSummerhouse(newSummerhouse);
                    
                    editID = 0;
                    break;
                }
                i++;
            }
        }
        setNewSummerhouse(new Summerhouse());
        listCopy(summerhouseList, filteredSummerhouseList);
        return PAGE_INDEX;
    }
    public String editSummerhouse(int summerhouseToEdit)
    {
        setEditID(summerhouseToEdit);
        return PAGE_EDIT;
    }
    public void deleteSummerhouse(Summerhouse summerhouseToDelete)
    {
        summerhouseList.remove(summerhouseToDelete);
        
        //summerhouseDAOImpl.deleteSummerhouse(summerhouseToDelete.getId());
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

    /**
     * @return the editID
     */
    public int getEditID() {
        return editID;
    }

    /**
     * @param editID the editID to set
     */
    public void setEditID(int editID) {
        this.editID = editID;
    }
    
    public void searchSummerhouses()
    {
        filteredSummerhouseList.clear();
        if(searchName.isEmpty() && searchMinRooms == 0)
        {
            filteredSummerhouseList = new ArrayList<Summerhouse>(summerhouseList);
        }
        else
        {
            for(Summerhouse sm : summerhouseList)
            {
                if((sm.getName().compareTo(searchName) == 0) || ((sm.getMaxRooms() >= searchMinRooms) && (searchMinRooms > 0)))
                {
                    filteredSummerhouseList.add(sm);
                }
            } 
        }
    }
    
    public void reserveSummerhouse(Summerhouse summerhouseToReserve)
    {
        //System.out.println("New: " + newReservation);
        //System.out.println("ResTime: " + summerhouseToReserve.getReservationTime());
        if(summerhouseToReserve.getReservationTime() == 0)
        {
            summerhouseToReserve.setReservationTime(newReservation);
            summerhouseToReserve.setReservationStart(startDate.getTime());
            //summerhouseToReserve.setReservationEnd
        }
        //System.out.println("New: " + newReservation);
        //System.out.println("ResTime: " + summerhouseToReserve.getReservationTime());
    }
    
    public String getEndDate(Summerhouse summerhouseToReserve)
    {
        if(summerhouseToReserve.getReservationTime() == 0)
        {
            return "Laisvas";
        }
        GregorianCalendar endDate = (GregorianCalendar)startDate.clone();
        endDate.add(GregorianCalendar.DATE, summerhouseToReserve.getReservationTime()*7);
        return dateFormat.format(endDate.getTime());
    }
    
    public void freeSummerhouse(Summerhouse summerhouseToFree)
    {
        summerhouseToFree.setReservationTime(0);
    }

    /**
     * @return the newReservation
     */
    public int getNewReservation() {
        return newReservation;
    }

    /**
     * @param newReservation the newReservation to set
     */
    public void setNewReservation(int newReservation) {
        this.newReservation = newReservation;
    }
    
    public String getSearchName() {
        return searchName;
    }

    /**
     * @param searchName the searchName to set
     */
    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    /**
     * @return the filteredSummerhouseList
     */
    public List<Summerhouse> getFilteredSummerhouseList() {
        return filteredSummerhouseList;
    }

    /**
     * @param filteredSummerhouseList the filteredSummerhouseList to set
     */
    public void setFilteredSummerhouseList(List<Summerhouse> filteredSummerhouseList) {
        this.filteredSummerhouseList = filteredSummerhouseList;
    }
    
    public void listCopy(List<Summerhouse> sourceList, List<Summerhouse> targetList)
    {
        targetList.clear();
        for(Summerhouse sm : sourceList)
        {
            targetList.add(sm);
        }
    }

    /**
     * @return the searchMinRooms
     */
    public int getSearchMinRooms() {
        return searchMinRooms;
    }

    /**
     * @param searchMinRooms the searchMinRooms to set
     */
    public void setSearchMinRooms(int searchMinRooms) {
        this.searchMinRooms = searchMinRooms;
    }

    /**
     * @return the startDate
     */
    public GregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(GregorianCalendar startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the stringDate
     */
    public String getStringDate() {
        return stringDate;
    }

    /**
     * @param stringDate the stringDate to set
     */
    public void setStringDate(String stringDate) {
        this.stringDate = stringDate;
    }
}