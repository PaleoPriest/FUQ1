/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import org.json.JSONObject;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.inject.Named;

/**
 *
 * @author Rugile
 */

@Named
public class FBRegister extends HttpServlet{

    private static final long serialVersionUID = 1L;

    @EJB
    private FBRegisterHelper fBLoginHelper;
        
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
        String code = req.getParameter("code");
        if (code == null || code.equals("")) {
            // an error occurred, handle this. Kazkoks grazinimas?
            errorMessage("Klaida arba nepatvirtinote Facebook", res);
        }

        String token = null;
        try {
            String g = "https://graph.facebook.com/oauth/access_token?client_id=217641221951231&redirect_uri=" + URLEncoder.encode("http://localhost:8080/FUQ2/FBLogin.do", "UTF-8") + "&client_secret=331f67448a5b4292e698529cffcca1e1&code=" + code;
            
            //System.out.println("sadf");
            
            URL u = new URL(g);
            URLConnection c = u.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
            String inputLine;
            StringBuffer b = new StringBuffer();
            while ((inputLine = in.readLine()) != null)
                b.append(inputLine + "\n");            
            in.close();
            token = b.toString();
            if (token.startsWith("{"))
                throw new Exception("error on requesting token: " + token + " with code: " + code);
        } catch (Exception e) {
                // an error occurred, handle this
                errorMessage("Gristame. Nepatvirtinote Facebook", res);
        }

        String graph = null;
        try {
            String g = "https://graph.facebook.com/me?fields=id,email,first_name,last_name,gender&" + token;
            URL u = new URL(g);
            URLConnection c = u.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
            String inputLine;
            StringBuffer b = new StringBuffer();
            while ((inputLine = in.readLine()) != null)
                b.append(inputLine + "\n");
            in.close();
            graph = b.toString();
        } catch (Exception e) {
                // an error occurred, handle this
                errorMessage("Nepatvirtinote Facebook. Gristama atgal", res);
        }
            

        String facebookId;
        String firstName;
        String lastName;
        String email;
        String gender;
        try {
            JSONObject json = new JSONObject(graph);

            facebookId = json.getString("id");
            firstName = json.getString("first_name");
            lastName = json.getString("last_name");
            email = json.getString("email");
            if (json.has("gender")) {
                String g = json.getString("gender");
                if (g.equalsIgnoreCase("female"))
                    gender = "Moteris";
                else if (g.equalsIgnoreCase("male"))
                    gender = "Vyras";
                else
                    gender = "Kita";
            } else {
                gender = "Kita";
            }
            
            Integer tempId = fBLoginHelper.isUserRegistered(facebookId);
            if(tempId!=null)
            {
                boolean isAdmin = fBLoginHelper.isUserAdmin(facebookId);
                fBLoginHelper.setSessionInfo(tempId, firstName, lastName, isAdmin);
                res.sendRedirect("index.html");
                return;
            }
            
            if(!fBLoginHelper.createUser(facebookId, firstName, lastName, email, gender))
            {
                System.out.println("Klaida");
                errorMessage("Vartotojas nesukurtas. Bandykite dar karta", res);
                return;
            }
            //klaida("Vartotojas nesukurtas. Nepatvirtinote Facebook.", res);
            res.sendRedirect("index.html");
            
        } catch (JSONException e) {
            // an error occurred, handle this
            errorMessage("Griztame atgal. Nepatvirtinote Facebook", res);
        } catch (NullPointerException npe){
            //System.out.println("asdf");
            errorMessage("Vartotojas nesukurtas. Nepatvirtinote Facebook.", res);
            //res.sendRedirect("index.html");
        } 
    }
    
    public void errorMessage(String message, HttpServletResponse res)throws IOException{
        PrintWriter out = res.getWriter();  
        res.setContentType("text/html");  
        out.println("<script type=\"text/javascript\">");
        String alert = "alert('" + message + "');";
        out.println(alert);
        out.println("location='index.html';");
        out.println("</script>");
    }
}