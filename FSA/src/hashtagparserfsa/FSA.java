/*
 * Finite State Automata (Automata de Estado Finito) 
 */
package hashtagparserfsa;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author egtor
 */
public class FSA {
    public class Estado{
        private char Elemento;
        private ArrayList<Estado> transiciones;
        private boolean Aceptacion = false;
       
       /**
        * Constructor
        * @param elemento
        * @param aceptacion 
        */ 
       public Estado(char elemento,boolean aceptacion){
          this.transiciones = new ArrayList<Estado>();
          this.Aceptacion = aceptacion; 
          this.Elemento = elemento;
       }
       public char getElemento(){
           return Elemento;
       }
       public void setElemento(char elemento){
           this.Elemento = elemento;
       }
       public ArrayList<Estado> getTransiciones(){
           return this.transiciones;
       }
       public void setAceptacion(boolean estado){
           this.Aceptacion = estado;
       }
       public boolean getAceptacion(){
           return Aceptacion;
       }
        /**
         * Inserta una nueva transición con el estado "nuevo"
         * @param nuevo
         */
        public void crearTransicion(Estado nuevo){
           this.transiciones.add(nuevo);
        }
        public void imprimirEstado(){
            System.out.println("Estado: ");
            System.out.println("Elemento:  " + this.Elemento);
            System.out.println("Aceptación: " + this.Aceptacion);
        }
        public void imprimirTransiciones(){
            for(Estado actual: transiciones){
                actual.imprimirEstado();
            }
        }
    }
    Estado Inicial;
    public FSA(){
        Inicial = new Estado('#',false);
    }
    public boolean insertarPalabra(String palabra){
       char[] caracteres = palabra.toCharArray();
       Estado estado_actual = Inicial;
       Estado insertando;
       ArrayList<Estado> transiciones;
       boolean insertado;
       boolean encontrado;
       for(int i = 0;i<caracteres.length;i++){
           insertado = false;
           encontrado = false;
           transiciones = estado_actual.getTransiciones();
           while(!insertado && !encontrado){
               for( Estado actual : transiciones){
                   //el elemento ya está en la lista, se comprueba si es necesario cambiar el estado de aceptacion 
                  if(actual.getElemento() == caracteres[i]){
                      if(i >= caracteres.length - 1){
                          actual.setAceptacion(true);
                      }
                      estado_actual = actual;
                      encontrado = true;
                  }
               }
                  //se terminaron las posibles trancisiones y no encontro el elemento, entonces se inserta
                  if (!encontrado) {
                      if(i < caracteres.length - 1){
                          insertando = new Estado(caracteres[i],false);
                      }
                      else{
                          insertando = new Estado(caracteres[i],true);  
                      }
                      estado_actual.crearTransicion(insertando);
                      estado_actual = insertando;  
                      insertado = true;
                  }   
            }
       }
       return true; 
    }
    public boolean buscarPalabra(char [] caracteres){
       Estado estado_actual = Inicial;
       ArrayList<Estado> transiciones;
       boolean encontrado;
       boolean busquedaTerminada = false;
       boolean resultado = false;
       for(int i = 0;i<caracteres.length;i++){
           encontrado = false; 
           transiciones = estado_actual.getTransiciones();
           while(!encontrado && !busquedaTerminada){
               for( Estado actual : transiciones){
                  //el caracter buscado está en lista de estados posibles
                  if(actual.getElemento() == caracteres[i]){
                      encontrado = true;
                      if(actual.getAceptacion() && (i == caracteres.length - 1)){
                          resultado = true;
                      }
                      else{
                          estado_actual = actual;
                      }
                  }
               }
                  //se terminaron las posibles trancisiones y no encontro el elemento
                  if(encontrado == false){
                      busquedaTerminada = true;
                  }
                  
            }
       }
       return resultado; 
    }
    public void imprimirFSA(){
        imprimirFSAR(Inicial,0);
    }
    private void imprimirFSAR(Estado estado,int nivel){       
        ArrayList<Estado> trasiciones = estado.getTransiciones();
        System.out.println("Nivel: " + nivel);
        estado.imprimirEstado();
        if(trasiciones.size() > 0){
            for(Estado siguiente : trasiciones){
                imprimirFSAR(siguiente, nivel + 1);
            }
        }
    }
}
