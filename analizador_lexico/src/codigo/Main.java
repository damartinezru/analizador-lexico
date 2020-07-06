/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.io.File;

/**
 *
 * @author Daniela Alexandra
 */
public class Main {
    public static void main(String[] args) {
        String ruta = "C:/Users/USUARIO/Desktop/Universidad-2020/2020-1/Ciencias-1/analizador_lexico/src/codigo/Lexer.flex";
        generarLexer(ruta);
        
    }
    public static void generarLexer(String ruta) {
        File archivo = new File(ruta);
        JFlex.Main.generate(archivo);
    }
}
