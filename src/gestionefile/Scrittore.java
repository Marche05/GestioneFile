package gestionefile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MC
 * @version 12/01/23
 */

public class Scrittore implements Runnable{

    String nomeFile;
    String utente;
    String password;
    
    public Scrittore(String nomeFile, String utente, String password){
        this.nomeFile = nomeFile;
        this.utente = utente;
        this.password = password;
    }
    
    @Override
    public void run() {
        scrivi();
    }
    /**
     * Scrive un file di testo usando la classe BufferedWriter
     */
    public void scrivi(){
        
        try (BufferedWriter br = new BufferedWriter(new FileWriter(nomeFile))) {

            //2) scrivo nel buffer
            br.write(this.utente+";"+this.password);
            
            //3) svuoto il buffer e salvo nel file i dati
            br.flush();         
        } catch (IOException ex) {
            Logger.getLogger(Scrittore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
