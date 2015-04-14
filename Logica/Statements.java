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

    public static boolean existePersona(String cedula) {
        try {
            Connection c = Conexion.conectar();
            String query = "select top 1 cedula from Persona where"
                    + " cedula='" + cedula + "';";
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int cont = 0;
            while (rs.next()) {
                cont += 1;
                break;
            }
            if (cont == 1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static boolean existeOrg(String cedula) {
        try {
            Connection c = Conexion.conectar();
            String query = "select top 1 cedula from Organizacion where"
                    + " cedula='" + cedula + "';";
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int cont = 0;
            while (rs.next()) {
                cont += 1;
                break;
            }
            if (cont == 1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static boolean modificarPersona(String cedula,
            String nombreCliente, String direccion, String ciudad,
            ArrayList<String> telefonos, int idCliente) {
        try {
            Connection c = Conexion.conectar();
            String query = "update Cliente "
                    + "set nombre='" + nombreCliente + "'"
                    + " where idCliente=" + idCliente;
            Statement stmt = c.createStatement();
            stmt.execute(query);
            System.out.println("name update");
            query = " update Direccion "
                    + "set direccion ='" + direccion + "'"
                    + ", ciudad='" + ciudad + "'"
                    + " where idCliente=" + idCliente;
            stmt = c.createStatement();
            stmt.execute(query);
            System.out.println("direc modificada");
            query = "Delete From Telefono "
                    + " where cedula=" + cedula;
            stmt = c.createStatement();
            stmt.execute(query);
            System.out.println("no problemo");
            System.out.println("telefonos borrados");
            for (int i = 0; i < telefonos.size(); i++) {
                query = "INSERT INTO Telefono(numTelefono,cedula) "
                        + " VALUES ('" + telefonos.get(i) + "','" + cedula + "');";
                stmt = c.createStatement();
                stmt.execute(query);
            }
            System.out.println("telefonos insertados");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean modificarOrganizacion(String cedula,
            String nombreOrg, String direccion, String ciudad,
            String nombreCon, String cargo, String telefono, int idCliente) {
        try {
            Connection c = Conexion.conectar();
            String query = "update Cliente "
                    + "set nombre='" + nombreOrg + "'"
                    + " where idCliente=" + idCliente;
            Statement stmt = c.createStatement();
            stmt.execute(query);

            query = "update Organizacion "
                    + "set cargo='" + cargo + "'"
                    + "set nombreEncargado='" + nombreCon + "'"
                    + "set telefono='" + telefono + "'"
                    + " where idCliente=" + idCliente;
            stmt = c.createStatement();
            stmt.execute(query);

            query = " update Direccion "
                    + "set direccion ='" + direccion + "'"
                    + ", ciudad='" + ciudad + "'"
                    + " where idCliente=" + idCliente;
            stmt = c.createStatement();
            stmt.execute(query);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

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
            System.out.println(idCliente);
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
        ResultSet rs = null;
        try {
            Connection c = Conexion.conectar();
            String query = "Select t.numTelefono,c.idCliente,c.estado,d.ciudad,"
                    + "d.direccion,c.nombre from Cliente as c "
                    + "inner join Persona as per "
                    + "On Per.idCliente=c.idCliente "
                    + "Inner join Direccion d "
                    + "On Per.idCliente=d.idCliente "
                    + "Inner join Telefono t "
                    + "On Per.cedula=t.cedula "
                    + " WHERE per.cedula='" + cedula + "';";
            Statement stmt = c.createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error");
        }
        return rs;
    }

    public static ResultSet obtenerOrganizacion(String cedula) {
        ResultSet rs = null;
        try {
            Connection c = Conexion.conectar();
            String query = "select c.idCliente,c.nombre,c.estado,d.ciudad,d.direccion,"
                    + "org.cargo,org.nombreEncargado,org.telefono "
                    + "from Proyecto_I.dbo.Cliente c "
                    + "Inner join Proyecto_I.dbo.Organizacion org "
                    + "On org.idCliente=c.idCliente "
                    + "Inner join Proyecto_I.dbo.Direccion d "
                    + "On org.idCliente=d.idCliente "
                    + " WHERE org.cedula='" + cedula + "';";
            Statement stmt = c.createStatement();
            rs = stmt.executeQuery(query);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al cargar datos");
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
                    + "	where T.anno = " + año + " and T.modelo = " + Revisiones.tamanoString(modelo, 15) + "";

            System.out.println(query);
            Statement stmt = c.createStatement();
            return stmt.executeQuery(query);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
