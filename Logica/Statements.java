/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logica;

import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author LMariano
 */
public class Statements {
    public  static boolean InsertarPersona(int Cedula,String cedula,
            String nombreContacto,int estadoCliente,String nombreCliente,
    int telefonoContacto){
        Connection c= Conexion.conectar();
        try{
            String query="INSERT INTO Cliente(estado,nombre) "
                    + " VALUES ('INACTIVO','Satan');";
            /*
            String query="INSERT INTO table_name "
                    + " VALUES (value1,value2,value3,...);"
            String query="INSERT INTO table_name "
                    + " VALUES (value1,value2,value3,...);"
            **/
            Statement stmt=c.createStatement();
            stmt.execute(query);
            return true;
        
        }catch(Exception e){e.printStackTrace();return false;}
        /*
            // Create and execute an SQL statement that returns some data.  
            String SQL = "SELECT Nombre, Carne FROM Estudiante";  
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery(SQL);
            // Iterate through the data in the result set and display it.  
            while (rs.next())  
            {  
               System.out.println(rs.getString(1) + " " + rs.getString(2));  
            }**/
                
    }
}
