
import java.util.Date;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

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
    @RequestScoped // @SessionScoped
    @Stateful
    
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
    int suma_int = 2016;
    int ID; //traukti is duombazes
    String gavejo_saskaita = "LT2016201620162016";
    String gavejas = "VasarnamiuNuoma";
    String moketojas = "Bulvė Ropė";
    String pastabos = "Nėra pastabų.";
    
    ///////////////////////////////////////////
    //RANDOM
    
    
    ///////////////////////////////////////////
    //FUNKCIJOS "MOKETI"
    //get name tiktai? kaip suma ir visa kita redaguoti? is programos tiktai
    //get date?
    //get is DUOMBAZES ID
    //to PDF
    
    ///////////////////////////////////////////
    //FUNKCIJOS "ZYMETI SUMOKEJUSIUS"
    //get names list
    //change status duombazeje myBool = !myBool;
    
    //DEL DUOBAZES PAKEITIMU reikia bool ideti ir ID
    
    /*
    MOKEJIMAI FUNKCIJOS:
    >patikrinti suzymetas varneles (is xhtml)
    >sugeneruoti pdf
    >show in new window & activate "back button"
    ------
    >kai mygtukas payed pushed
    is tam tikro xhtml su listu nariu paiimti info, kurios varneles pazymetos
    >ar tikrai? -yes/no: return with emtied
                         return with nothing changed
    >gali atzymeti (mygtukas atzymejimui kitoj vietoj? kaip patogiau?)
    -------
    duombaze: narys dar turi vieta su "payed" boolean
    */ 
}
