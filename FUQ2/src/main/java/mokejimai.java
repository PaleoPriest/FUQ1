
import DB_entities.Users;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Iron Frog
 */
    @Named
    @Stateless
    
public class mokejimai {
    ///////////////////////////////////////////
    //FUNKCIJOS "ZYMETI SUMOKEJUSIUS"
    //get names list
    //change status duombazeje myBool = !myBool;
    @PersistenceContext
    private EntityManager em;
    //List<String> vardai = new ArrayList<>();
    //int[] ids = int[n];
    int n = 100;
    
    public String spausdinti_vardus ()
    {
        //Users user = new Users();
        //List<Users> vartotojai = ArrayList<>();
        String spaudai = "~";
        Query uzklausa = em.createQuery("SELECT u.name, u.surname, u.pay, u.id FROM Users u");
        List<Users> vartotojai = uzklausa.getResultList();
        for (int i = 0; i < vartotojai.size(); i++)
        {
            spaudai = spaudai + vartotojai.get(i).getName() + vartotojai.get(i).getSurname() /*+ vartotojai.get(i).getPay().toString() + vartotojai.get(i).getId().toString() */+ "/n";
            //grazins su tarpais ar be? ar keisti boola?
        }
        //System.out.print(spaudai);
        return spaudai;
    }
    
    int[] IsChecked()
    {
        int[] ids = new int[n];
        //tipo istraukiame suzymetus vardus.... sudedam i lista
        ids[0] = 1;
        ids[1] = 2;
        return ids;
        //vardai.add("Petras Petraitis");
        //vardai.add("Bulve Rope");
        //return vardai;
    }
    void Keisti_kas_sumokejo ()
    {
        //Query uzklausa = em.createQuery("SELECT Name, Surname, Pay FROM Users");
        //List<Users> vartotojai = uzklausa.getResultList();
        int[] ids = IsChecked();
        int manoID;
        //String[] vard_pav;
        for (int i = 0; i < n; i++)
        {
            //vard_pav = vardai.get(i).split("\\s+");
            //query to check by id -> !bool
            //Users vartotojasID = new Users();
            //manoID = ids[i];
            Query uzklausa = em.createQuery("SELECT * FROM Users WHERE ID = :manoID").setParameter("manoID", ids[i]);
            List<Users> vartotojas = uzklausa.getResultList();
            vartotojas.get(0).setPay(!vartotojas.get(0).getPay());
            em.persist(vartotojas.get(0));
        }
        //List<String> moketojai = new ArrayList<String>(); 
        //i sita lista sumesti ...kazkaip... per xhtml suzymetus vardus
        //pasiimti lentele useriu eiti per vardus ir keisti !boolean
    }
}
