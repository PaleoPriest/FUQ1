
import login.User_valid;
import DB_entities.Users;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private Users user = new Users();

    List<User_valid> userString = new ArrayList<User_valid>();
    
    /*public boolean anketaPildyti(String vardas, String pavarde, String slapyvardis, String slap1, 
                    String slap2, String email, String tel, Object lytis, Date gimimoDate)
    {
        User_valid user = new User_valid(vardas, pavarde, slapyvardis, slap1, slap2, email, tel, lytis, gimimoDate);
        if (tikrinimasAnketos(user))
        {
            em.persist(user);
            return true;
        }
        return false;
    }
    
    private boolean tikrinimasAnketos(User_valid user)
    {
        if ((user.vardas == null) && (user.pavarde == null) && (user.slapyvardis == null)
            && (user.slap1 == null) && (user.slap2 == null) && (user.email == null)
            && (user.tel == null) && (user.lytis == null) && (user.gimimoDate == null))
                return false;
            
        if (user.slap1 != user.slap2)
            return false;
        
        return true;
    }*/
}
