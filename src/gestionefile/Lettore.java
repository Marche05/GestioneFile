package gestionefile;

import java.io.FileReader;
import java.io.IOException;


public class Lettore extends Thread{
    String nomeFile;
    
    public Lettore(String nomeFile){
        this.nomeFile = nomeFile;
    }
    
    /**
     * Legge il file senza tener conto del tipo di file
     * e lo mostra in output
     */
    public void leggi(){
        int i; 
        
        // try with resources 
        // dichiaro risorse dentro le parentesi tonde del try così si chiudono in automatico dopo l'esecuzione
        // non scrivo fr.close()
        try (FileReader fr = new FileReader(nomeFile)) { 
            
            //2) leggo carattere per carattere e lo stampo 
            while ((i=fr.read()) != -1)
                System.out.print((char) i);
            
            System.out.print("\n\r");

        } catch (IOException ex) {
            System.err.println("Errore in lettura!");
        }
    }
    

    @Override
    public void run(){
        leggi();
    }
}
