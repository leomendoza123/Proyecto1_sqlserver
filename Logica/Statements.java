/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author LMariano
 */
public class Statements {

    public static boolean InsertarPersona(int Cedula, String cedula,
            String nombreContacto, int estadoCliente, String nombreCliente,
            int telefonoContacto) {
        Connection c = Conexion.conectar();
        try {
            String query = "INSERT INTO Cliente(estado,nombre) "
                    + " VALUES ('INACTIVO','Satan');";
            /*
             String query="INSERT INTO table_name "
             + " VALUES (value1,value2,value3,...);"
             String query="INSERT INTO table_name "
             + " VALUES (value1,value2,value3,...);"
             **/
            Statement stmt = c.createStatement();
            stmt.execute(query);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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

    public static ResultSet getClientes() {
        Connection c = Conexion.conectar();
        try {
            String query = "";
            Statement stmt = c.createStatement();
            ResultSet x;
            x = stmt.executeQuery(query);
            return x;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static ResultSet getMarcas() {
        Connection c = Conexion.conectar();
        try {
            String query = "select * from dbo.Marca";
            Statement stmt = c.createStatement();
            ResultSet x = stmt.executeQuery(query);
            return x;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getProveedores() {
        Connection c = Conexion.conectar();
        try {
            String query = "select * from dbo.ProovedorN";
            Statement stmt = c.createStatement();
            ResultSet x = stmt.executeQuery(query);
            return x;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getParte() {
        Connection c = Conexion.conectar();
        try {
            String query = "select * from dbo.Parte";
            Statement stmt = c.createStatement();
            ResultSet x = stmt.executeQuery(query);
            return x;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getFabricantePartes() {
        Connection c = Conexion.conectar();
        try {
            String query = "select * from dbo.FabricanteParte";
            Statement stmt = c.createStatement();
            ResultSet x = stmt.executeQuery(query);
            return x;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean InsertarParte(String nombre, String marca, String fabricante) {
        Connection c = Conexion.conectar();
        try {
            String query = "INSERT INTO [dbo].[Parte]\n"
                    + "           ([nombreParte]\n"
                    + "           ,[marca]\n"
                    + "           ,[nombreFabricante])\n"
                    + "     VALUES\n"
                    + "           (" + Revisiones.tamanoString(nombre, 15) + "\n"
                    + "           ," + Revisiones.tamanoString(marca, 50) + "\n"
                    + "           ," + Revisiones.tamanoString(fabricante, 50) + ")";

            System.out.println(query);
            Statement stmt = c.createStatement();
            stmt.execute(query);
            return true;

        } catch (Exception e) {
            if (Revisiones.ErrorLlaveUnica("nombre estandar", e)) {
                return true;
            }
            e.printStackTrace();
            return false;
        }
    }

    public static boolean BorraPartePorNombre(String nombre) {
        Connection c = Conexion.conectar();
        try {
            String query = "DELETE FROM [dbo].[Parte]\n"
                    + "      WHERE nombreParte = '" + nombre + "'";

            System.out.println(query);
            Statement stmt = c.createStatement();
            stmt.execute(query);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean IsertarRelacionProveedorParte(String Porcentaje, String PrecioProveedor, String PrecioCliente, int Parte, String Proveedor) {
        Connection c = Conexion.conectar();
        try {
            String query = "INSERT INTO [dbo].[ParteXproveedor]\n"
                    + "           ([nombreProveedor]\n"
                    + "           ,[idParte]\n"
                    + "           ,[precioXproveedor]\n"
                    + "           ,[porcentajeGanancia]\n"
                    + "           ,[precioCliente])\n"
                    + "     VALUES\n"
                    + "           (" + Revisiones.tamanoString(Proveedor, 70) + "\n"
                    + "           ," + Parte + "\n"
                    + "           ," + PrecioProveedor + "\n"
                    + "           ," + Porcentaje + "\n"
                    + "           ," + PrecioCliente + ")";

            System.out.println(query);
            Statement stmt = c.createStatement();
            stmt.execute(query);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static ResultSet getTipoAutoMovil() {
        Connection c = Conexion.conectar();
        try {
            String query = "select * from dbo.TipoAutomovil";
            Statement stmt = c.createStatement();
            ResultSet x = stmt.executeQuery(query);
            return x;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static boolean IsertarRelacionProveedorAutomovil(int parte, int Automovil) {
        Connection c = Conexion.conectar();
        try {
            String query = "INSERT INTO [dbo].[TipoAutomovilXParte]\n"
                    + "           ([FK_Parte]\n"
                    + "           ,[FK_TipoAutomovil])\n"
                    + "     VALUES\n"
                    + "           (" + parte + "\n"
                    + "           ," + Automovil + ")";

            System.out.println(query);
            Statement stmt = c.createStatement();
            stmt.execute(query);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static ResultSet getProovedorXParte(int idParte) {
        Connection c = Conexion.conectar();
        try {
            String query = "SELECT P.nombre, PXP.precioXproveedor, PXP.precioCliente,PXP.porcentajeGanancia\n"
                    + "FROM ProovedorN as P\n"
                    + "INNER JOIN ParteXproveedor PXP\n"
                    + "ON PXP.nombreProveedor=P.nombre\n"
                    + "INNER JOIN PARTE Parte \n"
                    + "ON PXP.idParte = Parte.idParte\n"
                    + "where Parte.idParte = " + idParte + "";

            System.out.println(query);
            Statement stmt = c.createStatement();
            return stmt.executeQuery(query);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static boolean UpdateRelacionProveedorParte(String Porcentaje, String PrecioProveedor, String PrecioCliente, int Parte, String Proveedor) {
        Connection c = Conexion.conectar();
        try {
            String query = ""
                    + "UPDATE [dbo].[ParteXproveedor]\n"
                    + "   SET "
                    + "      [precioXproveedor] = " + PrecioProveedor + "\n"
                    + "      ,[porcentajeGanancia] = " + Porcentaje + "\n"
                    + "      ,[precioCliente] = " + PrecioCliente + "\n"
                    + " WHERE nombreProveedor = '" + Proveedor + "' AND idParte = " + Parte + "";

            System.out.println(query);
            Statement stmt = c.createStatement();
            stmt.execute(query);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static ResultSet getParteXTipoCarro(int año, String modelo) {
        Connection c = Conexion.conectar();
        try {
            String query = ""
                    + "SELECT P.idParte, P.nombreParte, T.idTipo\n"
                    + "	From Parte P\n"
                    + "	Inner Join TipoAutomovilXParte TXP\n"
                    + "	ON P.idParte = TXP.FK_Parte\n"
                    + "	INNER JOIN TipoAutomovil T\n"
                    + "	ON TXP.FK_TipoAutomovil = T.idTipo\n"
                    + "	where T.anno = "+año+" and T.modelo = "+Revisiones.tamanoString(modelo, 15)+"";

            System.out.println(query);
            Statement stmt = c.createStatement();
            return stmt.executeQuery(query);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    }
