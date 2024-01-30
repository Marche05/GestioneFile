package gestionefile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestioneFile {


    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        
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
        
        
        try{
            // nome file da scrivere
            String file = "user.csv";            
            int[] u = { 12, 8, 13, 29, 50 };

            // Oggetto per scrivere nel file
            DataOutputStream uscita = new DataOutputStream(new BufferedOutputStream(
                    new FileOutputStream(file)));
            
            
            for (int i = 0; i < u.length; i ++) {
                uscita.writeInt(u[i]);
            }
            
            uscita.flush();
            
        }catch (IOException ex){ 
            System.err.println("Errore in scrittura!");
        }
        
        try{
            // nome file da leggere
            String filelettura = "user.json";            
            
            String id;
            String nome;
            String cognome;
            String ruolo;
            
            // oggetto per scrivere nel file
            DataInputStream in = new DataInputStream(new
                    BufferedInputStream(new FileInputStream(filelettura)));
            
            /*
            try {
                while (true) {
                    id = in.readUTF();
                    System.out.println("id: %d "+ id);
                }
            } catch (EOFException e) {
                 System.err.println("Errore in file!");
            }
               */  
        
        }catch (IOException ex){ 
            System.err.println("Errore in scrittura!");
        }
        
        // file dove salvare gli oggetti serializzati
        File f = new File("dati.txt");
        
        // creazione utente
	User matteo = new User(1, "matteo", "marchesini", "studente");

        // utilizzo ObjectOutputStream per scrivere il file
	ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
        // scrivo l'oggetto
	out.writeObject(matteo);

        // utilizzo ObjectInputStream per leggere il file
	ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
         // leggo l'oggetto
	User utente_letto = (User) in.readObject();
	System.out.println("Oggetto letto! id: " + utente_letto.getId() + ", nome: " + utente_letto.getNome()+ ", cognome: " + utente_letto.getCognome()+ ", ruolo: " + utente_letto.getRuolo());

	out.close();
	in.close();
        
    }
    
}
