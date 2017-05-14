/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashtag;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author egtor
 */
public class Diccionario {
    
    private class Nodo{
     private char Caracter;
     private float Probabilidad = -1;
     private Nodo Hijo;
     private Nodo HermanoDerecho;
        
     public Nodo(char caracter, float probabilidad){
         this.Caracter = caracter;
         this.Probabilidad = probabilidad;
         this.Hijo = null;
         this.HermanoDerecho = null;
        }
     public void setHijo(Nodo Hijo){
         this.Hijo = Hijo; 
     }
     public Nodo getHijo(){
         return this.Hijo;
     }
     public void setHermanoDerecho(Nodo HermanoDerecho){
         this.HermanoDerecho = HermanoDerecho;
     }
     public Nodo getHermanoDerecho(){
         return this.HermanoDerecho;
     }
     public char getCaracter
     
    }
    
   Nodo Padre;
   String Archivo_de_palabras;
   /*
   * Carga el archivo de palabras y las va insertando en el diccionario
   */
   private void cargarPalabras(){
        BufferedReader br = null;
	FileReader fr = null;
        System.out.println("Cargando Diccionario...");
		try
                (BufferedReader reader = new BufferedReader(new FileReader(Archivo_de_palabras))) {
                  String linea;
                  while ((linea = reader.readLine()) != null)
                  {
                      //Aún no se toma en cuenta el peso de cada palabra
                      insertarPalabra(linea);
                  }

                }
                catch (Exception e)
                {
                  System.err.format("Exception occurred trying to read '%s'.", Archivo_de_palabras);
                }   
   } 
   public void insertarPalabra(String palabra){
       char[] caracteres = palabra.toCharArray();
       Nodo insertando;
       Nodo nodo_actual = Padre;
       boolean insertado = false; 
       for(int i = 0;i<caracteres.length;i++){
           insertando = new Nodo(caracteres[i],0);
           while(!insertado){
               if(nodo_actual.)
           }
       }
   }
   public void buscarPalabra(String palabra){
       
   }
}
