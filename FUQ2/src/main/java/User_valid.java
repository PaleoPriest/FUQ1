
import DB_entities.Users;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.SynchronizationType;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Paulina
 */

@Stateless
public class User_valid {
    
    public String vardas;
    public String pavarde; 
    public String slapyvardis;
    public String slap1; 
    public String slap2;
    public String email;
    public String tel;
    public Object lytis;
    public Date gimimoDate;

    public User_valid() {
    }
    
    public User_valid(String vardas, String pavarde,  String slapyvardis, String slap1,
                String slap2, String email, String tel, Object lytis, Date gimimoDate)
    {
        this.vardas = vardas;
        this.pavarde = pavarde;
        this.slapyvardis = slapyvardis;
        this.slap1 = slap1;
        this.slap2 = slap2;
        this.email = email;
        this.tel = tel;
        this.lytis = lytis;
        this.gimimoDate = gimimoDate;
    } 
     
    @PersistenceContext(synchronization = SynchronizationType.UNSYNCHRONIZED)
    private EntityManager em;

    public void create(Users user) {
        em.persist(user);
    }
}
