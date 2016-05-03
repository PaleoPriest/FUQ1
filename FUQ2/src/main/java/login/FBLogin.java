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

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

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
public class FBLogin extends HttpServlet{
    
    @Override   //doesn't get called
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
        //System.out.println("asdf");
        
        String code = req.getParameter("code");
        if (code == null || code.equals("")) {
            // an error occurred, handle this. Kazkoks grazinimas?
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
        }

        String facebookId;
        String firstName;
        String lastName;
        String email;
        String gender;
        try {
            JSONObject json = new JSONObject(graph);
            
            //System.out.println("sadf");
            
            //System.out.println(json);
            facebookId = json.getString("id");
            //System.out.println(facebookId);
            firstName = json.getString("first_name");
            //System.out.println(firstName);
            lastName = json.getString("last_name");
            //System.out.println(lastName);
            email = json.getString("email");
            //System.out.println(email);
            if (json.has("gender")) {
                String g = json.getString("gender");
                if (g.equalsIgnoreCase("female"))
                    gender = "female";
                else if (g.equalsIgnoreCase("male"))
                    gender = "male";
                else
                    gender = "Unknown";
            } else {
                gender = "Unknown";
            }
            //System.out.println("sadf");
            //System.out.println(firstName);
            
        } catch (JSONException e) {
            // an error occurred, handle this
        }   
    }
    //add interaction with db, actual logging in. Save login status to db?
    //add redirect here?
    
}