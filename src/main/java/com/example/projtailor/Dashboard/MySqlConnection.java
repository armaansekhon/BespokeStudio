package com.example.projtailor.Dashboard;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnection {

    public static Connection doconnect(){
     Connection con=null;
     try{
         Class.forName("com.mysql.cj.jdbc.Driver");
         con= DriverManager.getConnection("jdbc:mysql://localhost/projtailor","root","#Arman@1234");
     } catch (Exception exp) {
         exp.printStackTrace();
     }
        return con;
    }
    public static void main(String[] args)
    {
        if(doconnect()==null)
        {
            System.out.println("taar nahi judi") ;
        }
        else{
            System.out.println("taaran jud gayia");
        }
    }
}
