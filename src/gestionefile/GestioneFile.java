package gestionefile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
        
        // utilizzo DataOutputStream per scivere il file
        try (DataOutputStream uscita = new DataOutputStream(new FileOutputStream("user.csv"))) {
            
            // scrivo un intero (id) e le stringhe nel file
            uscita.writeInt(1);
            // scrivo le stringhe (nome, cognome, ruolo)
            uscita.writeUTF("name");
            uscita.writeUTF("surname");
            uscita.writeUTF("role");

            System.out.println("Dati scritti in user.csv");
        } catch (IOException e) {
            System.out.println("Errore in scrittura dati in user.csv");
        }
        
        // utilizzo DataInputStream per leggere il file        
        try (DataInputStream entrata = new DataInputStream(new FileInputStream("user.csv"))) {
            // leggo l'intero (id) e le stringhe
            int id = entrata.readInt();
            String nome = entrata.readUTF();
            String cognome = entrata.readUTF();
            String ruolo = entrata.readUTF();

            // Visualizza i dati letti
            System.out.println("Stampo i dati letti");
            System.out.println("ID: " + id);
            System.out.println("Nome: " + nome);
            System.out.println("Cognome: " + cognome);
            System.out.println("Ruolo: " + ruolo);
            
        } catch (IOException e) {
            System.out.println("Errore in lettura dati in user.csv");
        }
        
        // file dove salvare gli oggetti serializzati
        File f = new File("dati");
        
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
