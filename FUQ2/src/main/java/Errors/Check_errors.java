package Errors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Paulina
 */
public class Check_errors
{
    public String checkForErrors(boolean check, String msg)
	{
		if (check == true)
			return (msg);
		else
			return ("error msg");
	}
}
