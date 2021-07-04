/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author user
 */

public interface Bank extends Remote {

    public int passwordchecker(String password) throws RemoteException;

    public double balance(String password) throws RemoteException;

    public double deposite(String password, double money) throws RemoteException;

    public double withdraw(String password, double money) throws RemoteException;
    
    public String registration(String id,String fname,String lname, String password,String account, double balance,String role) throws RemoteException;
    
    public double transfer(String password, double money,int account) throws RemoteException;
    public ArrayList display()throws RemoteException;
    public void delete(String account)throws RemoteException;
    
    
}
