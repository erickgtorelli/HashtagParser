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
     public char getCaracter(){
         return this.Caracter;
     }
     public void imprimirNodo(){
         System.out.println("Nodo: ");
         System.out.println("Caracter: " + this.Caracter);
         System.out.println("Peso: " + this.Probabilidad);
     }
     
    }
    
   Nodo Padre;
   String Archivo_de_palabras;
   
   public Diccionario(String Archivo){
       Padre = new Nodo('~',0);
       Archivo_de_palabras = Archivo;
       cargarPalabras();
   }
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
                      //Por ahora peso booleano
                      insertarPalabra(linea,1);
                  }
                }
                catch (Exception e)
                {
                  System.err.format("Exception occurred trying to read '%s'.", Archivo_de_palabras);
                }   
   } 
   private void insertarPalabra(String palabra,float peso){
       char[] caracteres = palabra.toCharArray();
       Nodo insertando;
       Nodo nodo_actual = Padre;
       boolean insertado; 
       for(int i = 0;i<caracteres.length;i++){
           insertado = false;
           while(!insertado){
               if(nodo_actual.getCaracter() != caracteres[i]){
                   if(nodo_actual.getHermanoDerecho() != null){
                       nodo_actual = nodo_actual.getHermanoDerecho();
                   }
                   //insertando en el nivel actual
                   else{
                       //Si NO es el final de la palabra
                       if(i < caracteres.length -1){
                        // Peso negativo porque no es una palabra
                       insertando = new Nodo(caracteres[i],-1);
                       Nodo nuevo_nivel = new Nodo('~',0);
                       }
                       else{
                       insertando = new Nodo(caracteres[i],peso);   
                       }
                       Nodo nuevo_nivel = new Nodo('~',0);
                       nodo_actual.setHermanoDerecho(insertando);
                       nodo_actual.getHermanoDerecho().setHijo(nuevo_nivel);
                       nodo_actual = nodo_actual.getHermanoDerecho().getHijo();
                       insertado = true;
                   }
               }
               else{
                   if(nodo_actual.getHijo() != null){
                       nodo_actual = nodo_actual.getHijo();
                   }
                   else{
                       Nodo nuevo_nivel = new Nodo('~',0);
                       nodo_actual.setHijo(nuevo_nivel);
                       nodo_actual = nodo_actual.getHijo();
                   }
                   insertado = true;
                   
               }
           }
       }
   }
   public int buscarPalabra(String palabra){
       return 0;
   }
   
   public void imprimirDiccionario(){
       imprimirDiccionarioR(Padre,0);
   }
   private void imprimirDiccionarioR(Nodo actual,int nivel){
       
       if(!(actual.getCaracter() == '~')){
           System.out.println("Nivel: " + nivel);
           actual.imprimirNodo();
        }
       if(actual.getHijo()!= null){
           imprimirDiccionarioR(actual.getHijo(), nivel + 1);
       }
       if(actual.getHermanoDerecho() != null){
           imprimirDiccionarioR(actual.getHermanoDerecho(),nivel);
       }
   }
}
