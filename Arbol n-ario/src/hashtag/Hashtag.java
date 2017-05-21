/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashtag;

/**
 *
 * @author egtor
 */
public class Hashtag {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Diccionario diccionario = new Diccionario("palabras.txt");
        diccionario.imprimirDiccionario();
    }
    
}
