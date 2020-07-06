
package lexico;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Lexico extends JFrame implements ActionListener{

    JButton botonAnalisis = new JButton("Analizar");
    JTextField campoIngreso = new JTextField();
    JScrollPane panel = new JScrollPane();
    JScrollPane panelInterno = new JScrollPane();
    JTextArea areaSalida = new JTextArea();
    JLabel lbNombres = new JLabel("Daniela A. Martinez - Mateo Yate G. - Daniel A. Roa / Ciencias III");
    
    public static void main(String[] args) {
        
        String ruta = "D:/Usuarios/Mateo/Escritorio/Lexico/Lexico/src/lexico/Lexer.flex";
        generarLexer(ruta);
        
        Lexico lex = new Lexico();
        lex.setBounds(0, 0, 500, 500);
        lex.setTitle("Analizador Léxico de Condicionales - Ciencias III");
        lex.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        lex.setVisible(true);   
        
    }

    public static void generarLexer(String ruta) {
        
        File archivo = new File(ruta);
        JFlex.Main.generate(archivo);
    
    }
    
    public Lexico(){
        
        Container c = getContentPane();
        c.setLayout(null);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        
        c.add(botonAnalisis);
        c.add(campoIngreso);
        c.add(panel);
        c.add(areaSalida);
        c.add(lbNombres);
        
        botonAnalisis.addActionListener(this);
        botonAnalisis.setBounds(350, 25, 100, 30);
        botonAnalisis.setBackground(Color.GRAY);
        
        campoIngreso.setBounds(50, 25, 250, 30);
        
        lbNombres.setBounds(80, 5, 500, 10);
        
        panelInterno.setBounds(0, 250, 400, 2000);
        panelInterno.setPreferredSize(new Dimension(400, 2000));  
        panelInterno.setBackground(Color.LIGHT_GRAY);
        
        panel.setBounds(50, 70, 400, 350);
        panel.setPreferredSize(new Dimension(400, 350));
        panel.setBackground(Color.LIGHT_GRAY);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == botonAnalisis){
            
            panelInterno.removeAll();
//            

            File archivo = new File("archivo.txt");
            PrintWriter escribir;
            
            try {
                
                escribir = new PrintWriter(archivo);
                escribir.print(campoIngreso.getText());
                escribir.close();
                
            } catch (FileNotFoundException ex) {
                
                Logger.getLogger(Lexico.class.getName()).log(Level.SEVERE, null, ex);
                
            }
            
            try {

                //lectura archivo local archivo.txt
                Reader lector = new BufferedReader(new FileReader("archivo.txt"));

                //lectura archivo seleccionado
//                Reader lector = new BufferedReader(new FileReader(chooser.getSelectedFile()));
                Lexer lexer = new Lexer(lector);
                String resultado = "";
                while(true) {
                    Tokens tokens = lexer.yylex();
                    if(tokens == null){
                        resultado += "El ultimo token fue analizado...";
                        areaSalida.setText(resultado);
                        
                        areaSalida.setBounds(0, 0, 400, 2000);

                        panelInterno.add(areaSalida);
                        panelInterno.repaint();

                        panel.setViewportView(panelInterno);
                        
                        return;
                    }
                    switch(tokens){
                        case ERROR:
                            resultado += "Simbolo no identificado...";
                            break;
                        case Identificador: case Numero: case Reservada: case Delimitador: case Operador:
                            resultado += lexer.lecturaLexica + ": es: "+ tokens + "\n";
                            System.out.println(lexer.lecturaLexica);
                            break;
                        default:
                            resultado += "Token: "+ tokens + "\n";
                            break;

                    }
 
                }
                
            } catch (FileNotFoundException ex) {
                
                Logger.getLogger(Lexico.class.getName()).log(Level.SEVERE, null, ex);
            
            } catch (IOException ex) {
            
                Logger.getLogger(Lexico.class.getName()).log(Level.SEVERE, null, ex);
            
            }      
            
        }
        
    }
    
}
