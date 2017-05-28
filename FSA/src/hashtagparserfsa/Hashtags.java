/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashtagparserfsa;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author egtor
 */
public class Hashtags {
    private ArrayList<String> ColecionHashtags;
    private ArrayList<String> ColecionTweets;
    private String ArchivoTweets;
    private String ArchivoHashtags; 
    private long Lotes;
    FileInputStream InputStreamTweets = null;
    Scanner ScTweets = null;
    FileInputStream InputStreamHashtags = null;
    Scanner ScHashtags = null;
    Pattern HashtagPattern = Pattern.compile("(?:\\s|\\A)[##]+([A-Za-z0-9-_]+)");
    
    
    public Hashtags(String ArchivoTweets, int lotes) throws FileNotFoundException{
        this.ArchivoTweets = ArchivoTweets;
        this.ArchivoHashtags = "Hashtags.txt";
        this.Lotes = lotes; 
        InputStreamTweets = new FileInputStream(this.ArchivoTweets);
        ScTweets = new Scanner(InputStreamTweets, "UTF-8");
    }
    public ArrayList<String> obtenerHashtags() throws IOException{
        if(cargarTweets()){
        extraerHashtagsDeTweets();
        guardarHashtags();    
        }
        else{
            ColecionHashtags.clear();
        }
        return ColecionHashtags;
    }
    private boolean cargarTweets() throws IOException{
        long contador = 0;
        ColecionTweets.clear();
        try {
        while (ScTweets.hasNextLine() && contador < Lotes) {
            String line = ScTweets.nextLine();
            // System.out.println(line);
        }
        // note that Scanner suppresses exceptions
            } finally {
                if (InputStreamTweets != null) {
                    InputStreamTweets.close();
                }
                if (ScTweets != null) {
                    ScTweets.close();
                }
            }
        
        return contador != 0; 
    }
    /**
     * Extrae los hashtags de la coleccion de tweets actualmente cargados y los pone en un archivo 
     */
    private void extraerHashtagsDeTweets(){
        ColecionHashtags.clear();
        for(String tweet : ColecionHashtags){
            Matcher matcher = HashtagPattern.matcher(tweet);
            while(matcher.find())
            {
                ColecionHashtags.add(matcher.group(1));
            }
        }
        
    }
    private void guardarHashtags() throws IOException{
        for(String tweet: ColecionHashtags){
            FileWriter fstream = new FileWriter(ArchivoHashtags, true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(tweet + "\n");
        }
    }
    
}
