/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashtagparserfsa;

/**
 *
 * @author egtor
 */
public class HashtagParserFSA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Diccionario dic = new Diccionario();
       dic.cargarPalabras("diccionario.txt");
       dic.Automata.buscarPalabra("noticia".toCharArray());
       System.out.println(dic.Automata.buscarPalabra("morado".toCharArray()));
       dic.procesarHashtag("buenasnoticias");
       dic.procesarHashtag("vamoscontodo");
       
    }
    
}
