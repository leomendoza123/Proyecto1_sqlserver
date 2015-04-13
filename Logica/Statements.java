/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author LMariano
 */
public class Statements {

    public static boolean InsertarPersona(String cedula,
            String nombreCliente, String direccion, String ciudad,
            ArrayList<String> telefonos) {
        Connection c = Conexion.conectar();
        try {
            String query = "INSERT INTO Cliente(estado,nombre) "
                    + " VALUES ('INACTIVO','" + nombreCliente + "');";
            Statement stmt = c.createStatement();
            stmt.execute(query);

            query = "select top 1 idCliente from Cliente order by idCliente desc;";
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int idCliente = -1;
            while (rs.next()) {
                idCliente = Integer.parseInt(rs.getString(1));
            }

            query = "INSERT INTO Persona(cedula,idCliente) "
                    + " VALUES ('" + cedula + "'," + idCliente + ");";
            stmt = c.createStatement();
            stmt.execute(query);

            for (int i = 0; i < telefonos.size(); i++) {
                query = "INSERT INTO Telefono(numTelefono,cedula) "
                        + " VALUES ('" + telefonos.get(i) + "','" + cedula + "');";
                stmt = c.createStatement();
                stmt.execute(query);
            }

            query = "INSERT INTO Direccion(direccion,ciudad,idCliente) "
                    + " VALUES ('" + direccion + "','" + ciudad + "'," + idCliente + ");";
            stmt = c.createStatement();
            stmt.execute(query);
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean InsertarOrganizacion(String cedula,
            String nombreOrg, String direccion, String ciudad,
            String nombreCon, String cargo, String telefono) {
        Connection c = Conexion.conectar();
        try {
            String query = "INSERT INTO Cliente(estado,nombre) "
                    + " VALUES ('INACTIVO','" + nombreOrg + "');";
            Statement stmt = c.createStatement();
            stmt.execute(query);

            query = "select top 1 idCliente from Cliente order by idCliente desc;";
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int idCliente = -1;
            while (rs.next()) {
                idCliente = Integer.parseInt(rs.getString(1));
            }

            query = "INSERT INTO Organizacion(cedula,idCliente,cargo,"
                    + "nombreEncargado,telefono) "
                    + " VALUES ('" + cedula + "','" + idCliente + "','" + cargo + ""
                    + "','" + nombreCon + "','" + telefono + "');";
            stmt = c.createStatement();
            stmt.execute(query);

            query = "INSERT INTO Direccion(direccion,ciudad,idCliente) "
                    + " VALUES ('" + direccion + "','" + ciudad + "','" + idCliente + "');";
            stmt = c.createStatement();
            stmt.execute(query);
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static ResultSet obtenerPersona(String cedula) {
        ResultSet rs=null;
        try {
            Connection c = Conexion.conectar();
            String query = "select * from Cliente,Persona,Direccion,Telefono "
                    + " where Persona.cedula='" + cedula + "'";
            Statement stmt = c.createStatement();
            rs = stmt.executeQuery(query);
            int idCliente = -1;
            while (rs.next()) {
                System.out.println(rs.getString(1));//IDCLiente
                System.out.println(rs.getString(2));//Estado
                System.out.println(rs.getString(3));//nombre Cliente
                System.out.println(rs.getString(4));//CEdula
                
                System.out.println(rs.getString(6));//iddir
                System.out.println(rs.getString(7));//dir
                System.out.println(rs.getString(8));//ciudad
                
                System.out.println(rs.getString(10));//id tel
                System.out.println(rs.getString(11));//numtel
                
                System.out.println("----------");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ssssss");
        }
        return rs;
    }
    
    public static ResultSet obtenerOrganizacion(String cedula) {
        ResultSet rs=null;
        try {
            Connection c = Conexion.conectar();
            String query = "select * from Cliente,Organizacion,Direccion"
                    + " where Organizacion.cedula='" + cedula + "'"+
                     " ,Organizacion.cedula='" + cedula + "'";
            Statement stmt = c.createStatement();
            rs = stmt.executeQuery(query);
            int idCliente = -1;
            while (rs.next()) {
                System.out.println(rs.getString(1));//IDCLiente
                System.out.println(rs.getString(2));//Estado
                System.out.println(rs.getString(3));//nombre Cliente
                System.out.println(rs.getString(4));//CEdula
                System.out.println(rs.getString(5));
                System.out.println(rs.getString(6));//iddir
                System.out.println(rs.getString(7));//dir
                System.out.println(rs.getString(8));//ciudad
                System.out.println(rs.getString(9));
                System.out.println(rs.getString(10));//id tel
                System.out.println(rs.getString(11));//numtel
                System.out.println(rs.getString(12));
                System.out.println("----------");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ssssss");
        }
        return rs;
    }
}
