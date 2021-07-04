/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class BankImpl extends UnicastRemoteObject implements Bank {
    
     BankImpl()throws RemoteException{
        super();
    
    }
     

public int passwordchecker(String passme)throws RemoteException{
    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn;
        conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
        // Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb", "root", "");
        Statement st = (Statement) conn.createStatement();
        ResultSet result = st.executeQuery("select * from user where password='" + passme + "'");

        while (result.next()) {
            String role=result.getString("role");
            if(role.equals("admin")){
                return 1;
            
            }
            else{
                return 2;
            }

            

        }

    } catch (Exception e) {
        e.printStackTrace();

    }
    return 0;
    
}
public double balance(String passme)throws RemoteException{
    double balance=0;
    
    

    
    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn;
        conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
        // Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb", "root", "");
        Statement st = (Statement) conn.createStatement();
        ResultSet result = st.executeQuery("SELECT balance FROM user WHERE password='" + passme + "'");

        while (result.next()) {

            balance = result.getDouble("balance");

        }
        return balance;

    } catch (Exception e) {
        e.printStackTrace();
        return 8888;

    }
     
     
    
    
}
public double deposite(String passme,double amount){
    
    
    
    try{
          Class.forName("com.mysql.jdbc.Driver");
         Connection conn;
         conn= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
          Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb", "root", "");
          Statement st= (Statement) conn.createStatement();
          ResultSet result=st.executeQuery("SELECT balance FROM user WHERE password='"+passme+"'");
          
         while (result.next()) {
            double balance = result.getDouble("balance");

            double updated_balance = balance + amount;
            st.executeUpdate("UPDATE user SET balance ='" + updated_balance + "' WHERE password='"+passme+"'");
            return updated_balance;
        }
           
          
          
      }catch(Exception e){
          e.printStackTrace();
                     
           
      }
   
   
   return 88888;
}
public double transfer(String passme,double money,int account){
    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn;
        conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb", "root", "");
        Statement st = (Statement) conn.createStatement();
        Statement st1 = (Statement) conn.createStatement();
        ResultSet result = st.executeQuery("SELECT balance FROM user WHERE password='" + passme + "'");
        result.next();
        double balance1 = result.getDouble("balance")-money;
        
        if(balance1<0){
             return 0.0;
        }
       
        
        result.close();
        
        
        ResultSet transferdeTo = st1.executeQuery("SELECT balance FROM user WHERE account='" + account + "'");
        transferdeTo.next();
        
        
//        while (result.next()) {
             
            st.executeUpdate("UPDATE user SET balance='" + balance1 + "' WHERE password='" + passme + "'");
//            while (transferdeTo.next()) {
                double balance2 = transferdeTo.getDouble("balance");
                double transfered_balance = balance2 + money;
                st1.executeUpdate("UPDATE user SET balance='" + transfered_balance + "' WHERE account='" + account + "'");

//            }

//        }

    } catch (Exception e) {
        e.printStackTrace();

    }

return money;
}

public double withdraw(String passme,double ammount){
    
    
     try{
          Class.forName("com.mysql.jdbc.Driver");
         Connection conn;
         conn= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
          Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb", "root", "");
          Statement st= (Statement) conn.createStatement();
          ResultSet result=st.executeQuery("SELECT balance FROM user WHERE password='"+passme+"'");
          
         while (result.next()) {
            double balance = result.getDouble("balance");
             if (ammount>balance) {
                 return 0.0;
                 
             }

            double updated_balance = balance - ammount;
            st.executeUpdate("UPDATE user SET balance ='" + updated_balance + "' WHERE password='"+passme+"'");
            return updated_balance;
        }
           
          
          
      }catch(Exception e){
          e.printStackTrace();
                     
           
      }
    
    
    return 8888;
}
public String registration(String id,String fname,String lname, String password,String account, double balance,String role){
    try{
          Class.forName("com.mysql.jdbc.Driver");
         Connection conn;
         conn= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");

          PreparedStatement Statement =conn.prepareStatement("insert into user values(?,?,?,?,?,?,?)");
//          st.execute("INSERT into user (id,first_name,last_name,password,role,balance,account) values('"+id+"','"+fname+"','"+lname+"','"+password+"','"+role+"','"+balance+"','"+account+"')");
          Statement.setString(1, id);
          Statement.setString(2, fname);
          Statement.setString(3, lname);
          Statement.setString(4, password);
          Statement.setString(5, account);
          Statement.setDouble(6, balance);
          Statement.setString(7, role);
          int i=Statement.executeUpdate(); 
          conn.close();
          
      }catch(Exception e){
          e.printStackTrace();
                     
           
      }
    return "inserted";
    


}

public ArrayList display() throws RemoteException {
        
        ArrayList<User> al=new ArrayList();
        try{
            Class.forName("com.mysql.jdbc.Driver");
           com.mysql.jdbc.Connection con=(com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
           com.mysql.jdbc.Statement st=(com.mysql.jdbc.Statement) con.createStatement();
           ResultSet result=st.executeQuery("SELECT * FROM user");
           User user;
           while(result.next()){
               user=new User(result.getString(1), result.getString(2), result.getString(3), result.getString(5), result.getString(6), result.getString(7));
               al.add(user);
//               al.add(result.getString(1));
//               al.add(result.getString(2));
//               al.add(result.getString(3));
//               al.add(result.getString(5));
//               al.add(result.getString(6));
//               al.add(result.getString(7));
           
           }
            
        
        }catch(Exception e){
        }
        
        return al;
    }


public void delete(String account){
    
    try{
           Class.forName("com.mysql.jdbc.Driver");
           com.mysql.jdbc.Connection con=(com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
           com.mysql.jdbc.Statement st=(com.mysql.jdbc.Statement) con.createStatement();
           st.execute("DELETE FROM user where account ='"+account+"'");
       }catch(Exception e){
           
       }
}



}
