
import DB_entities.Users;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
public class Paskyra {
    @PersistenceContext
    private EntityManager em;
    private Users user;
    
    /*
    private string[7] userString;
    private obj userLytis;
    private Date userDate;
    public boolean anketaPildyti(string vardas, string pavarde, 
                        string slapyvardis, string slap1, 
                        string slap2, string email, 
                        string tel, obj lytis, Date gimimoDate)
    {
        userString[0] = vardas;
        userString[1] = pavarde;
        userString[2] = slapyvardis;
        userString[3] = slap1;
        userString[4] = slap2;
        userString[5] = email;
        userString[6] = tel;
        userLytis = lytis;
        userDate = gimimoDate;
        if (tikrinimasAnketos())
        {
            em.persist(user);
            return true;
        }
        return false;
    }
    
    private boolean tikrinimasAnketos()
    {
        if ((userString[0] == NULL)&&(userString[1] == NULL)
            &&(userString[2] == NULL)&&(userString[3] == NULL)
            &&(userString[4] == NULL)&&(userString[5] == NULL
            &&(userString[6] == NULL))) //string empty, string null....?
        {
            return false;
        }
        if (userString[3] != userString[4])
        {
            return false;
        }
        //if(...)...
        return true;
    }
    
    */
    
    public String hi()
    {
        return "hi";
    }
}
