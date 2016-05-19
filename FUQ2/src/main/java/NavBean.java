
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

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
@RequestScoped
@Stateful
public class NavBean implements Serializable {
    private static final String SUMMERHOUSE_ACTIONS = "vasarnamiai_actions?faces-redirect=true";
    private static final String SUMMERHOUSE_LIST_PAGE = "vasarnamiai?faces-redirect=true";
    
    public String toSActions()
    {
        return SUMMERHOUSE_ACTIONS;
    }
    public String toSummerhouseListPage()
    {
        return SUMMERHOUSE_LIST_PAGE;
    }
}
