/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import GUI.jdIAgregarParte;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Leo
 */
public class Revisiones {

    public static String tamanoString(String texto, int tamaño) throws Exception {
        if (texto.length() > tamaño) {
            JFrame warningFrame = new JFrame();
            JOptionPane.showMessageDialog(warningFrame, "El tamaño de '" + texto + "', no debería superar los " + tamaño + " caracteres");
            throw new Exception("tamaño del texto superior a " + tamaño);
        } else if (texto.length() == 0) {
            JFrame warningFrame = new JFrame();
            JOptionPane.showMessageDialog(warningFrame, "No se introdujeron los datos textuales requeridos");

        }

        return "'" + texto + "'";
    }


      
    public static int esEntero(String texto) throws NumberFormatException{
        try {
            return Integer.parseInt(texto);
        } 
        catch (NumberFormatException e) {

            JFrame warningFrame = new JFrame();
            JOptionPane.showMessageDialog(warningFrame, "Los datos numerales no son invalidos");
            throw e; 
        }

    }

    public static void ErrorSelecionDeListas(String listas) {
        JFrame warningFrame = new JFrame();
        JOptionPane.showMessageDialog(warningFrame, "Selecione los items correctamente en las litas  [" + listas + "]");
    }

    static boolean ErrorLlaveUnica(String Llaves, Exception e) {
        if (e.getMessage().indexOf("unique constraint") != 0) {
            JFrame warningFrame = new JFrame();
            JOptionPane.showMessageDialog(warningFrame, "Al menos uno de los siguientes campos ya esta siendo utilzado [" + Llaves + "]");
            return true;
        }
        return false;
    }

}
