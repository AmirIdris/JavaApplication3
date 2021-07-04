/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author user
 */
public class Server {
    public static void main(String args []){
        
        try{
            Bank obj=new BankImpl();
            Registry reg=LocateRegistry.createRegistry(1000);
            reg.rebind("StudentProfile", obj);
            System.out.println("Server waiting.......");
       
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
    
}
