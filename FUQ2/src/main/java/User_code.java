import javax.inject.Named;

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
public class User_code
{

	String username;
	String password;
	
	public String login(String userN, String pass)
	{
		//Get username & password from db
		if (username ==	userN && password == pass)
			;	//good user data, login user
		else
			return ("Wrong username or password has been entered.");
                return "";
	}
	
	public String addUser(String userN, String pass/*, String ...*/)
	{
		//insert all fields to db
		return checkForErrors(true/* or false*/, "Success of smth");	//insert done successfully
		
		/*if ()	//insert done successfully
			return ("New user created successful");
		else
			return ("error msg");*/
	}
	
	public String changeAccName(String username /*... fileds to change*/)	//change name / last name / nickname
	{
		/*
			Get user from db
			insert new fields to db
		*/
		return checkForErrors(true/* or false*/, "Success of smth");	//insert done successfully
	}
	
	public String changeAccImg(String username, Object img)
	{
		/*
			Get user from db
			insert new img to db
		*/
		return checkForErrors(true /*or false*/, "Success of smth");	//insert done successfully
	}
	
	
	
	
}