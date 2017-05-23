/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashtagparserfsa;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author egtor
 */
public class Diccionario {

public Diccionario(){
    
}
FSA Automata = new FSA();

public void cargarPalabras(String Archivo_de_palabras){
        BufferedReader br = null;
	FileReader fr = null;
        System.out.println("Cargando Diccionario...");
		try
                (BufferedReader reader = new BufferedReader(new FileReader(Archivo_de_palabras))) {
                  String linea;
                  while ((linea = reader.readLine()) != null)
                  {
                      Automata.insertarPalabra(linea);
                  }
                }
                catch (Exception e)
                {
                  System.err.format("Exception occurred trying to read '%s'.", Archivo_de_palabras);
                }   
   } 
    
}
