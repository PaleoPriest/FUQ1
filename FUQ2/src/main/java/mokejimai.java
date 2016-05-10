
import java.util.Date;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.inject.Singleton;

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
    @ApplicationScoped
    @Singleton
    //@Stateless
    //@TransactionAttribute – transaction management
    //@PersistenceContext: PersistenContextType – short-term or long-term data cache
    //SynchronizationType – automatic or manual flushing to DB
    //@Version 
public class mokejimai {   
        
    ///////////////////////////////////////////
    //TEKSTAS PDF'UI
    String pavedimas = "PAVEDIMAS";
    String tekstas1 = " eur"; //ideti euro zenkla?
    String tekstas2 = "Suma:            ";
    String tekstas3 = "Mokėjimo ID:     ";
    String tekstas4 = "Gavėjo saskaita: ";
    String tekstas5 = "Data:            ";
    String tekstas6 = "Mokėtojas:       ";
    String tekstas7 = "Gavėjas:         ";
    //String pastabos = "Pastabos:        "; pastabos bus dedamos apacioje pdf'o
    Date data = new Date(); //siandsienos data reikia ideti
    String data_string = "";
    int suma_int = 2016;
    int ID = 0; //traukti is duombazes
    String gavejo_saskaita = "LT2016201620162016";
    String gavejas = "VasarnamiuNuoma";
    String moketojas = "Bulvė Ropė";
    String pastabos = "Nėra pastabų.";
    
    ///////////////////////////////////////////
    //RANDOM
    
    
    ///////////////////////////////////////////
    //FUNKCIJOS "MOKETI" -> REQUEST TYPE
    //get name tiktai? kaip suma ir visa kita redaguoti? is programos tiktai
    //get date?
    //get is DUOMBAZES ID
    //to PDF
    void ToPDF()
    {
        //xhtml change name
        ID++;
        data_string = data.toString();
    }
    
    
    ///////////////////////////////////////////
    //FUNKCIJOS "ZYMETI SUMOKEJUSIUS"
    //get names list
    //change status duombazeje myBool = !myBool;
    void Keisti_kas_sumokejo ()
    {
        //List<String> moketojai = new ArrayList<String>(); 
        //i sita lista sumesti ...kazkaip... per xhtml suzymetus vardus
        //pasiimti lentele useriu eiti per vardus ir keisti !boolean
    }
}
