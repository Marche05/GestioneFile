package gestionefile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.Scanner;

public class GestioneFile {


    public static void main(String[] args) {
        
        //1)LETTURA
        Lettore lettore = new Lettore("user.json");
        lettore.start();
        //2)ELABORAZIONE
        
        Scanner s = new Scanner(System.in);
	
        System.out.print("Inserisci username: ");
        String utente = s.nextLine();
      	
        System.out.print("Inserisci password: ");
        String password = s.nextLine();
        
        //3) SCRITTURA
        Scrittore scrittore = new Scrittore("output.csv", utente, password);
        Thread threadScrittore = new Thread(scrittore);
        threadScrittore.start();
                
        // Utilizzo oggetto Path per indicare il persorso dei file necessario per la copia
        Path copia = Paths.get("output.csv");
        Path incolla = Paths.get("copia.csv");
        
        try {
            // Copio file come indicato in https://docs.oracle.com/javase/tutorial/essential/io/copy.html
            Files.copy(copia, incolla, REPLACE_EXISTING);
        } catch (IOException ex) {
            System.err.println("Errore in copia!");
        }
    }
    
}
