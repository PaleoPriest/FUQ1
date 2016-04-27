
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Paulina
 */
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
     
}
