/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashtagparserfsa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author egtor
 */
public class Diccionario {
ArrayList<ArrayList<Tupla<char[],Integer>>> Posibilidades;
FSA Automata;
public Diccionario(){
    Posibilidades = new ArrayList<>(); 
    Automata = new FSA();
}


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
 public void procesarHashtag(String hashtag){
    Posibilidades.clear();
    ArrayList<Tupla<char[],Integer>> PalabrasEncontradas;
    char[] caracteres;
    PalabrasEncontradas = new   ArrayList<Tupla<char[],Integer>>();
    caracteres = hashtag.toCharArray();
    procesarHashtagR(PalabrasEncontradas, caracteres);
    //String respuesta = evaluarPosibilidades();
    evaluarPosibilidades();
 }
 
 private int procesarHashtagR(ArrayList<Tupla<char[],Integer>> PalabrasEncontradas, char[] Resto){
     boolean palabraEncontrada = false;
     boolean alMenosUnaPalabra = false;
     Tupla<char[],Integer> palabra;
     ArrayList<Tupla<char[],Integer>> listaNueva = null;
     //evalua las posibles palabars desde la posición 0 a j
         for(int j = 0;j<=Resto.length;j++){
             palabraEncontrada = false;
             char [] evaluando = cortarListaChar(Resto, j);
             palabraEncontrada = Automata.buscarPalabra(evaluando);
             if(palabraEncontrada){
               alMenosUnaPalabra = true;
               palabra = new Tupla <char[], Integer>(evaluando,1);
               listaNueva = nuevaLista(PalabrasEncontradas,palabra);
               if(j < Resto.length - 1){
                procesarHashtagR(listaNueva, cortarListaCharI(Resto, j, Resto.length));
               }
               else{
                Posibilidades.add(listaNueva);
                }
            }
         }
    //si no encontro al menos una palabra se poner un peso negativo a ese caracter
    if(!alMenosUnaPalabra){
        char [] Resto1 = new char[1];
        Resto1 [0] = Resto [0];
        palabra = new Tupla<char[],Integer>(Resto1,-1);
        listaNueva = nuevaLista(PalabrasEncontradas,palabra);
        if(Resto.length > 1){
            procesarHashtagR(listaNueva,cortarListaCharI(Resto,1,Resto.length));
        }
        else{
            Posibilidades.add(listaNueva);
        }
    }    
         
    return 0; 
 }
 private String evaluarPosibilidades(){
     ArrayList<Tupla<char[], Integer>> max = null;
     Integer maxPeso = -10000;
     for(ArrayList<Tupla<char[], Integer>> subLista : Posibilidades){
         Integer contador = 0;
         for(Tupla<char[],Integer> tupla : subLista){
             contador += tupla.t; 
         }
         if(contador > maxPeso){
             maxPeso = contador;
             max = subLista;
         }
     }
     imprimirSubLista(max,maxPeso);
     return "test";
 }
 public void imprimirSubLista(ArrayList<Tupla<char[],Integer>> sublista,int peso){
     for(Tupla<char[],Integer> item: sublista){
         System.out.print(item.u);
         System.out.print(" ");
     }
     System.out.println("|| Peso: " + peso);
 }
 private char[] cortarListaChar(char[] lista,int corte){
     char[] resultado = new char[corte];
     System.arraycopy(lista, 0, resultado, 0, corte);
     return resultado;
 }
 private char[] cortarListaCharI(char[] lista,int i, int corte){
    char[] resultado = new char[corte-i];
    System.arraycopy(lista, i, resultado, 0, corte-i);
    return resultado; 
 }
private ArrayList<Tupla<char[],Integer>> nuevaLista(ArrayList<Tupla<char[],Integer>> lista, Tupla<char[],Integer> nuevo){
    ArrayList<Tupla<char[],Integer>> lista2;
    lista2 = new ArrayList<Tupla<char[],Integer>>(lista.size()+1);
    for (Tupla<char[],Integer> item : lista){
        Tupla<char[],Integer> tupla = new Tupla<char[],Integer>(item.u,item.t);
        lista2.add(tupla);
    }
    lista2.add(nuevo);
    return lista2;
}
}
