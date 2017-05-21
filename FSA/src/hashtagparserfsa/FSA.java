/*
 * Finite State Automata (Automata de Estado Finito) 
 */
package hashtagparserfsa;

import java.util.ArrayList;

/**
 *
 * @author egtor
 */
public class FSA {
    public class Estado{
        char Elemento;
        ArrayList<Estado> transiciones;
        boolean Aceptacion = false;
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
    
    public boolean insertarPalabra(String palabra){
       return true; 
    }
}
