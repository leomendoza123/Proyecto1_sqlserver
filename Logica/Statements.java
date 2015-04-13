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
