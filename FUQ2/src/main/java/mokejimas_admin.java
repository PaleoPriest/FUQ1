
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    @Named
    @Stateless
/**
 *
 * @author Iron Frog
 */
public class mokejimas_admin {
    
    @PersistenceContext
    private EntityManager em;    
        
    boolean ar_admin ()
    {
        //duombazes_boolean = get_admin() to boolean;
        boolean duombazes_boolean = true;
        if (!duombazes_boolean)
        {
            return false;
        }
        return true;
    }
    
    List<Object> get_admin ()
    {
        //get id from xhtml
        int id = 2;
        Query uzklausa = em.createQuery("SELECT AdministratoriausBoolean FROM Users WHERE ID = :manoID").setParameter("manoID", id);
        return uzklausa.getResultList();
    }
    
}
