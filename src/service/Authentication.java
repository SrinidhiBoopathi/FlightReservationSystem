/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.CustomerDAO;
import model.Admin;
/**
 *
 * @author srini
 */
public class Authentication {
    public static boolean verify(String email,String password){
        CustomerDAO custo=new CustomerDAO();

        String checkpassword =  custo.getPassword(email);
        //System.out.println("check="+checkpassword);

        if(checkpassword.equals(password)){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean adminverify(String username,String password){

        if(Admin.password.equals(password) && Admin.username.equals(username)){
            return true;
        }
        else{
            return false;
        }
    }

}
