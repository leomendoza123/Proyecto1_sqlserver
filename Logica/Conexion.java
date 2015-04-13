/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author LMariano
 */
public class Conexion {
    public static Connection conectar() 
       {
       Connection con=null;
       try  
       { 
            // Load the SQLServerDriver class, build the 
            // connection string, and get a connection 
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
            String connectionUrl = "jdbc:sqlserver://localhost;" + 
                                    "database=Proyecto_I;" + 
                                    "user=test;" + 
                                    "password=test"; 
            con = DriverManager.getConnection(connectionUrl); 
            /*
            // Create and execute an SQL statement that returns some data.  
            String SQL = "SELECT Nombre, Carne FROM Estudiante";  
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery(SQL);
            // Iterate through the data in the result set and display it.  
            while (rs.next())  
            {  
               System.out.println(rs.getString(1) + " " + rs.getString(2));  
            }
            **/
       }  
       catch(Exception e)  
       { 
            System.out.println("Error al conectarse a la base "+e); 
            System.exit(0);  
       }
       return con;
    }
    
}
