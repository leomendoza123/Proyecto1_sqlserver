/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author LMariano
 */
import Logica.Statements;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Menu {

    public void showMenuPrincipal() {
        try {
            int opcion = -1;
            while (opcion != 4) {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(
                        "*****Menu de Opciones*****\n"
                        + "1)Clientes\n"
                        + "2)Partes\n"
                        + "3)Orden\n"
                        + "4)Salir\n"
                ));//Cambiar el titulo del cuadro
                switch (opcion) {
                    case 1:
                        ShowMenuCliente();
                        break;
                    case 2:
                        ShowMenuPartes();
                        break;
                    case 3:
                        ShowMenuOrden();
                        break;
                    case 4:
                        JOptionPane.showMessageDialog(null, "Hasta Luego");
                        System.exit(0);
                        break;
                }
            }
        } catch (Exception e) {
            System.exit(0);
        }
    }

    private void ShowMenuCliente() throws SQLException {
        int opcion = -1;
        JFrame principal = new JFrame();
        jdAgregarClientePersona jdAgregarPersona = new jdAgregarClientePersona(principal, true);
        jdAgregarClienteOrganizacion jdAgregarOrg = new jdAgregarClienteOrganizacion(principal, true);
        jdListaClientes jdLista = new jdListaClientes(principal, true);
        int op;
        while (opcion != 5) {
            opcion = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            "*****Menu Cliente*****\n"
                            + "1)Insertar nuevos clientes\n"
                            + "2)Modificar un cliente viejo\n"
                            + "3)Suspender un cliente\n"
                            + "4)Listar Clientes\n"
                            + "5)Volver\n"
                    ));//Cambiar el titulo del cuadro
            switch (opcion) {
                case 1:
                    op = Integer.parseInt(JOptionPane.showInputDialog(
                            "1)Insertar Persona\n"
                            + "2)Insertar Organizacion"));
                    if (op == 1) {
                        jdAgregarPersona.setVisible(true);
                        jdAgregarPersona.toFront();
                    }
                    if (op == 2) {
                        jdAgregarOrg.setVisible(true);
                        jdAgregarOrg.toFront();
                    }
                    break;
                case 2:
                    try {
                        op = Integer.parseInt(JOptionPane.showInputDialog(
                                "1)Modificar Persona\n"
                                + "2)Modificar Organizacion"));
                        if (op == 1) {
                            String cedula = JOptionPane.showInputDialog(
                                    "Ingrese la cedula de la persona a modificar");
                            if (Statements.existePersona(cedula)) {
                                jdModificarPersona jdModPersona = new 
        jdModificarPersona(principal, true,cedula);
                                jdModPersona= new jdModificarPersona(principal,
                                        true, cedula);
                                jdModPersona.setVisible(true);
                                jdModPersona.toFront();
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "Cliente no existe");
                            }
                        }
                        if (op == 2) {
                            String cedula = JOptionPane.showInputDialog(
                                    "Ingrese la cedula de la organizacion a modificar");
                            if (Statements.existeOrg(cedula)) {
                                jdModificarOrganizacion modOrg = new 
        jdModificarOrganizacion(principal, true,"");
                                modOrg= new jdModificarOrganizacion(principal,
                                        true, cedula);
                                modOrg.setVisible(true);
                                modOrg.toFront();
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "Cliente no existe");
                            }
                        }
                    } catch (Exception e) {
                    }
                    break;
                case 3:
                    try {
                        op = Integer.parseInt(JOptionPane.showInputDialog(
                                "1)Suspender Persona\n"
                                + "2)Suspender Organizacion"));
                        if (op == 1) {
                            String cedula = JOptionPane.showInputDialog(
                                    "Ingrese la cedula de la persona a suspender");
                            if (Statements.existePersona(cedula)) {
                                Statements.suspenderPersona(cedula);
                                    JOptionPane.showMessageDialog(null,
                                        "Cliente Suspendido");
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "Cliente no existe");
                            }
                        }
                        if (op == 2) {
                            String cedula = JOptionPane.showInputDialog(
                                    "Ingrese la cedula de la organizacion a suspender");
                            if (Statements.existeOrg(cedula)) {
                                    Statements.suspenderOrg(cedula);
                                    JOptionPane.showMessageDialog(null,
                                        "Cliente Suspendido");
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "Cliente no existe");
                            }
                        }
                    } catch (Exception e) {
                    }
                    break;
                case 4:
                    jdLista.setVisible(true);
                    jdLista.toFront();
                    break;
            }
        }
    }

    private void ShowMenuPartes() {
        int opcion = -1;
        JFrame principal = new JFrame();
        while (opcion != 7) {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(
                    "*****Menu Partes*****\n"
                    + "1)Insertar nuevas partes\n"
                    + "2)Borrar una parte\n"
                    + "3)Asociar partes con proveedores\n"
                    + "4)Asociar partes con tipos de automóviles\n"
                    + "5)Actualizar precios de una parte ofrecida por un"
                    + " proveedor\n"
                    + "6)Listar partes por tipos de automóviles\n"
                    + "7)Volver\n"
            ));//Cambiar el titulo del cuadro
            switch (opcion) {
                case 1:
                    jdIAgregarParte jdAgregar = new jdIAgregarParte(principal, true);
                    jdAgregar.setVisible(true);
                    jdAgregar.toFront();
                    break;
                case 2:
                    jdBorrarParte jdBorrar = new jdBorrarParte(principal, true);

                    jdBorrar.setVisible(true);
                    jdBorrar.toFront();
                    break;
                case 3:
                    jdAsociarParteProovedor jdAPP = new jdAsociarParteProovedor(principal, true);
                    jdAPP.setVisible(true);
                    jdAPP.toFront();
                    break;
                case 4:
                    jdAsociarParteAutomovil jdAPA = new jdAsociarParteAutomovil(principal, true);
                    jdAPA.setVisible(true);
                    jdAPA.toFront();
                    break;
                case 5:
                    jdActualizarPrecioProovedor jdActualizar = new jdActualizarPrecioProovedor(principal, true);
                    jdActualizar.setVisible(true);
                    jdActualizar.toFront();
                    break;
                case 6:
                    jdListarParteXAutomovil jdListar = new jdListarParteXAutomovil(principal, true);
                    jdListar.setVisible(true);
                    jdListar.toFront();
                    break;
            }
        }
        /*
         Insertar nuevas partes (además de los datos de parte, se incluye la 
         marca y  el fabricante, pero no proveedores).
         Borrar una parte (no borrar si participa en una orden). 
         Asociar partes con proveedores (incluir información de costos). 
         Asociar partes con tipos de automóviles. 
         Actualizar precios de una parte ofrecida por un proveedor. 
         Listar partes por tipos de automóviles: dado modelo y año de 
         manufactura, obtener los datos de las partes que corresponden. 
         */
    }

    private void ShowMenuOrden() {
        int opcion = -1;
        while (opcion != 4) {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(
                    "*****Menu Partes*****\n"
                    + "1)Localizar proveedores para una partes\n"
                    + "2)Insertar una nueva orden\n"
                    + "3)Asociar a una orden la compra de una cantidad de partes"
                    + " obtenidas de cierto proveedor.\n"
                    + "4)Volver\n"
            ));//Cambiar el titulo del cuadro
            switch (opcion) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        }
        /*
         Localizar proveedores para una parte (se da el nombre de la parte y se
         obtienen los nombres e identificadores de los proveedores).
         Insertar una nueva orden para un cliente dado y una fecha dada.
         Asociar a una orden la compra de una cantidad de partes obtenidas de 
         cierto proveedor.
         */
    }

    private void suspenderCliente() {
        int cedula = Integer.parseInt(
                JOptionPane.showInputDialog("Digite la cedula"));
        //Si la query encuentra la cedula retorna "Cliente x-xxxx suspendido"
        //Else No se encontro cliente
    }

}
