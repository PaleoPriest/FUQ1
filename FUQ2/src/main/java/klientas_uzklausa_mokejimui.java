
import java.util.Date;
import javax.ejb.Stateful;
import javax.enterprise.context.ConversationScoped;
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
@ConversationScoped
@Stateful
public class klientas_uzklausa_mokejimui {
            
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
    int ID = 2016; //tipo cia reik irasyti, nes tipo nurodo kad uz metini moka
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
        data_string = data.toString();
        System.out.println("");
    }
}
